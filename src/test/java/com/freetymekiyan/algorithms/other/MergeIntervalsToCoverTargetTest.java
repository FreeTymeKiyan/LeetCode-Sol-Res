package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.Interval;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class MergeIntervalsToCoverTargetTest {

    @Test
    public void testMergeToCoverExamples() {
        List<Interval> intervals = Arrays.asList(Utils.toIntervalArray(new int[][]{{-1, 9}, {1, 10}, {0, 3}, {9, 10}, {3, 14}, {2, 9}, {10, 16}}));
        Interval target = new Interval(2, 15);
        int output = 2;
        MergeIntervalsToCoverTarget m = new MergeIntervalsToCoverTarget();
        Assert.assertEquals(m.mergeToCover(intervals, target), output);
        // All intervals on target's left, no overlapping.
        intervals = Arrays.asList(Utils.toIntervalArray(new int[][]{{-1, 0}, {0, 1}, {1, 2}}));
        target = new Interval(2, 15);
        output = 0;
        Assert.assertEquals(m.mergeToCover(intervals, target), output);
        // One left overlapping interval that cannot cover target.
        intervals = Arrays.asList(Utils.toIntervalArray(new int[][]{{1, 2}, {2, 3}}));
        target = new Interval(2, 15);
        output = 0;
        Assert.assertEquals(m.mergeToCover(intervals, target), output);
        // One right overlapping interval that cannot cover target.
        intervals = Arrays.asList(Utils.toIntervalArray(new int[][]{{14, 15}, {15, 16}, {16, 17}}));
        target = new Interval(2, 15);
        output = 0;
        Assert.assertEquals(m.mergeToCover(intervals, target), output);
    }
}