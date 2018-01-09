package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplifyPathTest {

    @Test
    public void testSimplifyPath() {
        SimplifyPath s = new SimplifyPath();
        Assert.assertEquals(s.simplifyPath("/home/"), "/home");
        Assert.assertEquals(s.simplifyPath("/a/./b/../../c/"), "/c");
        Assert.assertEquals(s.simplifyPath("/../"), "/");
        Assert.assertEquals(s.simplifyPath("/home//foo/"), "/home/foo");
        Assert.assertEquals(s.simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"), "/e/f/g");
    }
}