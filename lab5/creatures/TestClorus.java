package creatures;
import huglife.Action;
import huglife.Direction;
import huglife.Impassible;
import huglife.Occupant;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/** Tests the plip class
 *  @authr FIXME
 */

public class TestClorus {

/*
    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.95, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        // TODO
        Clorus p1 = new Clorus(2);
        assertEquals(2,p1.energy(),0.01);
        p1.move();
        p1.move();
        assertEquals(1.94,p1.energy(),0.01);
        assertEquals(0.97,p1.replicate().energy(),0.01);
        assertEquals(0.97,p1.energy(),0.01);
        p1.stay();
        assertEquals(0.98,p1.energy(),0.01);
        p1.stay();
        assertEquals(0.99,p1.energy(),0.01);
        p1.replicate();
        assertEquals(0.495,p1.energy(),0.01);
    }
*/

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        /*Clorus c = new Clorus(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);*/


        // Energy >= 1; replicate towards an empty space.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPilp = new HashMap<Direction, Occupant>();
        topPilp.put(Direction.TOP, new Plip());
        topPilp.put(Direction.BOTTOM, new Impassible());
        topPilp.put(Direction.LEFT, new Impassible());
        topPilp.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(topPilp);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        /*c = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);*/


        // Energy < 1; stay.
        /*c = new Clorus(.99);

        actual = c.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE);

        assertEquals(expected, actual);*/


        // Energy < 1; stay.
        /*c = new Clorus(.99);

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE);

        assertEquals(expected, actual);*/


        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}

