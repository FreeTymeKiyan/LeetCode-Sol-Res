/**
 * void enqueue(Item item)
 * Item dequeue()
 * int size()
 * boolean isEmpty()
 */
public class LinkedListQueue<Item> {

    private Node first; // link to least recently added node
    private Node last;  // link to most recently added node
    private int N;      // number of items on the queue

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null; // unnecessary
        if (isEmpty()) {
            first = last; // only 1 node
        } else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) {
            last = null; // no more node
        }
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {

        Item item;
        Node next;
    }
}
