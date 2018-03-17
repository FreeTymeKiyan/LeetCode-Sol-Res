package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FindSecondLargestTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{3, 1, 2}, 2},
                new Object[]{new int[]{5, 2, 3, 4}, 4},
                new Object[]{new int[]{-5, 2, 3, 4}, 3}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindSecondLargest(int[] nums, int expected) {
        FindSecondLargest f = new FindSecondLargest();
        Assert.assertEquals(f.findSecondLargest(nums), expected);
    }
}