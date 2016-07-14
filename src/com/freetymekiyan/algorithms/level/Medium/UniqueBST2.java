/**
 * Given n, <strong>generate</strong> all structurally unique BST's (binary
 * search trees) that store values 1...n.
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 
 * Tags: Tree, DP, Backtracking
 */
class UniqueBST2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * 1..n is the in-order traversal for any BST with nodes 1 to n. 
     * if pick i-th node as root
     * the left subtree will contain elements 1 to (i-1)
     * and the right subtree will contain elements (i+1) to n. 
     * use recursive calls to get back all possible trees for left and right 
     * subtrees and combine them in all possible ways with the root.
     */
    public List<TreeNode> generateTrees(int n) {
        return genTrees(1, n);
    }
    
    public List<TreeNode> genTrees (int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (start > end) { // base case
            list.add(null);
            return list;
        }
        
        List<TreeNode> left, right;
        for (int i = start; i <= end; i++) { // pick ith node from start to end
            left = genTrees(start, i - 1); // list of left subtree
            right = genTrees(i + 1, end); // list of right subtree
            for (TreeNode lnode : left) {
                for (TreeNode rnode: right) {
                    /*there exists a combination for each tree*/
                    TreeNode root = new TreeNode(i);
                    root.left = lnode; // attach root of left subtree
                    root.right = rnode; // attach root of right subtree
                    list.add(root); // add tree to result
                }
            }
        }
        return list;
    }
}
