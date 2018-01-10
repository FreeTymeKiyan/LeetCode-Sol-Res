package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TextJustificationTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        List<String> expected = List.of(
                "This    is    an",
                "example  of text",
                "justification.  "
        );
        return new Object[][]{
                new Object[]{new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16, expected},
                new Object[]{new String[]{""}, 2, List.of("  ")}
        };
    }

    @Test(dataProvider = "examples")
    public void testFullJustify(String[] words, int maxWidth, List<String> expected) {
        TextJustification t = new TextJustification();
        Assert.assertEquals(t.fullJustify(words, maxWidth), expected);
    }
}