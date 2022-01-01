package by.chebatul.task6.entity;

import java.util.Arrays;
import java.util.Optional;

public enum BookType {
	
	PAPER,
	ELECTRONIC;
	
	public static Optional<BookType> find(String type){
		return Arrays.stream(values())
				.filter(n -> n.name().equals(type))
				.findFirst();
	}

}
