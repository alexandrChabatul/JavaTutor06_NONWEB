package by.chebatul.task6.controller;

import java.util.Map.Entry;
import by.chebatul.task6.controller.command.CommandProvider;
import by.chebatul.task6.session.Session;

public class LibraryController implements Controller {

	private static final String PARAMS_DELIMETR = "---";
	private static final String COMMAND_DELIMETR = "==";
	private static final String FAIL_MESSAGE = "Wrong command.";

	private final Session session = new Session();

	// request form = action=param1---param2---param3
	// DELIMETR=---
	// EXAMPLES:
	// + login==email---password
	// + registration==email---name---password
	// registration==email---name---password-role(only for admins)
	// + findBooksByName==bookName
	// + findBooksByAuthor==author
	// + findAllBooks
	// + addBook==name---author---type
	// notification==name---author---type

	@Override
	public String doAction(String request) {

		String[] requestArray = request.split(COMMAND_DELIMETR);

		String[] params = requestArray.length > 1 ? requestArray[1].split(PARAMS_DELIMETR) : new String[0];

		var command = CommandProvider.getCommands().entrySet().stream()
				.filter(entry -> entry.getKey().equals(requestArray[0])).map(Entry::getValue).findFirst();

		if (command.isPresent()) {
			return command.get().execute(params, session);
		} else {
			return FAIL_MESSAGE;
		}

	}

}
