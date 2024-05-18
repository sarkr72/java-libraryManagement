package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;

//import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public class Utilities implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Author[] author = emitAuthor("files/First Names.txt", "files/Last Names.txt");
	private Book[] bkArr = makeBooks();

	public HashSet<String> getDictionary() throws IOException{
		HashSet<String> dic = new HashSet<>();
		FileReader file = new FileReader("dictionary.txt");
		BufferedReader read = new BufferedReader(file);
		int i = 0;
		while(i < 99154) {
			dic.add(read.readLine().toLowerCase());
			i++;
		}
		read.close();
		return dic;
	}
	public ProfileBag getProfileBag() throws IOException {
		ProfileBag profileBag = new ProfileBag();
		Image image = new Image("default.png");
		int i = 0;
		while (i < 100) {
			LinkedList<Book> list = new LinkedList<>();
			int j = 0;
			String phone = "";
			String zip = "";
			while (j < 10) {
				if (j < 5) {
					zip += (int) (Math.random() * 9);
				}
				phone += (int) (Math.random() * 9);
				j++;
			}
			int st = (int)(Math.random() * 1000);
			Profile profile = new Profile(author[i].getFirstName(), author[i].getLastName(),
					st + author[i].getLastName() + " st", phone, "town" + i, zip, image, author[i].getFirstName().toLowerCase(),
					author[i].getLastName(), "Active", list);
			profileBag.addProfile(profile);
			i++;
		}
		return profileBag;
	}

	public BookBag getBooks(int nElems) throws IOException {
		BookBag bag = new BookBag();
		int i = 0;
		while (i < nElems) {
			int rand = (int) (Math.random() * bkArr.length);
			bag.insert(bkArr[rand]);
			i++;
		}
		return bag;
	}

	public Book[] makeBooks() {
		int i = 0;
		String[][] titleAndIsbn = emitTitleAndIsbn("files/textbook_titles.txt", "files/textbook_isbns.txt", 5000);
		Book[] bookArr = new Book[titleAndIsbn.length];
		while (i < titleAndIsbn.length) {
			bookArr[i] = new Book(titleAndIsbn[i][0], titleAndIsbn[i][1], getRandomAuthor(author), emitPrice(), null,
					null); // bag.getArray()[(int) // (Math.random()*nElems)
			i++;
		}
		return bookArr;
	}

	private Author[] emitAuthor(String fNameFiles, String lNameFiles) {
		Author[] allAuthors = null;
		try {
			FileReader fNameFile = new FileReader(fNameFiles);
			FileReader lNameFile = new FileReader(lNameFiles);
			BufferedReader fName = new BufferedReader(fNameFile);
			BufferedReader lName = new BufferedReader(lNameFile);

			String firstName = "";
			String lastName = "";

			allAuthors = new Author[2000];
			int count = 0;

			while (count < 2000) {
				firstName = fName.readLine();
				lastName = lName.readLine();
				allAuthors[count++] = new Author(firstName, lastName);
			}
			fName.close();
			lName.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allAuthors;// getRandomAuthor(allAuthors);
	}

	public Author getRandomAuthor(Author[] names) {
		int rand = (int) (Math.random() * names.length);
		Author author = names[rand];
		return author;
	}

	public String[][] emitTitleAndIsbn(String titleFileName, String isbnFileName, int nElems) {
		String[] titles = new String[nElems];
		String[] isbns = new String[nElems];
		String[][] titleAndIsbn = new String[nElems][2];
		try {
			FileReader titleFile = new FileReader(titleFileName);
			FileReader isbnFile = new FileReader(isbnFileName);
			BufferedReader bf1 = new BufferedReader(titleFile);
			BufferedReader bf2 = new BufferedReader(isbnFile);

			int i = 0;
			while (i < nElems) {
				titles[i] = bf1.readLine();
				isbns[i] = bf2.readLine();
				i++;
			}
			bf1.close();
			bf2.close();

			for (int j = 0; j < nElems; j++) {
				for (int k = 0; k < 2; k++) {
					if (k == 0) {
						titleAndIsbn[j][k] = titles[j];
					} else if (k == 1) {
						titleAndIsbn[j][k] = isbns[j];
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		while(i < nElems) {
////			titleAndIsbn[titleCounter++][0] = titleScan.nextLine();
//			titleAndIsbn[0][isbnCounter++] = isbnScan.nextLine();
//		}

		return titleAndIsbn;
	}
//	public static BookBag<Book> shallowCopy(BookBag<Book> bag){
//		BookBag<Book> copy = new BookBag<Book>(bag.size());
//		for(int i = 0; i < bag.size(); i++) {
//			copy = bag[i];
//		}
//	}

	public double emitPrice() {
		double rand = (double) (Math.random() * 500);
		double value = Double.parseDouble(String.format("%10.2f", rand));
		return value;
	}
}
