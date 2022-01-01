package by.chebatul.task6.mapper;

import by.chebatul.task6.dto.UserDto;
import by.chebatul.task6.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

	private static final UserMapper INSTANCE = new UserMapper();

	@Override
	public UserDto mapFrom(User object) {
		return UserDto.builder().email(object.getEmail()).name(object.getName()).role(object.getRole()).build();
	}

	public static UserMapper getInstance() {
		return INSTANCE;
	}

}
