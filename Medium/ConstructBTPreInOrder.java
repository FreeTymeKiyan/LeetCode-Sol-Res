/**
 * Given preorder and inorder traversal of a tree, construct the binary tree
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * Tags: Tree, Array, DFS
 */
class ConstructBTPreInOrder {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Get root in preorder and search root in inorder
     * Then find range for left subtree and right subtree
     * Recurse down to build subtrees and connect to root
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
        if (ps > pe) return null;
        int pos = is;
        TreeNode root = new TreeNode(preorder[ps]);
        for (; pos <= ie; pos++) { // find root in inorder, no duplicates
            if (inorder[pos] == preorder[ps]) break;
        }
        root.left = buildTree(preorder, inorder, ps + 1, ps - is + pos, is, pos - 1); // left subtree
        root.right = buildTree(preorder, inorder, ps - is + pos + 1, pe, pos + 1, ie); // right subtree
        return root;
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
