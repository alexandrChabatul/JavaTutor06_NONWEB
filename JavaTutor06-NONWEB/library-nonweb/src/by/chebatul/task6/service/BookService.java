package by.chebatul.task6.service;

import java.util.List;

import by.chebatul.task6.dao.DaoProvider;
import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.dto.CreateBookDto;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.exception.ValidationException;
import by.chebatul.task6.mapper.BookMapper;
import by.chebatul.task6.mapper.CreateBookMapper;
import by.chebatul.task6.validator.CreateBookValidator;

public class BookService {

	private static final BookService INSTANCE = new BookService();

	private final CreateBookValidator bookValidator = CreateBookValidator.getInstance();
	private final CreateBookMapper createBookMapper = CreateBookMapper.getInstance();
	private final BookMapper bookMapper = BookMapper.getInstance();
	private final DaoProvider daoProvider = DaoProvider.getInstance();

	public BookDto create(CreateBookDto bookDto) throws ServiceException {
		var validationResult = bookValidator.isValid(bookDto);
		if (!validationResult.isValid()) {
			throw new ValidationException(validationResult.getErrors());
		}
		var bookEntity = createBookMapper.mapFrom(bookDto);
		try {
			daoProvider.getBookDao().save(bookEntity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return bookMapper.mapFrom(bookEntity);
	}

	public List<BookDto> findBookByName(String name) throws ServiceException {
		try {
			return daoProvider.getBookDao().findByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<BookDto> findBookByAuthor(String author) throws ServiceException {
		try {
			return daoProvider.getBookDao().findByAuthor(author);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<BookDto> findAllBooks() throws ServiceException {
		try {
			return daoProvider.getBookDao().findAllBooks();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public static BookService getInstance() {
		return INSTANCE;
	}

}
