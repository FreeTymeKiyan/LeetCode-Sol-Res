package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IsGraphBipartiteTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}, true},
                new Object[]{new int[][]{{1}, {0, 2, 3}, {1}, {1}}, true},
                new Object[]{new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}, false},
                new Object[]{new int[][]{{}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}}, false},
                new Object[]{new int[][]{{4}, {}, {4}, {4}, {0, 2, 3}}, true}

        };
    }

    @Test(dataProvider = "examples")
    public void testIsBipartite(int[][] graph, boolean expected) {
        IsGraphBipartite i = new IsGraphBipartite();
        Assert.assertEquals(i.isBipartite(graph), expected);
    }

    @Test(dataProvider = "examples")
    public void testIsBipartite2(int[][] graph, boolean expected) {
        IsGraphBipartite i = new IsGraphBipartite();
        Assert.assertEquals(i.isBipartite2(graph), expected);
    }
}