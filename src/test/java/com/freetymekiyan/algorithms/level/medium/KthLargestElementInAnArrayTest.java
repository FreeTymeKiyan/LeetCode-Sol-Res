package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class KthLargestElementInAnArrayTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{-1, 2, 0}, 2, 0},
                new Object[]{new int[]{-1, 2, 0}, 1, 2},
                new Object[]{new int[]{2, 1}, 1, 2},
                new Object[]{new int[]{2, 1}, 2, 1},
                new Object[]{new int[]{1}, 1, 1}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindKthLargest(int[] nums, int k, int output) {
        KthLargestElementInAnArray kth = new KthLargestElementInAnArray();
        Assert.assertEquals(kth.findKthLargest(nums, k), output);
    }

    @Test(dataProvider = "examples")
    public void testFindKthLargest2(int[] nums, int k, int output) {
        KthLargestElementInAnArray kth = new KthLargestElementInAnArray();
        Assert.assertEquals(kth.findKthLargest2(nums, k), output);
    }

    @Test(dataProvider = "examples")
    public void testFindKthLargest3(int[] nums, int k, int output) {
        KthLargestElementInAnArray kth = new KthLargestElementInAnArray();
        Assert.assertEquals(kth.findKthLargest3(nums, k), output);
    }
}