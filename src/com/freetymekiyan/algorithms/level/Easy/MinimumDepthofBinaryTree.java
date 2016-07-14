package gitLeetCode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * 
 * Depth-first-search
 * @author chenshuna
 *
 */

public class MinimumDepthofBinaryTree {
    public static int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        else
            return getminDepth(root.left, root.right, 1);
    }
     public static int getminDepth(TreeNode rootleft, TreeNode rootright, int n) {
        if(rootleft == null && rootright == null){
            return n;
        }
        if(rootleft == null){
            return getminDepth(rootright.left, rootright.right, n+1);
        }
        else if(rootright == null){
            return getminDepth(rootleft.left, rootleft.right, n+1);
        }
        else{
            return Math.min(getminDepth(rootleft.left, rootleft.right, n+1),getminDepth(rootright.left, rootright.right, n+1));
        }
    }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    TreeNode res = new TreeNode(1);
    res.left = new TreeNode(2);
    res.right = new TreeNode(4);
    res.left.left = new TreeNode(5);
    res.left.left = new TreeNode(6);
    
    System.out.print(minDepth(res));
  }

}

class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }
 }
