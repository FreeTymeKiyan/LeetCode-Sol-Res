/**
 * Sort a linked list using insertion sort.
 * 
 * Tags: Linkedlist, Sort
 */
class InsertionSortList {
    public static void main(String[] args) {
        
    }
    
    /**
     * Check the list one by one to find a node that has smaller value than 
     * nodes before it and swap
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        
        for (ListNode p = head.next, prev = head; p != null; prev = p, p = p.next) {
            for (ListNode c = pre; c.next != p; c = c.next) {
                if (c.next.val > p.val) { 
                    prev.next = p.next; // skip p
                    p.next = c.next; // insert between cur and cur.next
                    c.next = p;
                    p = prev; // p is inserted to somewhere in the front, reset
                    break;
                }
            }
        }
        return pre.next;
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
