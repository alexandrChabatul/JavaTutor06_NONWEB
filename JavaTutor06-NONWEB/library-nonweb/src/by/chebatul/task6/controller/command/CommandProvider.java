package by.chebatul.task6.controller.command;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommandProvider {

	@Getter
	private static final Map<String, Command> commands = new HashMap<>();

	static {
		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("findBooksByName", new FindBookByNameCommand());
		commands.put("findBooksByAuthor", new FindBookByAuthorCommand());
		commands.put("findAllBooks", new FindAllBookCommand());
		commands.put("addBook", new AddBookCommand());
		commands.put("suggestBook", new SuggestBookCommand());
	}

}
