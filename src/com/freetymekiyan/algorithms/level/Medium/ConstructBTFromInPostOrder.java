/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * Tags: Tree, Array, DFS
 */
class ConstructBTFromInPostOrder {

    public static void main(String[] args) {
        
    }

    /**
     * DFS, find root, find range of left and right sub trees
     * The calculation of post array is trivial
     * For left subtree, ps = ps, pe = ps - is - 1 + pos(offset, including root)
     * For right subtree, ps = pe - ie + pos, pe = pe - 1(without root)
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) return null;
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(postorder[pe]);
        int pos = is;
        for (; pos <= ie; pos++) {
            if (inorder[pos] == root.val) break;
        }
        // Note how to calcuclate the start and end indices for post array
        root.left = buildTree(inorder, postorder, is, pos - 1, ps, ps - is - 1 + pos);
        root.right = buildTree(inorder, postorder, pos + 1, ie, pe - ie + pos, pe - 1);
        return root;
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
