package by.chebatul.task6.controller.command;

import by.chebatul.task6.session.Session;
import by.chebatul.task6.util.PropertiesUtil;

public class SuggestBookCommand implements Command {

	private static final String RESPONSE = """
			Send your suggest to this email: %s
			""";
	private static final String MAIL_PATH = "mail.user";

	@Override
	public String execute(String[] params, Session session) {
		return RESPONSE.formatted(PropertiesUtil.get(MAIL_PATH));
	}

}
