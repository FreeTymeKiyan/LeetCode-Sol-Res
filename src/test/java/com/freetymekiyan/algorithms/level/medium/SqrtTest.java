package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SqrtTest {

    @Test
    public void testMySqrt() {
        Sqrt s = new Sqrt();
        int[] nums = {-1, 1, 2, 4, 9, 16, 25};
        int[] res = {-1, 1, 1, 2, 3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(s.mySqrt(nums[i]), res[i]);
        }
    }
}