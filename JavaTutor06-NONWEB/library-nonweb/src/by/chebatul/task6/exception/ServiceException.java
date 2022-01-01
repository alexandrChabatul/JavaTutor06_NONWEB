package by.chebatul.task6.exception;

public class ServiceException extends Exception{
	
	private static final long serialVersionUID = 2216807554052669103L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
