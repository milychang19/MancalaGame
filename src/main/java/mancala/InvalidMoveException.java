package mancala;
public class InvalidMoveException extends Exception{
    private static final long serialVersionUID = 4053673262215290176L;
    public InvalidMoveException(){
        super("you can not move this spot\n");
    }
    public InvalidMoveException(final String msg){
        super(msg);
    }

}