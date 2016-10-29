package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
 * level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * Hide Company Tags LinkedIn Facebook Amazon Microsoft Apple Bloomberg
 * Hide Tags Tree Breadth-first Search
 * Hide Similar Problems (M) Binary Tree Zigzag Level Order Traversal (E) Binary Tree Level Order Traversal II (E)
 * Minimum Depth of Binary Tree (M) Binary Tree Vertical Order Traversal
 */
public class BTLevelOrder {

    List<List<Integer>> returnList = new ArrayList<List<Integer>>();

    public static void main(String[] args) {

    }

    /**
     * Queue
     * Get size of the queue each time
     * Iterate that many times to build current level
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            res.add(curLevel);
        }

        return res;
    }

    public List<List<Integer>> levelOrderB(TreeNode root) {
        func(root, 0);
        return returnList;

    }

    public void func(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        //VISIT
        if (returnList.size() >= level + 1) {
            List<Integer> l = returnList.get(level);
            l.add(root.val);
        } else {

            List<Integer> temp = new ArrayList<Integer>();
            temp.add(root.val);
            returnList.add(level, temp);
        }

        //go left and right
        func(root.left, level + 1);
        func(root.right, level + 1);
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
