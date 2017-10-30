package drp.systemmgr.manager;

public class PasswordErrorException extends RuntimeException {
    public PasswordErrorException(){
    }
    public PasswordErrorException(String message){
        super(message);
    }
}
