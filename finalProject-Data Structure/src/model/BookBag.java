package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;


import java.util.Map.Entry;

public class BookBag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<String, Book> bookTreeMap;
	private TreeMap<String, Book> bookMap;
	private int nElems;
	private LinkedList<Book> historyList;
	private TreeMap<String, Book> historyMap;
	
	public BookBag() throws IOException{
		bookTreeMap = new TreeMap<>();
		bookMap =  new TreeMap<>();
		historyList = new LinkedList<>();
		historyMap = new TreeMap<>();
		nElems = 0;
	}

	public int getNElems() {
		return nElems;
	}
	
	public LinkedList<Book> getHistoryList(){
		return historyList;
	}
	public TreeMap<String, Book> getHistoryMap(){
		return historyMap;
	}
	public TreeMap<String, Book> getBookMap2(){
		return bookMap;
	}
	
	public void insert(Book book) throws IOException{
		if(bookTreeMap.containsKey(book.getIsbn())) {
			bookMap.put(book.getAuthor().getLastName(), book);
		}else {
		bookTreeMap.put(book.getIsbn(), book);
		}
	}

	public Book[] searchByIsbn(String isbn) {
		Book[] available = new Book[2];
		int i = 0;
		Book book = null;
		if(bookTreeMap.containsKey(isbn)) {
			available[i++] = bookTreeMap.get(isbn);
			book = bookTreeMap.get(isbn);
			System.out.println(book);
		}
		if(book != null && bookMap.containsKey(book.getAuthor().getLastName())) {
			available[i++] = book;
		}
		return Arrays.copyOf(available, i);
	}
	
	public Book[] deleteByIsbn(String isbn) {
		Book[] available = new Book[2];
		int i = 0;
		Book book = null;
		if(bookTreeMap.containsKey(isbn)) {
			System.out.println(bookTreeMap.get(isbn));
			book = bookTreeMap.get(isbn);
			bookTreeMap.remove(isbn);
			available[i++] = book;
		}
		if(book != null && bookMap.containsKey(book.getAuthor().getLastName())){
			available[i++] = book;
			bookMap.remove(book.getAuthor().getLastName());
		}
		return Arrays.copyOf(available, i);
	}

	public Book[] searchByLastName(String lastName) {
		Book[] books = new Book[10];
		int i = 0;
		Book book = null;
		if(bookMap.containsKey(lastName)) {
			books[i++] = bookMap.get(lastName);
			book = bookMap.get(lastName);
		}
		if(book != null && bookTreeMap.containsKey(book.getIsbn())) {
			books[i++] = bookTreeMap.get(book.getIsbn());
		}
		return Arrays.copyOf(books, i);
	}
	
		public Book[] deleteByLastName(String lastName) {
			Book[] books = new Book[10];
			int i = 0;
			Book book = null;
			if(bookMap.containsKey(lastName)) {
				books[i++] = bookMap.get(lastName);
				book = bookMap.get(lastName);
				bookMap.remove(lastName);
			}
			if(bookTreeMap.containsKey(book.getIsbn())) {
				books[i++] = book;
				bookTreeMap.remove(book.getIsbn());
			}
			return Arrays.copyOf(books, i);
		}
		
		public Book[] srarchByTitle(String title) {
			Book[] books = new Book[100];
			int i = 0;
			for (Entry<String, Book> entry : bookTreeMap.entrySet()) {
			    Book value = entry.getValue();
			    if( value.getAuthor().getLastName().compareTo(title) == 0) {
			    	books[i++] = value;
			    	System.out.println("found");
			    }
			}
			return Arrays.copyOf(books, i);
		}
		
		public Book[] deleteByTitle(String title) {
			String[] keys = new String[100];
			Book[] books = new Book[100];
			int i = 0;
			for (Entry<String, Book> entry : bookTreeMap.entrySet()) {
			    Book value = entry.getValue();
			    if( value.getAuthor().getLastName().compareTo(title) == 0) {
			    	String key = entry.getKey();
			    	keys[i++] = key;
			    }
			}
			int count = 0;
			i=i-1;
			while(i >= 0) {
				String str = keys[i];
				books[count++] = bookTreeMap.get(str);
				bookTreeMap.remove(str);
				i--;
			}
			return Arrays.copyOf(books, count);
		}
		
		public TreeMap<String, Book> getBookMap(){
			return bookTreeMap;
		}
		
