import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class PostOrderTraversal {

    public static void main(String[] args) {

    }
    
    /**
     * post order: left - right - root
     * modify pre order: root - left - right to root - right - left
     * reverse the result
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.left != null) s.push(curNode.left);
            if (curNode.right != null) s.push(curNode.right);
        }
        Collections.reverse(res);
        return res;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}