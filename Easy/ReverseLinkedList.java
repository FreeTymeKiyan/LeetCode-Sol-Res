/**
 * Reverse a singly linked list.
 *
 * Hint:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * Tags: Linked List
 * 
 * Similar Problems: (M) Reverse Linked List II, (M) Binary Tree Upside Down, (E) Palindrome Linked List
 */
public class ReverseLinkedList {

    /**
     * Recursive
     *
     * Divide the list into 2 parts - head and the rest starts from head.next
     * Reverse the rest of the linked list
     * Append head to the tail of reversed linked list
     * Return newHead of the reversed linked list
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;

    }

    /**
     * Iterative
     */
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
