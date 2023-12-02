package mancala;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.Serializable;


public class MancalaGame implements Serializable{
    private static final long serialVersionUID = 2561433769755134472L;
    private GameRules board;
    private final ArrayList<Player> players;

    public MancalaGame(){ 
        //initialize the mancala game
        board = new KalahRules();
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
    }

    /**
     * GameRule getter
     * @return board
     */
    public GameRules getBoard(){
        //Gets the GameRules for the game.
        return board;
    }

    /**
     * Current player getter
     * @return Player 
     */
    public Player getCurrentPlayer(){ 
        //Gets the current player.
        return players.get(board.getCurrentPlayer()-1);
    }

    /**
     * Getting the number of stones in the pit
     * @param int pit number 1-12
     * @return int number of stones
     */
    public int getNumStones(final int pitNum)throws PitNotFoundException{
        //Gets the number of stones in a specific pit.
        return board.getNumStones(pitNum);

    }

    /**
     * Getting store count
     * @param Player
     * @return number of stones
     */
    public int getStoreCount(final Player player)throws NoSuchPlayerException{
        //Gets the total number of stones in a player's store.
        try{
            return player.getStoreCount();
        }catch(NoSuchElementException err){
            throw new NoSuchPlayerException(err.getMessage());
        }
    }

    /**
     * Getting winner of the game
     * @return player who won
     */
    public Player getWinner()throws GameNotOverException{
        //Gets the winner of the game.
        final int store1 = players.get(0).getStoreCount();
        final int store2 = players.get(1).getStoreCount();
        if(!isGameOver()){
            throw new GameNotOverException();
        }
        if(store1 == store2){
            return null;
        }

        Player winner = players.get(1);
        if(store1 > store2){
            winner = players.get(0);
        }

        //might not need this part??
        if(board instanceof AyoRules){
            players.get(0).getProfile().addAyoStat();
            players.get(1).getProfile().addAyoStat();
            winner.getProfile().addAyoWon();
        }else{
            players.get(0).getProfile().addKalahStat();
            players.get(1).getProfile().addKalahStat();
            winner.getProfile().addKalahWon();
        }
        return winner;
    }

    /**
     * checking if the game is over
     * @return boolean true for game over, false for game not over
     */
    public boolean isGameOver(){
        //Checks if the game is over.
        return board.isSideEmpty(1) || board.isSideEmpty(7);
    }

    /**
     * move stones
     * @param int starting pit
     * @return number of stones left in the pits
     */
    public int move(final int startPit)throws InvalidMoveException{
        //Makes a move for the current player.

        try{
            board.moveStones(startPit, board.getCurrentPlayer());
        }catch(InvalidMoveException err){
            throw new InvalidMoveException(err.getMessage());
        }

        int stonesLeft = 0;
        for(int i = 1; i <= 12; i++){
            stonesLeft += board.getNumStones(i);
        }

        if(!board.isBonus()){
            if(board.getCurrentPlayer() == 1){
                board.setPlayer(2);
            }else{
                board.setPlayer(1);
            }
        }

        return stonesLeft;
    }

    /**
     * game rue setting
     * @param GameRules game rule
     */
    public void setBoard(final GameRules theRules){
        //Sets the GameRules for the game.
        board = theRules;
    }

    /**
     * set current player
     * @param Player
     */
    public void setCurrentPlayer(final Player player){
        if(players.get(0).equals(player)){
            board.setPlayer(1);
        }else{
            board.setPlayer(2);
        }
    }
    
    /**
     * Setting players for game and sets them in the board
     * 
     * @param onePlayer first player of game
     * @param twoPlayer second player of game
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        //Sets the players for the game.
        players.set(0, onePlayer);
        players.set(1, twoPlayer);
        board.registerPlayers(onePlayer, twoPlayer);
        setCurrentPlayer(onePlayer);
    }

    /**
     * Setting players for game and sets them in the board
     *  
     */
    public void startNewGame(){
        //Starts a new game by resetting the GameRules.
        board.resetBoard();
    }
    @Override
    public String toString(){
        //Generates a string representation of the game.
        final StringBuilder game = new StringBuilder();
        game.append("\nPlayerA: "+ players.get(0));
        game.append("\nPlayerB: "+ players.get(1)+"\n");
        game.append(board);
        return game.toString();
    }
}