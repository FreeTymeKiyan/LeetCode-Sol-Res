package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 449. Serialize and Deserialize BST
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 * <p>
 * Related Topics: Tree
 * Similar Questions: (H) Serialize and Deserialize Binary Tree, (M) Find Duplicate Subtrees
 */
public class SerializeAndDeserializeBst {

    /**
     * Unlike binary tree, binary search tree can be constructed with preorder + inorder traversal.
     * So null node can be ignored, thus making the string more compact.
     */
    public class Codec {

        private static final String SEPARATOR = ",";

        /**
         * Get a preorder traversal data.
         * Later can be sorted to get inorder traversal.
         */
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            dfs(root, sb);
            return sb.toString();
        }

        private void dfs(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val).append(SEPARATOR);
            dfs(root.left, sb);
            dfs(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            int[] preorder = Arrays.stream(data.split(SEPARATOR)).mapToInt(Integer::parseInt).toArray();
            int[] inorder = Arrays.copyOf(preorder, preorder.length);
            Arrays.sort(inorder);
            Map<Integer, Integer> valToIdx = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                valToIdx.put(inorder[i], i);
            }
            return dfs(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, valToIdx);
        }

        private TreeNode dfs(int[] preorder, int[] inorder, int ps, int pe, int is, int ie, Map<Integer, Integer> valToIdx) {
            if (ps > pe) {
                return null;
            }
            int rootIdx = valToIdx.get(preorder[ps]);
            TreeNode root = new TreeNode(preorder[ps]);
            root.left = dfs(preorder, inorder, ps + 1, ps + rootIdx - is, is, rootIdx - 1, valToIdx); // x - ps = rootIdx - is
            root.right = dfs(preorder, inorder, ps + rootIdx - is + 1, pe, rootIdx + 1, ie, valToIdx);
            return root;
        }
    }
}