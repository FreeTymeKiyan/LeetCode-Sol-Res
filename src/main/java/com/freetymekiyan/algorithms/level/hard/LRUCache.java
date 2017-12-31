package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 * <p>
 * Company Tags: Google, Uber, Facebook, Twitter, Zenefits, Amazon, Microsoft, Snapchat, Yahoo, Bloomberg, Palantir
 * Tags: Design
 * <p>
 * Use 2 data structures to implement an LRU Cache:
 * 1. A queue which is implemented by a doubly linked list. The max size of the queue will be equal to cache size. Put
 * least recently used at the tail.
 * 2. A hash map with Node's value as key and the Node as value.
 * 3. A dummy head and a dummy tail of the queue. So that we can access both head and tail fairly quick.
 */
class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    /**
     * Remember capacity.
     * Create cache map and doubly linked list.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Check key in cache.
     * If not in cache, return -1.
     * If in cache, get the node, move it to head, return its value.
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }

    /**
     * If key already in cache, only need to update value:
     * | Get the node, update its value, move to head.
     * If key is not in cache:
     * | Create a new node.
     * | Add it to the head of list and put it in cache.
     * | If cache size exceeds capacity:
     * |   Get the last node, which is the previous node of tail.
     * |   Remove it from list by its self-reference.
     * |   Remove it from cache by its key.
     */
    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            addNode(newNode);
            cache.put(key, newNode);
            if (cache.size() > capacity) {
                Node last = tail.prev;
                removeNode(last);
                cache.remove(last.key);
            }
        }
    }

    /**
     * Remove node from list and add it to head.
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Remove a node from double linked list.
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Add a node after head.
     */
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Double linked list node.
     * With previous node, next node, key, and value.
     */
    class Node {

        Node prev;
        Node next;
        int key;
        int val;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
