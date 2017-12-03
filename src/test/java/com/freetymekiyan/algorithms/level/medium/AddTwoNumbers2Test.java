package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.ListNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTwoNumbers2Test {

    @Test
    public void testAddTwoNumbersWithCarry() {
        ListNode l1 = Utils.buildLinkedList(new int[]{7, 2, 4, 3});
        ListNode l2 = Utils.buildLinkedList(new int[]{5, 6, 4});
        AddTwoNumbers2 a = new AddTwoNumbers2();
        ListNode result = a.addTwoNumbers(l1, l2);
        int[] expectedValues = new int[]{7, 8, 0, 7};
        Assert.assertNotNull(result);
        compare(expectedValues, result);
    }

    @Test
    public void testNullInputs() {
        ListNode l1 = null;
        ListNode l2 = null;
        AddTwoNumbers2 a = new AddTwoNumbers2();
        Assert.assertNull(a.addTwoNumbers(l1, l2));

        int[] list1 = {1, 2, 3};
        l1 = Utils.buildLinkedList(list1);
        ListNode result = a.addTwoNumbers(l1, l2);
        Assert.assertNotNull(result);
        compare(list1, result);

        l2 = l1;
        l1 = null;
        result = a.addTwoNumbers(l1, l2);
        Assert.assertNotNull(result);
        compare(list1, result);
    }

    private void compare(int[] expectedValues, ListNode node) {
        for (int value : expectedValues) {
            Assert.assertEquals(value, node.val);
            node = node.next;
        }
    }
}