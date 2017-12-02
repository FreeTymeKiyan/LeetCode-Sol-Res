package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.HashMap;

/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Tags: Tree Array Depth-first Search
 * Similar Problems: (M) Construct Binary Tree from Preorder and Inorder Traversal
 *
 * @author chenshuna
 */
public class ConstructBinaryTree {

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public static TreeNode helper(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR,
                                  HashMap<Integer, Integer> map) {
        if (inL > inR || postL > postR) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postR]);
        int loc = map.get(postorder[postR]);
        root.left = helper(inorder, inL, loc - 1, postorder, postL, postL + loc - inL - 1, map);
        root.right = helper(inorder, loc + 1, inR, postorder, postR - inR + loc, postR - 1, map);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {4, 2, 5, 1, 6, 3};
        int[] preorder = {4, 5, 2, 6, 3, 1};
        System.out.print(buildTree(inorder, preorder));
    }
}