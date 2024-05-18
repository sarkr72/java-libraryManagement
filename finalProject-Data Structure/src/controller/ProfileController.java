package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class ProfileController implements Initializable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag;
	private String userName = LogInController.getUserName();
	private Profile profile;
//	private TreeMap<String, Profile> profileTreeMap = CreateAccountController.getProfileTreeMap();
	private transient Image image;
    @FXML
    private TextField phoneField;
    
    @FXML
    private AnchorPane pane;
    

    @FXML
    private TextField lastNameF;

    @FXML
    private TextField firsrNameF;

	@FXML
	private TextArea addressArea;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField passwordField;

	@FXML
	private ImageView imageView;

	public void getUsername(String name) {
		userName = name;
	}
	
	@FXML
	void addProfilePic(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (file != null) {
			 image = new Image(file.toURI().toString());
			profile.setImage(image);
			imageView.setImage(profile.getImage());
			File file2 = new File("user " + userName+".png");
			BufferedImage bi = ImageIO.read(new File(file.getAbsolutePath()));
			ImageIO.write(bi, "png", file2);
		}
	}

	@FXML
	void goToLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoggedInScene2.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void editProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/EditProfileScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage)pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(new File("user " +userName+".png").exists()) {
//			BufferedImage bi = ImageIO.read(new File(userName + ".png"));
//			Image image2 = SwingFXUtils.toFXImage(bi,null);
			imageView.setImage(new Image("user " +userName+".png"));
		}else {
			imageView.setImage(new Image("default.png"));
		}
		profileBag = GetBags.getProfileBag();
		profile = profileBag.searchProfileByUserName(userName);
		if(profile != null) {
			addressArea.appendText("Address:\n\tStreet/Rd: " + profile.getAddress() + "\n\t" + "Town: " + profile.getTown()
					+ "\n\t" + "Zip code: " + profile.getZip());
			firsrNameF.setText(profile.getFirstName());
			lastNameF.setText(profile.getLastName());
			phoneField.setText(profile.getPhone());
			userNameField.setText(profile.getUserName());
			passwordField.setText(profile.getPassword());
			}
	}
}
