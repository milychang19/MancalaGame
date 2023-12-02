package mancala;

public class NoSuchPlayerException extends Exception{
    private static final long serialVersionUID = 7791337417655979794L;
    public NoSuchPlayerException(){
        super("player doesn't exist\n");
    }
    public NoSuchPlayerException(final String msg){
        super(msg);
    }

}