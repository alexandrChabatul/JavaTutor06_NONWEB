package by.chebatul.task6.controller.command;

import by.chebatul.task6.dto.CreateUserDto;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.exception.ValidationException;
import by.chebatul.task6.service.ServiceProvider;
import by.chebatul.task6.service.UserService;
import by.chebatul.task6.session.Session;

public class RegistrationCommand implements Command {

	private static final String REGISTRATION_SUCCESS_MESSAGE = "Registration was success. Now you can login.";
	private static final String REGISTRATION_FAIL_MESSAGE = "Error, please try again.";
	private static final String REGISTRATION_PARAM_FAIL_MESSAGE = "Wrong request form.";
	private static final String EXСEPTION_MESSAGE = "Connection error, please connect with administrator.";

	/*
	 * registration: - Standart registration - for all; - ADMIN registration - only
	 * for admins;
	 */

	@Override
	public String execute(String[] params, Session session) {
		String response;
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		if (params.length == 3) {
			try {
				boolean result = userService.registration(CreateUserDto.builder().email(params[0]).name(params[1])
						.password(params[2]).role(Role.USER.toString()).build());
				response = result ? REGISTRATION_SUCCESS_MESSAGE : REGISTRATION_FAIL_MESSAGE;
				return response;
			} catch (ValidationException e) {
				StringBuffer buffer = new StringBuffer();
				e.getErrors().stream().forEach(s -> buffer.append(s.getMessage() + System.lineSeparator()));
				return buffer.toString();
			} catch (ServiceException e) {
				return EXСEPTION_MESSAGE;
			}
		} else if (params.length == 4 && session.getCurrentUser() != null
				&& session.getCurrentUser().getRole().equals(Role.ADMIN)) {
			try {
				boolean result = userService.registration(CreateUserDto.builder().email(params[0]).name(params[1])
						.password(params[2]).role(params[3]).build());
				response = result ? REGISTRATION_SUCCESS_MESSAGE : REGISTRATION_FAIL_MESSAGE;
				return response;
			} catch (ValidationException e) {
				StringBuffer buffer = new StringBuffer();
				e.getErrors().stream().forEach(s -> buffer.append(s.getMessage() + System.lineSeparator()));
				return buffer.toString();
			} catch (ServiceException e) {
				return EXСEPTION_MESSAGE;
			}
		} else {
			return REGISTRATION_PARAM_FAIL_MESSAGE;
		}

	}

}
