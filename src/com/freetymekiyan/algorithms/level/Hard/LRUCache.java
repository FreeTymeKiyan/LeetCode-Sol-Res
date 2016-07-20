package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * (get means use, so we need to update)
 * <p>
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * <p>
 * Tags: Data Structure
 * <p>
 * Use 2 data structures to implement an LRU Cache
 * 1. A queue which is implemented by a doubly linked list. The max size of
 * the queue will be equal to cache size. Put least recently used at the end.
 * 2. A hash map with Node's value as key and the Node as value.
 * 3. A dummy head the queue.
 */
class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }

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

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

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
