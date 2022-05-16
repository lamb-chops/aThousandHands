package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.NeedGiveModel;
/**
 * David Lambert
 * inc950
 * 
 * Test class, responsible for testing model class input and output for error checking
 */
public class THAppTest {
	
	//class variables needed to run test
	NeedGiveModel model;
	private ArrayList<String> itemList, invAmount;

	//runs before each test to set up state beforehand
	@Before
	public void setUp() throws Exception {
		model = new NeedGiveModel();
		itemList = new ArrayList<String>();	
		invAmount = new ArrayList<String>();	
	}
	//I feel the test are self explanatory so I did not comment them
	@Test
	public void testAddItem() {
		model.addItem("tp", "0");
		itemList = NeedGiveModel.getItemList();
		invAmount = NeedGiveModel.getInvAmount();
		assertEquals(itemList.get(0), "tp");
		assertEquals(invAmount.get(0), "0");	
	}
	
	@Test
	public void testGetNumberOfItemsInInventory() {
		model.addItem("tp", "0");
		itemList = NeedGiveModel.getItemList();
		invAmount = NeedGiveModel.getInvAmount();
		assertEquals(NeedGiveModel.getNumberOfItemsInInventory("tp", itemList, invAmount), "0");
	}
	
	@Test
	public void testSubtractItem() {
		model.addItem("tp", "2");
		assertEquals(model.subtractItem("tp", "1"), "y");
	}
	
	@Test
	public void testAddUserName() {
		assertEquals(model.addUserName("abc123"), "y");
	}

}
