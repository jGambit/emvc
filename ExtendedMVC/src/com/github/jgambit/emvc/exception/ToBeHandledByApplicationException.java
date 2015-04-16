package com.github.jgambit.emvc.exception;

public class ToBeHandledByApplicationException extends Exception {

	private static final long serialVersionUID = -8587626150978648801L;
	
	public ToBeHandledByApplicationException(String message) {
		super(message);
	}
	
	public ToBeHandledByApplicationException(Throwable cause, String message) {
		super(message, cause);
	}
	
	public ToBeHandledByApplicationException(Throwable cause) {
		super(cause);
	}
	
}
