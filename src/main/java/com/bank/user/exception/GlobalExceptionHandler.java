package com.bank.user.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ErrorResponse getErrorResponse(String message, int statusCode) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setMessage(message);
		errorResponse.setStatusCode(statusCode);
		return errorResponse;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(UserNotFoundException e) {

		return new ResponseEntity<>(getErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> exceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getFieldErrors();

		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTime(LocalDateTime.now());
		validationErrorResponse.setMessage("Invalid input");
		validationErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

		fieldErrors.stream()
				.forEach(error -> validationErrorResponse.getErrors().put(error.getField(), error.getDefaultMessage()));
		return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

}
