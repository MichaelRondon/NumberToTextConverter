package com.mfra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * A simple utility method to abstract boiler plate code used to
	 * generate error message
	 */
	private ErrorDetails generateErrorDetails(Exception ex, WebRequest request) {
		return new ErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
	}

	private ErrorDetails generateErrorDetails(WebRequest request,
			String message) {
		return new ErrorDetails(new Date(), message,
				request.getDescription(false));
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleNotCustomizedExceptions(Exception ex, WebRequest request) {
		log.error("Unhandled Error", ex);
		ErrorDetails errorDetails = generateErrorDetails(ex, request);
		ResponseEntity<ErrorDetails> errorResponse = new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

		if (ex.getClass().equals(IllegalArgumentException.class)) {
			errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
					ex.getMessage());
			errorResponse = new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
		}
		return errorResponse;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		String errorMessage = String.format("Error invoking the service. %s. Required input: %s",
				ex.getMostSpecificCause().getMessage(),
				ex.getRequiredType());
		return new ResponseEntity<>(generateErrorDetails(request, errorMessage),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDetails> handleConstraintViolationException(
			ConstraintViolationException ex, WebRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Some errors were found in the request:");
		ex.getConstraintViolations().forEach(violation -> {
			stringBuilder.append('\n');
			stringBuilder.append(violation.getMessage());
			stringBuilder.append(" Invalid value: ");
			stringBuilder.append(violation.getInvalidValue());
		});
		log.error("An error was encountered processing request", ex);
		return new ResponseEntity<>(generateErrorDetails(request, stringBuilder.toString()),
				HttpStatus.BAD_REQUEST);
	}

}
