//package com.nikhil.employeeDemo.employee_Management.exception;
//
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
//        ErrorResponse error = new ErrorResponse("Not Found", ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse error = new ErrorResponse("Internal Server Error", ex.getMessage());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
