package controller;

import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.AdminBag;
import model.GetBags;
import model.Profile;

public class AdminLogInController implements Initializable {

	private AdminBag adminBag;
	private static String userName;
	private Admin admin;
	private String password;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField passwordField;

	@FXML
	private Label userNameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	void logIn(ActionEvent event) throws IOException {
		userName = userNameField.getText();
		password = passwordField.getText();
		userName = userName.toLowerCase();
		Admin admin = adminBag.searchAdmin(userName);
//		System.out.println(admin);
		int check = 0;
		if (check == 0) {
			if (admin != null && admin.getPassword().compareTo(password) == 0) {
				check = 1;
				changeSceneToLoggedIn(event);
			} else {
				passwordLabel.setText("Password or Username did not match");
			}
		} else {
			logIn(event);
		}
	}

	private void changeSceneToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(650);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void createAccount(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/CreateAdminScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	public static String getUsername() {
		return userName;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		adminBag = GetBags.getAdminBag();
	}
}
