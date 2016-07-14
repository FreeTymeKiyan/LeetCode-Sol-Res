/**
 * Write a C function to detect loop in a linked list
 * 
 * Tags: LinkedList
 */
class DetectLoop {
    public static void main(String[] args) {
        
    }
    
    /**
     * Floyd's Cycle-Finding Algorithm
     * Traverse linked list using two pointers.  
     * Move one pointer by one and other pointer by two.  
     * If these pointers meet at some node then there is 
     * a loop.  
     * If pointers do not meet then linked list doesn’t have loop.
     * 
     * Other methods: 
     * Put Node reference in a HashTable
     * Marked Visited Node, 
     */
    boolean hasLoop(Node head) {
        if (head == null || head.next == null) return false;
        for (Node slow = head, fast = head; fast != null && fast.next != null; slow = slow.next, fast = fast.next.next) {
            if (slow == fast) return true;
        }
        return false;
    }
    
    class Node {
        int val;
        Node next;
    }
}