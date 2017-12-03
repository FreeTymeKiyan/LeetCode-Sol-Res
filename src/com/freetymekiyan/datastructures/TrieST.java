import java.util.LinkedList;
import java.util.Queue;

/**
 * R-way implementation of Trie
 * <p>
 * API list
 * 1. void put(String key, T val)
 * 2. T get(String key)
 * 3. delete(String key)
 * 4. boolean contains(String key)
 * 5. boolean isEmpty()
 * 6. String longestPrefixOf(String s)
 * 7. Iterable<String> keysWithPrefix(String s)
 * 8. Iterable<String> keysThatMatch(String s)
 * 9. int size()
 * 10. Iterable<String> keys()
 * <p>
 * no duplicate or null keys
 * no null values
 */
public class TrieST<T> {

    /**
     * radix
     */
    private static int R = 256;
    /**
     * root of trie
     */
    private Node root = new Node();

    /**
     * get the value from trie with the key
     *
     * @return the value of the key. otherwise, return null
     */
    public T get(String key) {
        Node n = get(root, key, 0);
        if (n == null) {
            return null;
        }
        return (T) n.val;
    }

    private Node get(Node n, String key, int d) {
        // Return node associated with key in the subtrie rooted at n.
        if (n == null) {
            return null;
        }
        if (d == key.length()) {
            return n;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(n.next[c], key, d + 1);
    }

    /**
     * insert an entry to the trie
     */
    public void put(String key, T val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node n, String key, T val, int d) {
        // Change value associated with key if in subtrie rooted at n.
        if (n == null) {
            n = new Node();
        }
        if (d == key.length()) {
            n.val = val;
            return n;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        n.next[c] = put(n.next[c], key, val, d + 1);
        return n;
    }

    /**
     * 1. maintain the number of keys in an instance variable N
     * 2. maintain the number of keys in a subtrie as a node instance variable, updated after the recursive calls in
     * put() and delete()
     * 3. calculate recursively
     *
     * @return number of keys in the trie
     */
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        // lazy recursively implementation
        // traverse all of the nodes in the trie
        // counting the number having a non-null value
        // bad performance
        if (n == null) {
            return 0;
        }
        int cnt = 0;
        if (n.val != null) {
            cnt++; // add its own size
        }
        for (char c = 0; c < R; c++) {
            cnt += size(n.next[c]); // add children's size
        }
        return cnt;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node n, String pre, Queue<String> q) {
        if (n == null) {
            return;
        }
        if (n.val != null) {
            q.offer(pre); // pre is a valid key
        }
        for (char c = 0; c < R; c++) {
            collect(n.next[c], pre + c, q); // collect keys in children
        }
    }

    /**
     * support '.' only
     *
     * @param pat string pattern with or without '.'
     * @return all keys that match the given pattern
     */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        collect(root, "", pat, q);
        return q;
    }

    private void collect(Node n, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (n == null) {
            return;
        }
        if (d == pat.length() && n.val != null) {
            q.offer(pre);
        }
        if (d == pat.length()) {
            return;
        }
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(n.next[c], pre + c, pat, q);
            }
        }
    }

    /**
     * @return the longest prefix exists in the trie
     */
    public String longestPrefix(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node n, String s, int d, int length) {
        if (n == null) {
            return length;
        }
        if (n.val != null) {
            length = d; // find a key, update length
        }
        if (d == s.length()) {
            return length; // reach the deepest level
        }
        char c = s.charAt(d);
        return search(n.next[c], s, d + 1, length); // search next level
    }

    /**
     * delete a key and its corresponding value from the trie
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node n, String key, int d) {
        if (n == null) {
            return null;
        }
        if (d == key.length()) { // find the correct level
            n.val = null;
        } else {
            char c = key.charAt(d); // recurse further to next level
            n.next[c] = delete(n.next[c], key, d + 1);
        }
        // check if there is no more keys in the subtrie
        if (n.val != null) {
            return n; // check current node
        }
        for (char c = 0; c < R; c++) {
            if (n.next[c] != null) {
                return n; // check children
            }
        }
        return null; // the client value and all of the links in this node are all
    }

    private static class Node {

        private Object val;
        private Node[] next = new Node[R];
    }
}