package com.hecj.common.exception;

/**
 * @ClassName: ServiceException
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
        super();
    }
	
	public ServiceException(String message) {
        super(message);
    }

	@Override
	public String getMessage() {
		return " - Service Exception - "+super.getMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

}
