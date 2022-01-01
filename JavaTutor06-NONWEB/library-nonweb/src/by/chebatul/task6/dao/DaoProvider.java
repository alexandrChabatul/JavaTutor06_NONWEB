package by.chebatul.task6.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DaoProvider {

	private static final DaoProvider INSTANCE = new DaoProvider();

	private UserDao userDao = new UserDao();
	private BookDao bookDao = new BookDao();

	public static DaoProvider getInstance() {
		return INSTANCE;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
