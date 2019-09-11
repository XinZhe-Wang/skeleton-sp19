import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFind {

    private UnionFind unionFind;

    @Before
    public void init(){
        unionFind = new UnionFind(10);
    }

    @Test
    public void testintial(){
        assertArrayEquals(new int[]{0,1,2,3,4,5,6,7,8,9}, unionFind.id);
    }

    @Test
    public void testconnected(){
        unionFind.union(1,2);
        //unionFind.union(0,1);
        assertTrue(unionFind.connected(1,2));
        assertArrayEquals(new int[]{0,1,1,3,4,5,6,7,8,9}, unionFind.id);
    }

    @Test
    public void testvalidat(){
        assertTrue(unionFind.v);
    }
}