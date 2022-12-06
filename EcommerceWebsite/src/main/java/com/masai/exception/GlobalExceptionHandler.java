package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(LoginException le,WebRequest req)
	{
		MyErrorDetails error = new MyErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(le.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(ProductException pe,WebRequest req)
	{
		MyErrorDetails error = new MyErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(pe.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(UserException ue,WebRequest req)
	{
		MyErrorDetails error = new MyErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ue.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	
//	@ExceptionHandler(OrderException.class)
//	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(OrderException ce,WebRequest req)
//	{
//		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
//		error.setTimeStamp(LocalDateTime.now());
//		error.setMessage(ce.getMessage());
//		error.setDescription(req.getDescription(false));
//		
//		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
//	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(MethodArgumentNotValidException ve)
	{
		MyErrorDetails error = new MyErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ve.getMessage());
		error.setDescription(ve.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception ce,WebRequest req)
	{
		MyErrorDetails error = new MyErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
}
