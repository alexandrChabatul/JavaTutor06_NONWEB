package by.chebatul.task6.mapper;

import by.chebatul.task6.dto.CreateBookDto;
import by.chebatul.task6.entity.Book;
import by.chebatul.task6.entity.BookType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBookMapper implements Mapper<CreateBookDto, Book> {

	private static final CreateBookMapper INSTANCE = new CreateBookMapper();

	@Override
	public Book mapFrom(CreateBookDto object) {
		return Book.builder().name(object.getName()).author(object.getAuthor()).type(BookType.valueOf(object.getType()))
				.build();
	}

	public static CreateBookMapper getInstance() {
		return INSTANCE;
	}

}
