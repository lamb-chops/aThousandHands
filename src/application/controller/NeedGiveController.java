
package application.controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import Model.NeedGiveModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * David Lambert
 * inc950
 * 
 * Controller Class for the needgive.fxml file
 * Private var for everthing on page, including
 * a model class to instantiate to access that objects data.
 * I made the boolean as a public static so I would not have to
 * create a class and pass it around, since only one would ever be used. So static
 * allows the variable to apply to all instead of this.whatever.
 */
public class NeedGiveController {
	//fxml variables
    @FXML
    private Button submit, amount, user, home;
    @FXML
    private TextArea id, item, number, output;
    @FXML
    private Label label;
    @FXML
    private RadioButton donate, recieve;
    //class variables
    private NeedGiveModel model;
    public static boolean isDonating;
    
    //Takes the string user as param
    //Checks if that string is already in the user name arrayList
    //before outputting the correct response and writing to the file if needed
    //void because everything is being displayed on the view
    void saveUser(String user) throws IOException {
    	String valid = model.addUserName(user);
    	if (valid.contains("y")) {
    		output.appendText("User added!\n\n");
	    	FileWriter file = new FileWriter("/home/david/Desktop/apps/inc950-lab5/data/userName.txt", true);
	    	file.write(user+"\n");
	    	file.close();
    	}
        if (valid.contentEquals("n")) {
        	output.appendText("Error: invalid username format\n\n");
        }
    }
    //Takes items and amounts array list as params
    //Writes those to arraylist to file and closes file
    //void because everything is being done in the file
    private void saveInv(ArrayList<String> items, ArrayList<String> amounts ) throws IOException {

    	FileWriter file = new FileWriter("/home/david/Desktop/apps/inc950-lab5/data/inv.txt", false);
    	int length = items.size(); 
    	for (int j = 0; j < length; j ++) file.write(items.get(j)+", "+ amounts.get(j)+"\n");
    	
    	file.close();
    }
    //Takes event as param
    //Swithes the values of the radio buttons if the other is selected
    //void because output happening in fxml view
    @FXML
    void handle(ActionEvent event) {
    	if(isDonating) isDonating=false;
    	else isDonating=true;
    	if (isDonating) donate.setSelected(true);
    		
		if(!isDonating) recieve.setSelected(true);

    }
    //called when controller is first accessed from the app, instantiates model class for its data
    //checks if files exist to know if they need to be read from or not to fill in arraylist
    //creates togglegroup for buttons so only one can be selected at a time, then set the init
    //values for the buttons based on value passed to the public static boolean
    //void because called at creation to set up state, nothing to return
    @FXML
    private void initialize() throws FileNotFoundException {
		model = new NeedGiveModel();
		File invent = new File("/home/david/Desktop/apps/inc950-lab5/data/inv.txt");
		File names = new File("/home/david/Desktop/apps/inc950-lab5/data/userName.txt");
		if (invent.exists()) { //its ran before, need to pull info from file
			model.readInInv();
		}
		if (names.exists()) model.readInNames();
		ToggleGroup group = new ToggleGroup();
		donate.setToggleGroup(group);
		recieve.setToggleGroup(group);

		if (isDonating) donate.setSelected(true);
		else recieve.setSelected(true);
	}
    //Takes event as param when submit button is clicked
    //Pulls values from textfields and stores in strings
    //Checks if field have info or not for processing before outputting
    //the correct information to display
    //void because output is being shown on screen
    @FXML
    void submit(ActionEvent event) throws IOException {
    	String user = id.getText().toString();
    	String inv = item.getText().toString();
    	String amount = number.getText().toString();
    	id.clear();
    	item.clear();
    	number.clear();
    	output.clear();
    	
    	if (isDonating) {
    		//checks if user field is blank, save user if not
    		if(user.equals(null) || user==null || user.equals("") || user.isEmpty()) {}
    		else saveUser(user);
    		
    		//checks if inv or amount fields are empty, output text
    		if((inv==null || inv.equals("")) && (amount==null || amount.equals(""))) {
    			output.appendText("There inventory or amount field is blank!");
    			
    		}
    		else { //inv and amount are not empty to handle data
    			output.appendText("The item has been added!\n\n"); //clean up
    			model.addItem(inv, amount);
    			saveInv(NeedGiveModel.getItemList(), NeedGiveModel.getInvAmount());
    		}
    	}
    	else { //subtracting, handles data to display the correct output based on user input
    		String resp = model.subtractItem(inv, amount);
    		if (resp.contentEquals("n")) output.setText("Error: you cannot ask for more than we have.");
    		else if (resp.contentEquals("i")) output.setText("Error: item not in Inventory.");
    		else {
    			saveInv(NeedGiveModel.getItemList(), NeedGiveModel.getInvAmount());
    			output.appendText("We have successfuly processed your request!");
    			}
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
}
