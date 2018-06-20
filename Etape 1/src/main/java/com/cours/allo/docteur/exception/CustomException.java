package com.cours.allo.docteur.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

	public static final int UPDTAE_ERROR = 0;
	public static final int INSERT_ERROR = 1;
	public static final int FIND_ERROR = 2;
	public static final int CREATE_ERROR = 3;

	// code d'erreur
	private int code;

	public CustomException(int code) {
		super();
		this.code = code;
	}

	public CustomException(String message, int code) {
		super(message);
		this.code = code;
	}

	public CustomException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public CustomException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	// getter et setter
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}