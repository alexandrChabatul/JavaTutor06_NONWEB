package by.chebatul.task6.mapper;

import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.entity.Book;

public class BookMapper implements Mapper<Book, BookDto> {

	private static final BookMapper INSTANCE = new BookMapper();

	@Override
	public BookDto mapFrom(Book object) {
		return BookDto.builder().name(object.getName()).author(object.getAuthor()).type(object.getType()).build();
	}

	public static BookMapper getInstance() {
		return INSTANCE;
	}

}
