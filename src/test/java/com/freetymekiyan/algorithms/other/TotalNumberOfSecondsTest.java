package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TotalNumberOfSecondsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{List.of(new int[]{0, 1}, new int[]{1, 2}), 2},
                new Object[]{List.of(new int[]{0, 5}, new int[]{4, 6}), 6},
                new Object[]{List.of(new int[]{4, 6}, new int[]{1, 3}), 4},
                new Object[]{List.of(new int[]{4, 6}, new int[]{1, 8}), 7}
        };
    }

    @Test(dataProvider = "examples")
    public void testTotalNumberOfSeconds(List<int[]> events, int expected) {
        TotalNumberOfSeconds t = new TotalNumberOfSeconds();
        int s = 0;
        for (int[] e : events) {
            s = t.totalNumberOfSeconds(e);
        }
        Assert.assertEquals(s, expected);
    }
}