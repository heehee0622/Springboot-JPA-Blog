package com.syshology.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.syshology.blog.dto.ResposeDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResposeDto<String>handleArgumentException(IllegalArgumentException e) {
		return new ResposeDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//		return "<h1>"+e.getMessage()+"</h1>";
	}
}
