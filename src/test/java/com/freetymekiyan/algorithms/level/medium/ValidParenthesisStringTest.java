package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidParenthesisStringTest {

    @Test
    public void testCheckValidString() {
        String string = "(())((())()()(*)(*()(())())())()()((()())((()))(*";
        ValidParenthesisString v = new ValidParenthesisString();
        Assert.assertFalse(v.checkValidString(string));
    }
}