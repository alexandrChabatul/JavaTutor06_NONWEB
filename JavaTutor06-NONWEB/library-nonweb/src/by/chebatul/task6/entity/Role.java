package by.chebatul.task6.entity;

import java.util.Arrays;
import java.util.Optional;


public enum Role {
	
	ADMIN,
	USER;
	
	public static Optional<Role> find(String role){
		return Arrays.stream(values())
				.filter(n -> n.name().equals(role))
				.findFirst();
	}

}
