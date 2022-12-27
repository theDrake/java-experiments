//******************************************************************************
//    Filename: Temperature.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Temperature class for converting Fahrenheit to Celsius.
//******************************************************************************

import java.io.*;

public class Temperature {
  public static void main(String[] args) throws IOException {
    final double BASE = 32;
    final double CONVERSION_FACTOR = 5.0 / 9.0;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    double tempF, tempC;

    System.out.print("Enter temperature in Fahrenheit: ");
    tempF = Double.parseDouble(stdin.readLine());
    System.out.println("User entered: " + tempF + " degrees F");
    System.out.println((tempF - BASE) + " degrees F above freezing.");
    tempC = CONVERSION_FACTOR * (tempF - BASE);
    System.out.println("Temperature in Celsius: " + tempC);
  }
}
