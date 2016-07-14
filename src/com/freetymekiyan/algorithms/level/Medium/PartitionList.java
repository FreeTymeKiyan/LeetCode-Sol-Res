/**
 * Given a linked list and a value x, partition it such that all nodes less
 * than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * 
 * Tags: Linkedlist, Two Pointers
 */
class PartitionList {
    public static void main(String[] args) {
        
    }
    
    /**
     * Build left and right lists and concatenate
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode pre1 = new ListNode(0);
        ListNode p = pre1;
        ListNode pre2 = new ListNode(0);
        ListNode q = pre2;
        // Partition into two halves.        
        while(cur != null){
            if(cur.val < x){
                p.next = cur;
                p = p.next;
            } else {
                q.next = cur;
                q = q.next;
            }
            cur = cur.next;
        }
        //concatenate two havles
        q.next = null; // Make sure the last node points to null 
        p.next = pre2.next;
        return pre1.next;
    }
    
    /**
     * Move greater and equal value nodes to tail
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head; // too short
        ListNode dummy = new ListNode(0); // create a dummy node
        dummy.next = head;
        ListNode p = dummy;
        ListNode start = dummy;
        while (p != null && p.next != null){
            if (p.next.val >= x) p = p.next;
            else { // move smaller nodes to start
                if (p == start){  // don't forget the edge cases when p == start
                    start = start.next;
                    p = p.next;
                } else {
                    ListNode tmp = p.next; // move to start
                    p.next = tmp.next;
                    tmp.next = start.next;
                    start.next = tmp;
                    start = tmp; // don't forget to move start.
                }
            }
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
