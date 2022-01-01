package by.chebatul.task6.service;

import java.util.List;
import by.chebatul.task6.annotation.MethodName;
import by.chebatul.task6.dao.DaoProvider;
import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.dto.CreateBookDto;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.service.mail.MailService;

public class AdminRoleService implements RoleService<String, BookDto> {

	private final BookService bookService = BookService.getInstance();
	private final MailService mailService = MailService.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();

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

	@MethodName(name = "Добавить книгу.")
	public BookDto addBook(CreateBookDto createDto) throws ServiceException {
		return bookService.create(createDto);
	}

	public void bookNotification(BookDto book) throws ServiceException {
		List<String> emails;
		try {
			emails = daoProvider.getUserDao().getAllUserEmails();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		mailService.sendEmail(emails, List.of(book));
	}

}
