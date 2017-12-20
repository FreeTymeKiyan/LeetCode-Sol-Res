package com.freetymekiyan.algorithms.utils;

import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Utility class.
 */
public class Utils {

    public static ListNode buildLinkedList(int[] values) {
        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return head;
    }

    public static TreeNode buildBinaryTree(Integer[] values) {
        if (values == null || values.length == 0) {
            throw new InvalidParameterException("values should not be null or empty");
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            if (i + 1 < values.length && values[i + 1] != null) {
                node.right = new TreeNode(values[i + 1]);
                queue.offer(node.right);
            }
            i += 2;
        }
        return root;
    }

    public static boolean compareArrays(int[] array1, int[] array2) {
        if (array1 == null && array2 == null) return true;
        if (array1 == null || array2 == null) return false;
        if (array1.length != array2.length) return false;
        System.out.println("Compare these two arrays:");
        System.out.println(String.format("Array1 %s", Arrays.toString(array1)));
        System.out.println(String.format("Array2 %s", Arrays.toString(array2)));
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Interval[] toIntervalArray(int[][] arr) {
        Interval[] intervals = new Interval[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intervals[i] = new Interval(arr[i][0], arr[i][1]);
        }
        return intervals;
    }

    /**
     * This is the interface that allows for creating nested lists.
     * You should not implement it, or speculate about its implementation
     */
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class ListNode {

        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval() {
            start = 0;
            end = 0;
        }

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
