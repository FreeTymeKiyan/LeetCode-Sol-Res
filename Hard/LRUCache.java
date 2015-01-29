import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * (get means use, so we need to update)
 *
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 *
 * Tags: Data Structure
 * 
 * Use 2 data structures to implement an LRU Cache
 * 1. A Queue which is implemented using a doubly linekd list. The max size of 
 * the queue will be equal to cache size. Put most recently used at the end
 * 2. A Hash with Node's value as key and the Node as value
 * 3. A dummy head for Doubly LinkedList
 */
class LRUCache {
    
    public static void main(String[] args) {
        
    }

    /**
     * Doubly linked list node
     */
    static class Node{
        Node next;
        Node prev;
        int key;
        int val;
        
        Node () {}

        Node (int key, int val) {
            this.key = key;
            this.val = val;
        }

        /**
         * Delete current node
         */
        private void delete() {
            prev.next = next;
            if (next != null) next.prev = prev;
        }

        /**
         * Add current node to preNode's next
         */
        private void addAfter(Node preNode) {
            next = preNode.next;
            if (next != null) next.prev = this;
            preNode.next = this;
            prev = preNode;
        }
    }

    Node dummy = new Node();
    Node tail = dummy;
    Map<Integer, Node> cache = new HashMap<Integer, Node>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Check key in map
     * If not in map, return -1
     * If in map, update usage by getting node and moving it to tail
     * Then return its value
     */
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node n = cache.get(key);
        moveToTail(n);
        return n.val;
    }

    /**
     * Check key in map or not
     * If in map, get node, update value, and move to tail
     * If not, create node, put in cache, and add to tail
     * Capacity can exceed when adding a new node
     * If capacity exceeds, remove head from map and delete
     */
    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.val = value;
            moveToTail(n);
            return;
        }
        Node n = new Node(key, value); // add new node
        cache.put(key, n);
        addToTail(n); // update usage

        if (cache.size() > capacity){
            n = dummy.next;
            cache.remove(n.key);
            n.delete(); // delete dummy
        }
    }

    /**
     * Delete and add to tail
     */
    private void moveToTail(Node n){
        if (n == tail) return;
        n.delete(); // unlink node with other nodes first
        addToTail(n); // add it to tail
    }

    /**
     * Add node after tail and update tail to that node
     */
    private void addToTail(Node n){
        n.addAfter(tail);
        tail = n;
    }
}
