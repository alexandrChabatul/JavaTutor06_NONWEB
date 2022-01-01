package by.chebatul.task6.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.entity.User;
import by.chebatul.task6.exception.DaoException;
import by.chebatul.task6.util.PropertiesUtil;

public class UserDao implements Dao<User> {

	private static final String USERS_PATH_KEY = "db.users.path";
	private static final String DELIMETR_PATH = "db.delimetr";

	@Override
	public boolean save(User entity) throws DaoException {
		String delimetr = PropertiesUtil.get(DELIMETR_PATH);
		String user = "%s%s%s%s%s%s%s%s".formatted(entity.getEmail(), delimetr, entity.getName(), delimetr,
				entity.getPassword(), delimetr, entity.getRole(), System.getProperty("line.separator"));
		try {
			Files.write(Path.of(PropertiesUtil.get(USERS_PATH_KEY)), user.getBytes(), StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			return true;
		} catch (IOException e) {
			throw new DaoException(e);
		}

	}

	public Optional<User> findByEmail(String email) throws DaoException {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(USERS_PATH_KEY)).toFile()))) {
			Optional<String> userString = reader.lines().filter(line -> line.startsWith(email)).findFirst();

			return userString.map(line -> mapUserFromLine(line)).orElse(Optional.ofNullable(null));
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	private Optional<User> mapUserFromLine(String line) {
		var values = line.split(PropertiesUtil.get(DELIMETR_PATH));
		var user = User.builder().email(values[0]).name(values[1]).password(values[2]).role(Role.valueOf(values[3]))
				.build();
		return Optional.of(user);
	}

	public boolean checkEmail(String email) throws DaoException {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(USERS_PATH_KEY)).toFile()))) {
			return reader.lines().map(line -> line.split(PropertiesUtil.get(DELIMETR_PATH)))
					.filter(array -> array[0].equals(email)).findFirst().isPresent();
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	public List<String> getAllUserEmails() throws DaoException {
		List<String> emails = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader(Path.of(PropertiesUtil.get(USERS_PATH_KEY)).toFile()))) {
			emails = reader.lines().map(line -> line.split(PropertiesUtil.get(DELIMETR_PATH)))
					.filter(array -> array[3].equals(Role.USER.toString())).map(array -> array[0]).toList();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return emails;
	}

	@Override
	public boolean delete(User entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

}
