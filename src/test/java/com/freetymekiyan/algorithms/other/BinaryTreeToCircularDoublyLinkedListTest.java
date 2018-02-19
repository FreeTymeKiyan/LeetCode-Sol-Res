package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BinaryTreeToCircularDoublyLinkedListTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new Integer[]{10, 12, 15, 25, 30, 36}, new int[]{25, 12, 30, 10, 36, 15}},
                new Object[]{new Integer[]{1}, new int[]{1}},
                new Object[]{new Integer[]{1, 2}, new int[]{2, 1}},
                new Object[]{new Integer[]{1, null, 3}, new int[]{1, 3}},
                new Object[]{new Integer[]{1, 2, 3}, new int[]{2, 1, 3}}
        };
    }

    @Test(dataProvider = "examples")
    public void testBtToCircularList(Integer[] values, int[] expected) {
        TreeNode root = Utils.buildBinaryTree(values);
        BinaryTreeToCircularDoublyLinkedList b = new BinaryTreeToCircularDoublyLinkedList();
        TreeNode head = b.btToCircularList(root);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(head.val, expected[i]);
            head = head.right;
        }
    }

    @Test(dataProvider = "examples")
    public void testBtToCircularList2(Integer[] values, int[] expected) {
        TreeNode root = Utils.buildBinaryTree(values);
        BinaryTreeToCircularDoublyLinkedList b = new BinaryTreeToCircularDoublyLinkedList();
        TreeNode head = b.btToCircularList2(root);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(head.val, expected[i]);
            head = head.right;
        }
    }

    @Test(dataProvider = "examples")
    public void testBtToCircularList3(Integer[] values, int[] expected) {
        TreeNode root = Utils.buildBinaryTree(values);
        BinaryTreeToCircularDoublyLinkedList b = new BinaryTreeToCircularDoublyLinkedList();
        TreeNode head = b.btToCircularList3(root);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(head.val, expected[i]);
            head = head.right;
        }
    }
}