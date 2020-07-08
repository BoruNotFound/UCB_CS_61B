public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;

    /** Constructor */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    /** Creates a deep copy of LinkedListDeque other. */
    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        items = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            items[i] = (T) other.get(i);
        }
    }

    /** Resizes item list and copy the current item list to an assigned index.
     *  If flag is 1, then double the size, otherwise, halve the size.
     */
    private void resizeAndCopyTo(int srcPos, int desPos, int flag) {
        int len = items.length;
        T[] new_item;
        if (flag == 1) {
            new_item = (T[]) new Object[len * 2];
        } else {
            new_item = (T[]) new Object[len / 2];
        }

        System.arraycopy(items, srcPos, new_item, desPos, size);
        items = new_item;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (size < items.length) {
            // don't need to resize the array
            System.arraycopy(items, 0, items, 1, size);
        } else {
            // resize the array and copy the original one to index 1
            resizeAndCopyTo(0, 1, 1);
        }
        items[0] = item;

        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            // need to resize the array first
            resizeAndCopyTo(0,  0, 1);
        }

        items[size] = item;

        size += 1;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     *  separated by a space. Once all the items have been printed,
     *  print out a new line.
     */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);
            System.out.print(" ");
        }

        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T item = items[0];
        items[0] = null;

        size -= 1;
        if (size * 4 < items.length && items.length >= 16) {
            // usage is too low, shrink the array
            resizeAndCopyTo(1, 0, -1);
        } else {
            // don't need to resize
            System.arraycopy(items, 1, items, 0, size);
        }

        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T item = items[size - 1];
        items[size - 1] = null;

        size -= 1;
        if (size * 4 < items.length && items.length >= 16) {
            resizeAndCopyTo(0, 0, -1);
        }

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
        } else {
            return items[index];
        }
    }

}