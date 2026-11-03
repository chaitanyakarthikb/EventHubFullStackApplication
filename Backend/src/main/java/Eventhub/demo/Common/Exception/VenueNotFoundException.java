package Eventhub.demo.Common.Exception;

public class VenueNotFoundException extends RuntimeException{
    public VenueNotFoundException(String message){
        super(message);
    }
}
