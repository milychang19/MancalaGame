package mancala;
import java.io.Serializable;

public class Pit implements Serializable, Countable{
    private static final long serialVersionUID = 9104279439192249962L;
    private int numStone;

    public Pit(){
        //Initializes a new pit.
        numStone = 0;
    }

    /**
     * Increment stones
     */
    @Override
    public void addStone(){
        //Adds a stone to the pit.
        numStone++;
    }

    /**
     * Adding stones to pit
     * @param int amount of stones adding
     */
    @Override
    public void addStones(final int amount){
        numStone += amount;
    }

    /**
     * Stone getter
     * @return int number of stones
     */
    @Override
    public int getStoneCount(){
        //Gets the number of stones in the pit.
        return numStone;
    }
    
    /**
     * Removing stones
     * @return int stones removed
     */
    @Override
    public int removeStones(){
        //Removes and returns the stones from the pit.
        final int removedStones = numStone;
        numStone = 0;
        return removedStones;
    }

    @Override
    public String toString(){
        return " ["+numStone+"] ";
    }

}