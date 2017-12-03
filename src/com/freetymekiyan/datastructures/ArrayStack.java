import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * array
 * generics
 * resizing
 * loitering
 * iteration
 * <p>
 * void push(Item item)
 * Item pop()
 * boolean isEmpty()
 * int size()
 */
public class ArrayStack<Item> implements Iterable {

    private int N;
    private Item[] a = (Item[]) new Object[1];

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("to");
        System.out.println(stack.toString());
        stack.push("be");
        System.out.println(stack.toString());
        stack.push("or");
        System.out.println(stack.toString());
        stack.push("not");
        System.out.println(stack.toString());
        stack.push("to");
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.push("be");
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.push("that");
        System.out.println(stack.toString());
        stack.push("is");
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
    }

    public void push(Item i) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = i;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item i = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return i;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator iterator() {
        return new ReverseStackIterator();
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
               "a=" + Arrays.toString(a) +
               ", N=" + N +
               '}';
    }

    private class ReverseStackIterator implements Iterator {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Object next() {
            return a[--i];
        }
    }
}
