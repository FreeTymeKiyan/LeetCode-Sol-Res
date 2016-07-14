/**
 * Utility class.
 */
public class Utils {
    public static ListNode buildLinkedList(int[] values) {
        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }


}
