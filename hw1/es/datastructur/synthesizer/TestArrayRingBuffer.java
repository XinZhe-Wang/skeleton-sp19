package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        assertFalse(arb.isEmpty());
        assertEquals(3,arb.fillCount());
        arb.dequeue();
        assertEquals(1,(int)arb.dequeue());

    }
}
