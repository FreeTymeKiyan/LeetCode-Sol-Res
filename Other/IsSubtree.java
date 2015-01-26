/**
 * Check if one tree is another tree's subtree
 * 
 * Tags: Tree, Recursive
 */
class IsSubtree {
    
    /**
     * t1 root of tree, t2 root of subtree
     */
    boolean containsTree(Node t1, Node t2) {
        if (t2 == null) return true; // subtree is null
        return subTree(t1, t2);
    }
     
    /**
     * Recursive
     * If node values are the same, check if the tree matches
     * If not, go down to both subtrees
     */
    boolean subTree(Node r1, Node r2) {
        if (r1 == null) return false; // tree is null
        if (r1.value == r2.value) { // same root values
            if (matchTree(r1, r2)) return true;
        }
        return (subTree(r1.left, r2) || subTree(r1.right, r2)); // recurse down to r1's children
    }
    
    /**
     * Check if both trees are the same
     */
    boolean matchTree(Node r1, Node r2) {
        if (r2 == null && r1 == null) return true; // both null
        if (r1 == null || r2 == null) return false; // one null, one not null
        if (r1.value != r2.value) return false; // different root value
        return (matchTree(r1.left, r2.left) && matchTree(r1.right,              r2.right)); // recursively check children
    }
}
