package org.ecommerce.prices.application.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ENTITY_NOT_FOUND = "Entity not found";
    private static final String INTERNAL_ERROR = "Internal error";
    private static final String REQUIRED_PARAMS = "Bad Request. See required parameters";

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.OK, ex.getLocalizedMessage(), ENTITY_NOT_FOUND);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        ApiError apiError = new ApiError(HttpStatus.OK, ex.getLocalizedMessage(), INTERNAL_ERROR);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.OK, ex.getLocalizedMessage(), REQUIRED_PARAMS);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
