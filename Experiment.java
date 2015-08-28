//*****************************************************************************
//    Filename: Experiment.java
//
//      Author: David C. Drake (http://davidcdrake.com)
//
// Description: Contains an Experiment class for...experimenting...with stuff.
//*****************************************************************************

public class Experiment
{
  public static void main(String[] args)
  {
    int r = 0,
        s = 0,
        w = 12,
        sum = 0;

    for (r = 1; r <= w; r++)
    {
      for (s = r; s <= w; s++)
      {
        sum = sum + s;
      }
    }
    System.out.print("Sum = " + sum);
  }
}