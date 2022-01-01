package by.chebatul.task6.service;

import java.util.List;

import by.chebatul.task6.annotation.MethodName;
import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.exception.ServiceException;

public class UserRoleService implements RoleService<String, BookDto> {

	private final BookService bookService = BookService.getInstance();

	@MethodName(name = "Найти книги по названию.")
	@Override
	public List<BookDto> findBooksByName(String name) throws ServiceException {
		return bookService.findBookByName(name);
	}

	@MethodName(name = "Найти книги по автору.")
	@Override
	public List<BookDto> findBooksByAuthor(String author) throws ServiceException {
		return bookService.findBookByAuthor(author);
	}

	@MethodName(name = "Найти все книги.")
	@Override
	public List<BookDto> findAllBooks() throws ServiceException {
		return bookService.findAllBooks();
	}

}
