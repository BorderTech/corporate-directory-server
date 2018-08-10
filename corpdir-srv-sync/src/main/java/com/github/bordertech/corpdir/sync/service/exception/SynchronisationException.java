package com.github.bordertech.corpdir.sync.service.exception;

/**
 *
 * @author aswinkandula
 */
public class SynchronisationException extends RuntimeException {

	/**
	 * @param message the exception message
	 */
	public SynchronisationException(final String message) {
		super(message);
	}

	/**
	 * @param message the exception message
	 * @param e the original exception
	 */
	public SynchronisationException(final String message, final Throwable e) {
		super(message, e);
	}
}
