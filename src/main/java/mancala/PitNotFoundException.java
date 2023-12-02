package mancala;

public class PitNotFoundException extends Exception{
    private static final long serialVersionUID = 7971644609546703985L;
    public PitNotFoundException(){
        super("the pit is out of bounds\n");
    }
    public PitNotFoundException(final String msg){
        super(msg);
    }
}