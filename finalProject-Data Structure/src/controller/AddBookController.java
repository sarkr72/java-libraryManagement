package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.stage.Stage;
import model.Author;
import model.Book;
import model.BookBag;
import model.GetBags;

public class AddBookController implements Initializable {

	private String title;
	private String isbn;
	private String firstName;
	private String lastName;
	private double price = 0.0;

	@FXML
	private TextField titleField;

	@FXML
	private TextField isbnField;

	@FXML
	private Label isbnLabel;

	@FXML
	private TextField firstNameFieldl;

	@FXML
	private TextField lastNameField;

	private BookBag bookBag;

	@FXML
	void goBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(650);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToHomeProfile(ActionEvent event) throws IOException {
		title = titleField.getText();
		isbn = isbnField.getText();
		firstName = firstNameFieldl.getText();
		lastName = lastNameField.getText();
		
		int check = 0;
		if (check == 0) {
			if (isbn.matches(".*[a-z].*")) {
				isbnLabel.setText("enter only digits");
			} else {
				bookBag.insert(new Book(title, isbn, new Author(firstName, lastName), price, LocalTime.now(), null));
				check = 1;
				goBack(event);
			}
		} else {
			goToHomeProfile(event);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bookBag = GetBags.getBookBag();
	}

}
