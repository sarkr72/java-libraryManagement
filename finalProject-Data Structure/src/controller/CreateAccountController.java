package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

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
import model.Book;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class CreateAccountController implements Serializable, Initializable{

	//	private static TreeMap<String, Profile> profileTreeMap = new TreeMap<>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String town;
	private String zip;
	private String userName;
	private String password;
	private LinkedList<Book> borrowedBooks = new LinkedList<>();
	private transient Image image = null;
    @FXML
    private TextField firstNameF;

    @FXML
    private TextField lastNameF;
    
	@FXML
	private ImageView imageView;

	@FXML
	private Label passwordLabel;

	@FXML
	private Label userNameLabel;
	@FXML
	private Label zipLabel;

	@FXML
	private Label phoneLabel;

	@FXML
	private TextField addressField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField townField;

	@FXML
	private TextField zipField;

	@FXML
	private TextField emailAddField;
	@FXML
	private TextField passwordField;
    @FXML
    private Label picLabel;
	public void getUsername(String name) {
		 userName = name;
	}
	
	
	@FXML
	void addImage(ActionEvent event) throws IOException {
		if(emailAddField.getText().length() == 0) {
			picLabel.setText("enter userName!");
		}else {
			picLabel.setText("");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			image = new Image(file.toURI().toString());
			imageView.setImage(image);
			String str = "user " + emailAddField.getText() +".png";
			File file2 = new File(str);
			BufferedImage bi = ImageIO.read(new File(file.getAbsolutePath()));
			ImageIO.write(bi, "png", file2);
			}
		}
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
			address = addressField.getText();
			phone = phoneField.getText();
			town = townField.getText();
			zip = zipField.getText();
			userName = emailAddField.getText();
			password = passwordField.getText();
			if (phone.length() != 10 || phone.matches(".*[a-z].*")) {
				phoneLabel.setText("Enter 10 digits");
			} else if (zip.matches(".*[a-z].*") || zip.length() != 5) {
				zipLabel.setText("Only digits(5)");
			} else {
				if(userName.length() == 0) {
					userNameLabel.setText("provide a userName");
				}else if(password.length() == 0) {
					passwordLabel.setText("provide a password");
				}else if (profileBag.getProfileMap().containsKey(userName)) {
					userNameLabel.setText("username already in use");
				}else {
						check = 1;
						Profile profile = new Profile(firstName, lastName, address, phone, town, zip, image, userName, password, "Active",  borrowedBooks);
//						profile.setImage(image);
						System.out.println(profile);
						profileBag.addProfile(profile);
						changeSceneToLogIn(event);
			}
				}} else {
			applyForCard(event);
		}
	}

	public void changeSceneToLogIn(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LogInScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 400, 400);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	
	@FXML
	void goToHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/HomePageScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 400, 400);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		profileBag = GetBags.getProfileBag();
	}
}
