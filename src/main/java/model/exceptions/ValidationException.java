package model.exceptions;

import java.util.HashMap;

public class ValidationException extends RuntimeException {

	private HashMap<String, String> errors = new HashMap<String, String>();
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public void setError(String field, String msg) {
		this.errors.put(field, msg);
	}
	
	public HashMap<String, String> getErrors() {
		return this.errors;
	}
	
}
