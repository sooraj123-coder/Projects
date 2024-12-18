package com.sooraj.BlogApplication.global;

import com.sooraj.BlogApplication.exceptions.ErrorResponseDTO;
import com.sooraj.BlogApplication.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> caughtResourceNotFoundException(Exception exception){
        ErrorResponseDTO errorResponse=new ErrorResponseDTO(exception.getClass().getSimpleName(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> caughtMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        //ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO(exception.getClass().getSimpleName(), exception.getMessage());
        Map<String,String> myNewHash=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            String errorFiled=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            myNewHash.put(errorFiled,errorMessage);
        });
        return new ResponseEntity<>(myNewHash,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> caughtGeneralException(Exception exception){
        ErrorResponseDTO errorResponseDTO= new ErrorResponseDTO(exception.getClass().getSimpleName(), exception.getMessage());
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
    }
}
