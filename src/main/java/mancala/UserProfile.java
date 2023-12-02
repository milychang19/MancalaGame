package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable{
    private static final long serialVersionUID = 900888450055603254L;
    private String playerName;
    private int kalahPlayed;
    private int ayoPlayed;
    private int kalahWon;
    private int ayoWon;

    public UserProfile(){
        this("");
    }

    /**
     * Constructor
     * @param string name
     */
    public UserProfile(final String name){
        playerName = name;
        kalahPlayed = 0;
        ayoPlayed = 0;
        kalahWon = 0;
        ayoWon = 0;
    }

    /**
     * Saving serializable object
     * @param string name
     */
    public void setUser(final String name){
        playerName = name;
    }

    /**
     * user name getter
     * @return player name
     */
    public String getUser(){
        return playerName;
    }
    
    /**
     * increment kalah played
     * 
     */
    public void addKalahStat(){
        kalahPlayed++;
    }
    
    /**
     * Get kalah player
     * @return number of time played kalah
     */
    public int getKalahStat(){
        return kalahPlayed;
    }
    
    /**
     * increment ayo played
     */
    public void addAyoStat(){
        ayoPlayed++;
    }

    /**
     * ayo played getter
     * @return ayo played
     */
    public int getAyoStat(){
        return ayoPlayed;
    }
    
    /**
     * increment kalah won
     */
    public void addKalahWon(){
        kalahWon++;
    }

    /**
     * kalah won getter
     * @return kalah won
     */
    public int getKalahWon(){
        return kalahWon;
    }

    /**
     * Increment ayo won
     */
    public void addAyoWon(){
        ayoWon++;
    }

    /**
     * ayo won getter
     * @return ayo won
     */
    public int getAyoWon(){
        return ayoWon;
    }

    @Override
    public String toString(){
        final StringBuilder profile = new StringBuilder();
        profile.append("User Name: " + getUser());
        profile.append("\n# of KalahGamesPlayed = " + getKalahStat());
        profile.append("\n# of KalahGamesWon = " + getKalahWon());
        profile.append("\n# of AyoGamesPlayed = " + getAyoStat());
        profile.append("\n# of AyoGamesWon = " + getAyoWon());
        return profile.toString();
    }
}