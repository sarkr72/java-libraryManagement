package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class EditProfileController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag;
	private Profile profile;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String town;
	private String zip;
	private String userName;
	private String password;
	@FXML
	private TextField firstNameF;

	@FXML
    private TextField lastNameF;

	@FXML
	private TextField streetField;

	@FXML
	private TextField townField;

	@FXML
	private TextField zipField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField passwordField;

	@FXML
	private Label zipLabel;

	@FXML
	private Label phoneLabel;

	@FXML
	private Label passwordLabel;

	public void getProfileBag(ProfileBag bag) {
		profileBag = bag;
	}

	@FXML
	void changeToProfileScene(ActionEvent event) throws IOException {
		String check2 = "god";

//			userNameLabel.setText("");
//			passwordLabel.setText("");
//			phoneLabel.setText("");
//			zipLabel.setText("");

		firstName = firstNameF.getText();
		lastName = lastNameF.getText();
		address = streetField.getText();
		phone = phoneField.getText();
		town = townField.getText();
		zip = zipField.getText();
		password = passwordField.getText();

		int i = 0;
		int digit = 0;
		int letter = 0;

		int k = 0;

		if (firstName.length() != 0) {
			profile.setFirstName(firstName);
		}
		if (lastName.length() != 0) {
			profile.setlastName(lastName);
		}
		if (address.length() != 0) {
			profile.setAddress(address);
		}
		if (town.length() != 0) {
			System.out.println("3");
			profile.setTown(town);
		}
		if (phone.length() != 0) {
			System.out.println("4");
			if (phone.length() != 10 || phone.matches(".*[a-z].*")) {
				check2 = "notGood";
				phoneLabel.setText("Enter 10 digits");
				System.out.println("5");
			} else {
				check2 = "god";
				System.out.println("6");
				profile.setPhone(phone);
			}
		}
		if (zip.length() != 0) {
			System.out.println("7");
			if (zip.matches(".*[a-z].*") || zip.length() != 5) {
				check2 = "notGood";
				zipLabel.setText("Only digits(5)");
				System.out.println("8");
			} else {
				check2 = "god";
				profile.setZip(zip);
			}
		}
		if (password.length() != 0) {
				check2 = "god";
				profile.setPassword(password);
			}

		int check = 0;
		if (check == 0) {
			if (check2.contentEquals("god")) {
				check = 1;
				changeSceneToProfile(event);
			} else {
//				changeToProfileScene(event);
			}
		} else {
			changeToProfileScene(event);
		}

	}

	private void changeSceneToProfile(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ProfileScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		profileBag = GetBags.getProfileBag();
		userName = LogInController.getUserName();
		profile = profileBag.searchProfileByUserName(userName);
	}
}
