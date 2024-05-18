package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String town;
	private String zip;
	private String userName;
	private String password;
	private transient Image image;
	private LinkedList<Book> borrowedHistory;
	private TreeMap<String, Book> currentBorrowing;
	private static int bookCounter;
	private double fee = 0.0;

	public Admin(String firstName, String lastName, String address, String phone, String town, String zip,
			Image image, String userName, String password, List<Book> borrowedHistory) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.town = town;
		this.zip = zip;
		this.userName = userName;
		this.password = password;
		this.image = image;
		this.currentBorrowing = new TreeMap<>();
		this.borrowedHistory = new LinkedList<>();
		bookCounter = 0;
	}

	public TreeMap<String, Book> getCurrentBorrowing(){
		return currentBorrowing;
	}
	public int getBookCounter() {
		return bookCounter;
	}

	public void setFee(double f) {
		this.fee = Double.parseDouble(String.format("%10.2f", f));
	}

	public double getFee() {
		return Double.parseDouble(String.format("%10.2f", fee));
	}

	public LinkedList<Book> getborrowedHistory() {
		return borrowedHistory;
	}

	public void addborrowedHistory(Book book) {
		borrowedHistory.add(book);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Image getImage() {
//		return image;
//	}
//
//	public void setImage(Image image) {
//		this.image = image;
//	}

	@Override
	public String toString() {
		return "Admin [name=" + ", address=" + address + ", phone=" + phone + ", town=" + town + ", zip=" + zip
				+ ", userName=" + userName + ", password=" + password + "]";
	}
}
