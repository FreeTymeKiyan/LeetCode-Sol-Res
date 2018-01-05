package com.freetymekiyan.algorithms.level.hard;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * <p>
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * <p>
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 * |      1
 * |    /  \
 * |   2    3
 * |  / \    \
 * | 4   5    7
 * After calling your function, the tree should look like:
 * |      1 -> NULL
 * |    /  \
 * |   2 -> 3 -> NULL
 * |  / \    \
 * | 4-> 5 -> 7 -> NULL
 * Company Tags: Microsoft, Bloomberg, Facebook
 * Tags: Tree, Depth-first Search
 * Similar Problems: (M) Populating Next Right Pointers in Each Node
 */
public class PopulatingNextRight2 {

    /**
     * Iterative.
     * Connect next level at current level.
     * Current level is already connected by its previous level.
     * The first level, which is the root level, doesn't need to be connected.
     * <p>
     * Create a pointer pre initialized as root, means the pointer of previous level.
     * Create a dummy head before the each next level's leftmost node.
     * While pre is not null:
     * | Initialize a pointer of current level cur from dummy.
     * | While pre is not null:
     * |   If pre.left is not null:
     * |     Set cur.next to pre.left, move cur.
     * |   If pre.right is not null:
     * |     Set cur.next to pre.right, move cur.
     * |   Move pre to pre.next since all possibilities of pre node are done.
     * | Move pre to dummy.next because current level is fully connected.
     * | dummy.next is the leftmost node of current level.
     * | Set dummy.next to null.
     */
    public void connect(TreeLinkNode root) {
        TreeLinkNode pre = root;
        TreeLinkNode dummy = new TreeLinkNode(0); // Dummy head. dummy.next is the leftmost node of current level.
        while (pre != null) {
            TreeLinkNode cur = dummy; // Pointer of the next level, start from dummy.
            // At current level, connect next level.
            while (pre != null) {
                if (pre.left != null) { // Connect if root's left child not null.
                    cur.next = pre.left;
                    cur = cur.next;
                }
                if (pre.right != null) { // Connect if root's right child not null.
                    cur.next = pre.right;
                    cur = cur.next;
                }
                pre = pre.next; // Move previous level.
            }
            pre = dummy.next; // Move pre to next level's head.
            dummy.next = null; // IMPORTANT! dummy.next is updated when cur.next is first set.
            // Set dummy.next to null to avoid infinite loop.
        }
    }

    /**
     * Recursive.
     * Connect next level at current level.
     * Then recursively connect the next level.
     * Stop until level head is null.
     * For each level, the previous level is already connected.
     * Create a dummy node before the next level's head.
     * Create a work node as the current node to be connected.
     * For each node in previous level:
     * 1. If it has left child, connect with current node and move current node.
     * 2. If it has right child, connect with current node and move current node.
     * All possible children of current node is now connected with current node.
     * Current node also moves to the rightmost of next level.
     * So go to the next node of previous level.
     * Repeat until previous level runs out of nodes.
     */
    public void connect2(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode dummy = new TreeLinkNode(1); // Next level's dummy head.
        TreeLinkNode cur = dummy; // Work pointer to current node.
        while (root != null) {
            if (root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
        }
        connect2(dummy.next);
    }

    private class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }
}