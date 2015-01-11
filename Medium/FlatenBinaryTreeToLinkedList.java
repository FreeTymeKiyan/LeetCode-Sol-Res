/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 * 
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * 
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child
 * points to the next node of a pre-order traversal.
 * 
 * Tags: Tree, DFS
 */
class FlatenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        
    }
    
    /**
     * Add root's right subtree to left node's rightmost child
     * Then set that subtree as root's right subtree
     * And set root's left child to null
     * Move root to its right child and repeat
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) { // check left child
                TreeNode n = root.left;
                while (n.right != null) n = n.right; // rightmost child of left
                n.right = root.right; // insert right subtree to its right
                root.right = root.left; // set left subtree as right subtree
                root.left = null; // set left to null
            }
            root = root.right; // move to right child
        }
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}