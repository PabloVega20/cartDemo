package demo.onebox.domain.exception;

import demo.onebox.domain.enums.TypeError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class HandlerExceptionController {
    private final static String MESSAGE = "message";
    private final static String ERROR = "error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        String codeError = generateErrorCode();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put(MESSAGE, TypeError.ERROR_GENERIC.getValue());
        body.put("path", request.getDescription(false));
        body.put("code",codeError);

        logError(codeError, ex);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(NotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(ERROR, ex.getCode());
        body.put(MESSAGE, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    private String generateErrorCode() {
        return UUID.randomUUID().toString();
    }

    private void logError(String codeError, Exception ex){
        log.error("\n \n ************************************** \n\nERROR CODE = {}\n\n MESSAGE = {}\n\n CAUSE = {}\n\n TRACE = {}\n\n **************************************\n",
                codeError, ex.getMessage(), ex.getCause(), Arrays.toString(ex.getStackTrace()));
    }
}
