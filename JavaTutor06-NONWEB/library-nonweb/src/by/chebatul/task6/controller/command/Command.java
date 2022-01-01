package by.chebatul.task6.controller.command;

import by.chebatul.task6.session.Session;

public interface Command {

	String execute(String[] params, Session session);

}
