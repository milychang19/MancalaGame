package ui;

// Import necessary Java Swing and AWT libraries for GUI components and layouts
import java.io.IOException;
// import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


// Import custom classes for to-do list logic and file handling
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import mancala.MancalaGame;
import mancala.UserProfile;
import mancala.Player;
import mancala.MancalaDataStructure;
import mancala.GameRules;
import mancala.AyoRules;
import mancala.KalahRules;
import mancala.Pit;
import mancala.Store;
import mancala.Saver;

public class GameUI extends JFrame {
    // Constants for window size
    public static final int WIDTH = 900;
    public static final int HEIGHT = 500;

    private MancalaGame game;
    private GameRules board;
    private MancalaDataStructure data;
    private Player playerA;
    private Player playerB;

    private JPanel gameContainer;
    private JLabel messageLabel;
    private JMenuBar menuBar;
    private PositionAwareButton[][] pits;
    private JButton store1;
    private JButton store2;
    //private JTextField usernameField;

    public GameUI(String title) {
        super(); 
        basicSetUp(title);
        setupGameContainer();
        add(gameContainer, BorderLayout.CENTER);
        add(startUpPanel(), BorderLayout.EAST);
        makeMenu();
        setJMenuBar(menuBar);
        pack();
    }

    private void basicSetUp(String title){
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private JPanel startUpPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(startOldGameButton());
        buttonPanel.add(startNewGameButton());
        buttonPanel.add(saveGameButton());
        buttonPanel.add(quitGameButton());
        return buttonPanel;
    }

    private JPanel makeMancalaBoard() {
        JPanel board = new JPanel();
        store1 = new JButton();
        store2 = new JButton();
        JPanel pitPanel = new JPanel();
        JPanel player1 = new JPanel();
        player1.setLayout(new FlowLayout());
        player1.add(new JLabel(playerA.getName()+"'s Side"));
        JPanel player2 = new JPanel();
        player2.setLayout(new FlowLayout());
        player2.add(new JLabel(playerB.getName()+"'s Side"));
        pits = new PositionAwareButton[2][6];
        pitPanel.setLayout(new GridLayout(2, 6));
        int iterator = 12;
        for(int x = 0; x < 6; x++) {
            final int pitNum = iterator;
            pits[0][x] = new PositionAwareButton();
            pits[0][x].addActionListener(e->moveStones(pitNum));
            pits[0][x].setAcross(x + 1);
            pits[0][x].setDown(1);
            pitPanel.add(pits[0][x]);
            iterator--;
        }
        iterator = 0;
        for(int x = 0; x < 6; x++){
            iterator++;
            final int pitNum = iterator;
            pits[1][x] = new PositionAwareButton();
            pits[1][x].addActionListener(e->moveStones(pitNum));
            pits[1][x].setAcross(x + 1);
            pits[1][x].setDown(2);
            pitPanel.add(pits[1][x]);
        }

        store1.setText(String.valueOf(playerStore(1)));
        store2.setText(String.valueOf(playerStore(2)));
        board.setLayout(new BorderLayout());
        board.add(player2, BorderLayout.NORTH);
        board.add(store1, BorderLayout.EAST);
        board.add(pitPanel, BorderLayout.CENTER);
        board.add(store2, BorderLayout.WEST);
        board.add(player1, BorderLayout.SOUTH);
        updateView();
        return board;
    }
    protected void updateView() {
        store1.setText(String.valueOf(playerStore(1)));
        store2.setText(String.valueOf(playerStore(2)));
        int iterator = 12;
        for(int x = 0; x < 6; x++) {
            final int pitNum = iterator;
            pits[0][x].setText(String.valueOf(pitNumStones(iterator)));
            iterator--;
        }
        iterator = 0;
        for(int x = 0; x < 6; x++){
            iterator++;
            final int pitNum = iterator;
            pits[1][x].setText(String.valueOf(pitNumStones(iterator)));
        }
        pack();
    }

