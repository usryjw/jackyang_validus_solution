package com.validus.jyang.music.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MusicRestExceptionHandler {

	// Add an exception handler for SongNotFoundException
	
	@ExceptionHandler
	public ResponseEntity<MusicErrorResponse> handleException(MusicNotFoundException exc) {
		
		// create SongErrorResponse
		
		MusicErrorResponse error = new MusicErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	// Add another exception handler ... to catch any exception (catch all)

	@ExceptionHandler
	public ResponseEntity<MusicErrorResponse> handleException(Exception exc) {
		
		// create SongErrorResponse
		
		MusicErrorResponse error = new MusicErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}





