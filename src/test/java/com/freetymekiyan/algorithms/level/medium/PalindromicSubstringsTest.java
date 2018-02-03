package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class PalindromicSubstringsTest {

    private static final List<String> INPUTS = Collections.unmodifiableList(List.of("abc", "aaa"));
    private static final List<Integer> OUTPUTS = Collections.unmodifiableList(List.of(3, 6));

    @Test
    public void testCountSubstringsExamples() {
        PalindromicSubstrings p = new PalindromicSubstrings();
        for (int i = 0; i < INPUTS.size(); i++) {
            Assert.assertEquals(p.countSubstrings(INPUTS.get(i)), OUTPUTS.get(i).intValue());
            Assert.assertEquals(p.countSubstrings2(INPUTS.get(i)), OUTPUTS.get(i).intValue());
        }
    }

    @Test(description = "Should handle it and return 0 if input is null.")
    public void testNullInput() {
        PalindromicSubstrings p = new PalindromicSubstrings();
        p.countSubstrings(null);
        p.countSubstrings2(null);
    }
}