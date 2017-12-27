package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegerToEnglishWordsTest {

    @Test
    public void testNumberToWords() {
        int[] inputs = {
                0,
                1,
                2,
                10,
                20,
                21,
                100,
                123,
                1000,
                1100,
                1123,
                1000100
        };
        String[] outputs = {
                "Zero",
                "One",
                "Two",
                "Ten",
                "Twenty",
                "Twenty One",
                "One Hundred",
                "One Hundred Twenty Three",
                "One Thousand",
                "One Thousand One Hundred",
                "One Thousand One Hundred Twenty Three",
                "One Million One Hundred"
        };
        IntegerToEnglishWords i = new IntegerToEnglishWords();
        for (int j = 0; j < inputs.length; j++) {
            Assert.assertEquals(i.numberToWords(inputs[j]), outputs[j]);
        }
    }
}