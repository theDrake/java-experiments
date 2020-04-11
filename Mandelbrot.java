//******************************************************************************
//    Filename: Mandelbrot.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Mandelbrot applet class for drawing Mandelbrot fractals
//              according to user-provided coordinates.
//******************************************************************************

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

public class Mandelbrot extends java.applet.Applet implements ActionListener {
  final int MAX_RECURSION = 400, TEXTFIELD_SIZE = 5, APPLET_WIDTH = 900,
            APPLET_HEIGHT = APPLET_WIDTH, APPLET_LOCATION_X = 150,
            APPLET_LOCATION_Y = 20, CANVAS_WIDTH = 300,
            CANVAS_HEIGHT = CANVAS_WIDTH, CANVAS_LOCATION_X = 0,
            CANVAS_LOCATION_Y = 100, CANVAS_BOUNDS_WIDTH = 350,
            CANVAS_BOUNDS_HEIGHT = 380, MAX_COLOR = 255;

  private Button redrawButton;
  private Label xminLabel, xmaxLabel, yminLabel, ymaxLabel, titleLabel;
  private TextField xminField, xmaxField, yminField, ymaxField;
  private Canvas myCanvas;

  public void init() {
    setSize(APPLET_WIDTH, APPLET_HEIGHT);
    setLocation(APPLET_LOCATION_X, APPLET_LOCATION_Y);
    //setLayout(null);

    myCanvas = new Canvas();
    myCanvas.setBackground(Color.black);
    myCanvas.setBounds(0, 0, CANVAS_BOUNDS_WIDTH, CANVAS_BOUNDS_HEIGHT);
    myCanvas.setLocation(CANVAS_LOCATION_X, CANVAS_LOCATION_Y);
    titleLabel = new Label("The Mandelbrot Set");
    redrawButton = new Button("Redraw");
    xminLabel = new Label("X min:");
    xmaxLabel = new Label("X max:");
    yminLabel = new Label("Y min:");
    ymaxLabel = new Label("Y max:");
    xminField = new TextField("-2.25", 10);
    xmaxField = new TextField("0.75");
    yminField = new TextField("-1.5");
    ymaxField = new TextField("1.5");

    add(titleLabel);
    add(redrawButton);
    add(xminLabel);
    add(xminField);
    add(xmaxLabel);
    add(xmaxField);
    add(yminLabel);
    add(yminField);
    add(ymaxLabel);
    add(ymaxField);
    //add(myCanvas);

    redrawButton.addActionListener(this);
  }

  public void paint(Graphics g) {
    int count;
    double xmin = Double.parseDouble(xminField.getText()),
           xmax = Double.parseDouble(xmaxField.getText()),
           ymin = Double.parseDouble(yminField.getText()),
           ymax = Double.parseDouble(ymaxField.getText()),
           stepX = (xmax - xmin) / CANVAS_WIDTH,
           stepY = (ymax - ymin) / CANVAS_HEIGHT,
           calc_x = xmin, calc_y = ymin;

    for (int x = 0; x < CANVAS_WIDTH; x++) {
      for (int y = 0; y < CANVAS_HEIGHT; y++) {
        count = Calculate(calc_x, calc_y, 0, 0, 0);
        count = (int) (((double) count / MAX_RECURSION) * MAX_COLOR);
        if (count != 0) {
          count *= 10;
        }
        if (count > MAX_COLOR) {
          count = MAX_COLOR;
        }
        Color pixelColor = new Color(count, count, count);
        g.setColor(pixelColor);
        g.drawLine(x, y, x, y);
        calc_y = calc_y + stepY;
      }
      calc_y = xmin;
      calc_x = calc_x + stepX;
    }
    calc_x = xmin;
    calc_y = calc_y + stepY;
  }

  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  private int Calculate(double OrigX, double OrigY, double CurX, double CurY,
                        int count) {
    double newX = (CurX * CurX) + (CurY * CurY * -1) + OrigX,
           newY = (2 * CurX * CurY) + OrigY;

    if (count >= MAX_RECURSION || (newX * newX) - (newY * newY - 1) > 2) {
      return count;
    } else {
      return Calculate(OrigX, OrigY, newX, newY, ++count);
    }
  }
}
