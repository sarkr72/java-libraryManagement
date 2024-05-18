package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Admin;
import model.AdminBag;
import model.GetBags;
import model.Profile;

public class AdminProfileController implements Serializable, Initializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminBag adminBag;
	private String userName = AdminLogInController.getUsername();
	private transient Image image;
	@FXML
	private TextArea textArea;
	private Admin admin;

	public void getUsername(String name) {
		userName = name;
	}

	@FXML
	private ImageView imageView;

	@FXML
	void addImage(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			image = new Image(file.toURI().toString());
			imageView.setImage(image);
			String str = userName +".png";
			File file2 = new File(str);
			BufferedImage bi = ImageIO.read(new File(file.getAbsolutePath()));
			ImageIO.write(bi, "png", file2);
		}
	}
//	@FXML
//	void addProfilePic(ActionEvent event) throws FileNotFoundException {
//		FileChooser fileChooser = new FileChooser();
//
//		File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
//		if (file != null) {
//			 image = new Image(file.toURI().toString());
//			profile.setImage(image);
//			System.out.println(profile);
//			imageView.setImage(profile.getImage());
//		}
//	}

	@FXML
	void goToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void editProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminEditProfile.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(new File(userName + ".png").exists()) {
			imageView.setImage(new Image("admin " + userName + ".png"));
		}else {
			imageView.setImage(new Image("default.png"));
		}
		adminBag = GetBags.getAdminBag();
		admin = adminBag.searchAdmin(userName);
		if (admin != null) {
			textArea.appendText("FirstName: " + admin.getFirstName() + "\nLastName: " + admin.getLastName()
					+ "\nStreet/Rd: " + admin.getAddress() + "\nTown/City: " + admin.getTown() + "\tZip Code: "
					+ admin.getPhone() + "\nUsername: " + admin.getUserName() + "\nPassword: " + admin.getPassword());
		}
	}
}
