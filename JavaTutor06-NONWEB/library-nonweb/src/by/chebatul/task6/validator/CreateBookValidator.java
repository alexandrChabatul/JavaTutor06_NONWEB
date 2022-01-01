package by.chebatul.task6.validator;

import by.chebatul.task6.dto.CreateBookDto;
import by.chebatul.task6.entity.BookType;
import by.chebatul.task6.util.PropertiesUtil;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateBookValidator implements Validator<CreateBookDto> {

	private static final CreateBookValidator INSTANCE = new CreateBookValidator();
	private static final String DELIMETR_NAME = "db.delimetr";

	@Override
	public ValidationResult isValid(CreateBookDto object) {

		var validationResult = new ValidationResult();

		if (object.getName().contains(PropertiesUtil.get(DELIMETR_NAME))) {
			validationResult.add(Error.of("Invalid.Name",
					"Name is invalid. Please don't use %s!".formatted(PropertiesUtil.get(DELIMETR_NAME))));
		}

		if (object.getAuthor().contains(PropertiesUtil.get(DELIMETR_NAME))) {
			validationResult.add(Error.of("Author.Name",
					"Author is invalid. Please don't use %s!".formatted(PropertiesUtil.get(DELIMETR_NAME))));
		}

		if (BookType.find(object.getType()).isEmpty()) {
			validationResult.add(Error.of("Invalid.Type", "Type is invalid."));
		}

		return validationResult;
	}

	public static CreateBookValidator getInstance() {
		return INSTANCE;
	}

}
