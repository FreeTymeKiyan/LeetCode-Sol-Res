package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class LongestContinuousIncreasingSubsequenceTest {

    private static final List<Integer> INPUT1 = Collections.unmodifiableList(List.of(1, 3, 5, 4, 7));
    private static final int OUTPUT1 = 3;
    private static final List<Integer> INPUT2 = Collections.unmodifiableList(List.of(2, 2, 2, 2, 2));
    private static final int OUTPUT2 = 1;

    @Test
    public void testFindLengthOfLCIS() {
        LongestContinuousIncreasingSubsequence l = new LongestContinuousIncreasingSubsequence();
        int result1 = l.findLengthOfLCIS(INPUT1.stream().mapToInt(Integer::intValue).toArray());
        Assert.assertEquals(result1, OUTPUT1);
        result1 = l.findLengthOfLCIS2(INPUT1.stream().mapToInt(Integer::intValue).toArray());
        Assert.assertEquals(result1, OUTPUT1);

        int result2 = l.findLengthOfLCIS(INPUT2.stream().mapToInt(Integer::intValue).toArray());
        Assert.assertEquals(result2, OUTPUT2);
        result2 = l.findLengthOfLCIS2(INPUT2.stream().mapToInt(Integer::intValue).toArray());
        Assert.assertEquals(result2, OUTPUT2);
    }

    @Test
    public void testNullInput() {
        LongestContinuousIncreasingSubsequence l = new LongestContinuousIncreasingSubsequence();
        Assert.assertEquals(l.findLengthOfLCIS(null), 0);
        Assert.assertEquals(l.findLengthOfLCIS2(null), 0);
    }

    @Test
    public void testEmptyInput() {
        LongestContinuousIncreasingSubsequence l = new LongestContinuousIncreasingSubsequence();
        int[] emptyInput = {};
        Assert.assertEquals(l.findLengthOfLCIS(emptyInput), 0);
        Assert.assertEquals(l.findLengthOfLCIS2(emptyInput), 0);
    }

    @Test
    public void testSingleElementInput() {
        LongestContinuousIncreasingSubsequence l = new LongestContinuousIncreasingSubsequence();
        int[] input = {1};
        Assert.assertEquals(l.findLengthOfLCIS(input), 1);
        Assert.assertEquals(l.findLengthOfLCIS2(input), 1);
    }
}