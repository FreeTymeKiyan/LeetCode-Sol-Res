/**
 * Given a sorted linked list
 * delete all nodes that have duplicate numbers
 * leaving only distinct numbers from the original list.
 *
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * 
 * Tag: Linkedlist
 */
class RemoveDuplicatesFromSortedList2 {
    public static void main(String[] args) {
        ListNode head = buildTestList1();
        printList(head);
        printList(deleteDuplicates(head));
        System.out.println("---");
        head = buildTestList2();
        printList(head);
        printList(deleteDuplicates(head));
        System.out.println("---");
    }

    static ListNode buildTestList1() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(3);
        ListNode fifth = new ListNode(4);
        ListNode sixth = new ListNode(4);
        ListNode seventh = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;
        return head;
    }

    static ListNode buildTestList2() {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(1);
        ListNode forth = new ListNode(2);
        ListNode fifth = new ListNode(3);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        return head;
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("NULL");
    }
    
    /**
     * Set a pre pointer
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0); // dummy head
        dummyHead.next = head;
        ListNode pre = dummyHead; // two pointers
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) cur = cur.next; // skip
            if (pre.next == cur) pre = pre.next; // no dups
            else pre.next = cur.next; // cur moved
            cur = cur.next; // update current 
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
