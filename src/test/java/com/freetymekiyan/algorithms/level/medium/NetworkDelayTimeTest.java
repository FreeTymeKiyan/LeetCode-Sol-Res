package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NetworkDelayTimeTest {

    @Test
    public void testNetworkDelayTime() {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;
        NetworkDelayTime n = new NetworkDelayTime();
        Assert.assertEquals(n.networkDelayTime(times, N, K), 2);
        Assert.assertEquals(n.networkDelayTime2(times, N, K), 2);
        Assert.assertEquals(n.networkDelayTime3(times, N, K), 2);

        int[][] times2 = {{1, 2, 1}, {2, 1, 3}};
        N = 2;
        K = 2;
        Assert.assertEquals(n.networkDelayTime(times2, N, K), 3);
        Assert.assertEquals(n.networkDelayTime2(times2, N, K), 3);
        Assert.assertEquals(n.networkDelayTime3(times2, N, K), 3);
    }
}