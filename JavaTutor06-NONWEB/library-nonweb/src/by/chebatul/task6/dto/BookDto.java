package by.chebatul.task6.dto;

import by.chebatul.task6.entity.BookType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

	String name;
	String author;
	BookType type;

}
