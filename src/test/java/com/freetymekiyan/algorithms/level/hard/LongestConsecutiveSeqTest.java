package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestConsecutiveSeqTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{100, 4, 200, 3, 1, 2}, 4},
                new Object[]{new int[]{-7, -1, 3, -9, -4, 7, -3, 2, 4, 9, 4, -9, 8, -7, 5, -1, -7}, 4},
                new Object[]{new int[]{-2, -3, -3, 7, -3, 0, 5, 0, -8, -4, -1, 2}, 5},
                new Object[]{new int[]{7, -2, 9, 9, 1, 9, 8, -4, 6, -6, -6, 4, 1, 3, 6, 3, 5, -2, 3, 4, -6, 1, 5, -9, 6, 1, 2, -2, 1}, 9}
        };
    }

    @Test(dataProvider = "examples")
    public void testLongestConsecutive(int[] nums, int output) {
        LongestConsecutiveSeq l = new LongestConsecutiveSeq();
        Assert.assertEquals(l.longestConsecutive(nums), output);
    }

    @Test(dataProvider = "examples")
    public void testLongestConsecutive2(int[] nums, int output) {
        LongestConsecutiveSeq l = new LongestConsecutiveSeq();
        Assert.assertEquals(l.longestConsecutive2(nums), output);
    }
}