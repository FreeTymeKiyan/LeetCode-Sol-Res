/**
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1...n?
 * 
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 
 * Tags: Tree, DP
 */
class UniqueBST {
    public static void main(String[] args) {
        
    }
    
    /**
     * DP, Bottom-up approach.
     * a BST can be destruct to root, left subtree and right subtree.
     * if the root is fixed, every combination of unique left/right subtrees
     * forms a unique BST.
     * 
     * Let a[n] = number of unique BST's given values 1..n, then
     * a[n] = a[0] * a[n-1]     // put 1 at root, 2...n right
     *      + a[1] * a[n-2]     // put 2 at root, 1 left, 3...n right
     *      + ...
     *      + a[n-1] * a[0]     // put n at root, 1...n-1 left
     */
    public static int numTrees(int n) {
        if (n < 0) return 0;
        int[] trees = new int[n + 1];
        trees[0] = 1; // initialize 0, only 1 type of tree
        
        for(int i = 1; i <= n; i++) // from 1 ~ n
            for (int j = 0; j < i; j++) // from 0 ~ i - 1
                trees[i] += trees[j] * trees[i-j-1]; // note i-j-1 + j = i - 1

        return trees[n];
    }
    
    /**
     * Catalan Number
     */
    public int numTrees(int n) {
        if (n == 0 || n == 1 || n == 2) return n;
        int res = 2;
        for (int i = 3; i <= n; i++)
            res = res * 2 * (2 * i - 1) / (i + 1); // Catalan Number
        return res;
    }
}
