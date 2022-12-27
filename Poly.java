//******************************************************************************
//    Filename: Poly.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Poly applet class for creating and manipulating polygons.
//******************************************************************************

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Poly extends Applet implements MouseListener, MouseMotionListener {
  final int APPLET_WIDTH = 300, APPLET_HEIGHT = APPLET_WIDTH, MAX_POINTS = 100;

  int[] x = new int[MAX_POINTS], y = new int[MAX_POINTS];
  int points = 0, position;
  boolean finished = false;

  public void init() {
    addMouseListener(this);
    addMouseMotionListener(this);
    setBackground(Color.black);
    resize(APPLET_WIDTH, APPLET_HEIGHT);
  }

  public void paint(Graphics g) {
    g.setColor(Color.green);
    if (points > 0) {
      g.drawPolyline(x, y, points);
    }
    if (finished) {
      g.fillPolygon(x, y, points);
    }
  }

  public void mousePressed(MouseEvent event) {
    if (finished == false) {
      x[points] = event.getPoint().x;
      y[points] = event.getPoint().y;
      if (points > 0 && Math.abs(x[points] - x[0]) < 10 &&
          Math.abs(y[points] - y[0]) < 10) {
        finished = true;
      }
      points++;
    } else {
      int currentX = event.getPoint().x;
      int currentY = event.getPoint().y;
      for (int n = points - 1; n >= 0; n--) {
        if (Math.abs(currentX - x[n]) < 10 && Math.abs(currentY - y[n]) < 10) {
          position = n;
        }
      }
    }
    repaint();
  }

  public void mouseDragged(MouseEvent event) {
    if (finished) {
      Point point = event.getPoint();
      x[position] = point.x;
      y[position] = point.y;
    }
    repaint();
  }

  public void mouseClicked(MouseEvent event) {}
  public void mouseMoved(MouseEvent event) {}
  public void mouseReleased(MouseEvent event) {}
  public void mouseEntered(MouseEvent event) {}
  public void mouseExited(MouseEvent event) {}
}
