package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.AdminBag;
import model.GetBags;

public class AdminEditProfileC implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName = AdminLogInController.getUsername();
	private AdminBag adminBag = GetBags.getAdminBag();
	private Admin admin = adminBag.searchAdmin(userName);
	private String firstName;
	private String lastName;
	private String address;
	private String phones;
	private String town;
	private String zip;
	private String password;
	@FXML
	private TextField nameF;

	@FXML
	private TextField phoneF;

	@FXML
	private TextField streetF;

	@FXML
	private TextField townF;

	@FXML
	private TextField zipF;

	@FXML
	private Label zipLabel;

	@FXML
	private Label phone;
	@FXML
	private TextField usernameF;
	@FXML
	private TextField firstNameF;
	@FXML
	private TextField lastNameF;
	@FXML
	private Label userLabel;

	@FXML
	private TextField passwordF;

	@FXML
	private Label passwordLabel;

	@FXML
	void goToLogInScene(ActionEvent event) throws IOException {
		String check2 = "god";
//		userNameLabel.setText("");
//		passwordLabel.setText("");
//		phoneLabel.setText("");
//		zipLabel.setText("");
		firstName = firstNameF.getText();
		lastName = lastNameF.getText();
		address = streetF.getText();
		phones = phoneF.getText();
		town = townF.getText();
		zip = zipF.getText();
		password = passwordF.getText();

		int i = 0;
		int digit = 0;
		int letter = 0;

		int k = 0;

		if (firstName.length() != 0) {
			admin.setFirstName(firstName);
		}
		if (lastName.length() != 0) {
			admin.setFirstName(lastName);
		}
		if (address.length() != 0) {
			admin.setAddress(address);
		}
		if (town.length() != 0) {
			admin.setTown(town);
		}
		if (phones.length() != 0) {
			if (phones.length() != 10 || phones.matches(".*[a-z].*")) {
				check2 = "notGood";
				phone.setText("Enter 10 digits");
			} else {
				check2 = "god";
				admin.setPhone(phones);
			}
		}
		if (zip.length() != 0) {
			if (zip.matches(".*[a-z].*") || zip.length() != 5) {
				check2 = "notGood";
				zipLabel.setText("Only digits(5)");
			} else {
				check2 = "god";
				admin.setZip(zip);
			}
		}
		if (password.length() != 0) {
			check2 = "god";
			admin.setPassword(password);
		}
		int check = 0;
		if (check == 0) {
			if (check2.contentEquals("god")) {
				check = 1;
				changeSceneToadmin(event);
			} else {
//				changeToadminScene(event);
			}
		} else {
			goToLogInScene(event);
		}

	}

	private void changeSceneToadmin(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AdminProfileScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminProfileScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
