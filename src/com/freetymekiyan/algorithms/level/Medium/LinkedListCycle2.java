/**
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * Tags: Linkedlist, Two pointers, Math
 */
class LinkedListCycle2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Reset slow to head after cycle is detected
     * Then move until slow and fast meets
     * Each one step every time
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true; 
                break;
            }
        }
        if (!hasCycle) return null;
        slow = head;
        while (slow != fast) { // move x steps further
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
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
