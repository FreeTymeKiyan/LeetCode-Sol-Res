package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StrobogrammaticNumber3Test {

    @Test
    public void testStrobogrammaticInRange() {
        StrobogrammaticNumber3 s = new StrobogrammaticNumber3();
        Assert.assertEquals(s.strobogrammaticInRange("50", "100"), 3);
    }
}