package gitLeetCode;

import java.util.*;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

   For example:
   Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
   return [1,2,3].
 * @author chenshuna
 *
 * Note: Recursive solution
 */

public class BinaryTreePreorderTraversal {
	/**
    * Recursive solution
    * @param args
    */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        preorderTraversalTree(root, res);
        return res;
    }
    public static void preorderTraversalTree(TreeNode root, List<Integer> res){
        if(root != null){
            res.add(root.val);
            preorderTraversalTree(root.left, res);
            preorderTraversalTree(root.right, res);
        }
        else return;
    }
    
    /**
     * iterative solution
     * Use stack
     * @param args
     */
    public static List<Integer> preorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode res = new TreeNode(0);
		res.left = new TreeNode(2);
		res.right = new TreeNode(1);
		res.left.left = new TreeNode(4);
		res.left.right = new TreeNode(5);
		res.left.left.left = new TreeNode(9);
		
		System.out.print(preorderTraversal(res));
		System.out.print(preorderTraversalIterative(res));
	}

}
