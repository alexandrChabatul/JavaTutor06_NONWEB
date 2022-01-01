package by.chebatul.task6.controller.command;

import java.util.Optional;
import by.chebatul.task6.dto.UserDto;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.service.ServiceProvider;
import by.chebatul.task6.service.UserService;
import by.chebatul.task6.session.Session;

public class LoginCommand implements Command {

	private static final String LOGIN_SUCCESS_MESSAGE = "Login was success. Hello %s!";
	private static final String LOGIN_FAIL_MESSAGE = "Sorry, invalid login or password.";
	private static final String LOGIN_PARAM_FAIL_MESSAGE = "Wrong request form.";
	private static final String EXСEPTION_MESSAGE = "Connection error, please connect with administrator.";

	@Override
	public String execute(String[] params, Session session) {
		if (params.length == 2) {
			ServiceProvider provider = ServiceProvider.getInstance();
			UserService userService = provider.getUserService();
			try {
				Optional<UserDto> potentialUser = userService.login(params[0], params[1]);
				if (potentialUser.isPresent()) {
					return loginSuccess(potentialUser.get(), session);
				} else {
					return loginFail();
				}
			} catch (ServiceException e) {
				e.printStackTrace();
				return EXСEPTION_MESSAGE;
			}
		} else {
			return LOGIN_PARAM_FAIL_MESSAGE;
		}
	}

	private String loginFail() {
		return LOGIN_FAIL_MESSAGE;
	}

	private String loginSuccess(UserDto userDto, Session session) {
		session.setCurrentUser(userDto);
		return LOGIN_SUCCESS_MESSAGE.formatted(userDto.getName());
	}

}
