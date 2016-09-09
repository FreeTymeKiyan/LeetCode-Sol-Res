package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the
 * node's value. If it is a null node, we record using a sentinel value such as #.
 * <p>
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a
 * null node.
 * <p>
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary
 * tree. Find an algorithm without reconstructing the tree.
 * <p>
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such
 * as "1,,3".
 * <p>
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * <p>
 * Example 2:
 * "1,#"
 * Return false
 * <p>
 * Example 3:
 * "9,#,#,1"
 * Return false
 * <p>
 * Tags: Stack
 */
public class VerifyPreorderSerializationOfABinaryTree {

    /**
     * Stack.
     * Iterate through the string characters.
     * If it's a number, just push to stack.
     * If it's a '#', we need to figure out some sub situations:
     * 1) If the top of the stack is a number, then this '#' is the left child, just push it.
     * 2) If the top of the stack is a '#', then this '#' is the right child, we should pop the subtree.
     * 2.1) After the subtree is popped, if the stack top is still '#', it means the subtree should be popped again.
     * 2.2) If the stack top is a number, we need to add a '#' to mark that the next node knows it's a right child.
     * https://discuss.leetcode.com/topic/35973/java-intuitive-22ms-solution-with-stack
     */
    public boolean isValidSerialization(String preorder) {
        Deque<String> stack = new ArrayDeque<>();
        String[] nodes = preorder.split(",");
        for (int i = 0; i < nodes.length; i++) {
            String curr = nodes[i];
            while ("#".equals(curr) && !stack.isEmpty() && "#".equals(stack.peek())) {
                stack.pop();
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
            stack.push(curr);
        }
        return stack.size() == 1 && "#".equals(stack.peek());
    }

    /**
     * Degree.
     * A value provide 2 out degrees and 1 in degree, except root.
     * A '#' provide no out degree and 1 in degree.
     * https://discuss.leetcode.com/topic/35976/7-lines-easy-java-solution
     */
    public boolean isValidSerializationB(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            diff--;
            if (diff < 0) {
                return false;
            }
            if (!"#".equals(node)) {
                diff += 2;
            }
        }
        return diff == 0;
    }

}
