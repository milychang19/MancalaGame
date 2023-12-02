package mancala;
public class KalahRules extends GameRules{
    private final MancalaDataStructure data;
    private static final long serialVersionUID = -2725686637044221879L;
    public KalahRules(){
        super();
        data = getDataStructure();
    }

    /**
     * Checking if the pit chosen by the player is valid
     * @param int starting pit number 1 to 12
     * @param int player number 1 or 2
     * @return int number of stones to store
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException{
    //Moves stones for the player starting from a specific pit.
        if(startPit <= 0 || startPit > 12){
            throw new InvalidMoveException("Unable to move because this location doesn't exist");
        }else if(startPit <= 6 && playerNum == 2){
            throw new InvalidMoveException("Player 2 may not move this spot");
        }else if(startPit > 6 && playerNum == 1) {
            throw new InvalidMoveException("Player 1 may not move this spot");
        }else if(data.getNumStones(startPit) == 0){
            //if move stones return 0, then you may not move
            throw new InvalidMoveException("This pit is empty, you may not move it");
        }
        int storeStones = data.getStoreCount(playerNum);

        distributeStones(startPit);

        storeStones = data.getStoreCount(playerNum) - storeStones;
        return storeStones;    //return stones stores to player store
    }


    /**
     * Distributing stones
     * @param int starting point
     * @return total of stones we're distributing
     */
    @Override
    protected int distributeStones(final int startingPoint){
    //Helper method that distributes stones into pits and stores, skipping the opponent's store.
        setBonus(false);
        final int stones = data.removeStones(startingPoint);
        final int playerNum = getCurrentPlayer();
        //set iterator
        data.setIterator(startingPoint, playerNum, false);
        //Countable pit;
        int pitNum = -1;

        for(int i = 0; i < stones; i++){
            //pit = data.next();
            pitNum = data.getNum(data.next());
            if(pitNum == 7 || pitNum == 14){
                data.addToStore(playerNum, 1);
            }else if(pitNum > 7){
                data.addStones(pitNum -1, 1);
            }else{
                data.addStones(pitNum, 1);
            }
            //pit.addStone();
        }
        
        if(pitNum == 7 || pitNum == 14){
            setBonus(true);
        }else if(pitNum < 7 && playerNum == 1){
            captureStones(pitNum);
        }else if(pitNum > 7 && playerNum == 2){
            captureStones(pitNum - 1);
        }
        return stones;
    }

    /**
     * Capturing stones
     * @return int stones captured to stone
     */
    @Override
    protected int captureStones(final int stoppingPoint){
        //Captures stones from the opponent's pits.
        int captured = 0;
        final int playerNum = getCurrentPlayer();

        if(data.getNumStones(stoppingPoint) == 1){
            captured = data.removeStones(13 - stoppingPoint);
            data.addToStore(playerNum, captured);
            captured ++;
            data.addToStore(playerNum, data.removeStones(stoppingPoint));
        }
        return captured;
    }
}
