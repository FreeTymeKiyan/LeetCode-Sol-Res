/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p1 = l1, p2 = l2, p3 = head;
        int carry = 0;
        
        while(p1 != null || p2 != null){
            if(p1 != null){
                carry = carry + p1.val;
                p1 = p1.next;
            }
            if(p2 != null){
                carry = carry + p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(carry%10);
            p3 =p3.next;
            carry = carry/10;
        }
        if(carry == 1 && p3 != null){
            p3.next = new ListNode(1);
        } 
        
        return head.next;
    }
}
