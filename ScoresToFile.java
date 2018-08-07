//******************************************************************************
//    Filename: ScoresToFile.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Contains a ScoresToFile class for writing exam and lab scores,
//              provided by the user, to a file.
//******************************************************************************

import java.io.*;

public class ScoresToFile {
  public static void main(String[] args) throws IOException {
    final int NUM_EXAMS = 2, NUM_LABS = 11;

    String filename = "scores.txt", name;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    FileWriter fw = new FileWriter(filename);
    BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter outFile = new PrintWriter(bw);
    int[] exam = new int[NUM_EXAMS], lab = new int[NUM_LABS];
    int i;
    boolean error;

    System.out.print("Name: ");
    name = stdin.readLine();
    outFile.println("Name: " + name + "\tNo. of exams: " + NUM_EXAMS +
                    "\tNo. of labs: " + NUM_LABS + "\nExam scores: ");
    for (i = 0; i < NUM_EXAMS; i++) {
      do {
        error = false;
        try {
          System.out.print("Exam " + (i + 1) + ": ");
          exam[i] = Integer.parseInt(stdin.readLine());
        } catch (NumberFormatException exception) {
          System.out.println("\nERROR: Invalid input.\n");
          error = true;
        }
      } while (error);
      outFile.print(exam[i] + " ");
    }
    outFile.println("\nLab scores: ");
    for (i = 0; i < NUM_LABS; i++) {
      do {
        error = false;
        try {
          System.out.print("Lab " + (i + 1) + ": ");
          lab[i] = Integer.parseInt(stdin.readLine());
        } catch (NumberFormatException exception) {
          System.out.println("\nERROR: Invalid input.\n");
          error = true;
        }
      } while (error);
      outFile.print(lab[i] + " ");
    }
    outFile.println();
    outFile.close();
    System.out.println("\nData saved to: " + filename);
  }
}
