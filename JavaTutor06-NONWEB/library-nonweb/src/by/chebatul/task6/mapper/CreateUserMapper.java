package by.chebatul.task6.mapper;

import by.chebatul.task6.dto.CreateUserDto;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.entity.User;
import by.chebatul.task6.service.crypto.PasswordEncryptionService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

	private static final CreateUserMapper INSTANCE = new CreateUserMapper();
	private static final PasswordEncryptionService encryptor = PasswordEncryptionService.getInstance();

	@Override
	public User mapFrom(CreateUserDto object) {
		return User.builder().name(object.getName()).email(object.getEmail())
				.password(encryptor.getPassword(object.getPassword())).role(Role.valueOf(object.getRole())).build();
	}

	public static CreateUserMapper getInstance() {
		return INSTANCE;
	}

}
