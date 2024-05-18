package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashSet;

import controller.AdminLogInController;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class GetBags implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Utilities helper = new Utilities();
	private static AdminBag adminBag = new AdminBag();
	private static BookBag bookBag;
	private String adminUsername = AdminLogInController.getUsername();
	public static transient Image adminImage;
	{
		try {
			bookBag = new BookBag();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static ProfileBag profileBag;
	private static HashSet<String> dic;
	public static Image image;

	public void start1() throws Exception, ClassNotFoundException, EOFException {
		if (dic == null) {
			dic = helper.getDictionary();
		}
		if (new File("BookBag.dat").exists()) {
			File file = new File("BookBag.dat");
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream dis = new ObjectInputStream(fis);

			bookBag = (BookBag) (dis.readObject());
			dis.close();
			fis.close();
		} else {
			bookBag = helper.getBooks(5000);

		}
		if (new File("ProfileBag.dat").exists()) {
			File file2 = new File("ProfileBag.dat");
			FileInputStream fis2 = new FileInputStream(file2);
			ObjectInputStream dis2 = new ObjectInputStream(fis2);

			profileBag = (ProfileBag) (dis2.readObject());
			dis2.close();
			fis2.close();
		} else {
			profileBag = helper.getProfileBag();
		}
		if (new File("AdminBag.dat").exists()) {
			File file3 = new File("AdminBag.dat");
			FileInputStream fos3 = new FileInputStream(file3);
			ObjectInputStream dis3 = new ObjectInputStream(fos3);

			adminBag = (AdminBag) dis3.readObject();

			dis3.close();
			fos3.close();
		}
	}

	public static BookBag getBookBag() {
		return bookBag;
	}

	public static ProfileBag getProfileBag() {
		return profileBag;
	}

	public static AdminBag getAdminBag() {
		return adminBag;
	}
	public static HashSet<String> getDic(){
		return dic;
	}
//	public static void main(String[] args) {
//		launch(args);
//	}
}
