package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 * <p>
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
 * column).
 * <p>
 * If two nodes are in the same row and column, the order should be from left to right.
 * <p>
 * Examples:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * |   3
 * |  /\
 * | /  \
 * | 9  20
 * |    /\
 * |   /  \
 * |  15   7
 * return its vertical order traversal as:
 * |[
 * |  [9],
 * |  [3,15],
 * |  [20],
 * |  [7]
 * |]
 * Given binary tree [3,9,8,4,0,1,7],
 * |     3
 * |    /\
 * |   /  \
 * |   9   8
 * |  /\  /\
 * | /  \/  \
 * | 4  01   7
 * return its vertical order traversal as:
 * |[
 * |  [4],
 * |  [9],
 * |  [3,0,1],
 * |  [8],
 * |  [7]
 * |]
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 * |     3
 * |    /\
 * |   /  \
 * |   9   8
 * |  /\  /\
 * | /  \/  \
 * | 4  01   7
 * |    /\
 * |   /  \
 * |   5   2
 * return its vertical order traversal as:
 * |[
 * |  [4],
 * |  [9,5],
 * |  [3,0,1],
 * |  [8,2],
 * |  [7]
 * |]
 * Company Tags: Google, Snapchat, Facebook
 * Tags: Hash Table
 * Similar Problems: (E) Binary Tree Level Order Traversal
 */
public class BinaryTreeVerticalOrderTraversal {

    /**
     * BFS, Hash Table.
     * <p>
     * Vertical traversal is viewing a tree from left to right, from top to bottom.
     * We can divide the tree nodes into rows and columns, like a table.
     * When we traverse the tree:
     * | 1. if it's a left child, it should have a decremented column value.
     * | 2. if it's a right child, it should have a incremented column value.
     * <p>
     * Use a map to store level and the node values in it.
     * | Since we don't know the level range yet, we can only use map.
     * Use two integers, min and max, to track the range of levels.
     * | Then we can use this range we retrieve levels from the map.
     * Use a queue to traverse tree nodes in a level order.
     * | If left child is added to queue before right child, it will be in the front of the level.
     * Use another separate queue to store levels as integers.
     * | Otherwise we would have to modify the data structure of tree node.
     * Initialize level value of root as 0.
     * When accessing a left child, level value decrements by 1.
     * When accessing right, level value increments by 1.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> rowToNode = new HashMap<>();
        int min = 0; // Level range.
        int max = 0;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        Queue<Integer> levels = new ArrayDeque<>();
        nodes.offer(root);
        levels.offer(0);
        // BFS or level order traversal.
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int level = levels.poll();
            if (!rowToNode.containsKey(level)) { // Make sure each level has a list ready.
                rowToNode.put(level, new ArrayList<>());
            }
            rowToNode.get(level).add(node.val);
            if (node.left != null) {
                nodes.offer(node.left);
                levels.offer(level - 1);
                min = Math.min(min, level - 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                levels.add(level + 1);
                max = Math.max(max, level + 1);
            }
        }
        // Retrieve result from the map.
        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            res.add(rowToNode.get(i));
        }
        return res;
    }
}