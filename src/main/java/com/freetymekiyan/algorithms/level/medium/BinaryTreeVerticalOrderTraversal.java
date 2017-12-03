package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
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
     * Use a map to store level and the node values in it.
     * Use two integers, min and max, to track the range of levels.
     * And retrieve result from the map.
     * Use a queue for BFS on tree nodes.
     * Use another separate queue to store levels.
     * Initialize level value of root as 0.
     * When accessing a left child, level value decrement by 1.
     * When accessing right, level value increment by 1.
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = 0; // Level range.
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        queue.add(root);
        level.add(0);
        // BFS, put each node in its level.
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int curLvl = level.poll();
            if (!map.containsKey(curLvl)) {
                map.put(curLvl, new ArrayList<>());
            }
            map.get(curLvl).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                level.add(curLvl - 1);
                min = Math.min(min, curLvl - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                level.add(curLvl + 1);
                max = Math.max(max, curLvl + 1);
            }
        }
        // Retrieve result from map.
        List<List<Integer>> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }

}
