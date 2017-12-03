/**
 * API
 * ST(): create a symbol table
 * void put(Key key, Value val): put key-value pair into the table
 * Value get(Key key): value paired with key
 * void delete(Key key): remove key and its value from table
 * boolean contains(Key key): is there a value paired with key?
 * boolean isEmpty(): is the table empty?
 * int size(): number of key-value pairs in the table
 * Iterable<Key> keys(): all the keys in the table
 * <p>
 * Created by kiyan on 5/26/16.
 */
public class ST<Key, Value> {

    public void delete(Key key) {
        put(key, null);
    }

    private void put(Key key, Value val) {
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private Key get(Key key) {
        return null;
    }
}
