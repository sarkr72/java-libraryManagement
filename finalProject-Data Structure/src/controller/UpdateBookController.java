package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;

public class UpdateBookController {

	private String title;
	private String isbn;
	private String firstName;
	private String lastName;
	private double price = 0.0;
	private Book book = AdminLoggedInController.editBook;
	
	 @FXML
	    private TextField firstNF;

	    @FXML
	    private TextField lastNF;

	    @FXML
	    private TextField titleF;

	    @FXML
	    private TextField isbnF;
	    @FXML
	    private Label label;
	    @FXML
	    void editBook(ActionEvent event) throws IOException {
	    	title = titleF.getText();
			isbn = isbnF.getText();
			firstName = firstNF.getText();
			lastName = lastNF.getText();
			
			if(isbn.matches(".*[a-z].*")) {
				label.setText("only digits");
			}else {
				if(title.length() != 0) {
					book.setTitles(title);
				}
				if(isbn.length() != 0) {
					book.setIsbn(isbn);
				}
				if(firstName.length() != 0) {
					book.setFirstName(firstName);
					book.getAuthor().setFirstName(firstName);
				}
				if(lastName.length() != 0) {
					book.setLastName(lastName);
					book.getAuthor().setLastName(lastName);
				}
				changeSceneToLoggedIn(event);
			}
	    }
	    
	    private void changeSceneToLoggedIn(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInScene.fxml"));
			Scene scene = new Scene(root, 700, 700);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setWidth(700);
			window.setHeight(700);
			window.setScene(scene);
			window.show();
		}
}
