package de.dimetis.employee.common;

public class OperationFailedException extends Exception{
	private static final long serialVersionUID = 1L;

	public OperationFailedException(String message){
		super(message);
	}
	
	public OperationFailedException(String message, Throwable cause){
		super(message, cause);
	}
}
