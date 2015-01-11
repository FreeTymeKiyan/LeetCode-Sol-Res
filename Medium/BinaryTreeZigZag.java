/**
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * 
 * Tags: Tree, BFS, Stack
 */
class BinaryTreeZigZag {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Use queue to do BFS.
     * Get queue's size to get nodes in each level.
     * Use a boolean to indicate difference level order. 
     * Toggle it after a level is finished.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        boolean toggle = false;
        while (!q.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (!toggle) curLevel.add(n.val);
                else curLevel.add(0, n.val);
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            toggle = !toggle;
            res.add(curLevel);
        }
        return res;
    }
    
    /**
     * Use two lists, one for cur level, one for next level
     * Use a binary flag to determin whether we toggle the order of current
     * level or not
     * Update flag after each level
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        List<TreeNode> nextLevel = new ArrayList<TreeNode>();
        nextLevel.add(root);
        boolean toggle = false;
        while (!level.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            while (!level.isEmpty()) {
                TreeNode temp = level.remove(0);
                if (!toggle) curLevel.add(temp.val);
                else curLevel.add(0, temp.val); // insert to front
                if (temp.left != null) nextLevel.add(temp.left);
                if (temp.right != null) nextLevel.add(temp.right);
            }
            res.add(curLevel);
            level = nextLevel;
            toggle = toggle ? false : true;
        }
        return res;
    }
    
    public List<List<Integer>> zigzagLevelOrderB(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        List<TreeNode> nextLevel = new ArrayList<TreeNode>();
        nextLevel.add(root);
        boolean toggle = false;
        while (!level.isEmpty()) {
            List<Integer> curLevel = new ArrayList<Integer>();
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            while (!level.isEmpty()) {
                TreeNode temp = level.remove(0);
                if (!toggle) curLevel.add(temp.val);
                else curLevel.add(0, temp.val); // insert to front
                if (temp.left != null) nextLevel.add(temp.left);
                if (temp.right != null) nextLevel.add(temp.right);
            }
            res.add(curLevel);
            level = nextLevel;
            toggle = toggle ? false : true;
        }
        return res;
    }
}
