import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * Tags: Tree, Stack
 */
class BTPostOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;
        System.out.println(new BTPostOrder().postorderTraversal(root));
        System.out.println(new BTPostOrder().postorderTraversalA(root));
    }
    
    /**
     * post order: left - right - root
     * modify pre order: root - left - right to root - right - left
     * reverse the result
     */
    public List<Integer> postorderTraversalA(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.left != null) s.push(curNode.left);
            if (curNode.right != null) s.push(curNode.right);
        }
        Collections.reverse(res);
        return res;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Use two pointers. 1 for current node, 1 for previous traversed node
     * 3 situations:
     * 1. Traversing down, prev is not set or current is prev's child
     *      Push left child to stack if not null, then push rigth child 
     * 2. Traversing up from left, prev is current's left child
     *      Push right child to stack if not null
     * 3. Traversing up from right
     *      Visit, and pop
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        TreeNode prev = null; // previously traversed node
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            cur = s.peek();
            if (prev == null || prev.left == cur || prev.right == cur) { // traverse down
                if (cur.left != null) s.push(cur.left); // put left first
                else if (cur.right != null) s.push(cur.right);
            } else if (cur.left == prev) { // traverse up from the left
                if (cur.right != null) s.push(cur.right);
            } else { // traverse up from the right
                res.add(cur.val); // visit
                s.pop();
            }
            prev = cur;
        }
        return res;
    }
}