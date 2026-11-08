package Eventhub.demo.Common.Utils;
import java.util.HashMap;

public class ErrorMessageBody {
    public static HashMap<String,Object> buildErrorMessageBody(String apiStatus, String errorMessage){
        HashMap<String, Object> errorObj = new HashMap<>();
        errorObj.put("apiStatus",apiStatus);
        errorObj.put("message",errorMessage);
        return errorObj;
    }
}
