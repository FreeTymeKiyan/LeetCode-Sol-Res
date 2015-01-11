import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * Tags: Tree, Stack
 */
class BTPreOrder {

    public static void main(String[] args) {

    }

    /**
     * Iterative
     * Use a stack
     * Pop current top, and push right first then push left
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.right != null) s.push(curNode.right);
            if (curNode.left != null) s.push(curNode.left); // left pop first
        }
        return res;
    }
    
    /**
     * Recursive
     */
    public List<Integer> preorderTraversalB(TreeNode root) {
        if (root == null) return;
        visit();
        preorderTraversalB(root.left);
        preorderTraversalB(root.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
