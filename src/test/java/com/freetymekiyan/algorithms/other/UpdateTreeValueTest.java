package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateTreeValueTest {

    @Test
    public void testUpdateValue() {
        UpdateTreeValue.TreeNode root = new UpdateTreeValue.TreeNode();
        UpdateTreeValue.TreeNode left = new UpdateTreeValue.TreeNode();
        UpdateTreeValue.TreeNode mid = new UpdateTreeValue.TreeNode();
        UpdateTreeValue.TreeNode right = new UpdateTreeValue.TreeNode();
        root.val = 1;
        left.val = 0;
        mid.val = 0;
        right.val = 2;
        root.children.add(left);
        root.children.add(mid);
        root.children.add(right);
        left.parent = root;
        mid.parent = root;
        right.parent = root;
        UpdateTreeValue u = new UpdateTreeValue(root);
        u.updateValue(right, 0);
        Assert.assertEquals(root.val, 0);
    }
}