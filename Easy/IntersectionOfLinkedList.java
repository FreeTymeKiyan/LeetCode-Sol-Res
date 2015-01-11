/**
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function
 * returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 * Tags: Linkedlist
 */
class IntersectionOfLinkedList {

    public static void main(String[] args) {

    }

    /**
     * Time O(n), Space O(1)
     * skip all impossible ones
     * use recursion to compare next one
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = length(headA);
        int lenB = length(headB);
        int diff = Math.abs(lenA - lenB);
        
        if (lenA > lenB) while(diff-- > 0) headA = headA.next;
        else while(diff-- > 0) headB = headB.next;
        
        for (; headA != null && headB != null; headA = headA.next, headB = headB.next) if (headA.equals(headB)) return headA;
        return null;
    }

    private int length(ListNode n) {
        if (n == null) return 0;
        int length = 0;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
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