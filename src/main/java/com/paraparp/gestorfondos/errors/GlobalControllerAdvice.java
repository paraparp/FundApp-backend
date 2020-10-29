package com.paraparp.gestorfondos.errors;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.paraparp.gestorfondos.errors.exceptions.GenericNotFoundException;
import com.paraparp.gestorfondos.errors.exceptions.NewUserWithDifferentPasswordsException;
import com.paraparp.gestorfondos.errors.exceptions.SearchNoResultException;

@RestControllerAdvice
public class GlobalControllerAdvice  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NewUserWithDifferentPasswordsException.class)
	public ResponseEntity<ApiError> handleNewUserErrors(Exception ex) {
		return buildErrorResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	
	@ExceptionHandler({GenericNotFoundException.class, SearchNoResultException.class})
	protected ResponseEntity<Object> handleEntityNotFound(GenericNotFoundException ex) {

		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	

	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError  apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
	
	private ResponseEntity<ApiError> buildErrorResponseEntity(HttpStatus status, String message) {
		return ResponseEntity.status(status)
					.body(ApiError.builder()
							.status(status)
							.message(message)
							.build());
					
	}
	
//	@ExceptionHandler(JsonMappingException.class)
//	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
//		
//		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
//	
//	}

}
