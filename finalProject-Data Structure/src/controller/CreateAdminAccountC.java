package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Admin;
import model.AdminBag;
import model.Book;
import model.GetBags;

public class CreateAdminAccountC implements Serializable, Initializable {

	private AdminBag adminBag;
	private String address;
	private String phone;
	private String town;
	private String zip;
	private String userName = null;
	private String password;
//	private  TreeMap<String, LinkedList<Book>> borrowedBooks = new TreeMap<>();
//	private List<Book> borrowedBooks = new LinkedList<>();

	private transient Image image;
	@FXML
	private TextField lastNameF;

	@FXML
	private TextField firstNameF;
	@FXML
	private TextField passwordField;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField townField;

	@FXML
	private TextField streetField;

	@FXML
	private Label zipLabel;

	@FXML
	private Label phoneLabel;
	@FXML
	private TextField zipField;
	@FXML
	private Label userNameLabel;

	@FXML
	private Label passwordLabel;
    @FXML
    private Label picLabel;
	@FXML
	private ImageView imageView;
	private String firstName;
	private String lastName;

	@FXML
	void addImage(ActionEvent event) throws IOException {
		if(userNameField.getText().length() == 0) {
			picLabel.setText("enter userName first");
		}else {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			picLabel.setText("");
			image = new Image(file.toURI().toString());
			imageView.setImage(image);
			String str = "admin " + userNameField.getText() +".png";
			File file2 = new File(str);
			BufferedImage bi = ImageIO.read(new File(file.getAbsolutePath()));
			ImageIO.write(bi, "png", file2);
		}}
	}

	@FXML
	void applyForCard(ActionEvent event) throws IOException {
		int check = 0;
		if (check == 0) {
			userNameLabel.setText("");
			passwordLabel.setText("");
			phoneLabel.setText("");
			zipLabel.setText("");
			firstName = firstNameF.getText();
			lastName = lastNameF.getText();
			address = streetField.getText();
			phone = phoneField.getText();
			town = townField.getText();
			zip = zipField.getText();
			userName = userNameField.getText();
			password = passwordField.getText();
		
			if (phone.length() != 10 || phone.matches(".*[a-z].*")) {
				phoneLabel.setText("Enter 10 digits");
			} else if (zip.matches(".*[a-z].*") || zip.length() != 5) {
				zipLabel.setText("Only digits(5)");
			} else {
				if (userName.length() == 0) {
					userNameLabel.setText("provide a userName");
				} else if (password.length() == 0) {
					passwordLabel.setText("provide a password");
				} else if (adminBag.getAdminMap().containsKey(userName)) {
					userNameLabel.setText("username already in use");
				}else {
					userName = userName.toLowerCase();
					check = 1;
					Admin admin = new Admin(firstName, lastName, address, phone, town, zip, image, userName, password,
							new LinkedList<Book>());
					adminBag.insertAdmin(admin);
					changeSceneToLogIn(event);
				}
			}
		} else {
			applyForCard(event);
		}
	}
//				for (int j = 0; j < userName.length(); j++) {
//					if ((userName.charAt(j) + "").matches("^[a-zA-Z0-9]*$")) {
//						i++;
//						if (Character.isLetter(userName.charAt(j))) {
//							letter++;
//						}
//						if (Character.isDigit(userName.charAt(j))) {
//							digit++;
//						}
//					}
//				}
//				if (i < 5 || digit < 1 || letter < 1) {
//					userNameLabel.setText("UserName did not meet the requirements");
//				} else {
//					userName.toLowerCase();
//					password = passwordField.getText();
//					for (int j = 0; j < password.length(); j++) {
//						if ((password.charAt(j) + "").matches("^[a-zA-Z0-9]*$")) {
//							k++;
//						}
//					}
//					if (k < 5) {
//						passwordLabel.setText("Password did not meet the requirements");
//					} else if (adminBag.getAdminMap().containsKey(userName)) {
//						userNameLabel.setText("username already in use");
//					} else if (firstName.length() > 0 && lastName.length() > 0) {

	public void getUsername(String name) {
		userName = name;
	}

	public void changeSceneToLogIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AdminLogInScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);
//		Stage window = new Stage();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(750);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		adminBag = GetBags.getAdminBag();
	}
}
