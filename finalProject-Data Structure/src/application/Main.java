package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import controller.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.AdminBag;
import model.BookBag;
import model.GetBags;
import model.Profile;
import model.ProfileBag;
import model.Utilities;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public class Main extends Application implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception, EOFException {
		GetBags gb = new GetBags();
		gb.start1();
		Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageScene.fxml"));
		Scene scene = new Scene(root, 700, 700);
		Stage primaryStage = new Stage();
		primaryStage.setWidth(650);
		primaryStage.setHeight(650);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
