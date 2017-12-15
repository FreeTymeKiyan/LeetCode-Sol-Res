package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindSmallestLetterGreaterThanTargetTest {

    @Test
    public void testNextGreatestLetterExamples() {
        char[] letters = {'c', 'f', 'j'};
        FindSmallestLetterGreaterThanTarget f = new FindSmallestLetterGreaterThanTarget();
        char[] targets = {'a', 'c', 'd', 'g', 'j', 'k'};
        char[] outputs = {'c', 'f', 'f', 'j', 'c', 'c'};

        for (int i = 0; i < targets.length; i++) {
            Assert.assertEquals(f.nextGreatestLetter(letters, targets[i]), outputs[i]);
            Assert.assertEquals(f.nextGreatestLetter2(letters, targets[i]), outputs[i]);
            Assert.assertEquals(f.nextGreatestLetter3(letters, targets[i]), outputs[i]);
        }

        letters = new char[]{'e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'};
        char target = 'e';
        char output = 'n';
        Assert.assertEquals(f.nextGreatestLetter(letters, target), output);
        Assert.assertEquals(f.nextGreatestLetter2(letters, target), output);
        Assert.assertEquals(f.nextGreatestLetter3(letters, target), output);
    }
}