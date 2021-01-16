package model;

import java.util.List;

import dao.dThreadDAO;

public class GetThreadListLogic {
	public List<dThread> execute(){
		dThreadDAO dao = new dThreadDAO();
		List<dThread> threadList = dao.findall();
		return threadList;
	}
}
