package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WordSearchTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        return new Object[][]{
                new Object[]{board, "ABCCED", true},
                new Object[]{board, "SEE", true},
                new Object[]{board, "ABCB", false}
        };
    }

    @Test(dataProvider = "examples")
    public void testExist(char[][] board, String word, boolean expected) {
        WordSearch w = new WordSearch();
        Assert.assertEquals(w.exist(board, word), expected);
    }

    @Test(dataProvider = "examples")
    public void testExist2(char[][] board, String word, boolean expected) {
        WordSearch w = new WordSearch();
        Assert.assertEquals(w.exist2(board, word), expected);
    }
}