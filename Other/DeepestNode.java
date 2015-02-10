/**
 * Returns the deepest node in a binary tree. If the tree is complete, having 
 * two same depth of node, return the rightmost node.
 * 
 * Tags: Tree, DFS, Backtracking
 */
class DeepestNode {
    public static void main(String[] args) {
        
    }
    
    public Node deepestNode(Node root) {
        Node res = null;
        findDeepest(root, res, 0, 0);
        return res;
    }
    
    /**
     * Backtracking
     * If level > max, means a deeper node, update result and max level
     * Find more possibility in left and right subtrees
     */
    private void findDeepest(Node root, Node res, int level, int max) {
        if (root == null) return;
        if (level > max) {
            res = root;
            max = level;
            return;
        }
        findDeepest(root.left, res, level + 1, max);
        findDeepest(root.right, res, level + 1, max);
    }
    
    class Node {
        int val;
        Node left;
        Node right;
    }
}
