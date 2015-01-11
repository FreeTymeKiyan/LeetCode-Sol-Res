/**
 * Given a binary tree
 * 
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * 
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * 
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * 
 * Tags: Tree, DFS
 */
class PopulatingNextRight {
    public static void main(String[] args) {
        
    }
    
    /**
     * Iterative
     * Store node in previous line
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;
        while (pre.left != null) { // no more level if left child is null
            cur = pre;
            while (cur != null) { // work on next level
                cur.left.next = cur.right; // connect left and right
                // connect right child with next node's left child
                if (cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next; // move current to next node
            }
            pre = pre.left; // move to next line
        }
    }

    static class TreeLinkNode{
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }
}