//	public Book[] deleteByIsbn(String isbn) {
//		Book[] deletedBooks = new Book[100];
//		int count = 0;
//		for(Book b: bookTreeMap) {
//			if(b.getIsbn().compareTo(isbn) == 0) {
//				deletedBooks[count++] = b;
//				bookTreeMap.remove(b);
//			}
//		}
//		return Arrays.copyOf(deletedBooks, count);
//	}
	

//	public Book binarySearchByISbn(Book book) {
//		int low = 0;
//		int high = nElems - 1;
////		int mid = 0;
//
//		while (high >= low) {
//			int mid = (low + high) / 2;
//			if (books[mid].getIsbn().compareTo(book.getIsbn()) > 0) {
//				high = mid - 1;
//			} else if (book.getIsbn().compareTo(books[mid].getIsbn()) == 0) {
//				return books[mid];
//			} else {
//				low = mid + 1;
//			}
//		}
//		return null;
//	}

//	public void bubbleSortByISBN() {
//		int out;
//		int in;
//		for (out = nElems - 1; out >= 1; out--) {
//			for (in = 0; in < out; in++) {
//				if (books[in].getIsbn().compareTo(books[in + 1].getIsbn()) > 0) {
//					Book temp = books[in + 1];
//					books[in + 1] = books[in];
//					books[in] = temp;
////					swap(books[in], books[in+1]);
//				}
//			}
//		}
//	}
//
//
//	private void swap(Book book, Book book2) {
//		Book temp = book;
//		book = book2;
//		book2 = temp;
//	}

	

//	public Book[] deleteByLastName(String lastName) {
//		int count = 0;
//
//		Book[] indices = new Book[nElems];
//
//		for (int i = 0; i < nElems - 1; i++) {
//			if (books[i].getAuthor().getLastName().compareTo(lastName) == 0) {
//				indices[count++] = books[i];
//				for (int j = i; j < nElems - 1; j++) {
//					books[j] = books[j + 1];
//				}
//			} else {
//				continue;
//			}
//			nElems--;
//		}
//
//		Book[] deletedBooks = Arrays.copyOf(indices, count);
//		return deletedBooks;
//		int i;
//		int[] indices = new int[nElems];
//		int count = 0;
//		for (i = 0; i < nElems; i++) {
//			if (books[i].getAuthor().getLastName().contentEquals(lastName)) {
//				indices[count++] = i;
//			}
//		}
//		Book[] temp = new Book[count];
//		for (int j = 0; j < count; j++) {
//			temp[j] = books[indices[j]];
//			System.out.println("indices" + indices[j]);
//		}
//		if (indices.length == 0) {
//			System.out.println("indices length" + indices.length);
//			return null;
//		} else {
//			for (int j = 0; j < count; j++) {
//				for (int k = indices[j]; k < nElems - 1; k++) {
//					books[k] = books[k + 1];
//				}
//				nElems--;
//			}
//			return temp;
//	}

//	public void selectionSort() {
//		// n^2 n*(n-1)
//		// for almost sorted n
//		int out;
//		int in;
//		int min;
//		for (out = 0; out < nElems - 1; out++) {
//			min = out;
//			for (in = out + 1; in < nElems; in++) {
//				if (books[in].getIsbn().compareTo(books[min].getIsbn()) < 0) {
//					min = in;
//				}
//			}
////					swap(out, min);
//			Book temp = books[min];
//			books[min] = books[out];
//			books[out] = temp;
//		}
//	}
//
//	public void insertionSort() {// (n*n/2) big o is n^2, if sorted or almost big o is n; n+2n
//									// most efficient because it does not swap like other sorts. it makes 2 copies
//									// instead.
//		int in;
//		int out;
//		for (out = 1; out < nElems; out++) {
//			Book temp = books[out];
//			in = out;
//			while (in > 0 && books[in - 1].getIsbn().compareTo(temp.getIsbn()) >= 0) {
//				books[in] = books[in - 1];
//				in--;
//			}
//			books[in] = temp;
//		}
//	}

//	public void display() {
//		for (Book b : treeSet) {
//			System.out.println(b);
//		}
//	}

//	public BookStore shallowCopy(int nElems) {
//		BookStore copy = new BookStore(nElems);
//		for (int i = 0; i < nElems; i++) {
//			copy.insert(books[i]);
//		}
//		return copy;
//	}

}
