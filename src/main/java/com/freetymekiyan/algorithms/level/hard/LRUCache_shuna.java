package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;

/**
 * LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Tags: Design
 * HashMap + DoubleLinkedList
 * @author chenshun
*/

class DoubleLinkedList{
    int key;
    int value;
    DoubleLinkedList pre;
    DoubleLinkedList next;
    public DoubleLinkedList(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCacheShuna {
    private HashMap<Integer, DoubleLinkedList> map = new HashMap<Integer, DoubleLinkedList>();
    private int capacity;
    private int count;
    private DoubleLinkedList head;
    private DoubleLinkedList tail;
    
    public LRUCacheShuna(int capacity) {
        this.capacity = capacity;
        count = 0;
        head = new DoubleLinkedList(0, 0);
        tail = new DoubleLinkedList(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }
    
    public void deleteNode(DoubleLinkedList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void addToHead(DoubleLinkedList node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            DoubleLinkedList node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return -1;        
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            DoubleLinkedList node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } 
        else{
            DoubleLinkedList node = new DoubleLinkedList(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
                addToHead(node);
            } 
            else{
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }   
    }
}