package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javafx.scene.image.Image;

public class Profile implements Comparable<Profile>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
	private String phone;
	private String town;
	private String zip;
	private String userName;
	private String password;
	private transient Image image;
	private LinkedList<Book> borrowedHistory;
	private TreeMap<String, Book> currentBorrowing;
	private String firstName;
	private String lastName;
	public static int bookCounter;
	private double fee = 0.0 ;
	private double borrowDate;
	private long returnDate;
	private String status;

	public Profile(String firstName, String lastName, String address, String phone, String town, String zip,
			Image image, String userName, String password, String status, LinkedList<Book> borrowedHistory) {
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
		this.borrowedHistory = borrowedHistory;
		this.currentBorrowing = new TreeMap<>();
		this.status = status;
		bookCounter = 0;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public TreeMap<String, Book> getCurrentBorrowing() {
		return currentBorrowing;
	}
	public void setFee(double f) {
		this.fee =Double.parseDouble(String.format("%10.2f", f));
	}
	public double getFee() {
		return Double.parseDouble(String.format("%10.2f", fee));
	}

	public void setBorrowedDate(double d) {
		this.borrowDate = d;
	}
	
	public double getBorrowedDate() {
		return borrowDate;
	}
	
	public void setReturnDate(long d) {
		this.returnDate = d;
	}
	public long getReturnDate() {
		return returnDate;
	}
	public LinkedList<Book> getBorrowedBooks() {
		return borrowedHistory;
	}

	public void addBorrowedBook(Book book) {
		borrowedHistory.add(book);
		bookCounter++;
	}

	public void removeBorrowedBook(Book book) {
		borrowedHistory.remove(book);
		bookCounter--;
	}

	public List<Book> setBorrowedBooks() {
		return borrowedHistory;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Profile [name=" + ", address=" + address + ", phone=" + phone + ", town=" + town + ", zip=" + zip
				+ ", userName=" + userName + ", password=" + password + ", image=" + image +"status:" + status+ "]";
	}

	@Override
	public int compareTo(Profile o) {
		return this.userName.compareTo(o.getUserName());
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

}
