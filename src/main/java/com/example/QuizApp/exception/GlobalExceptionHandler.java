package com.example.QuizApp.exception;

import com.example.QuizApp.responseStructure.ResponseStructure;
import com.example.QuizApp.service.QuestionService;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        logger.warn("Validation failed (MethodArgumentNotValidException): {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(org.springframework.web.method.annotation.HandlerMethodValidationException.class)
public ResponseEntity<ResponseStructure<Map<String, String>>> handleHandlerMethodValidationException(
        org.springframework.web.method.annotation.HandlerMethodValidationException ex) {
    logger.warn("Validation failed (HandlerMethodValidationException): {}", ex.getMessage());
    Map<String, String> errors = new HashMap<>();
    ex.getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });

    ResponseStructure<Map<String, String>> structure = new ResponseStructure<>();
    structure.setCode(HttpStatus.BAD_REQUEST.value());
    structure.setMsg("Validation failed");
    structure.setData(errors);

    return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
}


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        logger.warn("Constraint violation: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error ->
                errors.put(error.getPropertyPath().toString(), error.getMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNotFound(QuestionNotFoundException ex) {
        logger.error("Question not found during deletion: {}", ex.getMessage());
        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.NOT_FOUND.value());
        structure.setMsg(ex.getMessage());
        structure.setData("Deletion failed");
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleNotFound(CategoryNotFoundException ex) {
        logger.error("Category not found: {}", ex.getMessage());
        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.NOT_FOUND.value());
        structure.setMsg(ex.getMessage());
        structure.setData("Category not found");
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuestionNotFoundForUpdateException.class)
    public ResponseEntity<ResponseStructure<String>> handleNotFound(QuestionNotFoundForUpdateException ex) {
        logger.error("Question not found for update: {}", ex.getMessage());
        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setCode(HttpStatus.NOT_FOUND.value());
        structure.setMsg(ex.getMessage());
        structure.setData("Question not found for Update");
        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

}
