/**
 * Path Sum
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the
 * path equals the given sum.
 * For example:Given the below binary tree and sum = 22,
 *            5
 *           / \
 *          4   8
 *         /   / \
 *       11  13  4
 *      /  \      \
 *     7    2      1
 *
 * Tags: Tree Depth-first Search
 * Similar Problems: (M) Path Sum II (H) Binary Tree Maximum Path Sum (M) Sum Root to Leaf Numbers
 * @author:chenshuna
 */

public class PathSum_112 {
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return false;
        }
        if(sum == root.val && root.left == null && root.right == null){
            return true;
        }
        if(root != null){
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode res = new TreeNode(5);
        res.left = new TreeNode(4);
        res.right = new TreeNode(8);
        res.left.left = new TreeNode(11);
        res.right.left = new TreeNode(13);
        res.right.right = new TreeNode(4);
        res.left.left.left= new TreeNode(7);
        res.left.left.right= new TreeNode(2);
        System.out.print(hasPathSum(res, 22));
    }
}