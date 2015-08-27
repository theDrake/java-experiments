//*****************************************************************************
//    Filename: Shapes.java
//
//      Author: David C. Drake (http://davidcdrake.com)
//
// Description: Contains classes for creating a variety of different shapes and
//              a Shapes class for randomly generating and drawing an array of
//              shapes.
//*****************************************************************************

import java.awt.*;
import java.applet.Applet;
import java.util.Random;

public class Shapes extends Applet
{
  final int NUM_SHAPES = 20,
            DISPLAY_WIDTH = 500,
            DISPLAY_HEIGHT = 500;
  Random r = new Random();
  public myShape[] shape = new myShape[NUM_SHAPES];
  int option, count;

  public void init()
  {
    setBackground(Color.white);
    resize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
    shape[0].SHAPE_MAX = DISPLAY_WIDTH / 2;
    shape[0].SHAPE_MIN = 20;
    shape[0].DISPLAY_SIZE = DISPLAY_WIDTH;
  }

  //---------------------------------------------------------------------------
  //  Shapes are randomly generated and drawn (along with center coordinates).
  //---------------------------------------------------------------------------
  public void paint(Graphics g)
  {
    for (count = 0; count < NUM_SHAPES; count++)
    {
      option = Math.abs(r.nextInt() % 4);
      switch (option)
      {
        case 0:
          shape[count] = new myEquilateralTriangle(g);
          ((myEquilateralTriangle) shape[count]).draw(g);
          break;
        case 1:
          shape[count] = new mySquare(g);
          ((mySquare) shape[count]).draw(g);
          break;
        case 2:
          shape[count] = new myRectangle(g);
          ((myRectangle) shape[count]).draw(g);
          break;
        case 3:
          shape[count] = new myCircle(g);
          ((myCircle) shape[count]).draw(g);
          break;
      }
    }
  }
}

abstract class myShape
{
  public static int SHAPE_MAX, SHAPE_MIN, DISPLAY_SIZE;
  protected int centerX, centerY, red, green, blue;
  protected Color shapeColor;
  protected Random r = new Random();

  public myShape(Graphics g)
  {
    centerX = Math.abs(r.nextInt() % DISPLAY_SIZE);
    centerY = Math.abs(r.nextInt() % DISPLAY_SIZE);
    setRandomColor(g);
  }

  protected void displayCoordinates(Graphics g)
  {
    g.setColor(Color.black);
    g.drawString("(" + centerX + "," + centerY + ")", centerX, centerY);
  }

  protected void setRandomColor(Graphics g)
  {
    red = Math.abs(r.nextInt() % 256);
    green = Math.abs(r.nextInt() % 256);
    blue = Math.abs(r.nextInt() % 256);
    shapeColor = new Color(red, green, blue);
    g.setColor(shapeColor);
  }
}

class myEquilateralTriangle extends myShape
{
  public int height;
  private int[] xPoints = new int[3],
                yPoints = new int[3];

  public myEquilateralTriangle(Graphics g)
  {
    super(g);
    height = Math.abs(r.nextInt() % SHAPE_MAX) + SHAPE_MIN;
    xPoints[0] = centerX - (2 * height / 3);
    yPoints[0] = centerY + (height / 3);
    xPoints[1] = centerX;
    yPoints[1] = centerY - (2 * height / 3);
    xPoints[2] = centerX + (2 * height / 3);
    yPoints[2] = centerY + (height / 3);
  }

  public void draw(Graphics g)
  {
    g.fillPolygon(xPoints, yPoints, 3);
    displayCoordinates(g);
  }
}

class mySquare extends myShape
{
  private int size; // Length of one side.

  public mySquare(Graphics g)
  {
    super(g);
    size = Math.abs(r.nextInt() % SHAPE_MAX) + SHAPE_MIN;
  }

  public void draw(Graphics g)
  {
    g.fillRect(centerX - size / 2, centerY - size / 2, size, size);
    displayCoordinates(g);
  }
}

class myRectangle extends myShape
{
  private int width, height;

  public myRectangle(Graphics g)
  {
    super(g);
    width = Math.abs(r.nextInt() % SHAPE_MAX) + SHAPE_MIN;
    height = Math.abs(r.nextInt() % SHAPE_MAX) + SHAPE_MIN;
  }

  public void draw(Graphics g)
  {
    g.fillRect(centerX - width / 2, centerY - height / 2, width, height);
    displayCoordinates(g);
  }
}

class myCircle extends myShape
{
  private int diameter;

  public myCircle(Graphics g)
  {
    super(g);
    diameter = Math.abs(r.nextInt() % SHAPE_MAX) + SHAPE_MIN;
  }

  public void draw(Graphics g)
  {
    g.fillOval(centerX - diameter / 2,
               centerY - diameter / 2,
               diameter, diameter);
    displayCoordinates(g);
  }
}
