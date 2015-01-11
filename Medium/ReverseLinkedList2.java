/**
 * Reverse a linked list from position m to n
 * Do it in-place and in one-pass
 *
 * Eg:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4
 * Return 1->4->3->2->5->NULL
 *
 * Note:
 * 1 <= m <= n <= length of the list
 * 
 * Tags: Linkedlist
 */
class reverseLinkedList2 {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Move pointers to m 
     * Then insert next code to sublist's head till we reach n
     * Reconnect sublist with original list after reversing
     * We need 1 dummy head, head and tail for sublist, 
     * And cur for current node, preCur for dummy head of sublist
     * 5 pointers in total
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < m; i++) pre = pre.next;
        
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) { // insert next to head to reverse
            ListNode temp = cur.next.next;
            cur.next.next = pre.next;
            pre.next = cur.next;
            cur.next = temp;
        }
        return dummy.next;
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
