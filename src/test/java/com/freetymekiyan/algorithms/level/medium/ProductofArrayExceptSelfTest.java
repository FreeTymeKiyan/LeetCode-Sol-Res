package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductofArrayExceptSelfTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}},
                new Object[]{new int[]{1, 2, 3}, new int[]{6, 3, 2}},
                new Object[]{new int[]{1, 2}, new int[]{2, 1}},
        };
    }


    @Test(dataProvider = "examples")
    public void testProductExceptSelf(int[] input, int[] output) {
        ProductofArrayExceptSelf p = new ProductofArrayExceptSelf();
        Assert.assertTrue(Utils.compareArrays(p.productExceptSelf(input), output));
    }

    @Test(dataProvider = "examples")
    public void testProductExceptSelf2(int[] input, int[] output) {
        ProductofArrayExceptSelf p = new ProductofArrayExceptSelf();
        Assert.assertTrue(Utils.compareArrays(p.productExceptSelf2(input), output));
    }
}