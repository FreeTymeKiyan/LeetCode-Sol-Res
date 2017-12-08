package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class ValidPalindrome2Test {

    private static final List<String> EXAMPLE_INPUTS = Collections.unmodifiableList(List.of("aba", "abca", "acac", "aacc", "a", "ab", "abc"));
    private static final List<Boolean> EXPECTED_OUTPUTS = Collections.unmodifiableList(List.of(true, true, true, false, true, true, false));

    @Test
    public void testValidPalindromeExamples() {
        ValidPalindrome2 v = new ValidPalindrome2();
        for (int i = 0; i < EXAMPLE_INPUTS.size(); i++) {
            boolean output = v.validPalindrome(EXAMPLE_INPUTS.get(i));
            boolean exampleOutput = EXPECTED_OUTPUTS.get(i);
            Assert.assertEquals(output, exampleOutput);
        }
    }

    @Test
    public void testNullString() {
        ValidPalindrome2 v = new ValidPalindrome2();
        assertFalse(v.validPalindrome(null));
    }

    @Test
    public void testEmptyString() {
        ValidPalindrome2 v = new ValidPalindrome2();
        assertFalse(v.validPalindrome(""));
    }
}