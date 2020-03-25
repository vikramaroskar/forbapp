package com.forb.forbapp.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ForbAppExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, InvalidOrderException.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();// "Flow with name already
												// exists";

		ErrorResource error = new ErrorResource(bodyOfResponse, "message string");
		// error.setFieldErrors(fieldErrorResources);

		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
