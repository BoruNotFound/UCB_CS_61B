package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount++;

        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T item = rb[first];
        rb[first] = null;
        first = (first + 1) % rb.length;
        fillCount--;

        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];
    }

    /**
     * Returns the capacity of the ring buffer.
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Returns the fillCount of the ring buffer.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int idx;
        private int count;

        public ArrayRingBufferIterator() {
            idx = first;
            count = 0;   // number of items that are already visited.
        }

        public boolean hasNext() {
            return (count < fillCount);
        }

        public T next() {
            T item = rb[idx];
            idx = (idx + 1) % rb.length;
            count++;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> other = (ArrayRingBuffer) o;
        if (this.capacity() != other.capacity() || this.fillCount() != other.fillCount()) {
            return false;
        }

        if (this.peek().getClass() != other.peek().getClass()) {
            return false;
        }

        boolean equal = true;
        /* Loops over each item to check equality. */
        for (int i = 0; i < this.capacity(); i++) {
            T thisItem = this.dequeue();
            T otherItem = other.dequeue();
            if (!thisItem.equals(otherItem)) {
                equal = false;
            }
            this.enqueue(thisItem);
            other.enqueue(otherItem);
        }

        return equal;
    }

    public static void main(String[] args) {
        ArrayRingBuffer<String> a = new ArrayRingBuffer<>(3);

        a.enqueue("I");
        a.enqueue("am");
        a.enqueue("Boru");
        for (String ai : a) {
            System.out.println(ai);
        }

    }
}
