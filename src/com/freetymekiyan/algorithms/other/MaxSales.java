package com.freetymekiyan.algorithms.other;

import org.junit.Assert;
import org.junit.Test;

/**
 * Suppose you are a salesman who travel around for business. Given n cities, for city i you can do business Ci times.
 * If you cannot go to the same city the next day. Find the maximum days you can do business.
 * <p>
 * Example 1:
 * Input: [7, 3, 2]
 * Output: 11
 * <p>
 * Example 2:
 * Input: [1000, 1, 1, 1]
 * Output: 7
 */
public class MaxSales {

    public int maxSalesTime(int[] c) {
        if (c == null || c.length == 0) {
            return 0;
        }
        if (c.length == 1) {
            return c[0] > 0 ? 1 : 0;
        }
        int n = c.length;
        int max = 0;
        int index = -1;
        while (true) {
            int curMax = 0;
            int curSecMax = 0;
            int curIdx = -1;
            int curSecIndx = -1;
            for (int j = 0; j < n; j++) {
                if (curIdx == -1) {
                    curMax = c[j];
                    curIdx = j;
                } else if (c[j] > curMax) {
                    curSecMax = curMax;
                    curSecIndx = curIdx;
                    curMax = c[j];
                    curIdx = j;
                } else if (c[j] > curSecMax) {
                    curSecMax = c[j];
                    curSecIndx = j;
                }
            }
            index = index == curIdx ? curSecIndx : curIdx;
            if (index == -1) {
                break;
            }
            c[index]--;
            max++;
        }
        return max;
    }

    @Test
    public void testExamples() {
        MaxSales m = new MaxSales();
        int[] c = {1000, 1, 1, 1};
        Assert.assertEquals(7, m.maxSalesTime(c));
        c = new int[]{7, 3, 2};
        Assert.assertEquals(11, m.maxSalesTime(c));
    }
}
