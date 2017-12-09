package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class MaximumSwapTest {

    private static final List<Integer> INPUTS = Collections.unmodifiableList(List.of(2736, 9973));
    private static final List<Integer> OUTPUTS = Collections.unmodifiableList(List.of(7236, 9973));

    @Test
    public void testMaximumSwapExamples() {
        MaximumSwap m = new MaximumSwap();
        for (int i = 0; i < INPUTS.size(); i++) {
            Assert.assertEquals(m.maximumSwap(INPUTS.get(i)), OUTPUTS.get(i).intValue());
        }
    }
}