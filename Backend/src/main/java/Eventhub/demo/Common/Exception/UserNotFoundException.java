package Eventhub.demo.Common.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
