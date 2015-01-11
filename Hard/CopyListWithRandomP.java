import java.util.*;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * Tags: Hashtable, Linkedlist
 */
class CopyListWithRandomP {
    
    public static void main(String[] args) {
        
    }
    
    /**
     * Use a hashmap to store map between original node and copy node
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode,RandomListNode> nodemap = new HashMap<RandomListNode,RandomListNode>();
        return copyListHelper(head, nodemap);
    }
    
    /**
     * Get copy node from map
     */
    public RandomListNode copyListHelper(RandomListNode head, HashMap<RandomListNode,RandomListNode> nodemap)  {
        if(head == null) return null;
        if(nodemap.containsKey(head)) return nodemap.get(head); // return copy
        RandomListNode ret = new RandomListNode(head.label);
        nodemap.put(head, ret); // build map
        ret.next = copyListHelper(head.next, nodemap); // build next
        ret.random = copyListHelper(head.random, nodemap); // build copy
        return ret;
    }
    
    /**
     * Insert a same order after current node
     * Then split into two lists
     */
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return head;
        RandomListNode p1 = head;
        while (p1 != null) {
            RandomListNode copy = new RandomListNode(p1.label);
            copy.next = p1.next;
            p1.next = copy;
            p1 = p1.next.next;
        }
        p1 = head;
        while (p1 != null && p1.next != null) {
            if (p1.random != null) p1.next.random = p1.random.next;
            p1 = p1.next.next;
        }
        // split lists
        p1 = head;
        RandomListNode copy = p1.next;
        RandomListNode dummy = copy;
        while (copy != null && p1 != null) {
            p1.next = p1.next.next;
            if (copy.next == null) break;
            copy.next = copy.next.next;
            copy = copy.next;
            p1 = p1.next;
        }
        return dummy;
    }
    
    /**
     * Build a new list and connect original and new node together
     */
    public RandomListNode copyRandomList3(RandomListNode haed) {
        
    }
    
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
}
