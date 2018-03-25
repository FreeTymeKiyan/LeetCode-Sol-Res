package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given preorder and inorder traversal of a tree, construct the binary tree
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * Tags: Tree, Array, DFS
 */
class ConstructBTPreInOrder {

    /**
     * Get root in preorder and search root in inorder.
     * Then find range for left subtree and right subtree.
     * Recurse down to build subtrees and connect to root.
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    /**
     * @param ps start index of preorder, inclusive
     * @param pe end index of preorder, inclusive
     * @param is start index of inorder, inclusive
     * @param ie end index of inorder, inclusive
     */
    private TreeNode dfs(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
        if (ps > pe) return null;
        int pos = is;
        TreeNode root = new TreeNode(preorder[ps]);
        for (; pos <= ie; pos++) { // Find root in inorder array, no duplicates. Linear scan.
            if (inorder[pos] == preorder[ps]) break;
        }
        // Use pe - ps = ie - is to get
        root.left = dfs(preorder, inorder, ps + 1, ps - is + pos, is, pos - 1);
        root.right = dfs(preorder, inorder, ps - is + pos + 1, pe, pos + 1, ie);
        return root;
    }

    /**
     * Optimized.
     * Replace linear scan with a map.
     * So that the index of root can be returned in O(1).
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, valToIndex);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int ps, int pe, int is, int ie, Map<Integer, Integer> valToIdx) {
        if (ps > pe) return null;
        int rootIdx = valToIdx.get(preorder[ps]);
        TreeNode root = new TreeNode(preorder[ps]);
        root.left = dfs(preorder, inorder, ps + 1, ps - is + rootIdx, is, rootIdx - 1);
        root.right = dfs(preorder, inorder, ps - is + rootIdx + 1, pe, rootIdx + 1, ie);
        return root;
    }
}
