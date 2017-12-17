package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAndEarnTest {

    @Test
    public void testDeleteAndEarnExamples() {
        int[] nums = new int[0];
        DeleteAndEarn d = new DeleteAndEarn();
        Assert.assertEquals(d.deleteAndEarn(nums), 0);
        nums = new int[]{3, 4, 2};
        Assert.assertEquals(d.deleteAndEarn(nums), 6);
    }
}