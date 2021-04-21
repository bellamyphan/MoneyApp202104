/*
 * Author: Bellamy Phan.
 * Date Created: 2020/12/07.
 */

public class SourceUnit {

	// Hold the information for one source.
	private int id;
	private String name;
	
	
	// Constructor.
	public SourceUnit(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	// Get ID.
	public int getID() {
		return id;
	}
	
	
	// Get name.
	public String getName() {
		return name;
	}
	
	
	// Set ID.
	public void setID(int id) {
		this.id = id;
	}
	
	
	// Set name.
	public void setName(String name) {
		this.name = name;
	}
	
	
	// toString
	@Override
	public String toString() {
		return Integer.toString(id) + " " + name;
	}
	
	
	
}
