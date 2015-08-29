//*****************************************************************************
//    Filename: ExperimentalApplet.java
//
//      Author: David C. Drake (http://davidcdrake.com)
//
// Description: An ExperimentalApplet class. For, you know, experiments.
//*****************************************************************************

import java.awt.*;
import java.applet.Applet;

public class ExperimentalApplet extends java.applet.Applet
{
  int[] x = new int[3],
        y = new int[3];
  int centerX, centerY, height;

  public void init()
  {
    centerX = 250;
    centerY = 250;
    height = 300;

    x[0] = centerX - (2 * height / 3);
    y[0] = centerY + (height / 3);
    x[1] = centerX;
    y[1] = centerY - (2 * height / 3);
    x[2] = centerX + (2 * height / 3);
    y[2] = centerY + (height / 3);

    resize(500, 500);
  }

  public void paint(Graphics g)
  {
    g.setColor(Color.blue);
    g.fillPolygon(x, y, 3);
    g.setColor(Color.red);
    g.drawRect(centerX, centerY, 1, 1);
  }
}
