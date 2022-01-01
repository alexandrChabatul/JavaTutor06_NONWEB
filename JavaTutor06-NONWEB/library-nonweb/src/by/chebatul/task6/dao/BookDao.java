package by.chebatul.task6.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.entity.Book;
import by.chebatul.task6.entity.BookType;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.util.PropertiesUtil;

public class BookDao implements Dao<Book> {

	private static final String BOOKS_PATH_KEY = "db.books.path";
	private static final String DELIMETR_PATH = "db.delimetr";

	@Override
	public boolean save(Book entity) throws DaoException {
		String delimetr = PropertiesUtil.get(DELIMETR_PATH);
		String book = "%s%s%s%s%s%s".formatted(entity.getName(), delimetr, entity.getAuthor(), delimetr,
				entity.getType(), System.getProperty("line.separator"));
		try {
			Files.write(Path.of(PropertiesUtil.get(BOOKS_PATH_KEY)), book.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	public List<BookDto> findByName(String name) throws DaoException {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(BOOKS_PATH_KEY)).toFile()))) {
			List<BookDto> books = new ArrayList<>();
			books = reader.lines().map(line -> line.split(PropertiesUtil.get(DELIMETR_PATH)))
					.filter(array -> array[0].toLowerCase().equals(name.toLowerCase())).map(s -> mapBookFromArray(s))
					.collect(Collectors.toList());
			return books;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public List<BookDto> findByAuthor(String author) throws DaoException {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(BOOKS_PATH_KEY)).toFile()))) {
			List<BookDto> books = new ArrayList<>();
			books = reader.lines().map(s -> s.split(PropertiesUtil.get(DELIMETR_PATH)))
					.filter(array -> array[1].toLowerCase().equals(author.toLowerCase())).map(s -> mapBookFromArray(s))
					.collect(Collectors.toList());
			return books;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public List<BookDto> findAllBooks() throws DaoException {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(BOOKS_PATH_KEY)).toFile()))) {
			List<BookDto> books = new ArrayList<>();
			books = reader.lines().map(s -> mapBookFromString(s)).collect(Collectors.toList());

			return books;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean delete(Book entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Book entity) {
		// TODO Auto-generated method stub

	}

	private BookDto mapBookFromString(String s) {
		var values = s.split(PropertiesUtil.get(DELIMETR_PATH));
		return BookDto.builder().name(values[0]).author(values[1]).type(BookType.valueOf(values[2])).build();
	}

	private BookDto mapBookFromArray(String[] s) {
		return BookDto.builder().name(s[0]).author(s[1]).type(BookType.valueOf(s[2])).build();
	}

}
