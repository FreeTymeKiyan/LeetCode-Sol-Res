package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchInRotatedSortedArrayTest {

    private static final int[] nums = {5, 1, 3};
    private static final int target = 5;
    private static final int expected = 0;

    @Test
    public void testSearch() {
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        Assert.assertEquals(s.search(nums, target), expected);
    }

    @Test
    public void testSearch2() {
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        Assert.assertEquals(s.search2(nums, target), expected);
    }
}