package by.chebatul.task6.main;

import java.util.Scanner;

import by.chebatul.task6.controller.LibraryController;

public class Test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LibraryController controller = new LibraryController();

		String request = null;

		do {
			System.out.println("""
					PLEASE WRITE COMMAND.

					request form = action=param1---param2---param3
						login==email---password
						registration==email---name---password
						registration==email---name---password-role(only for admins)
						findBooksByName==bookName
						findBooksByAuthor==author
						findAllBooks
						addBook==name---author---type
						notification==name---author---type

					INPUT:
					""");
			request = sc.next();
			System.out.println();

			String responce = controller.doAction(request);

			System.out.println(responce);

			System.out.println();
		} while (!request.equals("exit"));

	}

}
