package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DailyTemperaturesTest {

    @Test
    public void testDailyTemperatures() throws Exception {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73}; // Duplicate temperatures separated.
        int[] output = {1, 1, 4, 2, 1, 1, 0, 0};
        DailyTemperatures d = new DailyTemperatures();
        Assert.assertTrue(Utils.compareArrays(d.dailyTemperatures(temperatures), output));

        temperatures = new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}; // Duplicate temperatures followed.
        output = new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0};
        Assert.assertTrue(Utils.compareArrays(d.dailyTemperatures(temperatures), output));

        temperatures = new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34};
        output = new int[]{1, 0, 0, 2, 1, 0, 0, 0, 0, 0};
        Assert.assertTrue(Utils.compareArrays(d.dailyTemperatures(temperatures), output));
    }
}