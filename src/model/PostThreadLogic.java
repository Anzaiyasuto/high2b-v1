package model;

import dao.dThreadDAO;

public class PostThreadLogic {
	public void execute(dThread thread) {
		dThreadDAO dao = new dThreadDAO();
		dao.create(thread);
	}
}
