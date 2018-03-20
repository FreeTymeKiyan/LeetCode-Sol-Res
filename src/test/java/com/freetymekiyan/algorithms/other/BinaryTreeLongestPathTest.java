package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BinaryTreeLongestPathTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{null, null},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1}), "1"},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2}), "1->2"},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, 3}), "1->2"},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, 3, null, null, 4}), "1->3->4"}
        };
    }

    @Test(dataProvider = "examples")
    public void testLongestPath(TreeNode root, String expected) {
        BinaryTreeLongestPath b = new BinaryTreeLongestPath();
        Assert.assertEquals(b.longestPath(root), expected);
    }
}