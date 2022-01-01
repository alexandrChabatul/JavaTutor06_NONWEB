package by.chebatul.task6.controller.command;

import java.util.List;

import by.chebatul.task6.dto.BookDto;
import by.chebatul.task6.entity.Role;
import by.chebatul.task6.exception.ServiceException;
import by.chebatul.task6.service.RoleService;
import by.chebatul.task6.service.ServiceProvider;
import by.chebatul.task6.session.Session;

public class FindAllBookCommand implements Command {

	private static final String SUCCESS_MESSAGE = "Search results:";
	private static final String FAIL_MESSAGE = "Not found.";
	private static final String EXСEPTION_MESSAGE = "Connection error, please connect with administrator.";
	private static final String NULL_SESSION_MESSAGE = "This function is available only to users.";

	@Override
	public String execute(String[] params, Session session) {

		if (session.getCurrentUser() == null) {
			return NULL_SESSION_MESSAGE;
		}

		RoleService<String, BookDto> roleService;
		ServiceProvider serviceProvider = ServiceProvider.getInstance();

		roleService = session.getCurrentUser().getRole().equals(Role.USER) ? serviceProvider.getUserRoleService()
				: serviceProvider.getAdminRoleService();
		try {
			StringBuffer sb = new StringBuffer();
			List<BookDto> result = roleService.findAllBooks();
			if (result.size() > 0) {
				result.stream().forEach(s -> sb.append(s.toString() + System.lineSeparator()));
				return SUCCESS_MESSAGE + System.lineSeparator() + sb.toString();
			} else {
				return FAIL_MESSAGE;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			return EXСEPTION_MESSAGE;
		}
	}
}
