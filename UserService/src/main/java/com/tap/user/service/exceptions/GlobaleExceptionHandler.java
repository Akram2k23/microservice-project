package com.tap.user.service.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tap.user.service.payload.ApiResponse;

@RestControllerAdvice //use to handle global exception
public class GlobaleExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) // use to tell about this exception(ResourceNotFoundException) to this method
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
//		ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build;
		ApiResponse response = new ApiResponse(message, true, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
		
		
		
	}

}
