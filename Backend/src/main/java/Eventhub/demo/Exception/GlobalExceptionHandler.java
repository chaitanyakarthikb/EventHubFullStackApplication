package Eventhub.demo.Exception;

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
        HashMap<String,Object>map = new HashMap<>();
        map.put("apiStatus","error");
        map.put("message", eventNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String ,Object>> handleMethodArguementTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        HashMap<String , Object> map = new HashMap<>();
        map.put("apiStatus","error");
        map.put("message",String.format("The parameter '%s' must be a valid %s.",methodArgumentTypeMismatchException.getName(),methodArgumentTypeMismatchException.getRequiredType().getSimpleName()));
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(map);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleFallbackError(Exception e){
        HashMap<String,Object>map = new HashMap<>();
        map.put("apiStatus","error");
        map.put("message",e.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(map);
    }
}
