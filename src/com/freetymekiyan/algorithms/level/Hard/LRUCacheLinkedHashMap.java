package com.freetymekiyan.algorithms.level.hard;

import java.util.LinkedHashMap;
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
 */
class LRUCacheLinkedHashMap extends LinkedHashMap {

    private final int capacity;

    public LRUCacheLinkedHashMap(int capacity, float loadFactor) {
        super(capacity, loadFactor, true);
        this.capacity = capacity;
    }

    public static void main(String arg[]) {
        LRUCacheLinkedHashMap lruCache = new LRUCacheLinkedHashMap(4, 0.75f);
        lruCache.put(1, "Object1");
        lruCache.put(2, "Object2");
        lruCache.put(3, "Object3");
        lruCache.get(1);
        lruCache.put(4, "Object4");
        System.out.println(lruCache);
        lruCache.put(5, "Object5");
        lruCache.get(3);
        lruCache.put(6, "Object6");
        System.out.println(lruCache);
        lruCache.get(4);
        lruCache.put(7, "Object7");
        lruCache.put(8, "Object8");
        System.out.println(lruCache);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

}
