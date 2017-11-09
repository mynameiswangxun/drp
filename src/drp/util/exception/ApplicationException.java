package drp.util.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(){
        super();
    }
    public ApplicationException(String message){
        super(message);
    }
}
