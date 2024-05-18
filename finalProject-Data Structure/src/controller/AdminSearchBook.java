package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Admin;
import model.AdminBag;
import model.Book;
import model.BookBag;
import model.GetBags;
import model.ProfileBag;

public class AdminSearchBook implements Serializable, Initializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProfileBag profileBag = GetBags.getProfileBag();
	private ObservableList<Book> foundBooksList = FXCollections.observableArrayList();
	private static AdminBag adminBag = GetBags.getAdminBag();
	private String userName = AdminLogInController.getUsername();
	private Admin admin = adminBag.searchAdmin(userName);
	private ObservableList<Book> borrowedList;
	private HashSet<String> dic = GetBags.getDic();
	private BookBag bookBag = GetBags.getBookBag();
	private String isbn;
	private String lastName;
	private String title;

	@FXML
	private TableView<Book> availableTableV;
	@FXML
	private TableColumn<Book, String> dueDate;
	@FXML
	private TableColumn<Book, String> title1;
	@FXML
	private TableColumn<Book, String> isbn1;
	@FXML
	private TableColumn<Book, String> firstN1;
	@FXML
	private TableColumn<Book, String> lastN1;
	@FXML
	private TableView<Book> historyTableV;
	@FXML
	private TableColumn<Book, String> title2;
	@FXML
	private TableColumn<Book, String> isbn2;
	@FXML
	private TableColumn<Book, String> firstN2;
	@FXML
	private TableColumn<Book, String> lastN2;
	@FXML
	private TableColumn<Book, LocalTime> borrowTime2;
	@FXML
	private Button sesarchBtn;
	@FXML
	private MenuButton KEYWORD;
	@FXML
	private TextField searchField;
	@FXML
	private Label label;
	@FXML
	private Label feeLabel;
	@FXML
	private TextField searchHistory;

	@FXML
	void borrow(ActionEvent event) {
		Book book = availableTableV.getSelectionModel().getSelectedItem();
		ObservableList<Book> list = FXCollections.observableArrayList();
		if (book != null) {
			availableTableV.getSelectionModel().clearSelection();
			if (admin.getCurrentBorrowing().containsKey(book.getIsbn())) {

			} else {
				title2.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
				isbn2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
				firstN2.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
				lastN2.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
				borrowTime2.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));
				Book[] b = bookBag.deleteByIsbn(book.getIsbn());
				profileBag.getHistoryList().add(book);
				admin.getborrowedHistory().add(book);
				admin.getCurrentBorrowing().put(book.getIsbn(), book);
				book.setBorrowTime(LocalTime.now());
				book.setReturnTime(book.getBorrowTime().plusHours(0).plusMinutes(5) + "");
				bookBag.getHistoryMap().put(book.getIsbn(), book);
				bookBag.getHistoryList().add(book);
				list.add(book);
				historyTableV.refresh();
				historyTableV.getItems().addAll(list);
			}
		}
	}

	@FXML
	void returnBook(ActionEvent event) throws IOException {
		Book book = historyTableV.getSelectionModel().getSelectedItem();
		ObservableList<Book> list = FXCollections.observableArrayList(admin.getborrowedHistory());
		if (book != null) {
			title2.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
			isbn2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
			firstN2.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
			lastN2.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
			borrowTime2.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));
			historyTableV.getSelectionModel().clearSelection();
			bookBag.insert(book);
			bookBag.getHistoryMap().remove(book.getIsbn(), book);
			admin.getCurrentBorrowing().remove(book.getIsbn());
			book.setReturnTime("returned\n" + LocalTime.now());
			list.remove(book);
			list.add(book);
			historyTableV.refresh();
			historyTableV.setItems(list);
		}
	}

	@FXML
	void authorSearch(ActionEvent event) {
		KEYWORD.setText("author");
	}

	@FXML
	void isbnSearch(ActionEvent event) {

		KEYWORD.setText("Isbn");
	}

	void titleSearch() {
		title1.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
		isbn1.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		firstN1.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
		lastN1.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
		String[] checkWord = new String[1];
		ObservableList<Book> list = FXCollections.observableArrayList(bookBag.getBookMap().values());
		FilteredList<Book> filteredBook = new FilteredList<>(list, e1 -> true);
//		searchField.setOnKeyReleased(e1 -> {
		searchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredBook.setPredicate((Predicate<? super Book>) book -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCase = newValue.toLowerCase();
				String str = "";
				str += lowerCase;
				if (book.getTitles().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getAuthor().getFirstName().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getAuthor().getLastName().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getIsbn().startsWith(lowerCase)) {
					return true;
				}
				checkWord[0] = str;
				return false;
			});
		});
		SortedList<Book> sorted = new SortedList<>(filteredBook);
		sorted.comparatorProperty().bind(historyTableV.comparatorProperty());
