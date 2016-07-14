/**
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical 
 * and the nodes have the same value.
 * 
 * Tags: Tree, DFS
 */
class SameTree {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Recursive, pre-order check
     * If both node's values are the same, left subtrees are same and so right
     * Return true, otherwise return false
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q; // if one of them is null, it will return false. both null, true.
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right); // equal val, equal subtrees
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}