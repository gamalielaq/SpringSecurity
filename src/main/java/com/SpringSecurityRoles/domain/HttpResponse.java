package com.SpringSecurityRoles.domain;

import org.springframework.http.HttpStatus;

public class HttpResponse {
	private int code; // 200, 201, 400
	private HttpStatus httpStatus;
	private String reason;
	private String message;

	public HttpResponse() {};

	public HttpResponse(int code, HttpStatus httpStatus, String reason, String message) {
		super();
		this.code = code;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
