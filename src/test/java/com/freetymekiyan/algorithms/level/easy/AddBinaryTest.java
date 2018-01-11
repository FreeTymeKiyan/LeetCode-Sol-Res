package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddBinaryTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"1010", "1011", "10101"},
                new Object[]{"11110011001011110111110001010000111110011110101100011111010010001000001101111001000111", "111001011011111010001001111001100000101010000101100010101100010010010011011000", "11110100000101010011101011011010110111111111010110100100110100110100100000001100011111"},
        };
    }

    @Test(dataProvider = "examples")
    public void testAddBinary(String a, String b, String sum) {
        AddBinary ab = new AddBinary();
        Assert.assertEquals(ab.addBinary(a, b), sum);
    }

    @Test(dataProvider = "examples")
    public void testAddBinary2(String a, String b, String sum) {
        AddBinary ab = new AddBinary();
        Assert.assertEquals(ab.addBinary2(a, b), sum);
    }
}