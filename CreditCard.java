//******************************************************************************
//    Filename: CreditCard.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Contains a CreditCard applet class for calculating monthly
//              interest and payment amounts according to data initially entered
//              by the user.
//******************************************************************************

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;

public class CreditCard extends Applet implements ActionListener {
  final int TEXT_FIELD_SIZE = 5, APPLET_WIDTH = 390, APPLET_HEIGHT = 110;
  final double MONTHLY_PAYMENT_RATIO = 0.02, MIN_MONTHLY_PAYMENT = 20.0;

  double initialBalance, balance, interestRate, interestCharge, totalInterest,
         percentage, payment;
  NumberFormat dollar = NumberFormat.getCurrencyInstance(),
               percent = NumberFormat.getPercentInstance();
  private Label inputBLabel, inputIRLabel, outputBLabel, outputILabel,
                outputPLabel, resultBLabel, resultILabel, resultPLabel;
  private TextField inputBalance, inputInterestRate;
  private Button monthButton;
  private String text;
  private boolean firstTime;
  //private JPanel myPanel;

  public void init() {
    firstTime = true;
    inputBLabel = new Label("Initial balance:");
    inputIRLabel = new Label("Interest rate:");
    outputBLabel = new Label("New balance:");
    outputILabel = new Label("Total interest charged:");
    outputPLabel = new Label("% of original balance:");
    resultBLabel = new Label("N/A");
    resultILabel = new Label("N/A");
    resultPLabel = new Label("N/A");
    inputBalance = new TextField(TEXT_FIELD_SIZE);
    inputInterestRate = new TextField(TEXT_FIELD_SIZE);
    monthButton = new Button("One Month");
    monthButton.addActionListener(this);

    /*myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
    myPanel.add(inputBLabel);
    myPanel.add(inputBalance);
    myPanel.add(inputIRLabel);
    myPanel.add(inputInterestRate);
    myPanel.add(outputBLabel);
    myPanel.add(resultBLabel);
    myPanel.add(outputILabel);
    myPanel.add(resultILabel);
    myPanel.add(outputPLabel);
    myPanel.add(resultPLabel);
    myPanel.add(monthButton);*/

    add(inputBLabel, BorderLayout.NORTH);
    add(inputBalance, BorderLayout.NORTH);
    add(inputIRLabel, BorderLayout.NORTH);
    add(inputInterestRate, BorderLayout.NORTH);
    add(outputBLabel, BorderLayout.WEST);
    add(resultBLabel, BorderLayout.EAST);
    add(outputILabel, BorderLayout.WEST);
    add(resultILabel, BorderLayout.EAST);
    add(outputPLabel, BorderLayout.WEST);
    add(resultPLabel, BorderLayout.EAST);
    add(monthButton, BorderLayout.SOUTH);

    setBackground(Color.white);
    setSize(APPLET_WIDTH, APPLET_HEIGHT);
  }

  public void actionPerformed(ActionEvent event) {
    // First time button gets pushed, read user data from TextFields:
    if (firstTime) {
      text = inputInterestRate.getText();
      interestRate = Double.parseDouble(text) / 100;
      text = inputBalance.getText();
      balance = Double.parseDouble(text);
      initialBalance = balance;
      firstTime = false;
    }

    // Calculate monthly payment:
    if (balance <= MIN_MONTHLY_PAYMENT) {
      payment = balance;
      balance = 0.0;
    } else if ((balance * MONTHLY_PAYMENT_RATIO) > MIN_MONTHLY_PAYMENT) {
      payment = balance * MONTHLY_PAYMENT_RATIO;
    } else {
      payment = MIN_MONTHLY_PAYMENT;
    }
    balance -= payment;

    // Calculate monthly interest charge:
    if (balance > 0.0) {
      interestCharge = balance * interestRate;
      balance += interestCharge;
    } else {
      interestCharge = 0.0;
      percentage = 0.0;
    }
    totalInterest += interestCharge;
    percentage = totalInterest / initialBalance;

    // Update labels:
    resultBLabel.setText("" + dollar.format(balance));
    resultILabel.setText("" + dollar.format(totalInterest));
    resultPLabel.setText("" + percent.format(percentage));
  }
}
