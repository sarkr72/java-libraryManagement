package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class LogInController implements Serializable, Initializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag = GetBags.getProfileBag();
	private static String userName;
	private String password;

	@FXML
	private AnchorPane pane;
	@FXML
	private Label userNameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField passwordField;
	private Image image = GetBags.image;

	@FXML
	void logIn(ActionEvent event) throws IOException {

		userName = userNameField.getText();
		password = passwordField.getText();
		userName = userName.toLowerCase();
		Profile profile = profileBag.searchProfileByUserName(userName);

		int check = 0;
		if (check == 0) {
			if (profile != null) {
				if (profile.getStatus().compareTo("Suspended") != 0) {
					if (profile.getPassword().compareTo(password) != 0) {
						passwordLabel.setText("Password or Username did not match");
					} else {
						check = 1;
						changeSceneToLoggedIn(event);
					}
				} else {
					passwordLabel.setText("Your account is suspended");
				}
			} else {
				passwordLabel.setText("You are not registered");
			}
		} else {
			logIn(event);
		}
//			if (profileTreeMap.containsKey(userName)) {
//				if (profileTreeMap.get(userName).getPassword().compareTo(password) == 0) {
//					passwordLabel.setText("");
//					
//					changeSceneToLoggedIn(event);
//					
//				} else {
//					System.out.println("did not find");
//					passwordLabel.setText("Password or Username did not match");
//					logIn(event);
//				}
	}

	@FXML
	void goToHomePage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(650);
		window.setHeight(650);
		window.setScene(scene);
		window.show();
	}

	public void changeSceneToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoggedInScene2.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(800);
		window.setHeight(800);
		window.setScene(scene);
		window.show();
	}

	public static String getUserName() {
		return userName;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		profileBag 
	}
}
