package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BestTimeToBuyAndSellStockWithTransactionFeeTest {

    @Test
    public void testMaxProfit() {
        BestTimeToBuyAndSellStockWithTransactionFee b = new BestTimeToBuyAndSellStockWithTransactionFee();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        Assert.assertEquals(b.maxProfit(prices, fee), 8);
    }

    @Test
    public void testSpecialPrices() {
        int[] prices = null;
        int fee = 2;
        BestTimeToBuyAndSellStockWithTransactionFee b = new BestTimeToBuyAndSellStockWithTransactionFee();
        Assert.assertEquals(b.maxProfit(prices, fee), 0);
        prices = new int[]{};
        Assert.assertEquals(b.maxProfit(prices, fee), 0);
        prices = new int[]{1};
        Assert.assertEquals(b.maxProfit(prices, fee), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeFee() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = -1;
        BestTimeToBuyAndSellStockWithTransactionFee b = new BestTimeToBuyAndSellStockWithTransactionFee();
        b.maxProfit(prices, fee);
    }

    @Test
    public void testMaxProfit2() {
        BestTimeToBuyAndSellStockWithTransactionFee b = new BestTimeToBuyAndSellStockWithTransactionFee();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        Assert.assertEquals(b.maxProfit2(prices, fee), 8);
    }
}