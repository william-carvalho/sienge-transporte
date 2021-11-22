package com.sienge.transporte.exception;

public class NotFoundException extends BusinessException {
	private static final long serialVersionUID = 7578156512280281572L;

	public NotFoundException() {
		super("no matches found");
	}
	
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
