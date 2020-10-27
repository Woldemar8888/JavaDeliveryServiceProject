package by.epamtc.aladzyin.controller;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider  {
	private Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("go_to_authorization_page", new GoToAuthorizationPageCommand());
		commands.put("go_to_registration_page", new GoToRegistrationPageCommand());
		commands.put("authorization", new Authorization());
		commands.put("registration", new Registration());
		commands.put("go_to_main_page", new GoToMainPageCommand());
		commands.put("change_locale", new ChangeLocale());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}

}
