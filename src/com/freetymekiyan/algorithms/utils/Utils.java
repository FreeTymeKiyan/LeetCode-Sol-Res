package com.freetymekiyan.algorithms.utils;

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
}
