package by.chebatul.task6.exception;

import java.util.List;

import by.chebatul.task6.validator.Error;
import lombok.Getter;

public class ValidationException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	@Getter
	private final List<Error> errors;

	public ValidationException(List<Error> list) {
		super();
		this.errors = list;
	}

}
