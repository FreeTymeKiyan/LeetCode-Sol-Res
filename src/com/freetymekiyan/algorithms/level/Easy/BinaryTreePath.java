import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * For example, given the following binary tree:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * All root-to-leaf paths are:
 *
 * ["1->2->5", "1->3"]
 *
 * Tags: Tree Depth-first Search
 * Similar Problems: (M) Path Sum II
 */
public class BinaryTreePath {

    /**
     * Use helper function to do DFS
     */
    public List<String> binaryTreePath1(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        dfs(res, root.val + "", root);
        return res;
    }

    private void dfs(List<String> res, String path, TreeNode root) {
        if (root.left == null && root.right == null) res.add(path);
        if (root.left != null) dfs(res, path + "->" + root.left.val, root.left);
        if (root.right != null) dfs(res, path + "->" + root.right.val, root.right);
    }

    /**
     * No helper
     */
    public List<String> binaryTreePath2(TreeNode root) {
        List<String> paths = new ArrayList<>();

        if (root == null) {
            return paths;
        }

        if (root.left == null && root.right == null) {
            paths.add(root.val + "");
            return paths;
        }

        // left subtree
        for (String path : binaryTreePath2(root.left)) {
            paths.add(root.val + "->" + path);
        }

        // right subtree
        for (String path : binaryTreePath2(root.right)) {
            paths.add(root.val + "->" + path);
        }

        return paths;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
