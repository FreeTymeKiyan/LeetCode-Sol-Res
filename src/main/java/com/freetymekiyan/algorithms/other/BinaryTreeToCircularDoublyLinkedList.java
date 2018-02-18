package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, convert it to a Circular Doubly Linked List.
 * <p>
 * The left and right pointers in nodes are to be used as previous and next pointers respectively in converted Circular
 * Linked List.
 * The order of nodes in List must be the same as In-order of the given binary tree.
 * The first node of In-order traversal must be head node of the Circular list.
 */
public class BinaryTreeToCircularDoublyLinkedList {

    /**
     * Iteratively.
     * Left is previous. Right is next.
     * In-order traverse the binary tree to create the linked list.
     * When traversing a node, connect it with the tail of the list.
     * Then move tail to the new tail.
     * Move the traversing point to its right subtree.
     */
    public TreeNode btToCircularList(TreeNode root) {
        if (root == null) return null;
        TreeNode dummy = new TreeNode(-1);
        TreeNode tail = dummy;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.pop();
                tail.right = top;
                top.left = tail;
                tail = tail.right;
                cur = top.right;
            }
        }
        TreeNode head = dummy.right;
        head.left = tail;
        tail.right = head;
        return head;
    }

    /**
     * Recursive.
     * Recurrence relation:
     * The final list is the connection of left subtree's list, root and right subtree's list.
     * Base case:
     * If a root is null, the list head is null.
     */
    public TreeNode btToCircularList2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftList = btToCircularList(root.left);
        TreeNode rightList = btToCircularList(root.right);
        root.left = root; // Make root itself a doubly circular linked list.
        root.right = root;
        if (leftList != null) { // Concatenate with left list.
            TreeNode leftTail = leftList.left;
            leftTail.right = root;
            root.left = leftTail;
            root.right = leftList;
            leftList.left = root;
        }
        if (rightList != null) { // Concatenate with right list.
            TreeNode rightTail = rightList.left;
            rightTail.right = root;
            root.left = rightTail;
            root.right = rightList;
            rightList.left = root;
        }
        return leftList == null ? root : leftList; // Return the head.
    }
}