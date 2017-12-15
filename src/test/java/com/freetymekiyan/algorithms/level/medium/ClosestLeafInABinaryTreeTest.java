package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ClosestLeafInABinaryTreeTest {

    @Test
    public void testFindClosestLeaf() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{1, 3, 2});
        int k = 1;
        ClosestLeafInABinaryTree c = new ClosestLeafInABinaryTree();
        int closestLeaf = c.findClosestLeaf(root, k);
        Assert.assertTrue(Set.of(2, 3).contains(closestLeaf));

        root = Utils.buildBinaryTree(new Integer[]{1});
        k = 1;
        Assert.assertEquals(c.findClosestLeaf(root, k), 1);

        root = Utils.buildBinaryTree(new Integer[]{1, 2, 3, null, null, 4, null, 5});
        k = 5;
        Assert.assertEquals(c.findClosestLeaf(root, k), 5);

        root = Utils.buildBinaryTree(new Integer[]{1, 2});
        k = 1;
        Assert.assertEquals(c.findClosestLeaf(root, k), 2);
    }
}