package hotel.exception;

public class FuncionarioInvalidoException extends RuntimeException{

    public FuncionarioInvalidoException(String mensagem){
        super(mensagem);
    }
}
