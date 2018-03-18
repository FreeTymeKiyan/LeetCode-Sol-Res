package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ThreeSumReuseOnceTest {

    @Test
    public void testThreeSumReuseOnce() {
        int[] nums = {3, 2, 1, 4, 6, 7};
        int target = 10;
        List<List<Integer>> expected = List.of(List.of(1, 2, 7), List.of(1, 3, 6), List.of(2, 2, 6), List.of(3, 3, 4), List.of(2, 4, 4));
        ThreeSumReuseOnce t = new ThreeSumReuseOnce();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(t.threeSumReuseOnce(nums, target), expected));

        target = 9;
        expected = List.of(List.of(1, 1, 7), List.of(1, 2, 6), List.of(1, 4, 4), List.of(2, 3, 4)); // Note that [3,3,3] is invalid.
        Assert.assertTrue(Utils.compareListsIgnoreOrder(t.threeSumReuseOnce(nums, target), expected));
    }
}