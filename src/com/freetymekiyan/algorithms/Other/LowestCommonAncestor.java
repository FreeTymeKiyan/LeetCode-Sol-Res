/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2, 
 * write a program to find the least common ancestor.
 * Allow a node to be a descendant of itself
 * 
 * Tags: Tree
 */
class LowestCommonAncestor {
    public static void main(String[] args) {
        
    }
    
    /**
     * If root is null, just return null
     * If root's value matches with n1 or n2, return root
     * If not, find lca recursively in both left and right subtrees
     * If both are not null, one value in left and the other in right, 
     * return root
     * If one is not null, return that one
     */
    public Node findLca(Node root, int n1, int n2) {
        if (root == null) return null;
        if (root.val == n1 || root.val == n2) return root;
        
        Node leftLca = findLca(root.left, n1, n2);
        Node rightLca = findLca(root.right, n1, n2);
        if (leftLca != null && rightLca != null) return root;
        return leftLca != null ? leftLca : rightLca;
    }
    
    class Node {
        int val;
        Node left;
        Node right;
        
        public Node() {}
            
        public Node(int v) {
            val = v;
        }
    }
}
