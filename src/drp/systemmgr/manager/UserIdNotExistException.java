package drp.systemmgr.manager;

public class UserIdNotExistException extends RuntimeException{
    public UserIdNotExistException(){

    }
    public UserIdNotExistException(String message){
        super(message);
    }
}
