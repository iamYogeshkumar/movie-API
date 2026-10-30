package com.movieflix.exception;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public ProblemDetail handleMovieNotFoundException(MovieNotFoundException exception) {

		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ProblemDetail handleFileNotFoundException(FileNotFoundException exception) {

		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(EmptyFileException.class)
	public ProblemDetail handleEmptyFileException(EmptyFileException exception) {

		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(FileExistsException.class)
	public ProblemDetail handleFileExistsException(FileExistsException exception) {

		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	

}
