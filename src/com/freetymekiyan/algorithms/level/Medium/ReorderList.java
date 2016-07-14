/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * Tags: Linkedlist
 */
class ReorderList {
    public static void main(String[] args) {
        // {1,2,3,4,5,6}
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        head.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        fifth.next = sixth;
        ReorderList r = new ReorderList();
        r.printList(head);
        r.reorderList(head);
        r.printList(head);
    }
    
    private void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println("NULL");
    }


    
    /**
     * Find mid point, then split list into 2 halves
     * Reverse latter half, then merge two lists
     */
    private void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // find mid point use runner's technique
        ListNode mid = head;
        ListNode tail = head;
        while (tail != null && tail.next != null) {
            mid = mid.next;
            tail = tail.next.next;
        }
        ListNode cur = mid.next;
        mid.next = null; // split mid and mid.next
        // reverse list 2 
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = mid.next; // insert after mid
            mid.next = cur;
            cur = temp; // move to next node
        }
        // reorder list
        ListNode left = head; 
        ListNode right = mid.next;
        while (right != null) { // latter half has fewer elements
            mid.next = right.next;
            right.next = left.next;
            left.next = right;
            // move to next node
            left = right.next;
            right = mid.next;
        }
    }
    
    /**
     * TLE, O(n^2)
     */
    private void reorderList(ListNode head) {;
        if (head == null || head.next == null) return;
        
        ListNode cur = head;
        while (cur != null && cur.next != null && cur.next.next != null) {
            ListNode beforeTail = cur.next;
            ListNode curNext = cur.next;
            while (beforeTail != null && beforeTail.next != null
                && beforeTail.next.next != null) {
                beforeTail = beforeTail.next;
            }
            cur.next = beforeTail.next;
            beforeTail.next.next = curNext;
            beforeTail.next = null;
            cur = curNext;
        }
        return;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
