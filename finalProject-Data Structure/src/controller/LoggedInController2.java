package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Author;
import model.Book;
import model.BookBag;
import model.GetBags;
import model.Profile;
import model.ProfileBag;

public class LoggedInController2 implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProfileBag profileBag = GetBags.getProfileBag();
	private String userName = LogInController.getUserName();
	private Profile profile = profileBag.searchProfileByUserName(userName);
	private ObservableList<Book> borrowedList = FXCollections.observableArrayList(profile.getBorrowedBooks());
	private double fee;
	private BookBag bookBag = GetBags.getBookBag();
	private ObservableList<Book> foundBooksList = FXCollections.observableArrayList();
	private String isbn;
	private String lastName;
	private String title;
	private HashSet<String> dic = GetBags.getDic();

	@FXML
	private AnchorPane pane;
	@FXML
	private TableColumn<Book, String> titleC;
	@FXML
	private TableColumn<Book, String> isbnC;
	@FXML
	private TableColumn<Book, String> firstNameC;
	@FXML
	private TableColumn<Book, String> lastNameC;
	@FXML
	private TableColumn<Book, LocalTime> dateC;
	@FXML
	private TableView<Book> tableView;
	@FXML
	private TableColumn<Book, String> column1;
	@FXML
	private TableColumn<Book, String> column2;
	@FXML
	private TableView<Book> tableViewHistory;
	@FXML
	private TableColumn<Book, String> column3;
	@FXML
	private TableColumn<Book, String> column4;
	@FXML
	private TableColumn<Book, Author> authorC;
	@FXML
	private TableColumn<Book, String> dueDate2;
	@FXML
	private TableColumn<Book, String> dueDate;
	@FXML
	private Label label;
	@FXML
	private Label label2;
	@FXML
	private TextField searchF;
	@FXML
	private Button searchBtn;
//	@FXML
//	private ListView<Book> listView;
	@FXML
	private MenuButton keyWord;
	@FXML
	private TableColumn<Book, LocalTime> date;
	@FXML
	private Button borrowBtn;
	@FXML
	private Button returnBtn;
	@FXML
	private Label nameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label townLabel;
	@FXML
	private Label zipLabel;
	@FXML
	private Label feeLabel;
	@FXML
	private TextField searchHistory;
	private Author author;

	@FXML
	void borrowBook(ActionEvent event) throws IOException {
		Book book = tableView.getSelectionModel().getSelectedItem();
		ObservableList<Book> list = FXCollections.observableArrayList();
		if (book != null) {
			tableView.getSelectionModel().clearSelection();
			if (profile.getCurrentBorrowing().containsKey(book.getIsbn())) {

			} else {
				titleC.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
				isbnC.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
				firstNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
				lastNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
				dateC.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));
				bookBag.deleteByIsbn(book.getIsbn());
				profile.addBorrowedBook(book);
				profile.getCurrentBorrowing().put(book.getIsbn(), book);
				profileBag.getHistoryList().add(book);
				profileBag.getHistoryMap().put(book.getIsbn(), book);
				book.setBorrowTime(LocalTime.now());
				book.setReturnTime(book.getBorrowTime().plusHours(0).plusMinutes(5) + "");
				bookBag.getHistoryMap().put(book.getIsbn(), book);
				bookBag.getHistoryList().add(book);
				list.add(book);
				tableViewHistory.refresh();
				tableViewHistory.getItems().addAll(list);
			}
		}

	}

	public void searchHistory() {
		column1.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
		column2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		column3.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
		column4.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
		String[] checkWord = new String[1];
		ObservableList<Book> list = FXCollections.observableArrayList(profile.getBorrowedBooks());
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
//					checkWord[0] = str;
					return false;
				});
			});
			SortedList<Book> sorted = new SortedList<>(filteredBook);
			sorted.comparatorProperty().bind(tableView.comparatorProperty());
//			if(!sorted.isEmpty()) {
				tableViewHistory.setItems(sorted);
