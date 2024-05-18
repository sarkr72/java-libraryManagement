package model;

import java.io.Serializable;
import java.time.LocalTime;

public class Book implements Serializable, Comparable<Book>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titles;
	private String isbn;
	private Author author;
	private double price;
	private String firstName;
	private String lastName;
	private LocalTime borrowTime;
	private String returnTime;
	
	public Book(String titles, String isbn, Author author, double price, LocalTime borrowTime, String returnTime) {
		super();
		this.titles = titles;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
		this.firstName = author.getFirstName();
		this.lastName = author.getLastName();
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
	}

	public void setBorrowTime(LocalTime time) {
		this.borrowTime = time;
	}
	public void setReturnTime(String time) {
		this.returnTime = time;
	}
	public LocalTime getBorrowTime() {
		return borrowTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Book(String isbn) {
		this.isbn = isbn;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [" + "titles=" + titles + ", isbn=" + isbn + " " + author + ", price=" + price
				+ "] \n";
	}

	@Override
	public int compareTo(Book o) {
		return this.isbn.compareTo(o.getIsbn());
	}
}
