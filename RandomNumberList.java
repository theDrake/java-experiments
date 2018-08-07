//******************************************************************************
//    Filename: RandomNumberList.java
//
//      Author: David C. Drake (https://davidcdrake.com)
//
// Description: Contains RandomNumberList, NumberList, and RandomNumber classes
//              for generating, printing, and recursively sorting linked lists
//              of random numbers.
//******************************************************************************

import java.util.*;

public class RandomNumberList {
  public static void main(String[] args) {
    final int LIST_SIZE = 50;
    NumberList numList = new NumberList();

    for (int x = 0; x < LIST_SIZE; x++) {
      numList.insert(new RandomNumber());
    }
    for (int x = 0; x < LIST_SIZE; x++) {
      numList.sort(numList.list);
    }
    System.out.println(numList);
  }
}

class NumberList {
  public NumberNode list;

  NumberList() {
    list = null;
  }

  public void insert(RandomNumber newNumber) {
    NumberNode node = new NumberNode(newNumber);
    NumberNode current;

    if (list == null) {
      list = node;
    } else {
      current = list;
      while (current.next != null) {
        current = current.next;
      }
      current.next = node;
    }
  }

  public void sort(NumberNode currentNode) {  // Sorts in descending order.
    int temp1, temp2;

    if (currentNode.next != null) {
      temp1 = currentNode.num.value;
      temp2 = currentNode.next.num.value;
      if (temp1 < temp2) {
        currentNode.num.value = temp2;
        currentNode.next.num.value = temp1;
      }
      sort(currentNode.next);
    }
  }

  public String toString() {
    String result = "";
    NumberNode current = list;

    while (current != null) {
      result += current.num.toString() + "\n";
      current = current.next;
    }

    return result;
  }

  private class NumberNode {
    public RandomNumber num;
    public NumberNode next;

    public NumberNode(RandomNumber theNumber) {
      num = theNumber;
      next = null;
    }
  }
}

class RandomNumber {
  final int MAX_ABS_VALUE = 100;
  Random r = new Random();
  public int value;

  public RandomNumber() {
    value = (int) ((Math.random() * (2 * MAX_ABS_VALUE)) - MAX_ABS_VALUE);
  }

  public String toString() {
    return ("" + value);
  }
}
