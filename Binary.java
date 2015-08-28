//*****************************************************************************
//    Filename: Binary.java
//
//      Author: David C. Drake (http://davidcdrake.com)
//
// Description: Contains a Binary class for converting numbers from binary to
//              decimal and vice versa.
//*****************************************************************************

import java.io.*;
import java.lang.*;

public class Binary
{
  public static void main(String[] args) throws IOException
  {
    final int BINARY_LENGTH = 16,
              INT_MAX = 32767,
              INT_MIN = 0;
    String option,
           repeat = "y",
           binary;
    int x, n, power, num, remainder;
    BufferedReader stdin = new BufferedReader
      (new InputStreamReader(System.in));

    do
    {
      num = 0;
      binary = "";
      System.out.print("Type 'b' for binary to decimal, 'd' for decimal to "
                       "binary: ");
      option = stdin.readLine();

      // Binary to decimal:
      if (option.equalsIgnoreCase ("b"))
      {
        do
        {
          x = 0;
          System.out.print("Enter a " + BINARY_LENGTH +
                           "-bit binary number: ");
          binary = stdin.readLine();
          if (binary.length() != BINARY_LENGTH)
          {
            System.out.println("You must enter exactly " + BINARY_LENGTH +
                               " digits.\n");
            x = 1;
          }
          else
          {
            n = 0;
            while (x == 0 && n < BINARY_LENGTH)
            {
              if (binary.charAt(n) != '0' && binary.charAt(n) != '1')
              {
                System.out.println ("All digits must be 0 or 1.\n");
                x = 1;
              }
              n++;
            }
          }
        }while (x == 1);
        power = 0;
        for (x = BINARY_LENGTH - 1; x >= 0; x--)
        {
          num += ((binary.charAt(x) - 48) * Math.pow(2, power));
          power++;
        }
        System.out.println("Your number in decimal: " + num);
      }

      // Decimal to binary:
      if (option.equalsIgnoreCase ("d"))
      {
        do
        {
          System.out.print("Enter a decimal number between " + INT_MIN +
                           " and " + INT_MAX + ": ");
          num = Integer.parseInt(stdin.readLine());
          if (num < INT_MIN || num > INT_MAX)
          {
            System.out.println("The number must be no less than " + INT_MIN +
                               " and no greater than " + INT_MAX + ".\n");
            x = 1;
          }
          else
          {
            x = 0;
          }
        }while (x == 1);
        for (x = 0; x < BINARY_LENGTH; x++)
        {
          remainder = num % 2;
          binary = remainder + binary;
          num = num / 2;
        }
        System.out.println("Your number in binary: " + binary);
      }
      System.out.print("\nDo you want to convert another number? (y/n) ");
      repeat = stdin.readLine();
      System.out.println("\n");
    }while(repeat.equalsIgnoreCase("y"));
  }
}
