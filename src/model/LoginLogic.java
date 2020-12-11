package model;

public class LoginLogic {
	public boolean execute(User user) {
		if(user.getName().replaceAll(" ", "").replaceAll("　", "").equals("")) {
			user.setName("名無し");
		}

		if(user.getPass().equals("abcd")) { return true; }
		return false;
	}
}
