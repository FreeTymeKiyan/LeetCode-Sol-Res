package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidWordAbbreviationTest {

  @DataProvider
  public Object[][] getExamples() {
    return new Object[][]{
        new Object[]{null, null, true},
        new Object[]{null, "", false},
        new Object[]{"", null, false},
        new Object[]{"internationalization", "i12iz4n", true},
        new Object[]{"apple", "a2e", false},
        new Object[]{"apple", "0a2e", false} // Edge case: leading zeros
    };
  }

  @Test(dataProvider = "getExamples")
  public void testValidWordAbbreviation(String word, String abbr, boolean expected) {
    final boolean actual = new ValidWordAbbreviation().validWordAbbreviation(word, abbr);
    Assert.assertEquals(actual, expected);
  }
}