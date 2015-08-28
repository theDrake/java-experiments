//*****************************************************************************
// Filename: MagicSquare.java
//
//    Author: David C. Drake (http://davidcdrake.com)
//
// Description: Contains a MagicSquare class for creating a colorful square of
//              numbers such that the sum of each row, column, and diagonal is
//              the same. That sum is also displayed below the square.
//*****************************************************************************

import java.awt.*;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

public class MagicSquare extends Applet
{
  final int NUM_ROWS = 5,
            NUM_COLS = NUM_ROWS,
            DELAY = 1000;
  Random r = new Random();
  Timer timer;
  Color special;
  boolean done;
  int i, j, m, red, green, blue, firstSum, sum;
  int[][] square = new int[NUM_ROWS][NUM_COLS];

  public void init()
  {
    setBackground(Color.white);
    resize(40 * NUM_ROWS, 40 * NUM_COLS);
    timer = new Timer(DELAY, new ReboundActionListener());
    timer.start();

    i = NUM_ROWS - 1;
    j = NUM_ROWS / 2;
    done = false;
    for (m = 1; m <= NUM_ROWS * NUM_COLS; m++)
    {
      square[i][j] = m;
      if (++i == NUM_ROWS)
      {
        i = 0;
      }
      if (++j == NUM_ROWS)
      {
        j = 0;
      }
      if (square[i][j] != 0)
      {
        if (--j < 0)
        {
          j = NUM_ROWS - 1;
        }
        if (i <= 1)
        {
          i += NUM_ROWS;
        }
        i -= 2;
      }
    }
    sum = test();
  }

  public void paint(Graphics g)
  {
    for (m = 0; m < NUM_ROWS * NUM_COLS; m++)
    {
      red = Math.abs(r.nextInt() % 256);
      green = Math.abs(r.nextInt() % 256);
      blue = Math.abs(r.nextInt() % 256);
      special = new Color(red, green, blue);
      g.setColor(special);

      i = m / NUM_ROWS;
      j = m % NUM_ROWS;
      g.drawString("" + square[i][j], 40 + 25 * j, 50 + 25 * i);
      if (m == (NUM_ROWS * NUM_COLS) - 1)
      {
        i = NUM_ROWS + 1;
        j = 1;
        g.drawString("" + sum, 40 + 25 * j, 50 + 25 * i);
      }
    }
  }

  public int test()
  {
    //-------------------------------------------------------------------------
    // Test the sum of each column.
    //-------------------------------------------------------------------------
    m = firstSum = 0;
    for (i = 0; i < NUM_ROWS; i++)
    {
      sum = 0;
      for (j = 0; j < NUM_COLS; j++)
      {
        sum += square[i][j];
      }
      if (i == 0)
      {
        firstSum = sum;
      }
      else if (sum != firstSum)
      {
        m++;
      }
    }
    if (m > 0)
    {
      System.out.println("Error(s) in column sums: " + m);
    }

    //-------------------------------------------------------------------------
    // Test the sum of each row.
    //-------------------------------------------------------------------------
    m = 0;
    for (j = 0; j < NUM_COLS; j++)
    {
      sum = 0;
      for (i = 0; i < NUM_ROWS; i++)
      {
        sum += square[i][j];
      }
      if (sum != firstSum)
      {
        m++;
      }
    }
    if (m > 0)
    {
      System.out.println("Error(s) in row sums: " + m);
    }

    //-------------------------------------------------------------------------
    // Test the sum of each diagonal.
    //-------------------------------------------------------------------------
    sum = 0;
    for (i = 0, j = 0;
         i < NUM_ROWS;
         i++, j++)
    {
      sum += square[i][j];
    }
    if (sum != firstSum)
    {
      System.out.println("Error in sum of top-left to bottom-right diagonal.");
    }
    sum = 0;
    for (i = NUM_ROWS - 1, j = 0;
         i >= 0;
         i--, j++)
    {
      sum += square[i][j];
    }
    if (sum != firstSum)
    {
      System.out.println("Error in sum of top-right to bottom-left diagonal.");
    }

    return firstSum;
  }

  private class ReboundActionListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      repaint();
    }
  }
}
