package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GraphValidTreeTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}, true},
                new Object[]{5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}, false},
                new Object[]{5, new int[][]{{0, 1}, {0, 4}, {1, 4}, {2, 3}}, false}
        };
    }

    @Test(dataProvider = "examples")
    public void testValidTree(int n, int[][] edges, boolean output) {
        GraphValidTree g = new GraphValidTree();
        Assert.assertEquals(g.validTree(n, edges), output);
    }
}