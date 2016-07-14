import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking
 * about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * <p>
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * <p>
 * Tags: Linked List
 */
public class OddEvenLinkedList {

    private OddEvenLinkedList o;

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    @Before
    public void setUp() {
        o = new OddEvenLinkedList();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertNull(oddEvenList(null));
        ListNode res = o.oddEvenList(new ListNode(1));
        Assert.assertNotNull(res);
        Assert.assertEquals(1, res.val);
        int[] values = {1, 2};
        res = o.oddEvenList(Utils.buildLinkedList(values));
        Assert.assertNotNull(res);
        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(values[i], res.val);
            res = res.next;
        }
    }

    @Test
    public void testExamples() {
        // 1 -> 2 -> 3 -> 4 -> 5 -> NULL
        int[] values = {1, 2, 3, 4, 5};
        ListNode res = o.oddEvenList(Utils.buildLinkedList(values));
        Assert.assertNotNull(res);
        // 1 -> 3 -> 5 -> 2 -> 4 -> NULL
        int[] results = {1, 3, 5, 2, 4};
        for (int i = 0; i < results.length; i++) {
            Assert.assertNotNull(res);
            Assert.assertEquals(results[i], res.val);
            res = res.next;
        }

        // 1 -> 2 -> 3 -> 4 -> NULL
        values = new int[]{1, 2, 3, 4};
        res = o.oddEvenList(Utils.buildLinkedList(values));
        Assert.assertNotNull(res);
        // 1 -> 3 -> 2 -> 4 -> NULL
        results = new int[]{1, 3, 2, 4};
        for (int i = 0; i < results.length; i++) {
            Assert.assertNotNull(res);
            Assert.assertEquals(results[i], res.val);
            res = res.next;
        }

        //
        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        res = o.oddEvenList(Utils.buildLinkedList(values));
        Assert.assertNotNull(res);
        results = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        for (int i = 0; i < results.length; i++) {
            Assert.assertNotNull(res);
            Assert.assertEquals(results[i], res.val);
            res = res.next;
        }
    }

    @After
    public void tearDown() {
        o = null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }
    }

}
