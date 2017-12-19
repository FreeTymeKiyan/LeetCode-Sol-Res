package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.Interval;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NonOverlappingIntervalsTest {

    @Test
    public void testEraseOverlapIntervalsExamples() {
        NonOverlappingIntervals n = new NonOverlappingIntervals();

        int[][] input = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        Interval[] intervals = Utils.toIntervalArray(input);
        int output = 1;
        Assert.assertEquals(n.eraseOverlapIntervals(intervals), output);

        input = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        intervals = Utils.toIntervalArray(input);
        output = 2;
        Assert.assertEquals(n.eraseOverlapIntervals(intervals), output);

        input = new int[][]{{1, 2}, {2, 3}};
        intervals = Utils.toIntervalArray(input);
        output = 0;
        Assert.assertEquals(n.eraseOverlapIntervals(intervals), output);

        input = new int[][]{{1, 3}, {2, 4}, {2, 3}};
        intervals = Utils.toIntervalArray(input);
        output = 2;
        Assert.assertEquals(n.eraseOverlapIntervals(intervals), output);
    }


}