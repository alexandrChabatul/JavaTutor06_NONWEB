package by.chebatul.task6.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
	
	String email;
	String name;
	String password;
	Role role;
	
}
