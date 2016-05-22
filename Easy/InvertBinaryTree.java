/**
 * Invert a binary tree.
 *
 *       4
 *     /   \
 *    2     7
 *   / \   / \
 *  1   3 6   9
 *
 * to
 *
 *       4
 *     /   \
 *    7     2
 *   / \   / \
 *  9   6 3   1
 *
 * Tags: Tree
 *
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);

        return root;
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
