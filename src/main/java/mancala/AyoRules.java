package mancala;


public class AyoRules extends GameRules{
   private final MancalaDataStructure data;
   private static final long serialVersionUID = 4681672042081338217L;

   public AyoRules(){
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
   public int moveStones(final int startPit, final int playerNum)throws InvalidMoveException{
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
   public int distributeStones(final int startingPoint){//throws PitNotFoundException{    
   //Helper method that distributes stones into pits and stores, skipping the opponent's store.
      int totalStones = 0;
      int stones = data.removeStones(startingPoint);

      final int playerNum = getCurrentPlayer();
      //set iterator
      data.setIterator(startingPoint, playerNum, true);
      Countable pit = new Pit();
      int pitNum = -1;

      do{
         totalStones += stones;
         for(int i = 0; i < stones; i++){
            pit = data.next();
            pitNum = data.getNum(pit);
            pit.addStone();
         }  

         if(pit.getStoneCount() == 1 || pitNum == 7 || pitNum == 14){
            stones = 0;
         }else{
            stones = pit.removeStones();
         }

      }while(stones != 0);

      if(pitNum < 7 && playerNum == 1){
         captureStones(pitNum);
      }else if(pitNum < 14 && pitNum > 7 && playerNum == 2){
         captureStones(pitNum - 1);
      }
      return totalStones;
   }

   /**
     * Capturing stones
     * @return int stones captured to stone
     */
   @Override
   public int captureStones(final int stoppingPoint){//throws PitNotFoundException{
      int captured = 0;
        final int playerNum = getCurrentPlayer();

        if(data.getNumStones(stoppingPoint) == 1 && data.getNumStones(13 - stoppingPoint) != 0){
            captured = data.removeStones(13 - stoppingPoint);
            data.addToStore(playerNum, captured);
        }
        //Captures stones from the opponent's pits.
        //System.out.println("Captured "+captured+" stone(s)");
        return captured;   
   }  
}