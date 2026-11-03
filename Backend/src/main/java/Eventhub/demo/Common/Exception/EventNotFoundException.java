package Eventhub.demo.Common.Exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
