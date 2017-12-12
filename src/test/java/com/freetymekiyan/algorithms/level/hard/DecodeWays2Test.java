package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class DecodeWays2Test {

    private static final Map<String, Integer> EXAMPLES = Map.of(
            "0", 0,
            "1", 1,
            "1*", 18,
            "**", 96,
            "***", 999,
            "*1*1*0", 404,
            "**********1111111111", 133236775, // Test overflow.
            "********************", 104671669 // Test overflow.
    );

    @Test
    public void testNumDecodings() {
        DecodeWays2 d = new DecodeWays2();
        for (Map.Entry<String, Integer> e : EXAMPLES.entrySet()) {
            System.out.println("Input is: " + e.getKey());
            Assert.assertEquals(d.numDecodings(e.getKey()), e.getValue().intValue());
            Assert.assertEquals(d.numDecodings2(e.getKey()), e.getValue().intValue());
        }
    }
}