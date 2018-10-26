package com.shenzou.web.jdbc;

public class Todos {
	
	private int ID;
	private String description;
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Todos(int iD, String description) {
		super();
		ID = iD;
		this.description = description;
	}

	
	public Todos(String description) {
		super();
		this.description = description;
	}

	@Override
	public String toString() {
		return "Todos [ID=" + ID + ", description=" + description + "]";
	}
	
	
	
	
	

}
