package Eventhub.demo.Utils;
import java.util.HashMap;

public class ErrorMessageBody {
    private static final HashMap<String, Object> errorObj = new HashMap<>();
    public static HashMap<String,Object> buildErrorMessageBody(String apiStatus, String errorMessage){
        errorObj.put("apiStatus",apiStatus);
        errorObj.put("message",errorMessage);
        return errorObj;
    }
}
