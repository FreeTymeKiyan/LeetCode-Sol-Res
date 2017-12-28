package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LowestCommonAncestorOfABinaryTreeTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{Utils.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4}), new TreeNode(5), new TreeNode(1), 3},
                new Object[]{Utils.buildBinaryTree(
                        new Integer[]{37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null,
                                null, null, 8}), new TreeNode(-100), new TreeNode(-71), -48}
        };
    }

    /**
     * Tests not passing since p and q are not the original object but a new node with same value.
     * Test cases are only for reference purpose.
     */
    @Test(dataProvider = "examples")
    public void testLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, int output) {
        LowestCommonAncestorOfABinaryTree l = new LowestCommonAncestorOfABinaryTree();
        Assert.assertEquals(l.lowestCommonAncestor(root, p, q).val, output);
    }

    @Test(dataProvider = "examples")
    public void testLowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q, int output) {
        LowestCommonAncestorOfABinaryTree l = new LowestCommonAncestorOfABinaryTree();
        Assert.assertEquals(l.lowestCommonAncestor2(root, p, q).val, output);
    }
}