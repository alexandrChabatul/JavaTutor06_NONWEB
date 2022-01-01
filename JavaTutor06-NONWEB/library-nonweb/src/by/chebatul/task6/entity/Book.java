package by.chebatul.task6.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Book {

	String name;
	String author;
	BookType type;

}
