package hotel.exception;

public class ReservaInvalidaException extends RuntimeException{

    public ReservaInvalidaException (String mensagem){
        super(mensagem);
    }
}