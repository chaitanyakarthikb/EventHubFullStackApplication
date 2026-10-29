package Eventhub.demo.Exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
