package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * <p>
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * <p>
 * Company Tags: Microsoft, Facebook
 * Tags: Stack, String
 */
public class SimplifyPath {

    /**
     * Stack.
     * Split path with slash, there can be 4 situations:
     * 1) A correct name, push into stack.
     * 2) A dot, skip.
     * 3) Double dot, should pop last directory from stack, if not empty.
     * 4) Empty, skip.
     * Finally, go through stack and concatenate words.
     * Implementation:
     * Check input path. If it's null or empty, return empty string.
     * Create a deque as stack.
     * Split input path with slash. Get a string array of names.
     * For each name in names:
     * | If name is empty, OR name is a dot:
     * |   Skip.
     * | If name is "..":
     * |   Pop from stack. But make sure stack is not empty first.
     * | Else if it's just a name:
     * |   Push the name onto stack.
     * Create a string builder for result
     * While stack is not empty:
     * | Insert the name popped from stack to the front.
     * | Insert a slash to the front before the name.
     * Return "/" if string builder is empty. Otherwise return the string.
     */
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        Deque<String> s = new ArrayDeque<>();
        String[] words = path.split("/");
        for (String str : words) {
            if (str.length() == 0 || str.equals(".")) { // Empty or 1 dot, skip.
                continue;
            }
            if (str.equals("..")) { // Double dots, pop.
                if (!s.isEmpty()) { // Note that we should check whether stack is empty.
                    s.pop();
                }
            } else { // A valid word, push.
                s.push(str);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!s.isEmpty()) {
            res.insert(0, s.pop()).insert(0, "/");
        }
        return res.length() == 0 ? "/" : res.toString();
    }

    @Test
    public void testExamples() {
        Assert.assertEquals("/home", simplifyPath("/home/"));
        Assert.assertEquals("/c", simplifyPath("/a/./b/../../c/"));
        Assert.assertEquals("/", simplifyPath("/../"));
        Assert.assertEquals("/home/foo", simplifyPath("/home//foo/"));
        Assert.assertEquals("/e/f/g", simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }
}
