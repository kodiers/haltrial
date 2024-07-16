package com.tfl.haltrial.web.v1;

import com.tfl.haltrial.services.exceptions.CannotSendMessageException;
import com.tfl.haltrial.services.exceptions.InvalidRequestDataException;
import com.tfl.haltrial.services.exceptions.ObjectNotFoundException;
import com.tfl.haltrial.web.v1.dto.response.ErrorCode;
import com.tfl.haltrial.web.v1.dto.response.ErrorData;
import com.tfl.haltrial.web.v1.dto.response.ResponseDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private String getMessageForMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        if (!errors.isEmpty()) {
            return errors.stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining("; "));
        }
        return exception.getMessage();
    }

    private ResponseDto<ErrorData> makeErrorResponse(Exception e, ErrorCode code) {
        log.error("Error occurred while processing request: {}", e.getMessage(), e);
        ErrorData data = new ErrorData();
        data.setMessage(e.getMessage());
        data.setError(e.getClass().getSimpleName());
        data.setErrorCode(code);
        return new ResponseDto<>(false, data);
    }

    private ResponseDto<ErrorData> makeErrorResponse(MethodArgumentNotValidException exception) {
        log.error("Error occurred while processing request: {}", exception.getMessage(), exception);
        String message = getMessageForMethodArgumentNotValidException(exception);
        ErrorData data = new ErrorData();
        data.setMessage(message);
        data.setError(exception.getClass().getSimpleName());
        data.setErrorCode(ErrorCode.INVALID_REQUEST_DATA);
        return new ResponseDto<>(false, data);
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<ErrorData> handleObjectNotFoundException(ObjectNotFoundException exception) {
        return makeErrorResponse(exception, ErrorCode.OBJECT_NOT_FOUND);
    }

    @ExceptionHandler(value = CannotSendMessageException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseDto<ErrorData> handleCannotSendMessageException(CannotSendMessageException exception) {
        return makeErrorResponse(exception, ErrorCode.INVALID_OBJECT_STATE);
    }

    @ExceptionHandler(value = InvalidRequestDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<ErrorData> handleInvalidRequestDataException(InvalidRequestDataException exception) {
        return makeErrorResponse(exception, ErrorCode.INVALID_REQUEST_DATA);
    }

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<ErrorData> handleValidationException(ValidationException exception) {
        return makeErrorResponse(exception, ErrorCode.INVALID_REQUEST_DATA);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<ErrorData> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return makeErrorResponse(exception);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<ErrorData> handleAllExceptions(Exception exception) {
        return makeErrorResponse(exception, ErrorCode.UNKNOWN_ERROR);
    }




}
