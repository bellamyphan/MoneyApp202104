/*
 * Author: Bellamy Phan.
 * Date Created: 2020/12/07.
 */

public class AccountUnit {
	
	// Hold the information for one account.
	private int id;
	private String name;
	private String type;
	private Time open;
	private Time close;
	
	
	// Constructor.
	public AccountUnit(int id, String name, String type, Time open, Time close) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.open = open;
		this.close = close;
	}
	
	
	// Get ID.
	public int getID() {
		return id;
	}
	
	
	// Get name.
	public String getName() {
		return name;
	}
	
	
	// Get type.
	public String getType() {
		return type;
	}
	
	
	// Get open.
	public Time getOpen() {
		return open;
	}
	
	
	// Get close.
	public Time getClose() {
		return close;
	}
	
	
	// Set ID.
	public void setID(int id) {
		this.id = id;
	}
	
	
	// Set name.
	public void setName(String name) {
		this.name = name;
	}
	
	
	// Set type.
	public void setType(String type) {
		this.type = type;
	}
	
	
	// Set open.
	public void setOpen(Time open) {
		this.open = open;
	}
	
	
	// Set close.
	public void setClose(Time close) {
		this.close = close;
	}
	
	
	// toString
	@Override
	public String toString() {
		String string = "";
		string += "ID: " + this.id + "\n";
		string += "Type: " + this.type + "\n";
		string += "Name: " + this.name + "\n";
		string += "Open date: " + this.open.toString() + "\n";
		
		// Check the close day
		if (this.close.getTime() == 0) {
			string += "Close date: Future.";
		} else {
			string += "Close date: " + this.close.toString();
		}
		return string;
	}
	
}
