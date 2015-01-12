/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 *
 * Tags: Tree, DFS
 */
class MaxDepth {
    public static void main(String[] args) {

    }

    /**
     * Recursive
     * If node is null, return depth 0
     * Return the max of two + 1
     */
    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}