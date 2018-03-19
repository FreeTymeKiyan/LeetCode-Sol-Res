package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a sorted doubly linked list, convert it to a balanced binary search tree.
 * <p>
 * Company: Facebook
 */
public class DoublyLinkedListToBinarySearchTree {

    /**
     * Recursive.
     * Convert a linked list and return the root of the binary search tree.
     * Pick the middle point as the root.
     * Convert left linked list to left subtree.
     * Convert right linkedin list to right subtree.
     */
    public TreeNode convert(TreeNode head) {
        if (head == null) return null;
        int n = getLength(head);
        return helper(new TreeNode[]{head}, n);
    }

    /**
     * Build the left subtree first.
     * As size shrinks, we can reach the position of next node in tree.
     * Set the node and its left child.
     * Move head to next linked list node.
     * Build the right subtree and set as right child.
     * Base case:
     * If length <= 0, there is no node, return null.
     */
    private TreeNode helper(TreeNode[] head, int len) { // The array is to hold reference of updated head, like inout in Swift.
        if (len <= 0) {
            return null;
        }
        TreeNode leftRoot = helper(head, len / 2);
        TreeNode node = head[0];
        node.left = leftRoot;
        head[0] = head[0].right;
        node.right = helper(head, len - len / 2 - 1);
        return node;
    }

    private int getLength(TreeNode head) {
        if (head == null) return 0;
        TreeNode tail = head.left;
        int len = 1;
        for (TreeNode cur = head; cur != tail; cur = cur.right) {
            len++;
        }
        return len;
    }

}