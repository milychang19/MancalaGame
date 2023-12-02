package mancala;
import java.io.Serializable;

public class Store implements Serializable, Countable{
    private static final long serialVersionUID = 711811418438094236L;
    private Player owner;
    private int totalStone;

    public Store(){
        //Initializes a new store.
        totalStone = 0;
    }

    /**
     * Increment total stone
     */
    @Override
    public void addStone(){
        totalStone++;
    }

    /**
     * Add stones
     * @param int amount of stones added
     */
    @Override
    public void addStones(final int amount){
        //Adds stones to the store.
        totalStone += amount;
    }
    
    /**
     * Removing stones
     * @return int stones removes
     */
    @Override
    public int removeStones(){
        //Empties the store and returns the number of stones that were in it.
        final int stones = totalStone;
        totalStone = 0;
        return stones;
    }
    
    /**
     * Returning owner
     * @return player
     */
    public Player getOwner(){
        //Gets the owner of the store.
        return owner;
    }
    
    /**
     * stone count getter
     * @return int total stone
     */
    @Override
    public int getStoneCount(){
        //Gets the total number of stones in the store.
        return totalStone;
    
    }
    
    /**
     * owner setter
     * @param Player
     */
    public void setOwner(final Player player){
        //Sets the owner of the store.
        owner = player;
    }
    
    @Override
    public String toString(){
        return "["+totalStone+"]";
    }
}