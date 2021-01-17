package model;

import dao.dThreadDAO;

public class GetThreadTitleLogic {
	public dThread execute(int id) {
		dThreadDAO dao = new dThreadDAO();
		dThread thread = dao.findTitle(id);
		return thread;
	}
}
