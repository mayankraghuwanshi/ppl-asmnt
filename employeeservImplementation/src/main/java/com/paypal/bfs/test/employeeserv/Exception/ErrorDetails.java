package com.paypal.bfs.test.employeeserv.Exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String code;
	private Map<String,String> errors;

	public ErrorDetails(){
		timestamp = new Date();
	}
}
