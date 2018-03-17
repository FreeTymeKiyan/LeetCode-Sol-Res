package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AlmostBalancedTreeTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{Utils.buildBinaryTree(new Integer[]{1}), true},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2}), true},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, 3}), true},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, null, 3, null, null, null, 4}), true},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, null, 2, null, null, null, 3, null, null, null, null, null, null, 4}), true},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, 3, 4, null, null, null, 5, null, null, null, null, null, null, 6}), false}
        };
    }

    @Test(dataProvider = "examples")
    public void testIsAlmostBalanced(TreeNode root, boolean expected) {
        AlmostBalancedTree a = new AlmostBalancedTree();
        Assert.assertEquals(a.isAlmostBalanced(root), expected);
    }
}