package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mutter implements Serializable {
	private String userName;
	private String text;
	private Date time;
	private int id;

	public Mutter() {}
	public Mutter(int id, String userName, String text, Date time) {
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
	public Date getDate() {
		return time;
	}

}
