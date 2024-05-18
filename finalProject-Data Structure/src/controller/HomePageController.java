package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AdminBag;
import model.BookBag;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class HomePageController implements Serializable, Initializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag;
	private BookBag bookBag;
	private AdminBag adminBag;
	private String userName;
	private transient Image image;
	@FXML
	private MenuItem item;

	@FXML
	private AnchorPane pane;
	private String adminUsername;
	private Image adminImage;

	@FXML
	void sceneChangeToApply(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
		Scene scene = new Scene(root, 850, 850);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void sceneChangeToLogIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LogInScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 400, 400);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToAdmin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void exit(ActionEvent event) throws IOException {
		File file = new File("BookBag.dat");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(bookBag);
		dos.close();
		fos.close();
		File file2 = new File("ProfileBag.dat");
		FileOutputStream fos2 = new FileOutputStream(file2);
		ObjectOutputStream dos2 = new ObjectOutputStream(fos2);
		  dos2.writeObject(profileBag);
		Profile profile = profileBag.searchProfileByUserName(userName);

//		 dos2.defaultWriteObject();
//		    dos2.writeObject(image == null ? null : image.getUrl());

		dos2.close();
		
		File file3 = new File("AdminBag.dat");
		FileOutputStream fos3 = new FileOutputStream(file3);
		ObjectOutputStream dos3 = new ObjectOutputStream(fos3);
		dos3.writeObject(adminBag);
//		Admin admin = adminBag.searchAdmin(adminUsername);
		dos3.close();
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookBag = GetBags.getBookBag();
		profileBag = GetBags.getProfileBag();
		userName = LogInController.getUserName();
		adminUsername = AdminLogInController.getUsername();
		adminBag = GetBags.getAdminBag();
	}

}
