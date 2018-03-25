package com.freetymekiyan.algorithms.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose we have a n-ary tree, each node has 3 states: 0, 1, 2.
 * If all children node's values are 0, the parent node must be 0 as well.
 * If all children node's values are 2, the parent node must be 2 as well.
 * All the other nodes are 1.
 * Given a valid tree like this, implement a function that changes the value of a node.
 * <p>
 * Note:
 * Each node can access its parent node.
 * Value can only be changed to 0 or 2.
 * <p>
 * Company: Facebook
 */
public class UpdateTreeValue {

    private final TreeNode root;

    public UpdateTreeValue(TreeNode root) {
        this.root = root;
    }

    /**
     * Recursive.
     * When one node changes value, its parent might change as well.
     * And so its parent's parent.
     *
     * @param node        The reference to the node that will change its value.
     * @param targetValue Can only be 0 and 2.
     */
    public void updateValue(TreeNode node, int targetValue) {
        while (helper(node, targetValue)) {
            node = node.parent;
        }
    }

    private boolean helper(TreeNode node, int targetValue) {
        node.val = targetValue;
        TreeNode parent = node.parent;
        if (parent == null) {
            return false;
        }
        for (TreeNode child : parent.children) {
            if (child == node) {
                continue;
            }
            if (child.val != targetValue) {
                return false;
            }
        }
        parent.val = targetValue;
        return true;
    }

    static class TreeNode {
        int val; // Can only be 0, 1 and 2.
        List<TreeNode> children = new ArrayList<>();
        TreeNode parent;
    }
}