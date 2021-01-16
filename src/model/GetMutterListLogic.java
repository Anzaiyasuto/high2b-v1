package model;

import java.util.List;

import dao.MutterDAO;

public class GetMutterListLogic {
	public List<Mutter> execute(int id) {
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findall(id);
		return mutterList;
	}
}
