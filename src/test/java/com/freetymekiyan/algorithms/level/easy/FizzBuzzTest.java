package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FizzBuzzTest {

    @Test
    public void testFizzBuzz() {
        int n = 15;
        List<String> expected = List.of(
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
        FizzBuzz f = new FizzBuzz();
        Assert.assertEquals(f.fizzBuzz(15), expected);

    }
}