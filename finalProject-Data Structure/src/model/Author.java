package model;

import java.io.Serializable;

public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "firstName:" + firstName + ", lastName:" + lastName;
	}
}
