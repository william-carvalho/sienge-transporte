package com.sienge.transporte.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 7578156512280281572L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
