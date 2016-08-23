package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Note: You may assume that the string is well-formed:
 * <p>
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, -, ,, ].
 * <p>
 * Example 1:
 * <p>
 * Given s = "324",
 * <p>
 * You should return a NestedInteger object which contains a single integer 324.
 * Example 2:
 * <p>
 * Given s = "[123,[456,[789]]]",
 * <p>
 * Return a NestedInteger object containing a nested list with 2 elements:
 * <p>
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * i.  An integer containing value 456.
 * ii. A nested list with one element:
 * a. An integer containing value 789.
 * <p>
 * Tags: Stack, String
 * Similar Problems: (M) Flatten Nested List Iterator
 */
public class MiniParser {

    private MiniParser m;

    public NestedInteger deserialize(String s) {
        if (!s.startsWith("[")) {
            return new NestedInteger(Integer.parseInt(s));
        }
        Deque<NestedInteger> stack = new ArrayDeque<>();
        NestedInteger res = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                stack.push(new NestedInteger());
            } else if (c == '-' || Character.isDigit(c)) {
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    j++;
                }
                int val = Integer.parseInt(s.substring(i, j));
                NestedInteger ni = new NestedInteger(val);
                stack.peek().add(ni);
                i = j - 1;
            } else if (c == ']') {
                res = stack.pop();
                if (!stack.isEmpty()) {
                    stack.peek().add(res); // Add to previous NestedInteger's list
                }
            } // if c is ',', skip it
        }
        return res;
    }

    @Before
    public void setUp() {
        m = new MiniParser();
    }

    @Test
    public void testExamples() {
        String input = "[456,[789]]";
        Assert.assertEquals(input, m.deserialize(input).toString());
        input = "[123,[456,[789]]]";
        Assert.assertEquals(input, m.deserialize(input).toString());
        input = "324";
        Assert.assertEquals(input, m.deserialize(input).toString());
        input = "-3";
        Assert.assertEquals(input, m.deserialize(input).toString());
    }

    @After
    public void tearDown() {
        m = null;
    }

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    class NestedInteger {

        private int value;
        private boolean isInteger;
        private ArrayList<NestedInteger> list;

        // Constructor initializes an empty nested list.
        public NestedInteger() {
            list = new ArrayList<>();
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.value = value;
            isInteger = true;
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return isInteger;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return isInteger ? value : null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.value = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return isInteger ? null : list;
        }

        @Override
        public String toString() {
            if (isInteger) {
                return Integer.toString(value);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).toString());
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
