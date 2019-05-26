package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestSubstringWithAtMostTwoDistinctCharactersTest {

  @DataProvider
  public Object[][] exampleCases() {
    return new Object[][]{
        new Object[]{"eceba", 3},
        new Object[]{"ccaabbb", 5},
        new Object[]{"aaa", 3}
    };
  }

  @Test(dataProvider = "exampleCases")
  public void testLengthOfLongestSubstringTwoDistinct(String s, int length) {
    final LongestSubstringWithAtMostTwoDistinctCharacters l = new LongestSubstringWithAtMostTwoDistinctCharacters();
    Assert.assertEquals(l.lengthOfLongestSubstringTwoDistinct(s), length);
  }

  @DataProvider
  public Object[][] edgeCases() {
    return new Object[][]{
        new Object[]{null, 0},
        new Object[]{"", 0},
        new Object[]{"a", 1},
    };
  }

  @Test(dataProvider = "edgeCases")
  public void testEdgeCases(String s, int length) {
    testLengthOfLongestSubstringTwoDistinct(s, length);
  }

}