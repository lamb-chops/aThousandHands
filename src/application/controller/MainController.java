package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * David Lambert
 * inc950
 * 
 * Controller for the Main.fxml file, handles data flow between the 
 * java file and the view file
 */
public class MainController {
	//private javafx elem
    @FXML
    private AnchorPane anchor;  
    @FXML
    private Label label;
    @FXML
    private Button need;
    @FXML
    private Button give;
    @FXML
    private Button inv;
    
    //Takes event as param, sets the isDonating bool so the 
    //next controller knows which radiobutton is selected, before loading relevant file.
    //void because event handler
    @FXML
    private void need (MouseEvent event) throws IOException {
    	try {
    		NeedGiveController.isDonating=false;
			URL url = new File("src/application/view/NeedGive.fxml").toURI().toURL();
			anchor = FXMLLoader.load(url);
			Scene scene = new Scene(anchor);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		} 
    	
    }
    //Takes event as param, sets the isDonating bool so the 
    //next controller knows which radiobutton is selected, before loading relevant file.
    //void because event handler
    @FXML
    private void give (MouseEvent event) throws IOException {
    	try {
    		NeedGiveController.isDonating=true;
			URL url = new File("src/application/view/NeedGive.fxml").toURI().toURL();
			anchor = FXMLLoader.load(url);
			Scene scene = new Scene(anchor);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		} 
    	
    }
    //Takes event as param, and loads relevant file.
    //void because event handler
    @FXML
    private void inv (MouseEvent event) throws IOException {
    	try {
			URL url = new File("src/application/view/Inventory.fxml").toURI().toURL();
			anchor = FXMLLoader.load(url);
			Scene scene = new Scene(anchor);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}  	
    }
}
    	
