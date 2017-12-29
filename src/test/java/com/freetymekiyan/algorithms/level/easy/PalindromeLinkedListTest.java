package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.ListNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PalindromeLinkedListTest {

    @Test
    public void testIsPalindrome() {
        ListNode h = Utils.buildLinkedList(new int[]{1, 2});
        PalindromeLinkedList p = new PalindromeLinkedList();
        Assert.assertFalse(p.isPalindrome(h));
    }
}