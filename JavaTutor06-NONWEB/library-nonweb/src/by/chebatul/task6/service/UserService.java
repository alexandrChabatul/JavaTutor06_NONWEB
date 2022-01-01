package by.chebatul.task6.service;

import java.util.Base64;
import java.util.Optional;

import by.chebatul.task6.dao.DaoProvider;
import by.chebatul.task6.dao.UserDao;
import by.chebatul.task6.dto.CreateUserDto;
import by.chebatul.task6.dto.UserDto;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.exception.ValidationException;
import by.chebatul.task6.mapper.CreateUserMapper;
import by.chebatul.task6.mapper.UserMapper;
import by.chebatul.task6.service.crypto.PasswordEncryptionService;
import by.chebatul.task6.validator.CreateUserValidator;

public class UserService {

	private static final byte PASSWORD_LENGTH = 40;
	private static final byte PASSWORD_START = 0;

	private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();
	private final UserMapper userMapper = UserMapper.getInstance();
	private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
	private final PasswordEncryptionService encryptService = PasswordEncryptionService.getInstance();

	public Optional<UserDto> login(String email, String attempPassword) throws ServiceException {
		UserDao userDao = daoProvider.getUserDao();
		UserDto userDto = null;
		try {
			var user = userDao.findByEmail(email);
			if (user.isPresent()) {
				var userEntity = user.get();
				var pass = userEntity.getPassword().subSequence(PASSWORD_START, PASSWORD_LENGTH).toString();
				var salt = Base64.getDecoder().decode(userEntity.getPassword()
						.subSequence(PASSWORD_LENGTH, userEntity.getPassword().length()).toString());
				boolean rightPass = encryptService.authenticate(attempPassword, pass, salt);
				if (rightPass) {
					userDto = userMapper.mapFrom(userEntity);
				}
			}
			;
			return Optional.ofNullable(userDto);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public boolean registration(CreateUserDto createUserDto) throws ServiceException, ValidationException {
		var validationResult = createUserValidator.isValid(createUserDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
		var userEntity = createUserMapper.mapFrom(createUserDto);
		try {
			return daoProvider.getUserDao().save(userEntity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
