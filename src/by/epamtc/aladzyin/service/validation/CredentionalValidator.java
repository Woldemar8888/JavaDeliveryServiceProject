package by.epamtc.aladzyin.service.validation;

public class CredentionalValidator {
	
	public static boolean isCorrect( String login, String password )  {
		return isLogicCorrect(login)  && isPasswordCorrect(password);
	}
	
	private static boolean isLogicCorrect(String login) {
		if(login.length() <= 2) {
			return false;
		}
		return true;
	}
	
	private static boolean isPasswordCorrect(String password) {
		if(password.length() <= 2) {
			return false;
		}
		return true;
	}
	
	
}
