package model;

import java.util.List;

import dao.MutterDAO;

public class GetMutterListLogic {
	public List<Mutter> execute() {
		MutterDAO dao = new MutterDAO();
		List<Mutter> mutterList = dao.findall();
		return mutterList;
	}
}
