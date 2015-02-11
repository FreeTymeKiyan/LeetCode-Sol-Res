import java.util.*;

/**
 * Implement a hash table
 *
 * Tags: DS
 */
public class MyHashtable {
    
    LinkedList<Node>[] array;
    int size;

    class Node {
        String key;
        Object value;

        public Node(String k, Object v) {
            key = k;
            value = v;
        }
    }

    public MyHashtable(int size) {
        array = new LinkedList[size];
        this.size = size;
    }
    
    public static void main(String[] args) {
        
    }

    /**
     * Create a new linkedlist if not in array, add value and set the list
     * If linkedlist in array, search for the key and update value
     * If key not found, add a new node with value to the end of list
     */
    public void put(String key, Object value) {
        if (key == null || value == null) throw new IllegalArgumentException();
        int index = hashcode(key) % size;
        if (array[index] == null) {
            LinkedList<Node> list = new LinkedList<Node>();
            list.add(new Node(key, value));
            array[index] = list;
        } else {
            LinkedList<Node> list = array[index];
            boolean found = false;
            for (Node n : list) {
                if (n.key.equals(key)) {
                    n.value = value;
                    found = true;
                    break;
                }
            }
            if (!found) list.add(new Node(key, value));
        }
    }

    /**
     * If not in array, return null
     * If in array, get the linkedlist and search for the key
     * If no key, return null
     */
    public Object get(String key) {
        if (key == null) return null; // validation
        int index = hashcode(key) % size;
        if (array[index] == null) return null;
        LinkedList<Node> list = array[index];
        for (Node n : list) {
            if (key.equals(n.key)) {
                return n.value;
            }
        }
        return null;
    }
    
    /**
     * hash function to get the index
     */
    private int hashcode(String key) {
        // TODO implement hash function
        return 0;
    }
}
