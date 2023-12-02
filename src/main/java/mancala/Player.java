package mancala;
import java.io.Serializable;
public class Player implements Serializable{
    private static final long serialVersionUID = -8851945913366135663L;
    private Store playerStore;
    private UserProfile user;

    public Player(){
        //Initializes a new player.
        this("");
    }

    /**
     * player constructor with name
     * @param String player name
     */
    public Player(final String name){
        //Initializes a new player with a name.
        user = new UserProfile(name);
    }

    /**
     * Player constructor with user profile as a parameter
     * @param userProfile profile
     * @return Player 
     */
    public Player(final UserProfile userData){
        //Initialize with an old user profile data
        user = userData;
    }

    /**
     * getting user profile
     * @return user profile
     */
    public UserProfile getProfile(){
        return user;
    }

    /**
     * getting user name
     * @return user name
     */
    public String getName(){
        //Gets the name of the player.
        return user.getUser();
    }

    /**
     * store getter
     * @return player store
     */
    public Store getStore(){
        //Gets the player's store where they collect stones.
        return playerStore;
    }
    
    /**
     * store count getter
     * @return int number of stone in store
     */
    public int getStoreCount(){
        //Gets the cound of the number of stones in the player's store where they collect stones.
        return playerStore.getStoneCount();
    }
    
    /**
     * name setter
     * @param String player name
     */
    public void setName(final String name){
        //Sets the player's name.
        user.setUser(name);
    }
    
    /**
     * store setting
     * @param Store player store
     */
    public void setStore(final Store store){
        //Sets the player's store.
        playerStore = store;
    }
    
    @Override
    public String toString(){
        return user.getUser();
    }

}