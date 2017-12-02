package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a binary tree
 * <p>
 * | struct TreeLinkNode {
 * |   TreeLinkNode *left;
 * |   TreeLinkNode *right;
 * |   TreeLinkNode *next;
 * | }
 * <p>
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 * |      1
 * |    /  \
 * |   2    3
 * |  / \  / \
 * | 4  5  6  7
 * <p>
 * After calling your function, the tree should look like:
 * |      1 -> NULL
 * |    /  \
 * |   2 -> 3 -> NULL
 * |  / \  / \
 * | 4->5->6->7 -> NULL
 * <p>
 * Company Tags: Microsoft
 * Tags: Tree, Depth-first Search
 * Similar Problems: (H) Populating Next Right Pointers in Each Node II, (M) Binary Tree Right Side View
 */
class PopulatingNextRight {

    /**
     * Iterative.
     * Connect the next level from current level.
     * For each level, get the left most node.
     * Start from the left most node, while node is not null:
     * | Set current node's left child's next to current node's right.
     * | If current node's next exists:
     * |   Set current node's right child's next to current node's next's left.
     * | Move current to it's next.
     * Move to the next level.
     */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;
        while (pre.left != null) { // Stop when next level doesn't exist.
            cur = pre; // Current pointer of this level.
            while (cur != null) {
                cur.left.next = cur.right; // Set left child's next to right child.
                if (cur.next != null) { // Set right child's next to next node's left child.
                    cur.right.next = cur.next.left;
                }
                cur = cur.next; // Move to the next node.
            }
            pre = pre.left; // Move to the next level.
        }
    }

    static class TreeLinkNode {
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }
}