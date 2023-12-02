package mancala;
import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private static final long serialVersionUID = 665760008981015651L;
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)
    private boolean bonus = false;

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        resetBoard();
    }

    public void gameEnd(){
        int pitStart;
        int playerStore;
        if(isSideEmpty(1)){//Check player 1's side
            pitStart = 7;
            playerStore = 2;
        }else{
            pitStart = 1;
            playerStore = 1;
        }

        for(int i = 0; i < 6; i++){
            gameBoard.addToStore(playerStore, gameBoard.removeStones(pitStart+i));
        }

    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    protected void setBonus(final boolean newBonus){
        bonus = newBonus;
    }
    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    /* default */ MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Get the store count
     *
     * @return store count
     */
    public int getStoreCount(final int playerNum){
        return gameBoard.getStoreCount(playerNum);
    }

    protected int getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * get the bonus instance variable
     *
     * @return boolean true or false, whether or not we're having a bonus turn
     */
    public boolean isBonus(){
        return bonus;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */ boolean isSideEmpty(final int pitNum){
        boolean empty = true;
        for(int i = 0; i < 6; i++){
            if(gameBoard.getNumStones(pitNum + i) != 0){
                empty = false;
            }
        }
        return empty;
        // This method can be implemented in the abstract class.
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
        final Store store1 = new Store();
        final Store store2 = new Store();

        one.setStore(store1);
        two.setStore(store2);
        store1.setOwner(one);
        store2.setOwner(two);
        gameBoard.setStore(store1, 1);
        gameBoard.setStore(store2, 2);
        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {

        final StringBuilder board = new StringBuilder();
        board.append("    12   11   10    9    8    7 \n   ");
        for(int i = 12; i > 6; i--){
            board.append(" ["+gameBoard.getNumStones(i)+"] ");
        }
        board.append("\n");
        board.append("["+gameBoard.getStoreCount(2)+"]");
        for(int i = 0; i < 30; i++){
            board.append(" ");
        }
        board.append("["+gameBoard.getStoreCount(1)+"]\n   ");
        
        for(int i = 1; i <= 6; i++){
            board.append(" ["+gameBoard.getNumStones(i)+"] ");
        }
        board.append("\n     1    2    3    4    5    6\n");
        return board.toString();
        // Implement toString() method logic here.
    }
}
