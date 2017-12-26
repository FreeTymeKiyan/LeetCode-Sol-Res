package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SerializeAndDeserializeBinaryTreeTest {

    @Test
    public void testExample() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{1, 2, null, null, 3, 4, null, null, 5});
        SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
        String output = s.serialize(root);
        TreeNode newRoot = s.deserialize(output);
        String output2 = s.serialize(newRoot);
        Assert.assertEquals(output, output2);
    }
}