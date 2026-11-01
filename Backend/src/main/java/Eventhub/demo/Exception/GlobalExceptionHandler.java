package Eventhub.demo.Exception;

import Eventhub.demo.Utils.ErrorMessageBody;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEventNotFound(EventNotFoundException eventNotFoundException){
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error",eventNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String ,Object>> handleMethodArguementTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        assert methodArgumentTypeMismatchException.getRequiredType() != null;
        HashMap<String,Object> map = ErrorMessageBody.buildErrorMessageBody("error",String.format("Invalid Type for parameter %s , Expected %s.",methodArgumentTypeMismatchException.getName(),methodArgumentTypeMismatchException.getRequiredType().getSimpleName()));
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(map);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleFallbackError(Exception e){
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error","An Unexpected Error Occurred, Please try again");
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(map);
    }
}
