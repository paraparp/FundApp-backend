package com.paraparp.gestorfondos.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paraparp.gestorfondos.model.dto.ErrorDTO;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//404 : Not Found - EntityNotFoundException
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorDTO response = new ErrorDTO();

		response.setTimestamp(LocalDateTime.now());

		response.setMessage(ex.getLocalizedMessage());
		response.setHttpErrorCode(httpStatus.value());

		List<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());
		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause());
		response.setErrors(errors);

		response.setPath(request.getDescription(false));

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
	}

	// 409 : Conflict - DataIntegrityViolationException
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<?> resourceNotFoundException(DataIntegrityViolationException ex, WebRequest request) {
//		HttpStatus httpStatus = HttpStatus.CONFLICT;
//		ErrorDTO response = new ErrorDTO();
//
//		response.setTimestamp(LocalDateTime.now());
//
//		response.setMessage(ex.getMostSpecificCause().toString());
//		response.setHttpErrorCode(httpStatus.value());
//
//		List<String> errors = new ArrayList<>();
//		errors.add("Exception: " + ex.getClass());
//		errors.add("Caught by: " + ex.getClass());
//		errors.add("Throwed by: " + ex.getCause());
//		response.setErrors(errors);
//
//		response.setDescription("Referential integrity constraint violation");
//
//		response.setPath(request.getDescription(false));
//
//		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
//	}

	// 400 : Bad Request - ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();

		response.setTimestamp(LocalDateTime.now());

		response.setMessage(ex.getLocalizedMessage());
		response.setHttpErrorCode(httpStatus.value());

		List<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());
		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause());
		response.setErrors(errors);

		response.setPath(request.getDescription(false));

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders hh,
			HttpStatus hs, WebRequest wr) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();
		response.setTimestamp(LocalDateTime.now());
		response.setHttpErrorCode(httpStatus.value());

		response.setMessage(ex.getLocalizedMessage());

		if (ex.getLocalizedMessage().contains("enumerable")) {
			response.setDescription("Incorrect value");
		}

		ArrayList<String> errors = new ArrayList<>();
		errors.add("Exception: " + ex.getClass());
		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(DataIntegrityViolationException ex, HttpStatus hs, WebRequest wr) {

		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();

		response.setTimestamp(LocalDateTime.now());
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
	}

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

		// Map<String, String> fieldErrors =
		// ex.getBindingResult().getFieldErrors().stream().collect(
		// Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest wr) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorDTO response = new ErrorDTO();

		response.setTimestamp(LocalDateTime.now());
		ArrayList<String> errors = new ArrayList<>();

		errors.add("Exception: " + ex.getClass());
		response.setMessage(ex.getLocalizedMessage());

		errors.add("Caught by: " + ex.getClass());
		errors.add("Throwed by: " + ex.getCause().getClass());

		response.setHttpErrorCode(httpStatus.value());
		response.setErrors(errors);
		response.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));

		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);

	}

//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders hh, HttpStatus hs, WebRequest wr) {
//
//		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//		ErrorDTO response = new ErrorDTO();
//		response.setTimestamp(LocalDateTime.now());
//		response.setHttpErrorCode(httpStatus.value());
//
//		response.setMessage(ex.getLocalizedMessage());
//
//		if (ex.getLocalizedMessage().contains("enumerable")) {
//			response.setDescription("Incorrect value");
//		}
//	
//			response.setDescription(ex.getLocalizedMessage());
//		
//		ArrayList<String> errors = new ArrayList<>();
//		errors.add("Exception: " + ex.getClass());
//		errors.add("Caught by: " + ex.getClass());
//		errors.add("Throwed by: " + ex.getCause().getClass());
//
//		response.setErrors(errors);
//
//		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, wr);
//	
//	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		ErrorDTO response = new ErrorDTO();
//
//		response.setTimestamp(LocalDateTime.now());
//
//		response.setMessage(ex.getLocalizedMessage());
//		response.setHttpErrorCode(httpStatus.value());
//
//		List<String> errors = new ArrayList<>();
//		errors.add("Exception: " + ex.getClass());
//		errors.add("Caught by: " + ex.getClass());
//		errors.add("Throwed by: " + ex.getCause());
//		response.setErrors(errors);
//
//		response.setPath(request.getDescription(false));
//
//		return handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
//	}

}