    protected void moveStones(int pitNum){
        try{
            game.move(pitNum);
        }catch(InvalidMoveException err){
            sendMsg("Move Error!! "+err.getMessage());
        }
        updateView();
        checkGameState();
    }

    private void sendMsg(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }

    private void checkGameState() {
        if(game.isGameOver()){
            board.gameEnd();
            updateView();
            String congrats = "";
            String button;

            try{
                Player winner = game.getWinner();
                if(winner == null){
                    congrats = "Well done! It's a tie! Play Again?";
                }else{
                    congrats = winner.getName()+" Won!! YAYYYYY Play Again?";
                }   
            }catch(GameNotOverException err){
                sendMsg(err.getMessage());
            }
            button = "Play Again";
            int selection = 0;
            JOptionPane gameOver = new JOptionPane();
            selection = gameOver.showConfirmDialog(null,congrats,button,JOptionPane.YES_NO_OPTION); 
            if (selection == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                newGame(new KalahRules()); 
            }
        }
    }

    private int pitNumStones(int pitNum){
        return board.getNumStones(pitNum);
    }

    private int playerStore(int player){
        return board.getStoreCount(player);
    }

    private void makeMenu(){
        menuBar = new JMenuBar();
        JMenu rules = new JMenu("Mancala Rules");
        JMenuItem kalahSelect= new JMenuItem("Kalah Rule");
        kalahSelect.addActionListener(e -> newGame(new KalahRules()));
        JMenuItem ayoSelect= new JMenuItem("Ayo Rule");
        ayoSelect.addActionListener(e -> newGame(new AyoRules()));
        rules.add(kalahSelect);
        rules.add(ayoSelect);
        menuBar.add(rules);
    }

    private JPanel makeMenuPanel(){
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(startNewGameButton());
        menu.add(quitGameButton());
        menu.add(saveGameButton());
        return menu;
    }

    protected void newGame(GameRules rule) {
        gameContainer.removeAll();
        game = new MancalaGame();
        game.setBoard(rule);
        playerA = new Player("Emily");
        playerB = new Player("Austin");
        game.setPlayers(playerA,playerB);
        board = game.getBoard();
        gameContainer.add(makeMancalaBoard());
        pack();
    }

    protected void loadGame(){
        Saver saver = new Saver();
        try {
            game = (MancalaGame) saver.loadObject("mancalaGame.ser");
        } catch (IOException | ClassNotFoundException e) {
            sendMsg("An error occurred while loading UserProfile: " + e.getMessage());
        }
        updateView();
    }

    protected void saveGame(){
        Saver saver = new Saver();
        try{
            saver.saveObject(game, "mancalaGame.ser");
            sendMsg("MancalaGame saved successfully.");
        } catch (IOException e){
            sendMsg("An error occurred while saving Mancala Game: " + e.getMessage());
        }
        System.exit(0);
    }

    private JButton startNewGameButton() {
        JButton button = new JButton("New Mancala");
        button.addActionListener(e -> {
            newGame(new KalahRules());
        });
        return button;
    }
    private JButton startOldGameButton() {
        JButton button = new JButton("Resume Game");
        button.addActionListener(e->loadGame());
        return button;
    }
    private JButton quitGameButton(){
        JButton button = new JButton("Quit Game");
        button.addActionListener(e->System.exit(0));
        return button;
    }
    private JButton saveGameButton(){
        JButton button = new JButton("Save");
        button.addActionListener(e->saveGame());
        return button;
    }

    private JPanel startupMessage() {
        JPanel temp = new JPanel();
        temp.add(new JLabel("Welcome to Mancala Game!"));
        return temp;
    }
    
    public void setupGameContainer(){
        gameContainer.add(startupMessage());
    }

    public static void main(String[] args) {
        GameUI ui = new GameUI("MancalaGame");
        ui.setVisible(true);
    }

}