package com.freetymekiyan.datastructures;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryIndexedTreeTest {

    private BinaryIndexedTree tree;

    @Before
    public void setUp() {
        int[] arr = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        tree = new BinaryIndexedTree(arr, arr.length);
    }

    @Test
    public void testExamples() {
        Assert.assertEquals(2, tree.getSum(0));
        Assert.assertEquals(3, tree.getSum(1));
        Assert.assertEquals(4, tree.getSum(2));
        Assert.assertEquals(7, tree.getSum(3));
        tree.update(3, 6); // Update BIT for above change in arr[]
        Assert.assertEquals(13, tree.getSum(3));
    }

    @After
    public void tearDown() {
        tree = null;
    }

}