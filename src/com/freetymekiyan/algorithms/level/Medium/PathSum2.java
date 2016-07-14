import java.util.*;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * Tags: Tree, DFS
 */
class PathSum2 {
    public static void main(String[] args) {

    }

    /**
     * 
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        pathSum(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    /**
     * DFS or backtracking
     * Note that we can't pass path directly 
     * Dereference before recursing
     */
    public void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return; // return if current node is null 
        sum -= root.val; // update sum
        if (root.left == null && root.right == null && sum == 0) {
            path.add(root.val);
            res.add(new ArrayList<Integer>(path)); // add dereferenced path
            path.remove(path.size()-1); // reset path here!
            return;
        }
        path.add(root.val); // add value to current path
        pathSum(root.left, sum, path, res);
        pathSum(root.right, sum, path, res);
        path.remove(path.size()-1);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}