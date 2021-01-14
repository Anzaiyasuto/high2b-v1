package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Mutter implements Serializable {
	private String userName;
	private String text;
	private Timestamp time;
	private int id;

	public Mutter() {}
	public Mutter(int id, String userName, String text, Timestamp time) {
		this.userName = userName;
		this.text = text;
		this.time = time;
		this.id = id;
	}

	public int getId() { return id;}
	public String getUserName() { return userName; }
	public String getText() { return text; }
	public String getTime() {
		String date;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd E HH:mm:ss");
		date = df.format(time).toString();
		return date;
	}
	//
	public Timestamp getDate() {
		//
		return time;
	}

}
