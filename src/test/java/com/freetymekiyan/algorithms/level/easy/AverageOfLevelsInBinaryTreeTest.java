package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AverageOfLevelsInBinaryTreeTest {

    @Test
    public void testAverageOfLevelsExample() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        AverageOfLevelsInBinaryTree a = new AverageOfLevelsInBinaryTree();
        List<Double> output = List.of((double) 3, 14.5, (double) 11);
        Assert.assertEquals(a.averageOfLevels(root), output);
        Assert.assertEquals(a.averageOfLevels2(root), output);
    }

    @Test
    public void testOverflow() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE});
        AverageOfLevelsInBinaryTree a = new AverageOfLevelsInBinaryTree();
        List<Double> output = List.of((double) Integer.MAX_VALUE, (double) Integer.MAX_VALUE);
        Assert.assertEquals(a.averageOfLevels(root), output);
        Assert.assertEquals(a.averageOfLevels2(root), output);
    }
}