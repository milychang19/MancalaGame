package ui;

import mancala.MancalaGame;
import mancala.Player;
import mancala.GameRules;
import mancala.KalahRules;
import mancala.AyoRules;
import java.util.Scanner;
import mancala.InvalidMoveException;


public class TextUI{
    private Scanner scan;
    private MancalaGame game;
    private Player playerA;
    private Player playerB;

    public TextUI(){
        scan = new Scanner(System.in);
        game = new MancalaGame();
    }

    public void gameRule(){
        System.out.println("What game rule do you want to play?");
        System.out.println("1. Kalah Rule\n2. Ayo Rule");
        System.out.println("(input 1 or 2)");
        if(scan.nextInt() == 1){
            game.setBoard(new KalahRules());
        }else{
            game.setBoard(new AyoRules());
        }
    }

    public void getPlayerInput(){
        System.out.print("Enter PlayerA's name: ");
        //playerA = new Player(scan.nextLine());
        System.out.print("Enter PlayerB's name: ");
        //playerB = new Player(scan.nextLine());
        playerA = new Player("Emily");
        playerB = new Player("Austin");
        game.setPlayers(playerA, playerB);
    }

    public void startGame(){
        
        getPlayerInput();
        gameRule();
        System.out.println("Mancala Game Start!");
        
        while(!game.isGameOver()){
            System.out.println(game);
            run();
        }
    }

    public int run(){
        //set current player
        boolean valid = false;
        int stonesLeft = 0;
        while(!valid){
            try{
                System.out.println(game.getCurrentPlayer()+"'s turn!");
                System.out.print("Enter the position you want to move: ");
                stonesLeft = game.move(scan.nextInt());
                valid = true;
            }catch(InvalidMoveException err){
                valid = false;
                System.out.println(err.getMessage());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }   

        return stonesLeft;
    }

    // public void gameOver(int moveStones){
    //     game.gameEnding(moveStones);
    //     System.out.println(game);
    //     try{
    //         Player winner = game.getWinner();
    //         if(winner == null){
    //             System.out.println("We Have a Tied!!!");
    //         }else{
    //             System.out.println(winner+" Won!!!");
    //         }
    //     }catch(GameNotOverException err){
    //         System.out.println(err.getMessage());
    //     }
    // }

    public static void main(String[] args){
        TextUI ui = new TextUI();
        ui.startGame(); 
    }
}