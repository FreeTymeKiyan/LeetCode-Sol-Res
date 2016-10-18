package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * |   1
 * |  / \
 * | 2   3
 * |    / \
 * |   4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 * <p>
 * Company Tags: LinkedIn, Google, Uber, Facebook, Amazon, Microsoft, Yahoo, Bloomberg
 * Tags: Tree, Design
 * Similar Problems: (M) Encode and Decode Strings
 */
public class SerializeAndDeserializeBinaryTree {

    private static final String SPLITER = ",";
    private static final String NULLNODE = "#";

    /**
     * Pre-order traversal with root and a string builder.
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    /**
     * Pre-order traversal.
     * => 1,2,#,#,3,4,#,#,5,#,#,
     */
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULLNODE).append(SPLITER);
            return;
        }
        sb.append(node.val).append(SPLITER);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    /**
     * Recursive.
     * Same as pre-order traversal.
     * Split data and create a queue of string values first.
     * Each time, poll a node from the queue, create the current root.
     * Then build left and right subtree recursively.
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(SPLITER)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.poll();
        // Get a value from queue and build node
        if (NULLNODE.equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(nodes); // Build left subtree
        node.right = buildTree(nodes); // Build right subtree
        return node;
    }

    @Test
    public void testExamples() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{1, 2, null, null, 3, 4, null, null, 5});
        String s = serialize(root);
        TreeNode newRoot = deserialize(s);
        String s1 = serialize(newRoot);
        Assert.assertEquals(s, s1);
    }
}
