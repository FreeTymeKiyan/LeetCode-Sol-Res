package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * <p>
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

    private static final String SEPARATOR = ","; // Not using Character since split only takes String as input.
    private static final String NULL_NODE = "#";

    /**
     * Recursive.
     * Pre-order traversal with root and a string builder.
     * Can only pick traversals that root appears before its children.
     * That comes down to BFS and pre-order.
     * BFS are easy to think, but hard to implement since:
     * 1. We need to traverse null nodes, meaning that we need to insert null into queue.
     * 2. Java queue does not allow null values.
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    /**
     * Recursive. Pre-order traversal.
     * Append current node's val and a delimiter.
     * Then recurse down to left and right subtrees.
     * Base case:
     * If node is null, append a null node and a delimiter.
     * => 1,2,#,#,3,4,#,#,5,#,#,
     */
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_NODE).append(SEPARATOR);
            return;
        }
        sb.append(node.val).append(SEPARATOR);
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    /**
     * Recursive.
     * Same as pre-order traversal.
     * Split data and create a queue of string values first.
     */
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new ArrayDeque<>(Arrays.asList(data.split(SEPARATOR)));
        return buildTree(nodes);
    }

    /**
     * Poll a value string from the queue.
     * If null node, return null.
     * Create a tree node with value.
     * Then build left and right subtrees recursively.
     * Return the node in the end.
     */
    private TreeNode buildTree(Queue<String> nodes) {
        // Get a value from queue and build node.
        String val = nodes.poll();
        if (NULL_NODE.equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(nodes); // Build left subtree.
        node.right = buildTree(nodes); // Build right subtree.
        return node;
    }
}