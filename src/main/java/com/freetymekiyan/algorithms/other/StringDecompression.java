package com.freetymekiyan.algorithms.other;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

/**
 * input ： abbaba4x[a]bb3x[abaa2x[bab]]
 * <p>
 * output : abbabaaaaabbabaababbababaababbababaababbab
 * <p>
 * Created by kiyan on 6/3/16.
 */
public class StringDecompression {

    private StringDecompression sd;

    /**
     *
     */
    public String decompress(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder nested = new StringBuilder();
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // Save pattern in a stack
                String pattern = s.substring(i, i + 2); // Assume that only 1 digit before x
                int j = i + 3;
                while (j < s.length() && Character.isLetter(s.charAt(j))) {
                    pattern += s.charAt(j);
                    j++;
                }
                i = j - 1;
                stack.push(pattern);
            } else if (c == ']') {
                // Pop pattern from stack
                String[] pop = stack.pop().split("x");
                String pattern = "";
                for (int j = 0; j < Integer.parseInt(pop[0]); j++) {
                    pattern += pop[1] + nested.toString();
                }
                nested.setLength(0); // Clear string builder
                nested.append(pattern);
                if (stack.empty()) {
                    res.append(nested.toString());
                    nested.setLength(0);
                }
            } else {
                res.append(c);
            }
            i++;
        }
        return res.toString();
    }

    @Before
    public void setUp() {
        sd = new StringDecompression();
    }

    @Test
    public void testExamples() {
        String res = sd.decompress("abbaba4x[a]bb3x[abaa2x[bab]]");
        Assert.assertEquals("abbabaaaaabbabaababbababaababbababaababbab", res);
    }

    @After
    public void tearDown() {
        sd = null;
    }
}
