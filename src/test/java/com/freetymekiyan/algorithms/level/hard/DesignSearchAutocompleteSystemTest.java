package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class DesignSearchAutocompleteSystemTest {

    @Test
    public void testAutocompleteSystem() {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        DesignSearchAutocompleteSystem.AutocompleteSystem system = new DesignSearchAutocompleteSystem.AutocompleteSystem(sentences, times);
        List<String> result = system.input('i');
        List<String> expected = List.of("i love you", "island", "i love leetcode");
        Assert.assertArrayEquals(expected.toArray(new String[]{}), result.toArray(new String[]{}));
        result = system.input(' ');
        expected = List.of("i love you", "i love leetcode");
        Assert.assertArrayEquals(expected.toArray(new String[]{}), result.toArray(new String[]{}));
        result = system.input('a');
        expected = Collections.emptyList();
        Assert.assertArrayEquals(expected.toArray(new String[]{}), result.toArray(new String[]{}));
        result = system.input('#');
        expected = Collections.emptyList();
        Assert.assertArrayEquals(expected.toArray(new String[]{}), result.toArray(new String[]{}));
    }
}
