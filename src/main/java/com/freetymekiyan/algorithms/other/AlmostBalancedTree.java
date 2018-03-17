package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a tree, return whether the tree is almost balanced or not.
 * The height different between the leaves of an almost balanced tree cannot exceed 1.
 * Note that it's different from the definition of balanced tree since there might be only 1 leaf node in a tree and
 * it's still almost balanced.
 * <p>
 * Company: Facebook
 */
public class AlmostBalancedTree {

    /**
     * The largest height difference of 2 leaves must be <= 1.
     * How to find the largest height difference?
     * By traversing all heights can be generated.
     * Just need to record the min and max.
     * When there is a leaf, make sure min and max are updated.
     * And the difference between them is smaller than or equal to 1.
     * If not, return false.
     * After all heights are checked, return true.
     */
    public boolean isAlmostBalanced(TreeNode root) {
        if (root == null) return true;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> heights = new ArrayDeque<>();
        nodes.push(root);
        heights.push(0);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            int height = heights.pop();
            if (node.left == null && node.right == null) {
                if (height > max) {
                    max = height;
                }
                if (height < min) {
                    min = height;
                }
                if (max - min > 1) return false;
            }
            if (node.left != null) {
                nodes.push(node.left);
                heights.push(height + 1);
            }
            if (node.right != null) {
                nodes.push(node.right);
                heights.push(height + 1);
            }
        }
        return true;
    }
}