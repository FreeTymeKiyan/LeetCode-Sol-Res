/**
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 * 
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * 
 * Tags: Linkedlist
 */
class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {

    }

    /**
     * nested while loop, skip next node
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next; // skip next node
            }
            cur = cur.next; // to next node
        }
        return head;
    }

    public ListNode myDeleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            while (cur != null && pre.val == cur.val) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
            if (cur != null) cur = cur.next;
        }
        return dummyHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}