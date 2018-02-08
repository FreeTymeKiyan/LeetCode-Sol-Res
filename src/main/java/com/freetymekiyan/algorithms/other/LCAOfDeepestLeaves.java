package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.GTNode;

/**
 * Given a random tree, find the lowest common ancestor of the deepest leaves of the tree.
 * OR find the smallest subtree of all the deepest leaves.
 * <p>
 * Company: Facebook
 */
public class LCAOfDeepestLeaves {

    /**
     * If the tree's height is h, root is at level 0.
     * The deepest leaves are at level h - 1, lowest level.
     * If the LCA is at level x, the deepest leaves level differences to their LCA are the same.
     * Each child of the root is a root of a subtree. Each subtree has a height.
     * If all the heights are the same, each subtree would contain some deepest leaves, that means root is the LCA.
     * If multiple heights are maximal, those subtree contain some deepest leaves, that still means root is the LCA.
     * If only one has the max height, that subtree contains all deepest leaves, LCA is in it.
     * Base cases:
     * If root is null, LCA is null.
     * If root has no child, LCA is root itself.
     */
    public GTNode findLca(GTNode root) {
        GTNodeWrapper result = getHeightAndLca(root);
        return result.node;
    }

    /**
     * Get the height of each child of root.
     * If the height is smaller than max height, do nothing.
     * If the height is larger than max height, recurse down.
     * If the height is the same as max height, current root is the LCA.
     *
     * @return The LCA and the height wrapper in GTNodeWrapper class.
     */
    private GTNodeWrapper getHeightAndLca(GTNode root) {
        GTNodeWrapper wrapper = new GTNodeWrapper();
        if (root == null) {
            wrapper.height = -1;
            return wrapper;
        }
        if (root.children.isEmpty()) {
            wrapper.node = root;
            wrapper.height = 0;
            return wrapper;
        }
        int maxHeight = Integer.MIN_VALUE;
        for (GTNode c : root.children) {
            GTNodeWrapper w = getHeightAndLca(c);
            if (w.height > maxHeight) {
                wrapper = w;
                maxHeight = w.height;
            } else if (w.height == maxHeight) {
                wrapper.node = root;
            }
        }
        wrapper.height = maxHeight + 1;
        return wrapper;
    }

    private class GTNodeWrapper {
        GTNode node;
        int height;
    }
}