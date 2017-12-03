/**
 * void push(Item item)
 * Item pop()
 * boolean isEmpty()
 * int size()
 */
public class LinkedListStack<Item> {

    private Node first;
    private int N;

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<>();
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("not");
        stack.pop();
        stack.pop();
        stack.push("to");
        stack.push("be");
        stack.pop();
        stack.push("that");
        stack.pop();
        stack.pop();
        System.out.println("stack = " + stack);
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {

        Item item;
        Node next;
    }
}
