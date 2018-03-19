package com.freetymekiyan.algorithms.utils;

import java.util.*;

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
            throw new IllegalArgumentException("values should not be null or empty");
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

    public static GTNode buildTree(List<List<int[]>> valueAndChildren) {
        if (valueAndChildren == null || valueAndChildren.size() == 0) {
            throw new IllegalArgumentException("values should not be null or empty");
        }
        GTNode root = new GTNode();
        root.val = valueAndChildren.get(0).get(0)[0];
        root.count = valueAndChildren.get(0).get(0)[1];
        Queue<GTNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty() && level < valueAndChildren.size()) {
            level++;
            for (int i = queue.size(); i > 0; i--) {
                GTNode node = queue.poll();
                for (int j = 0; j < node.count; j++) {
                    GTNode child = new GTNode();
                    child.val = valueAndChildren.get(level).get(j)[0];
                    child.count = valueAndChildren.get(level).get(j)[1];
                    node.children.add(child);
                    queue.offer(child);
                }
            }
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

    public static boolean compareMatrices(int[][] m1, int[][] m2) {
        if (m1 == null && m2 == null) return true;
        if (m1 == null || m2 == null) return false;
        if (m1.length != m2.length || m1[0].length != m2[0].length) return false;
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) return false;
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
     * Compare 2 lists ignoring order.
     */
    public static <T> boolean compareListsIgnoreOrder(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        return set1.equals(set2);
    }

    public static boolean compareTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return compareTrees(root1.left, root2.left) && compareTrees(root1.right, root2.right);
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

    public static class GTNode {
        public int val;
        public List<GTNode> children = new ArrayList<>();
        public int count;
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