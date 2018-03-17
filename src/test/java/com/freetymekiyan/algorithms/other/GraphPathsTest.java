package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class GraphPathsTest {

    @Test
    public void testGraphPaths() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node2;
        node3.right = node5;
        node4.left = node3;
        GraphPaths g = new GraphPaths();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(g.graphPaths(node1), List.of("1->2->4->3->5", "1->3->5")));
    }
}