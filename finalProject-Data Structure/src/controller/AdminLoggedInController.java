package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Book;
import model.BookBag;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class AdminLoggedInController implements Initializable {

	private AdminLogInController ac = new AdminLogInController();
	private ProfileBag profileBag = GetBags.getProfileBag();
	private BookBag bookBag = GetBags.getBookBag();;
	private String username = LogInController.getUserName();
	private TreeMap<String, Book> bookMap = bookBag.getBookMap();
	public static Book editBook;

	@FXML
	private Button status;
	@FXML
	private Label tableLabel;
	@FXML
	private AnchorPane pane;
	@FXML
	private TableView<Object> tableView;

	@FXML
	private TableColumn<Object, String> column1;

	@FXML
	private TableColumn<Object, String> column2;

	@FXML
	private TableColumn<Object, Object> column3;
	@FXML
	private Button editB;
	@FXML
	private Label label;

	@FXML
	private Label label2;
	@FXML
	private TextField searchUser;
	@FXML
	private Button overDueBtn;
	@FXML
	private Label dueLabel;
	@FXML
	private MenuItem userItem;
	@FXML
	private ContextMenu contextMenu;
    @FXML
    private TableColumn<Object, String> c4;

	@FXML
	void addUser(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void deleteUser(ActionEvent event) {
		status.setVisible(false);
		searchUser.setVisible(false);
		overDueBtn.setVisible(false);
		userItem.setVisible(false);
		tableLabel.setText("");
		dueLabel.setText("");
		label2.setText("");
		label.setText("");
		Object item = tableView.getSelectionModel().getSelectedItem();
		if (item == null) {
			label2.setText("select an user");
		} else if (bookBag.getBookMap().containsValue(item) || profileBag.getHistoryMap().containsValue(item)) {
			label2.setText("select an user");
		} else {
			Profile profile = (Profile) item;
			if (profile != null) {
				label2.setText("");
				profileBag.deleteProfileByUserName(profile.getUserName());
//				tableView.getItems().remove(profile);
				ObservableList<Object> profileList = FXCollections
						.observableArrayList(profileBag.getProfileMap().values());
				tableView.setItems(profileList);
			}
		}
	}

	@FXML
	void deleteBook(ActionEvent event) throws IOException {
		status.setVisible(false);
		searchUser.setVisible(false);
		overDueBtn.setVisible(false);
		userItem.setVisible(false);
		tableLabel.setText("");
		dueLabel.setText("");
		label2.setText("");
		label.setText("");
		Object item = tableView.getSelectionModel().getSelectedItem();
		if (item != null && profileBag.getProfileMap().containsValue(item)) {
			label.setText("select a book");
		} else {
			Book book = (Book) item;
			if (book != null) {
				if (profileBag != null && profileBag.getHistoryList() != null) {
					if (!profileBag.getHistoryList().contains(book)) {
						System.out.println("bye");
						label.setText("");
						bookBag.deleteByIsbn(book.getIsbn());
						tableView.getItems().remove(book);
					} else {
						label.setText("Book isn't returned");
					}
				} else {
					label.setText("");
					bookBag.deleteByIsbn(book.getIsbn());
					tableView.getItems().remove(book);
				}
			} else {
				label.setText("View books and select first");
			}
		}
	}

	@FXML
	void issueBook(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminSearchBook.fxml"));
		Scene scene = new Scene(root, 900, 800);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void issuedBooks(ActionEvent event) throws IOException {
		status.setVisible(false);
		searchUser.setVisible(false);
		overDueBtn.setVisible(false);
		userItem.setVisible(false);
		dueLabel.setText("");
		label2.setText("");
		label.setText("");
		tableLabel.setText("");
		column1.setText("Title");
		column2.setText("Isbn");
		column3.setText("author");
		column1.setCellValueFactory(new PropertyValueFactory<Object, String>("titles"));
		column2.setCellValueFactory(new PropertyValueFactory<Object, String>("isbn"));
		column3.setCellValueFactory(new PropertyValueFactory<Object, Object>("author"));
		c4.setText("");
		c4.setCellValueFactory(null);
		if (profileBag.getHistoryList() != null) {
			ObservableList<Object> list = FXCollections.observableArrayList(profileBag.getHistoryList());
			tableView.setItems(list);
		}
	}

	@FXML
	void viewBooks(ActionEvent event) {
		status.setVisible(false);
		searchUser.setVisible(false);
		overDueBtn.setVisible(false);
		userItem.setVisible(false);
		label2.setText("");
		label.setText("");
		tableLabel.setText("");
		dueLabel.setText("");
		column1.setText("Title");
		column2.setText("Isbn");
		column3.setText("author");
		column1.setCellValueFactory(new PropertyValueFactory<Object, String>("titles"));
		column2.setCellValueFactory(new PropertyValueFactory<Object, String>("isbn"));
		column3.setCellValueFactory(new PropertyValueFactory<Object, Object>("author"));
		c4.setText("");
		c4.setCellValueFactory(null);
		ObservableList<Object> list = FXCollections.observableArrayList(bookBag.getBookMap2().values());
		tableView.setItems(list);
	}

	@FXML
	void suspendAccount(ActionEvent event) {
		ObservableList<Object> list = FXCollections.observableArrayList(GetBags.getProfileBag().getProfileMap().values());
		status.setVisible(true);
		column1.setText("Username");
		column2.setText("Phone");
		column3.setPrefWidth(120);
		column3.setText("Borrowed Books:");
		c4.setText("Status");
		column1.setCellValueFactory(new PropertyValueFactory<Object, String>("userName"));
		column2.setCellValueFactory(new PropertyValueFactory<Object, String>("phone"));
		column3.setCellValueFactory(new PropertyValueFactory<Object, Object>("borrowedBooks"));
		c4.setCellValueFactory(new PropertyValueFactory<Object, String>("status"));
		Object p = tableView.getSelectionModel().getSelectedItem();

		if(p!=null) {
			Profile profile = (Profile)p;
		status.setOnAction(e2 -> {
			GetBags.getProfileBag().searchProfileByUserName(profile.getUserName()).setStatus("Suspended");
			list.remove(profile);
			list.add(profile);
			tableView.refresh();
			tableView.setItems(list);
		});}
	}
	
	@FXML
	void viewUsers(ActionEvent event) {
		overDueBtn.setVisible(true);
		searchUser.setVisible(true);
		label2.setText("");
		label.setText("");
		dueLabel.setText("");
		column1.setText("Username");
		column2.setText("Phone");
		column3.setPrefWidth(120);
		column3.setText("Borrowed Books:");
		c4.setText("Status");
		column1.setCellValueFactory(new PropertyValueFactory<Object, String>("userName"));
		column2.setCellValueFactory(new PropertyValueFactory<Object, String>("phone"));
		column3.setCellValueFactory(new PropertyValueFactory<Object, Object>("borrowedBooks"));
		c4.setCellValueFactory(new PropertyValueFactory<Object, String>("status"));
		
		ObservableList<Object> list = FXCollections.observableArrayList(profileBag.getProfileMap().values());
		tableView.setItems(list);
		ObservableList<Profile> profileList = FXCollections.observableArrayList(profileBag.getProfileMap().values());
		FilteredList<Profile> filteredProfile = new FilteredList<>(profileList, e1 -> true);
//		searchUser.setOnKeyReleased(e1 -> {
		searchUser.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredProfile.setPredicate((Predicate<? super Profile>) profile -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCase = newValue.toLowerCase();
				if (profile.getUserName().toLowerCase().startsWith(lowerCase)) {
					return true;
				}
				return false;
			});
		});
		SortedList<Object> sorted = new SortedList<>(filteredProfile);
		sorted.comparatorProperty().bind(tableView.comparatorProperty());
		if (sorted.get(0) != null) {
			tableView.setItems(sorted);
		}
		
//		});
		overDueBtn.setOnAction(e -> {
			Object item = tableView.getSelectionModel().getSelectedItem();
			if (item == null) {
				dueLabel.setText("select a user");
			} else if (bookBag.getBookMap().containsValue(item) || profileBag.getHistoryMap().containsValue(item)) {
				dueLabel.setText("select a user");
			} else {
				Profile profile = (Profile) item;
//				status.setVisible(true);
				overDueBtn.setVisible(true);
				userItem.setVisible(true);
				tableLabel.setText("OverDued Books");
				userItem.setText("UserName: " + profile.getUserName());
				dueLabel.setText("");
				TreeMap<String, Book> overDue = profile.getCurrentBorrowing();
				List<Book> dues = overDue.values().stream()
						.filter(b -> b.getReturnTime().substring(0, 7).contains("OverDue"))
						.collect(Collectors.toList());
				tableView.setOnMouseClicked((MouseEvent e3) -> {
					contextMenu.show(tableView, e3.getScreenX(), e3.getScreenY());
				});
				
				label2.setText("");
				label.setText("");
				column1.setText("Title");
				column2.setText("Isbn");
				column3.setText("author");
				column1.setCellValueFactory(new PropertyValueFactory<Object, String>("titles"));
				column2.setCellValueFactory(new PropertyValueFactory<Object, String>("isbn"));
				column3.setCellValueFactory(new PropertyValueFactory<Object, Object>("author"));
				c4.setText("");
				c4.setCellValueFactory(null);
				ObservableList<Object> list2 = FXCollections.observableArrayList(dues);
				tableView.setContextMenu(contextMenu);
				tableView.setItems(list2);
			}
		});
		status.setVisible(true);
	}

	@FXML
	void addBook(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AddBookScene.fxml"));
		Scene scene = new Scene(root, 600, 600);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(650);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminProfileScene.fxml"));
		Scene scene = new Scene(root, 800, 800);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(800);
		window.setHeight(800);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void editBook(ActionEvent event) throws IOException {
		userItem.setVisible(false);
		tableLabel.setText("");
		dueLabel.setText("");
		label2.setText("");
		label.setText("");
		Object item = tableView.getSelectionModel().getSelectedItem();
		if (item == null) {
			label.setText("select a book");
		} else if (profileBag.getProfileMap().containsValue(item)) {
			label.setText("select a book");
		} else {
			editBook = (Book) item;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UpdateBookScene.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root, 800, 800);
			Stage window = (Stage) pane.getScene().getWindow();
			window.setWidth(600);
			window.setHeight(600);
			window.setScene(scene);
			window.show();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		editBook
		tableLabel.setText("");
		label2.setText("");
		label.setText("");
	}
}
