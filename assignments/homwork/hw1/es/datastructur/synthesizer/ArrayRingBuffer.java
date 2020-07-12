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
        fillCount ++;

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
        fillCount --;

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

    public static void main(String args[]) {
        ArrayRingBuffer<String> a = new ArrayRingBuffer<>(3);

        a.enqueue("I");
        a.enqueue("am");
        a.enqueue("Boru");
        System.out.println(a.dequeue());
        //a.enqueue("aaa");
        System.out.println(a.capacity());
        System.out.println(a.fillCount());
        System.out.println(a.dequeue());
        System.out.println(a.isEmpty());
        a.enqueue("s1");
        a.enqueue("s2");
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());
        System.out.println(a.dequeue());
        System.out.println(a.isEmpty());
    }
}
