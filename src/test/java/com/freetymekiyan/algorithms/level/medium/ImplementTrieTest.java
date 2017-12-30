package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ImplementTrieTest {

    @Test
    public void testInsert() {
        ImplementTrie.Trie trie = new ImplementTrie.Trie();
        trie.insert(null);
        trie.insert("key");
        trie.insert("ke");
        trie.insert("kevin");
        trie.insert("kiyan");
    }

    @Test
    public void testStartsWith() {
        ImplementTrie.Trie trie = new ImplementTrie.Trie();
        Assert.assertFalse(trie.startsWith(null));
        Assert.assertTrue(trie.startsWith(""));
        trie.insert("key");
        Assert.assertTrue(trie.startsWith(""));
        Assert.assertTrue(trie.startsWith("ke"));
        Assert.assertFalse(trie.startsWith("ka"));
        Assert.assertTrue(trie.startsWith("key"));
        Assert.assertFalse(trie.startsWith("keys"));
        trie.insert("ke");
        Assert.assertTrue(trie.startsWith("ke"));
        Assert.assertFalse(trie.startsWith("search"));
    }

    @Test
    public void testSearch() {
        ImplementTrie.Trie trie = new ImplementTrie.Trie();
        Assert.assertFalse(trie.search(null));
        Assert.assertFalse(trie.search(""));
        Assert.assertFalse(trie.search("key"));
        trie.insert("key");
        Assert.assertTrue(trie.search("key"));
        Assert.assertFalse(trie.search("ke"));
        trie.insert("kevin");
        Assert.assertFalse(trie.search("ke"));
        Assert.assertTrue(trie.search("key"));
        Assert.assertTrue(trie.search("kevin"));
        trie.insert("kiyan");
        Assert.assertFalse(trie.search("ke"));
        Assert.assertTrue(trie.search("kiyan"));
        trie.insert("ke");
        Assert.assertTrue(trie.search("ke"));
    }

}