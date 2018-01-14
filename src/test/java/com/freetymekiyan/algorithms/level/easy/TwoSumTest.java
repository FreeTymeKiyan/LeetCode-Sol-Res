package com.freetymekiyan.algorithms.level.easy;

import org.junit.Assert;
import org.testng.annotations.Test;

public class TwoSumTest {

    @Test
    public void testTwoSum() {
        TwoSum t = new TwoSum();
        int[] numbers = {3, 2, 4}; // 6 = 3 + 3
        int target = 6;
        int[] res = t.twoSum(numbers, target);
        Assert.assertArrayEquals(new int[]{1, 2}, res);

        numbers = new int[]{2, 7, 11, 15};
        target = 9;
        res = t.twoSum(numbers, target);
        Assert.assertArrayEquals(new int[]{0, 1}, res);
    }
}