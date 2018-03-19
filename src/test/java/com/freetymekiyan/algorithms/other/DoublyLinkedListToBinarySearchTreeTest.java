package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DoublyLinkedListToBinarySearchTreeTest {

    @Test
    public void testConvert() {
        int[] values = {10, 12, 15, 25, 30, 36};
        TreeNode dummy = null;
        TreeNode prev = null;
        for (int v : values) {
            TreeNode node = new TreeNode(v);
            if (dummy == null) {
                dummy = new TreeNode(-1);
                dummy.right = node;
            }
            if (prev != null) {
                prev.right = node;
                node.left = prev;
            }
            prev = node;
        }
        dummy.right.left = prev;
        prev.right = dummy.right;
        DoublyLinkedListToBinarySearchTree d = new DoublyLinkedListToBinarySearchTree();
        TreeNode root = d.convert(dummy.right);

        TreeNode expected = Utils.buildBinaryTree(new Integer[]{25, 12, 36, 10, 15, 30, null});
        Assert.assertTrue(Utils.compareTrees(root, expected));
    }
}