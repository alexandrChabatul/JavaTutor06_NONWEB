package by.chebatul.task6.validator;

import by.chebatul.task6.dao.DaoProvider;
import by.chebatul.task6.dto.CreateUserDto;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.exception.ServiceException;

public class CreateUserValidator implements Validator<CreateUserDto> {

	private static final CreateUserValidator INSTANCE = new CreateUserValidator();

	private final DaoProvider daoProvider = DaoProvider.getInstance();

	private static final String EMAIL_PATTER = "[a-zA-Z]\\S*@[a-zA-Z]+.[a-zA-Z]+";

	@Override
	public ValidationResult isValid(CreateUserDto userDto) throws ServiceException {
		var validationResult = new ValidationResult();

		try {
			if (daoProvider.getUserDao().checkEmail(userDto.getEmail())) {
				validationResult.add(Error.of("Invalid.Email", "This email address already exists."));
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		if (!userDto.getEmail().matches(EMAIL_PATTER)) {
			validationResult.add(Error.of("Invalid.Email", "Email is invalid."));
		}

		if (userDto.getName().contains(" ")) {
			validationResult.add(Error.of("Invalid.Email", "Name cannot contains spaces."));
		}

		if (Role.find(userDto.getRole()).isEmpty()) {
			validationResult.add(Error.of("Invalid.Role", "Role is invalid."));
		}

		return validationResult;
	}

	public static CreateUserValidator getInstance() {
		return INSTANCE;
	}

}
