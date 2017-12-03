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
            int curMax = Integer.MIN_VALUE;
            int curIdx = -1;
            for (int j = 0; j < n; j++) {
                if (index != -1 && j == index) {
                    continue;
                }
                if (c[j] > curMax) {
                    curMax = c[j];
                    curIdx = j;
                }
            }
            index = curIdx;
            if (c[index]-- == 0) {
                break;
            }
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
        c = new int[]{7, 3, 2, 1};
        Assert.assertEquals(13, m.maxSalesTime(c));
    }
}
