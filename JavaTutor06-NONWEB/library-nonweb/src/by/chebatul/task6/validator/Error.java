package by.chebatul.task6.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {

	String code;
	String message;

}