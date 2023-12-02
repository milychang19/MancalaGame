package mancala;
public class GameNotOverException extends Exception{
    private static final long serialVersionUID = -1894883535467494497L;
    public GameNotOverException(){
        super("game is not over");
    }
    public GameNotOverException(final String msg){
        super(msg);
    }
}
