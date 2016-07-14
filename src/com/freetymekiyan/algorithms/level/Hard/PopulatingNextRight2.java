/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous
 * solution still work?
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * Tags: Tree ,DFS
 */
class PopulatingNextRight2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Store the head of next level
     * Store previous node 
     * Do level order traversal with a pointer
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode cur = root;  // current node of current level
        TreeLinkNode prev; // previous node
        TreeLinkNode nextHead; // nextHead of the next level

        while (cur != null) {
            nextHead = null;
            prev = null;
            while (cur != null) {
                if (cur.left != null) { // left child
                    if (prev != null) prev.next = cur.left;
                    else nextHead = cur.left; // set nextHead
                    prev = cur.left; // move right
                }
                if (cur.right != null) { // right child
                    if (prev != null) prev.next = cur.right;
                    else nextHead = cur.right; // set nextHead
                    prev = cur.right; // move right
                }
                cur = cur.next; // move right to next node in same level
            }
            // move to next level
            cur = nextHead;
        }
    }
    
    public void connect2(TreeLinkNode root) {
        // 1
        TreeLinkNode tempChild = new TreeLinkNode(0);
        while (root != null) {
            TreeLinkNode currentChild = tempChild;
            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
            tempChild.next = null;
        }
        // 2
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
    }
    
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}