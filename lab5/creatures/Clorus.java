package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private double repEnergyRetained = 0.2;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("Clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        return color(r,g,b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        energy = energy+c.energy();
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy-0.03; /*Clorus每Move一次都会lose 0.03*/
        // TODO
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy = energy+0.01; /*Clorus每Stay一次都会gain 0.01*/
        if (energy<0){
            energy = 0;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        double babyEnergy = energy*0.5;
        energy = energy*0.5;
        return new Clorus(babyEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        List<Direction> emptyNeighbors = new ArrayList<>();
        List<Direction> pilpNeighbors = new ArrayList<>();

        for (Direction d: neighbors.keySet()){
            if (neighbors.get(d).name()=="empty"){
                emptyNeighbors.add(d);
            }
            else if (neighbors.get(d).name()=="plip"){
                pilpNeighbors.add(d);
            }
        }
        /*Collections.shuffle(emptyNeighbors);
        Collections.shuffle(pilpNeighbors);*/
        if (emptyNeighbors.isEmpty()&&pilpNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }else if (!pilpNeighbors.isEmpty()){
            int moveDir = StdRandom.uniform(pilpNeighbors.size());
            return new Action(Action.ActionType.ATTACK,pilpNeighbors.get(moveDir));
        }else if (this.energy>=1){
            int moveDir = StdRandom.uniform(emptyNeighbors.size());
            return new Action(Action.ActionType.REPLICATE,emptyNeighbors.get(moveDir));
        }else if (this.energy<0){
            return new Action(Action.ActionType.DIE);
        }
        int moveDir = StdRandom.uniform(emptyNeighbors.size());
        return new Action(Action.ActionType.MOVE,emptyNeighbors.get(moveDir));

        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}

        /*if (false) { // FIXME
            // TODO
        }*/

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)

        // Rule 3

        // Rule 4
    }
}
