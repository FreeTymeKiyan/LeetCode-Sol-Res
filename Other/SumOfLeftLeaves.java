/**
 * Find sum of all left leaves in a given Binary Tree
 *
 * Tags: Tree, DFS
 */
class SumOfLeftLeaves {

    public static void main(String[] args) {
        SumOfLeftLeaves s = new SumOfLeftLeaves();
        int sum = s.sumOfLeftLeaves(s.buildTree());
        System.out.println(sum);
    }

    /**
     * DFS, recursive
     * Make sure current node is not null
     * Check whether left child is leaf node
     * If yes, add its value to result
     * If not, recurse on the left subtree
     * Recurse on the right subtree after that
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if (root != null) {
            if (isLeaf(root.left)) {
                res += root.left.val;
            } else {
                res += sumOfLeftLeaves(root.left);
            }
            res += sumOfLeftLeaves(root.right);
        }
        return res;
    }
    
    private boolean isLeaf(TreeNode n) {
        if (n == null) return false;
        if (n.left == null && n.right == null) return true;
        return false;
    }
    
    private TreeNode buildTree() {
        TreeNode t0 = new TreeNode(20);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(49);
        
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(12);
        TreeNode t5 = new TreeNode(15);
        
        TreeNode t6 = new TreeNode(23);
        TreeNode t7 = new TreeNode(52);
        TreeNode t8 = new TreeNode(50);
        
        t0.left = t1;
        t0.right = t2;
        t1.left = t3;
        t1.right = t4;
        t4.right = t5;
        t2.left = t6;
        t2.right = t7;
        t7.left = t8;
        
        return t0;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
