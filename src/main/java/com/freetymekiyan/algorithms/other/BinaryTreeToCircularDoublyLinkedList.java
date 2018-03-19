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
 * <p>
 * Company: Facebook
 */
public class BinaryTreeToCircularDoublyLinkedList {

    /**
     * In-order traversal, iteratively.
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
                // Visit.
                tail.right = top; // Connect nodes.
                top.left = tail;
                tail = tail.right; // Update tail.
                cur = top.right; // Move current pointer to right subtree.
            }
        }
        // Remember to connect linked list head with tail.
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
        TreeNode leftList = btToCircularList2(root.left);
        TreeNode rightList = btToCircularList2(root.right);
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

    public TreeNode btToCircularList3(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] head = new TreeNode[1];
        convert(root, prev, head);
        return head[0];
    }

    /**
     * Modify in-order traversal.
     * Previous node is the node before current root in sorted order, which is the tail.
     * If head and tail are available, root can be added to form a new circular doubly linked list.
     * <p>
     * Recurrence relation:
     * Convert the whole tree requires converting the left subtree first, then the root, then the right subtree.
     * If left tail is not null, connect it with root.
     * If left tail is null, root is the head.
     * Then the new tail would be root.
     * The new head is either root or left subtree's head.
     * Connect root with the head to form a full circular doubly linked list.
     * Then convert the right subtree.
     */
    private void convert(TreeNode root, TreeNode[] prev, TreeNode[] head) {
        if (root == null) return;
        convert(root.left, prev, head);
        root.left = prev[0]; // Connect root with previous node.
        if (prev[0] != null) {
            prev[0].right = root;
        } else { // If previous node is null, root is the head.
            head[0] = root;
        }

        TreeNode right = root.right; // Temporarily save right child.
        head[0].left = root; // Connect root with left linkedin list's head.
        root.right = head[0];
        prev[0] = root; // Set root as previous node.
        convert(right, prev, head);
    }
}