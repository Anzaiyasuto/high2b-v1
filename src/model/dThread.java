package model;

import java.sql.Timestamp;

public class dThread {
	private int id;
	private String dTitle;
	private Timestamp dTime;


	public dThread(int id, String dTitle, Timestamp dTime) {
		this.id = id;
		this.dTitle = dTitle;
		this.dTime = dTime;
	}

	public int getId() {return id;}
	public String getTitle() {return dTitle;}
	public Timestamp getData() {return dTime;}


	//private List<Mutter> mutterList = new ArrayList<Mutter>();
}