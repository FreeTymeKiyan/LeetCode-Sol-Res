package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumberOfIslandsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{buildGrid(new String[]{"11110", "11010", "11000", "00000"}), 1},
                new Object[]{buildGrid(new String[]{"11000", "11000", "00100", "00011"}), 3},
        };
    }

    @Test(dataProvider = "examples")
    public void testNumIslands(char[][] grid, int output) {
        NumberOfIslands n = new NumberOfIslands();
        Assert.assertEquals(n.numIslands(grid), output);
    }

    @Test(dataProvider = "examples")
    public void testNumIslandsUnionFind(char[][] grid, int output) {
        NumberOfIslands n = new NumberOfIslands();
        Assert.assertEquals(n.numIslandsUnionFind(grid), output);
    }

    private char[][] buildGrid(String[] val) {
        char[][] grid = new char[val.length][val[0].length()];
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[i].length(); j++) {
                grid[i][j] = val[i].charAt(j);
            }
        }
        return grid;
    }
}