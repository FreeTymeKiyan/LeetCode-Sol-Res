package com.freetymekiyan.datastructures;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return null. (get means use, so we need to update)
 * <p>
 * set(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache<K, V> {

    private final int capacity;
    private Map<K, Node> cache;
    private Node dummyHead = new Node();
    private Node tail = new Node();


    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new InvalidParameterException("Cache capacity should be larger than 0");
        }
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        dummyHead.next = tail;
        dummyHead.prev = null;
        tail.prev = dummyHead;
        tail.next = null;
    }

    public void set(K key, V val) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.val = val;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, val);
            addFirst(newNode);
            cache.put(key, newNode);
            if (cache.size() > capacity) {
                K toRemove = removeLast();
                cache.remove(toRemove);
            }
        }
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            throw new NullPointerException("This key is NOT in cache.");
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.val;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addFirst(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addFirst(Node n) {
        n.prev = dummyHead;
        n.next = dummyHead.next;

        n.next.prev = n;
        n.prev.next = n;
    }

    private K removeLast() {
        K key = tail.prev.key;
        removeNode(tail.prev);
        return key;
    }

    class Node {

        Node prev;
        Node next;
        K key;
        V val;

        public Node() {
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
