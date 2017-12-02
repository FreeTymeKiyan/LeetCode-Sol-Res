package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Suppose we abstract our file system by a string in the following manner:
 * <p>
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * <p>
 * dir
 * subdir1
 * subdir2
 * file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * <p>
 * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
 * <p>
 * dir
 * subdir1
 * file1.ext
 * subsubdir1
 * subdir2
 * subsubdir2
 * file2.ext
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty
 * second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file
 * file2.ext.
 * <p>
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For
 * example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length
 * is 32 (not including the double quotes).
 * <p>
 * Given a string representing the file system in the above format, return the length of the longest absolute path to
 * file in the abstracted file system. If there is no file in the system, return 0.
 * <p>
 * Note:
 * The name of a file contains at least a . and an extension.
 * The name of a directory or sub-directory will not contain a ..
 * Time complexity required: O(n) where n is the size of the input string.
 * <p>
 * Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
 */
public class LongestAbsoluteFilePath {

    private LongestAbsoluteFilePath l;

    /**
     * Stack.
     * Using stack to save previous length at each level.
     * Find current level by the last index of "\t" in filename + 1.
     * Compare current level with stack size, stack size should be one level larger than current level.
     * Calculate current length, with slash at the end.
     * Push this length into stack.
     * If current name is a file, update max.
     * The length here should minus 1 to remove the last "/".
     */
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // Dummy level, start from 0
        String[] files = input.split("\n");
        int max = 0;
        for (String f : files) {
            int level = f.lastIndexOf("\t") + 1;
            while (stack.size() > level + 1) { // Stack size is one level larger since we have a dummy level
                stack.pop();
            }
            int len = stack.peek() + f.length() - level + 1; // Previous length + current length + "/"
            stack.push(len);
            if (f.contains(".")) { // Update max only when it is a file
                max = Math.max(max, len - 1); // Length without the last "/"
            }
        }
        return max;
    }

    @Before
    public void setUp() {
        l = new LongestAbsoluteFilePath();
    }

    @Test
    public void testExamples() {
        Assert.assertEquals(20, l.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        Assert.assertEquals(32, l.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }

    @After
    public void tearDown() {
        l = null;
    }

}
