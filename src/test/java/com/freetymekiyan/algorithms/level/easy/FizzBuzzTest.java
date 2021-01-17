package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FizzBuzzTest {

  private static final List<String> EXPECTED = List.of(
      "1",
      "2",
      "Fizz",
      "4",
      "Buzz",
      "Fizz",
      "7",
      "8",
      "Fizz",
      "Buzz",
      "11",
      "Fizz",
      "13",
      "14",
      "FizzBuzz");

  @Test
  public void testFizzBuzz() {
    FizzBuzz f = new FizzBuzz();
    Assert.assertEquals(f.fizzBuzz(15), EXPECTED);
  }

  @Test
  public void testFizzBuzz2() {
    FizzBuzz f = new FizzBuzz();
    Assert.assertEquals(f.fizzBuzz2(15), EXPECTED);
  }
}