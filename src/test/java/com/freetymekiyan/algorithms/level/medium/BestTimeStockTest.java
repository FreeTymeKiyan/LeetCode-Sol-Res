package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BestTimeStockTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{7, 1, 5, 3, 6, 4}, 5},
                new Object[]{new int[]{7, 6, 4, 3, 1}, 0}
        };
    }

    @Test(dataProvider = "examples")
    public void testMaxProfit(int[] prices, int expected) {
        BestTimeStock b = new BestTimeStock();
        Assert.assertEquals(b.maxProfit(prices), expected);
    }

    @Test(dataProvider = "examples")
    public void testMaxProfit2(int[] prices, int expected) {
        BestTimeStock b = new BestTimeStock();
        Assert.assertEquals(b.maxProfit2(prices), expected);
    }
}