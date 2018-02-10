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
     * Divide the tree nodes into rows and columns, like a table/matrix/grid.
     * When traversing:
     * | If it's a left child, its column should decrement by 1.
     * | If it's a right child, its column should increment by 1.
     * <p>
     * Use a map to store column and the node values in it.
     * | Since the column range is unknown, only map is feasible.
     * Use two integers, min and max, to track the range of columns.
     * | Then this range can be used to retrieve column values from the map.
     * Use a queue to traverse tree nodes in a level order.
     * | If left child is enqueued before right child, it will be in the front in the result.
     * Use another separate queue to store columns as integers.
     * | Otherwise extend the data structure of tree node to add a field named column.
     * Initialize column value of root as 0.
     * When enqueueing a left child, column decrements by 1.
     * When right, column value increments by 1.
     * Update the column range when column value changes.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> colToNodes = new HashMap<>();
        int min = 0; // Column range.
        int max = 0;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        Queue<Integer> cols = new ArrayDeque<>();
        cols.offer(0);
        // BFS or level order traversal.
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int col = cols.poll();
            // Same as colToNodes.computeIfAbsent(col, ArrayList::new).add(node.val);
            if (!colToNodes.containsKey(col)) { // Make sure each level has a list already.
                colToNodes.put(col, new ArrayList<>());
            }
            colToNodes.get(col).add(node.val);
            if (node.left != null) {
                nodes.offer(node.left);
                cols.offer(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                cols.add(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        // Retrieve result from the map.
        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            res.add(colToNodes.get(i));
        }
        return res;
    }
}