package com.freetymekiyan.algorithms.other;

/**
 * Created by kiyan on 5/5/16.
 * Now become 543. Diameter of Binary Tree
 */
public class TreeDiameter {
    public int getTreeDiameter(TreeNode root) {
        int d = 0;
        if (root == null) return d;
        d = height(root.left) + height(root.right) + 1;
        int ld = getTreeDiameter(root.left);
        int rd = getTreeDiameter(root.right);
        /*
         Return max of following three
         1) Diameter of left subtree
         2) Diameter of right subtree
         3) Height of left subtree + height of right subtree + 1 */
        return Math.max(d, Math.max(ld, rd));
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
