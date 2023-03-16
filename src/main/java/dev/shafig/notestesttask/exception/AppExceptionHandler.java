package dev.shafig.notestesttask.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = {PostAlreadyLikedException.class})
    public ResponseEntity<Object> handlePostAlreadyLikedException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT, ex.getLocalizedMessage(), "post already liked");
        logger.error(ex);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "resource not found");
        logger.error(ex);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(value = {InvalidCredentialException.class})
    public ResponseEntity<Object> handleInvalidCredentialException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "invalid credentials");
        logger.error(ex);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

    @ExceptionHandler(value = {UsernameAlreadyExistsException.class})
    public ResponseEntity<Object> handleUsernameAlreadyExistsException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT, ex.getLocalizedMessage(), "username already exists");
        logger.error(ex);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), errorMessage.getStatus());
    }

}