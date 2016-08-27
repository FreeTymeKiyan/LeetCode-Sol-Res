package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * <p>
 * <p>
 * Tags: Array
 * Similar Problems: (M) Missing Ranges, (H) Data Stream as Disjoint Intervals
 */
public class SummaryRanges {

    private SummaryRanges s;

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int end = i + 1;
            while (end < nums.length && nums[end] - nums[end - 1] == 1) {
                end++;
            }
            if (end - i == 1) {
                res.add(Integer.toString(nums[i]));
            } else {
                res.add(nums[i] + "->" + nums[end - 1]);
                i = end - 1;
            }
        }
        return res;
    }

    @Before
    public void setUp() {
        s = new SummaryRanges();
    }

    @Test
    public void testExamples() {
        int[] nums = {0, 1, 2, 4, 5, 7};
        List<String> res = s.summaryRanges(nums);
        Assert.assertEquals("[0->2, 4->5, 7]", res.toString());
    }

    @After
    public void tearDown() {
        s = null;
    }

}
