package model;

import dao.MutterDAO;

public class PostMutterLogic {
	public void execute(Mutter mutter, int id) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter, id);
	}
}
