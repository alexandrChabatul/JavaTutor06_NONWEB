package by.chebatul.task6.dto;

import by.chebatul.task6.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {

	String email;
	String name;
	Role role;

}
