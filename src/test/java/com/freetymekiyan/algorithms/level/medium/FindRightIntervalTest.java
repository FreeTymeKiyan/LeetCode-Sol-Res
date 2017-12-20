package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.Interval;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class FindRightIntervalTest {

    @DataProvider(name = "examples")
    public Object[][] createExamples() {
        return new Object[][]{
                new Object[]{Utils.toIntervalArray(new int[][]{{1, 2}}), new int[]{-1}},
                new Object[]{Utils.toIntervalArray(new int[][]{{3, 4}, {2, 3}, {1, 2}}), new int[]{-1, 0, 1}},
                new Object[]{Utils.toIntervalArray(new int[][]{{1, 4}, {2, 3}, {3, 4}}), new int[]{-1, 2, -1}}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindRightIntervalExamples(Interval[] intervals, int[] output) {
        FindRightInterval f = new FindRightInterval();
        Assert.assertTrue(Arrays.equals(f.findRightInterval(intervals), output));
    }

    @Test(dataProvider = "examples")
    public void testFindRightInterval2Examples(Interval[] intervals, int[] output) {
        FindRightInterval f = new FindRightInterval();
        Assert.assertTrue(Arrays.equals(f.findRightInterval2(intervals), output));
    }
}