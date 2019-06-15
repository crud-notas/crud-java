package com.esig.crudesig.exception.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private String type;
	private Date timestamp;
	private String message;
	private String details;
	
}
