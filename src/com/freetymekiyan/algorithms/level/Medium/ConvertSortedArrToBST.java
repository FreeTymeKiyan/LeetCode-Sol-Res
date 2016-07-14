/**
 * Given an array where elements are sorted in ascending order, convert it to a
 * <strong>height balanced</strong> BST.
 * 
 * Tags: Tree, DFS
 */
class ConvertSortedArrToBST {
    
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        TreeNode root = sortedArrayToBST(arr);
        System.out.println(root.val);
    }
    
    public static TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) return null;
        return helper(num, 0, num.length - 1);
    }
    
    /**
     * Recursive, DFS
     * Divide into left subtree and right subtree with indices range
     * Choose mid point as the root of subtree
     */
    public static TreeNode helper(int[] num, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, left, mid - 1); // left and mid -1 
        root.right = helper(num, mid + 1, right); // mid + 1 and right
        return root;
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
