package com.tylerhawkins.examples;

// https://pressbooks.howardcc.edu/jrip3/chapter/so-you-want-to-win-plinko/
public class PlinkoGame {
  private static int numberOfBoardRows = 13;

  public static void main(String[] args) {
    System.out.println("\nLet's play Plinko! We're going to drop 10,000 chips.\n");
    System.out.println("Generating results...\n");

    int[] results = dropPlinkoChips(10000);
    int[] counts = generateFrequencies(results);
    printActualVersusExpectedCounts(results, counts);
  }

  protected static int[] dropPlinkoChips(int numberOfChips) {
    int[] results = new int[numberOfChips];

    for (int i = 0; i < numberOfChips; i++) {
      results[i] = dropPlinkoChip();
    }

    return results;
  }

  protected static int dropPlinkoChip() {
    double chipX = 5.0;
    final double moveLeftAmount = -0.5;
    final double moveRightAmount = 0.5;
    final double leftMostColumn = 1.0;
    final double rightMostColumn = 9.0;

    // The chip is always in column 5 (chipX = 5) for row 1 (chipY = 1)
    for (int chipY = 1; chipY < numberOfBoardRows; chipY++) {
      if (chipX == leftMostColumn) {
        // Chip is already on the very left side of the board and can only move right
        chipX += moveRightAmount;
      } else if (chipX == rightMostColumn) {
        // Chip is already on the very right side of the board and can only move left
        chipX += moveLeftAmount;
      } else {
        // Chip is able to move either left or right
        double randomNumber = Math.random();
        double movementX = randomNumber > 0.5 ? moveRightAmount : moveLeftAmount;
        chipX += movementX;
      }
    }

    return (int) chipX;
  }

  protected static int[] generateFrequencies(int[] results) {
    int[] counts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    for (int result : results) {
      counts[result]++;
    }

    return counts;
  }

  private static void printActualVersusExpectedCounts(int[] results, int[] actualCounts) {
    int numberOfCoinDrops = results.length;
    double[] expectedPercentages = {0, 0.019, 0.054, 0.121, 0.193, 0.226, 0.193, 0.121, 0.054, 0.019};
    double[] actualPercentages = new double[10];

    for (int i = 0; i < actualCounts.length; i++) {
      actualPercentages[i] = actualCounts[i] / (double) numberOfCoinDrops;
    }

    System.out.println("Expected vs. Actual Percentages Per Slot");
    System.out.println("----------------------------");
    System.out.println("| Slot | Expected | Actual |");
    System.out.println("|--------------------------|");

    for (int i = 1; i < actualPercentages.length; i++) {
      System.out.println("| " + i + "    | " + expectedPercentages[i] + "    | " + String.format("%.3f", actualPercentages[i]) + "  |");
    }

    System.out.println("----------------------------");
  }
}
