/**
 * Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * Tags: Linked list
 * Similar Problems: Swap Nodes in Pairs
 * @author chenshuna
 */
public class reverseKGroup {
    public static ListNode reverselist(ListNode pre,ListNode next){
        ListNode last = pre.next;
        ListNode cur = last.next;
        while(cur != next){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        return last;
    }
    
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newhead = new ListNode(0);
        newhead.next = head;
        ListNode pre = newhead;
        ListNode cur = head;
        int count = 0;
        while(cur != null){
            count++;
            ListNode next = cur.next;
            if(count == k){
                pre = reverselist(pre,next);
                count = 0;
            }
            cur = next;
        }
        return newhead.next;
    }

    public static void main(String[] args) {
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        res.next.next = new ListNode(3);
        res.next.next.next = new ListNode(4);
        res.next.next.next.next = new ListNode(5);
        ListNode display = reverseKGroup(res, 2);
        while(display != null){
            System.out.print(display.val);
            display = display.next;
        }
       
    }

}
