package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {

    private final int capacity;
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
        rb = (T[])new Object[capacity];
        first = 0;
        last =0;
        this.fillCount = 0;
        this.capacity = capacity;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pointer=0;
            @Override
            public boolean hasNext() {
                return pointer!=fillCount;
            }

            @Override
            public T next() {
                T returnItem = rb[pointer];
                pointer++;
                return returnItem;
            }
        };
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        } else if (o == null) {
            return false;
        } else if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (fillCount != other.fillCount) {
            return false;
        }
        Iterator<T> selfIterator = iterator();
        Iterator<T> otherIterator = other.iterator();
        while (selfIterator.hasNext()) {
            if (selfIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    @Override
    public int capacity(){
        return this.capacity;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }else {
            rb[last]=x;
            last++;
            if (last>=this.capacity()){
                this.last = 0;
            }
        }
        fillCount+=1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }else {
            T returnItem = rb[first];
            rb[first]=null;
            first++;
            if (first>=this.capacity){
                this.first = 0;
            }
            fillCount--;
            return returnItem;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        return rb[first];
    }



    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
