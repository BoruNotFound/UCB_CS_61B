public class LinkedListDeque<T> implements Deque<T> {

    /** Node class */
    class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    /** Constructor */
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    /** Creates a deep copy of LinkedListDeque other. */
    public LinkedListDeque(LinkedListDeque other) {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;

        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i));
        }
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        Node new_first = new Node(item, null, null);
        sentinel.next.prev = new_first;

        new_first.next = sentinel.next;
        new_first.prev = sentinel;
        sentinel.next = new_first;

        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        Node new_last = new Node(item, null, null);
        sentinel.prev.next = new_last;

        new_last.next = sentinel;
        new_last.prev = sentinel.prev;
        sentinel.prev = new_last;

        size += 1;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return this.size;
    }

    /** Prints the items in the deque from first to last,
     *  separated by a space. Once all the items have been printed,
     *  print out a new line.
     */
    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("");
            return ;
        }

        Node temp = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.item);
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.println("");
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        Node first = sentinel.next;
        T item = first.item;
        first.next.prev = sentinel;
        sentinel.next = first.next;

        // clear references
        first.prev = null;
        first.next = null;
        first.item = null;

        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        Node last = sentinel.prev;
        T item = last.item;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;

        // clear references
        last.prev = null;
        last.next = null;
        last.item = null;

        size -= 1;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null.
     *  Must not alter the deque!
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node temp = sentinel.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.item;
    }

    /** Recursively get */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return scanRecursive(sentinel.next, index);
        }
    }

    private T scanRecursive(Node node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return scanRecursive(node.next, index - 1);
        }
    }

    /** Main function.
    public static void main(String[] args) {
        LinkedListDeque<Integer> l = new LinkedListDeque();
        l.addFirst(3);
        l.addFirst(5);
        l.addLast(7);

        LinkedListDeque<Integer> l2 = new LinkedListDeque(l);
        l2.printDeque();

        l2.removeFirst();
        l2.printDeque();
        l.printDeque();
    }

    */
}