//			availableTableV.setItems(sorted);
//		});

		sesarchBtn.setOnAction(e -> {
			String[] word = checkWord[0].split(" ");
			int i = 0;
			while (i < word.length) {
				if (!dic.contains(word[i])) {
					label.setText("'" + word[i] + "' wrong splled");
				} else {
					availableTableV.setItems(sorted);
				}
				i++;
			}
		});
	}

	@FXML
	void goToAdminLoggedIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLoggedInScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(650);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToAdminProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminProfileScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	public void searchHistory() {
		title1.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
		isbn1.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		firstN1.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
		lastN1.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
		ObservableList<Book> list = FXCollections.observableArrayList(admin.getborrowedHistory());
		FilteredList<Book> filteredBook = new FilteredList<>(list, e1 -> true);
	searchHistory.setOnKeyReleased(e1 -> {
		searchHistory.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredBook.setPredicate((Predicate<? super Book>) book -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCase = newValue.toLowerCase();
				if (book.getTitles().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getAuthor().getFirstName().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getAuthor().getLastName().toLowerCase().contains(lowerCase)) {
					book.setFirstName(book.getAuthor().getFirstName());
					book.setLastName(book.getAuthor().getLastName());

					return true;
				} else if (book.getIsbn().startsWith(lowerCase)) {
					return true;
				}
				return false;
			});
		});
		SortedList<Book> sorted = new SortedList<>(filteredBook);
		sorted.comparatorProperty().bind(historyTableV.comparatorProperty());
		historyTableV.setItems(sorted);
	});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		searchHistory();
		titleSearch();
		bookBag = GetBags.getBookBag();
		if (admin != null) {
			borrowedList = FXCollections.observableArrayList(admin.getborrowedHistory());
			if (admin.getborrowedHistory() != null) {
				int i = 0;
				label.setText("");
				while (i < admin.getborrowedHistory().size()) {
					Book borrowedBook = admin.getborrowedHistory().get(i);
					borrowedBook.getAuthor().getFirstName();
					borrowedBook.getAuthor().getLastName();
					title2.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
					isbn2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
					firstN2.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
					lastN2.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
					borrowTime2.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));

					int time = LocalTime.now().getMinute()
							- admin.getborrowedHistory().get(i).getBorrowTime().getMinute();
					if (time < 0) {
						time = (24 - borrowedBook.getBorrowTime().getHour());
						time = (time * 60) + borrowedBook.getBorrowTime().getMinute();
					}
					if (time > 5) {
						if (!(borrowedBook.getReturnTime().substring(0, 8).contains("returned"))) {
							borrowedBook.setReturnTime(
									"OverDue\n" + borrowedBook.getBorrowTime().plusHours(0).plusMinutes(5));
						}
						admin.setFee(admin.getFee() + (time - 5) * .01);
					}
					i++;
				}
				if (admin.getFee() != 0) {
					feeLabel.setText("$" + admin.getFee());
				}
				dueDate.setCellValueFactory(new PropertyValueFactory<Book, String>("returnTime"));
				historyTableV.setItems(borrowedList);
			}
		}
	}
}