//			}
//		searchBtn.setOnAction(e ->{
//			String[] word = checkWord[0].split(" ");
//			int i = 0;
//			while(i < word.length) {
//				if(!dic.contains(word[i])) {
//					label.setText("'"+word[i] + "' wrong splled");
//				}else {
//					tableView.setItems(sorted);
//				}
//				i++;
//			}
		});

	}

	@FXML
	void returnBook(ActionEvent event) throws IOException {
		Book book = tableViewHistory.getSelectionModel().getSelectedItem();
		ObservableList<Book> list = FXCollections.observableArrayList(profile.getBorrowedBooks());
		if (book != null) {
			titleC.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
			isbnC.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
			firstNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
			lastNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
			dateC.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));
			tableViewHistory.getSelectionModel().clearSelection();
			bookBag.insert(book);
			profileBag.getHistoryList().remove(book);
			profileBag.getHistoryMap().remove(book.getIsbn());
			profile.getCurrentBorrowing().remove(book.getIsbn());
			book.setReturnTime("returned\n" + LocalTime.now());
			list.remove(book);
			list.add(book);
			tableViewHistory.refresh();
			tableViewHistory.setItems(list);
		}
	}

	public void search() {
		column1.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
		column2.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		column3.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
		column4.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
		String[] checkWord = new String[1];
		ObservableList<Book> list = FXCollections.observableArrayList(bookBag.getBookMap().values());
		FilteredList<Book> filteredBook = new FilteredList<>(list, e1 -> true);
//		searchF.setOnKeyReleased(e1 -> {
		searchF.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredBook.setPredicate((Predicate<? super Book>) book -> {
				label.setText("");
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
		sorted.comparatorProperty().bind(tableView.comparatorProperty());
//			tableView.setItems(sorted);
//		});

		searchBtn.setOnAction(e -> {
			String[] word = checkWord[0].split(" ");
			int i = 0;
			while (i < word.length) {
				if (!dic.contains(word[i])) {
					label.setText("'" + word[i] + "' wrong splled");
				} else {
					tableView.setItems(sorted);
				}
				i++;
			}
		});

	}

	@FXML
	void viewOverDueBooks(ActionEvent event) {
		List<Book> overDue = profileBag.getHistoryList().stream()
				.filter(s -> s.getReturnTime().substring(0, 7).compareTo("OverDue") == 0).collect(Collectors.toList());
//		List<Book> sorted = profileBag.getHistoryList().stream().sorted(Comparator.comparing(Book::getBorrowTime))
//	            .collect(Collectors.toList());
		ObservableList<Book> list = FXCollections.observableArrayList(overDue);
		titleC.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
		isbnC.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		firstNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
		lastNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
		dateC.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));
		tableViewHistory.refresh();
		tableViewHistory.setItems(list);
//		sorted.sort((d1,d2) -> d1.getBorrowTime().getMinute() - (d2.getBorrowTime().getMinute()));
		// Iterator<Book> iterator = profileBag.getHistoryList().iterator();
////		Book book = iterator.next();
//		while(iterator.hasNext()) {
//		Book book = iterator.next();
//		if(book.getBorrowTime())
//		}
	}

	@FXML
	void openProfile(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ProfileScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(700);
		window.setHeight(700);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void goToHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/HomePageScene.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);
		Stage window = (Stage) pane.getScene().getWindow();
		window.setWidth(600);
		window.setHeight(600);
		window.setScene(scene);
		window.show();
	}

//	public ObservableList<Book> getBorrowedList() {
//		return borrowedList;
//	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		search();
		searchHistory();
		tableView.getItems().addAll(foundBooksList);
		nameLabel.setText("");
		streetLabel.setText("");
		townLabel.setText("");
		zipLabel.setText("");
		feeLabel.setText("");
		if (profile != null) {
			nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
			streetLabel.setText(profile.getAddress());
			townLabel.setText(profile.getTown());
			zipLabel.setText(profile.getZip());
//			tableView.getItems().removeAll();
			if (profile.getBorrowedBooks() != null) {
				int i = 0;
				label.setText("");
				while (i < profile.getBorrowedBooks().size()) {
					Book borrowedBook = profile.getBorrowedBooks().get(i);
					borrowedBook.getAuthor().getFirstName();
					borrowedBook.getAuthor().getLastName();
					titleC.setCellValueFactory(new PropertyValueFactory<Book, String>("titles"));
					isbnC.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
					firstNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("firstName"));
					lastNameC.setCellValueFactory(new PropertyValueFactory<Book, String>("lastName"));
					dateC.setCellValueFactory(new PropertyValueFactory<Book, LocalTime>("borrowTime"));

					long time = borrowedBook.getBorrowTime().until(LocalTime.now(), ChronoUnit.MINUTES);
					if (time < 0) {
						time = (24 - borrowedBook.getBorrowTime().getHour());
						time = (time * 60) + borrowedBook.getBorrowTime().getMinute();
					}
					if (time > 5) {
						if (!(borrowedBook.getReturnTime().substring(0, 8).contains("returned"))) {
							borrowedBook.setReturnTime(
									"OverDue\n" + borrowedBook.getBorrowTime().plusHours(0).plusMinutes(5));
						}
						profile.setFee(profile.getFee() + ((time - 5) * .01));
					}
					i++;
				}

				dueDate2.setCellValueFactory(new PropertyValueFactory<Book, String>("returnTime"));
				feeLabel.setText("$" + profile.getFee());
				tableViewHistory.setItems(borrowedList);
				
			}
		}
	}
}
