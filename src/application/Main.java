package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * David Lambert
 * inc950
 *
 * This is the main class where the program enters. It extends the application
 * class for javafx. It starts up the java fx application and loads the 
 * page viewed when first opened.
 * */
public class Main extends Application {
	@Override
	//takes primarystage as param before loading and displaying main.fxml
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/application/view/Main.fxml").toURI().toURL();
			AnchorPane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//entry point for program
	public static void main(String[] args) {
		launch(args);
	}
}