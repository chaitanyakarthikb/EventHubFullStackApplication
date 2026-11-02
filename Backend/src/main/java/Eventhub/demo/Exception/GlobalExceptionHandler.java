package Eventhub.demo.Exception;

import Eventhub.demo.Utils.ErrorMessageBody;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEventNotFound(EventNotFoundException eventNotFoundException){
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error",eventNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleIllegalArgumentException(BadRequestException exception){
        log.error("IllegalArgumentException , error: {}",exception.getMessage());
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error",exception.getMessage());
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(map);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String ,Object>> handleMethodArguementTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        assert methodArgumentTypeMismatchException.getRequiredType() != null;
        log.error("MethodArgumentTypeMismatchException , error: {}",methodArgumentTypeMismatchException.getMessage());
        HashMap<String,Object> map = ErrorMessageBody.buildErrorMessageBody("error",String.format("Invalid Type for parameter %s , Expected %s.",methodArgumentTypeMismatchException.getName(),methodArgumentTypeMismatchException.getRequiredType().getSimpleName()));
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(map);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Map<String,Object>> handleUnexpectedTypeException(UnexpectedTypeException exception){
        log.error("UnexpectedTypeException , error: {}",exception.getMessage());
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error",String.format("Validation Failed due to the following " + exception.getMessage() ));
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.error("Validation failed , error: {}",exception.getMessage());

        Map<String, Object> body = ErrorMessageBody.buildErrorMessageBody("error", "Validation failed");
        body.put("errors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleFallbackError(Exception e){
        log.error("Something went wrong : {}",e.getMessage());
        HashMap<String,Object>map = ErrorMessageBody.buildErrorMessageBody("error","An Unexpected Error Occurred, Please try again");
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(map);
    }
}
