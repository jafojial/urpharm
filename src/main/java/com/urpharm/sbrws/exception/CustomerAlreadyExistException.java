/**
 * 
 */
package com.urpharm.sbrws.exception;

/**
 * @author JAFOJIAL
 *
 */
public class CustomerAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
