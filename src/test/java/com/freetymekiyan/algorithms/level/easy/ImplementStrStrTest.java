package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ImplementStrStrTest {

    @Test
    public void testStrStr() {
        ImplementStrStr i = new ImplementStrStr();
        String str1 = "14531234";
        String str2 = "123";
        String str3 = "1123";
        String str4 = "1245";
        String str5 = "12121212123";
        Assert.assertEquals(i.strStr(str1, str2), 4);
        Assert.assertEquals(i.strStr(str3, str2), 1);
        Assert.assertEquals(i.strStr(str4, str2), -1);
        Assert.assertEquals(i.strStr(str5, str2), 8);
    }
}