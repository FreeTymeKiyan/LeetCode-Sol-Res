/**
 * Given a binary tree, find the maximum path sum.
 *
 * The path may start and end at any node in the tree.
 *
 * For example:
 * Given the below binary tree,
 *
 *        1
 *       / \
 *      2   3
 * Return 6.
 *
 * Tags: Tree, DFS
 */
class BinaryTreeMaxPathSum {
    public static void main(String[] args) {

    }

    int max;

    /**
     * Post order traversal
     * Get path sum of left and right sub trees
     * curMax of this level can be root's value v or v+left or v+right
     * max sum can be biggest of prevMax, curMax, and left + right + root.val
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        max = root.val;
        helper(root);
        return max;
    }

    /**
     * Post order traversal
     */
    int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // calculate current max, only one branch
        int curMax = Math.max(root.val, Math.max(left, right) + root.val);
        // update max
        max = Math.max(max, Math.max(curMax, left + right + root.val));
        return curMax; // note that return curMax here for result of upper level
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}