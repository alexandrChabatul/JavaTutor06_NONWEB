package by.chebatul.task6.controller.command;

import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.dto.CreateBookDto;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.exception.ValidationException;
import by.chebatul.task6.service.AdminRoleService;
import by.chebatul.task6.service.ServiceProvider;
import by.chebatul.task6.session.Session;

public class AddBookCommand implements Command {

	private static final String SUCCESS_MESSAGE = "Success.";
	private static final String FAIL_MESSAGE = "Fail.";
	private static final String PARAM_FAIL_MESSAGE = "Wrong request form.";
	private static final String EXСEPTION_MESSAGE = "Connection error, please connect with administrator.";
	private static final String ACCESS_SESSION_MESSAGE = "This function is available only for admins.";

	@Override
	public String execute(String[] params, Session session) {

		if (session.getCurrentUser() == null || !session.getCurrentUser().getRole().equals(Role.ADMIN)) {
			return ACCESS_SESSION_MESSAGE;
		}
		if (params.length != 3) {
			return PARAM_FAIL_MESSAGE;
		}

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		AdminRoleService roleService = serviceProvider.getAdminRoleService();

		try {
			BookDto result = roleService
					.addBook(CreateBookDto.builder().name(params[0]).author(params[1]).type(params[2]).build());
			roleService.bookNotification(result);
			return SUCCESS_MESSAGE;
		} catch (ValidationException e) {
			StringBuffer buffer = new StringBuffer();
			e.getErrors().stream().forEach(s -> buffer.append(s.getMessage() + System.lineSeparator()));
			return FAIL_MESSAGE + System.lineSeparator() + buffer.toString();
		} catch (ServiceException e) {
			return EXСEPTION_MESSAGE;
		}
	}

}
