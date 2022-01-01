package by.chebatul.task6.validator;

import by.chebatul.task6.exception.ServiceException;

public interface Validator<T> {

	ValidationResult isValid(T object) throws ServiceException;

}
