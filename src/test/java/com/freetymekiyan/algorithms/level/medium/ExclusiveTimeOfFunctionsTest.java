package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class ExclusiveTimeOfFunctionsTest {


    @Test
    public void testExclusiveTimeExamples() {
        List<String> logs = Collections.unmodifiableList(List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6"));
        int[] output = {3, 4};
        ExclusiveTimeOfFunctions e = new ExclusiveTimeOfFunctions();
        Assert.assertTrue(Utils.compareArrays(e.exclusiveTime(2, logs), output));
    }
}