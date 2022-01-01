package by.chebatul.task6.session;

import by.chebatul.task6.dto.UserDto;

public class Session {

	private UserDto currentUser;

	public Session() {
	}

	public UserDto getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDto currentUser) {
		this.currentUser = currentUser;
	}

}
