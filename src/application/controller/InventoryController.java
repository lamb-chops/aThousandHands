package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import Model.NeedGiveModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * David Lambert
 * inc950
 * 
 * Controller for inventory.fxml file. Keeps all private variables for each
 * part of the page,and controls how the data that is calculated in the model class
 * is displayed on the view file.
 */
public class InventoryController {

    @FXML
    private TextArea item, output;
    @FXML
    private Button find,home;
    @FXML
    private Label label;
    
    private ArrayList<String> items, amounts;
    
    //called when controller is first accessed from the app
    //init's arrays and stores values if any
    //nothing to return, just sets up state for file
    @FXML
    private void initialize() throws FileNotFoundException {
		//print list 
    	items = new ArrayList<String>();
    	amounts = new ArrayList<String>();
    	if (NeedGiveModel.getItemList() != null) {
    		items = NeedGiveModel.getItemList();
    		amounts = NeedGiveModel.getInvAmount();
    	}
    	
    	File file = new File ("/home/david/Desktop/apps/inc950-lab5/data/inv.txt");
    	//if the app is first open, but has run before so the file exist
    	if (items.isEmpty() && file.exists()) {
    		readInInv();
    		for (int i=0; i<items.size(); i++) output.appendText(items.get(i)+", "+amounts.get(i)+"\n");
    	}
    	//if first time through app ever, no data to read in or inputted yet
    	else if (items.isEmpty() && !file.exists()) {
    		output.appendText("");
    	}
    	//otherwise not first time through
    	else {
    		String line;
    		for (int i=0; i<items.size(); i++) {
    			output.appendText(items.get(i)+", "+amounts.get(i)+"\n");
    		}
    	}
	}
    //Reads inventory that is already in file
    //void because no output, just storing data in ArrayList
    public void readInInv() throws FileNotFoundException {
		File file = new File ("/home/david/Desktop/apps/inc950-lab5/data/inv.txt");
		Scanner input = new Scanner (file);
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] split = line.split(",");
			items.add(split[0]);
			amounts.add(split[1].trim());
		}
	}
    //loads Home page, void because its a handler
    //Takes mouseEvent as param
	@FXML
    private void home (MouseEvent event) throws IOException {
    	try {
			URL url = new File("src/application/view/Main.fxml").toURI().toURL();
			AnchorPane anchor = FXMLLoader.load(url);
			Scene scene = new Scene(anchor);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	//takes in action event, pulls text from that object, checks if that item
	//is in the inv arrayList, and outputs the appropriate information and response
	//void because event handler
	@FXML
    void find(ActionEvent event) {
		output.clear();
    	String search = item.getText().toString().trim();
    	String response = NeedGiveModel.getNumberOfItemsInInventory(search, items, amounts);
    	if (response.equals("n")) output.appendText("Error, item not in inv.");
    	else output.appendText(response);
    }
}

