package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class BinaryTreeInOrderTraversalTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, null, 2, 3}), List.of(1, 3, 2)},
                new Object[]{Utils.buildBinaryTree(new Integer[]{1, 2, 6, 3, 4, null, 7, null, null, 5, null, null, null, null, null}), List.of(3, 2, 5, 4, 1, 6, 7)}
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(TreeNode root, List<Integer> expected) {
        BinaryTreeInOrderTraversal b = new BinaryTreeInOrderTraversal();
        Assert.assertEquals(b.inorderTraversal(root), expected);
    }
}