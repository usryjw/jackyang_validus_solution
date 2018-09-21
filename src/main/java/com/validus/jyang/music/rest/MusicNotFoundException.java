package com.validus.jyang.music.rest;

public class MusicNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MusicNotFoundException() {
	}

	public MusicNotFoundException(String message) {
		super(message);
	}

	public MusicNotFoundException(Throwable cause) {
		super(cause);
	}

	public MusicNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MusicNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
