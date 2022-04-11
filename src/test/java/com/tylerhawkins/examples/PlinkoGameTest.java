package com.tylerhawkins.examples;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlinkoGameTest {
  @Test
  public void dropPlinkoChipIntoIntegerBucketBetweenOneAndNineInclusive() {
    int bucket = PlinkoGame.dropPlinkoChip();
    assertTrue("Bucket must be greater than 0", bucket > 0);
    assertTrue("Bucket must be less than 10", bucket < 10);
  }

  @Test
  public void dropPlinkoChipBucketIsAnInteger() {
    int bucket = PlinkoGame.dropPlinkoChip();
    assertEquals((int) bucket, bucket);
  }

  @Test
  public void dropSpecifiedNumberOfPlinkoChips() {
    int[] results = PlinkoGame.dropPlinkoChips(100);
    assertEquals(100, results.length);
  }

  @Test
  public void generateCorrectFrequenciesFromResults() {
    int[] results = {3, 4, 4, 4, 4, 5, 5, 5, 6, 6};
    int[] expectedFrequencies = {0, 0, 0, 1, 4, 3, 2, 0, 0, 0};

    assertArrayEquals(expectedFrequencies, PlinkoGame.generateFrequencies(results));
  }
}
