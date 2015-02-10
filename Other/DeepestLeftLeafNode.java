/**
 * Given a Binary Tree, find the deepest leaf node that is left child of its 
 * parent.
 * 
 * Tags: Tree, DFS, Backtracking
 */
class DeepestLeftLeafNode {
    public static void main(String[] args) {
        
    }
    
    /**
     * 
     */
    public Node deepestLeftLeaf(Node root) {
        Node res = null;
        deepestLeftLeaf(root, 0, 0, false, res);
        return res;
    }
    
    /**
     * Backtracking
     * If is left child, is leaf node, and level > maxLevel
     * Update result and maxLevel, then return
     */
    public void deepestLeftLeaf(Node root, int level, int maxLevel, boolean isLeft, Node res) {
        if (root == null) return;
        
        if (isLeft && root.left == null && root.right == null && level > maxLevel) {
            res = root;
            maxLevel = level;
            return;
        }
        
        deepestLeftLeaf(root.left, level + 1, maxLevel, true, res);
        deepestLeftLeaf(root.right, level + 1, maxLevel, false, res);
    }
}
