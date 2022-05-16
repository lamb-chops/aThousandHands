package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * David Lambert
 * inc950
 * 
 * Model class for need/give, responsible for data handling
 */
public class NeedGiveModel {
	
	//private class vars
	private static ArrayList<String> invAmount, localItem, localAmount;
	private ArrayList<String> userList;
	private static ArrayList<String> itemList;

	//generator to set object state on creation
	public NeedGiveModel() {
		userList = new ArrayList<String>();
		itemList = new ArrayList<String>();
		invAmount = new ArrayList<String>();
	}
	//takes the item and amount as string params
	//checks if the item is in the list, if it is then
	//increment its value, if not then add to list
	//void because just adding data to arraylist
	public void addItem(String inv, String amount) {
		if (amount.equals(null)) amount = "0"; //check
		if (itemList.contains(inv)) {
			int index = itemList.indexOf(inv);
			int currAmount = Integer.parseInt(invAmount.get(index));
			int newAmount = currAmount + Integer.parseInt(amount);
			invAmount.set(index, Integer.toString(newAmount));
		}
		else {
			itemList.add(inv);
			invAmount.add(amount);
		}
	}
	//takes the item and amount as string params
	//checks if the item is in the list, if it is then
	//decrements its value, output relevenat information
	//based on if there is enough to take that much
	//or if it is in inv list to begin with
	//returns a string which I used to see which result was achieved
	//so I know what needs to be outputted
	public String subtractItem(String inv, String amount) {
		
		if (amount.equals(null)) amount = "0"; //check
		if (itemList.contains(inv)) {
			int index = itemList.indexOf(inv);
			int currAmount = Integer.parseInt(invAmount.get(index));
			int newAmount = currAmount - Integer.parseInt(amount);
			if (newAmount < 0) return "n";
			else invAmount.set(index, Integer.toString(newAmount));
			return "y";
		}
		else return "i";
			
	}
	//takes the item being searched for as a string, and the itemlist and amount list as arraylist
	//checks if item is in item list, if so, return the amount associated with it. If not,
	//returns a string which I used to see which result was achieved
	//so I know what needs to be outputted
	public static String getNumberOfItemsInInventory(String search, ArrayList<String> itemList, ArrayList<String> amount) {
		localItem = itemList;
		localAmount = amount;
		if (localItem.contains(search)) {
			int index = itemList.indexOf(search);
			String amountReturned = amount.get(index);
			return amountReturned;
		}
		return "n";
	}
	//takes name user entered as param
	//first checks if id is right format, if it is
	//then add to list if not already there
	//returns a string so I know which result was achieved and what needs to be outputted
	public String addUserName(String name) {
		name = name.trim();
	
		char[] ch = name.toCharArray();
		
		if (name.length() != 6) return "n";//is this right?
		else if (Character.isLetter(ch[0]) && Character.isLetter(ch[1]) && Character.isLetter(ch[2]) && 
				Character.isDigit(ch[3]) && Character.isDigit(ch[4]) && Character.isDigit(ch[5])) {
			if(!userList.contains(name)) {
				userList.add(name);
				return "y"; //yes to modify list
			}
			else { return "f"; }//in list, do nothing
		}
		else return "n"; //improper format
	}
	
	//getters and setters for private vars
	public static ArrayList<String> getInvAmount() {
		return invAmount;
	}
	public void setInvAmount(ArrayList<String> invAmount) {
		NeedGiveModel.invAmount = invAmount;
	}
	public ArrayList<String> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}
	public static ArrayList<String> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<String> itemList) {
		NeedGiveModel.itemList = itemList;
	}
	//Reads in the lines from the file and stores in relevant arraylist
	//void because data is just being stored
	public void readInInv() throws FileNotFoundException {
		File file = new File ("/home/david/Desktop/apps/inc950-lab5/data/inv.txt");
		Scanner input = new Scanner (file);
		
		while (input.hasNextLine()) {
			String line = input.nextLine();
			String[] split = line.split(",");
			itemList.add(split[0].trim());
			invAmount.add(split[1].trim());
		}
		input.close();
	}
	//Reads in the lines from the file and stores in relevant arraylist
	//void because data is just being stored
	public void readInNames() throws FileNotFoundException {
		File file = new File ("/home/david/Desktop/apps/inc950-lab5/data/userName.txt");
		Scanner input = new Scanner (file);
		
		while (input.hasNextLine()) userList.add(input.nextLine());
		
		input.close();
	}
}
