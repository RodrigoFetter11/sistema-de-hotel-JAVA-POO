package hotel.exception;

public class ClienteInvalidoException extends RuntimeException {

    public ClienteInvalidoException(String mensagem){
        super(mensagem);
    }
}
