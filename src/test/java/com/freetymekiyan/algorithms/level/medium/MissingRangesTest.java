package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class MissingRangesTest {

  @DataProvider
  public Object[][] successExamples() {
    return new Object[][]{
        new Object[]{new int[]{0, 1, 3, 50, 75}, 0, 99, List.of("2", "4->49", "51->74", "76->99")},
        new Object[]{new int[]{-1}, -2, -1, List.of("-2")},
        new Object[]{new int[]{1, 1, 1}, 1, 1, Collections.emptyList()},
        new Object[]{new int[]{1, 3}, 0, 9, List.of("0", "2", "4->9")},
        new Object[]{new int[]{-2147483648, 2147483647}, -2147483648, 2147483647, List.of("-2147483647->2147483646")},
        new Object[]{new int[]{-1}, -1, 0, List.of("0")}
    };
  }

  @Test(dataProvider = "successExamples")
  public void testFindMissingRanges(int[] nums, int lower, int upper, List<String> expected) {
    final MissingRanges m = new MissingRanges();
    Assert.assertEquals(expected, m.findMissingRanges(nums, lower, upper));
  }
}