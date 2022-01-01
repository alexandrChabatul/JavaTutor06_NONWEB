package by.chebatul.task6.validator;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ValidationResult {

	@Getter
	final List<Error> errors = new ArrayList<>();

	public void add(Error error) {
		this.errors.add(error);
	}

	public boolean isValid() {
		return this.errors.isEmpty();
	}

}
