package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

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
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 0;
        b = 76;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
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
        if (energy==0){
            g=63;
            return color(r,g,b);
        }
        else if (energy ==2){
            g=255;
            return color(r,g,b);
        }
        else {
            g=(int)(96*energy+63);
            return color(r,g,b);
        }
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy-0.15; /*Pilp每Move一次都会lose 0.15*/
        if (energy<0){
            energy = 0;
        }else if (energy>2){
            energy =2;
        }
        // TODO
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy = energy+0.2; /*Pilp每Stay一次都会gain 0.2*/
        if (energy<0){
            energy = 0;
        }else if (energy>2){
            energy =2;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        double babyEnergy = energy*0.5;
        energy = energy*0.5;
        return new Plip(babyEnergy);
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
        List<Direction> clorusNeighbors = new ArrayList<>();
        boolean anyClorus = false;
        for (Map.Entry<Direction,Occupant> entry:neighbors.entrySet()){
            if (entry.getValue().name().equals("empty")){
                emptyNeighbors.add(entry.getKey());
            }
            if (entry.getValue().name().equals("Clorus")){
                clorusNeighbors.add(entry.getKey());
                anyClorus =true;
            }
        }
        /*Collections.shuffle(emptyNeighbors);*/
        if (emptyNeighbors.size()==0){
            return new Action(Action.ActionType.STAY);
        }else if (this.energy>=1){
            return new Action(Action.ActionType.REPLICATE,emptyNeighbors.get((int)Math.random()*emptyNeighbors.size()));
        }else if (anyClorus==true && Math.random()<0.5){
            return new Action(Action.ActionType.MOVE,emptyNeighbors.get((int)Math.random()*emptyNeighbors.size()));
        }else return new Action(Action.ActionType.STAY);

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
