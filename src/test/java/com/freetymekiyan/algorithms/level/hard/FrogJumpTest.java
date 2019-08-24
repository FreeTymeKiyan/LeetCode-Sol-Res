package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FrogJumpTest {

  @DataProvider
  public Object[][] testCases() {
    return new Object[][]{
        new Object[]{new int[]{0, 1, 3, 4, 5, 7, 9, 10, 12}, true},
        new Object[]{new int[]{0}, true},
        new Object[]{new int[]{1}, true},
        new Object[]{new int[]{0, 1}, true},
        new Object[]{new int[]{0, 2}, false}};
  }

  @Test
  public void testCanCross(final int[] stones, final boolean expected) {
    final FrogJump f = new FrogJump();
    final boolean result = f.canCross(stones);
    Assert.assertTrue(result == expected);
  }
}