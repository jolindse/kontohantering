package kontohantering.logic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.view.CustomerTableModel;
import kontohantering.view.FormEvent;
import kontohantering.view.IFormListener;
import kontohantering.view.StandardFrame;
/*
 *  Controller class
 *  -------------------------
 *  This will be started from mainloop and handles
 *  all connections between GUI and user interface.
 *  
 *  It contains one static method to get a reference
 *  to it from every class in order to maintain this
 *  relationship.
 */ 

public class Controller implements IFormListener {

	private static final int ARRAYFULL = 0;
	private static final int ARRAYPART = 1;
	private static Controller controllerHandler;
	
	private String fileName = "test.csv";
	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;
	
	public Controller() {
	}
	
	public void initControllerDB(CustomerDB customerDB){
		this.customerDB = customerDB;
		loadDB();
	}
	
	public void initControllerView(StandardFrame view) {
		this.view = view;
	}

	public void initControllerHandler(Controller controller) {
		controllerHandler = controller;
	}
	
	// METHOD
	
	public static Controller getController(){
		/*
		 *  Static method to return a handler to the controller.
		 */
		return controllerHandler;
	}
	
	// METHODS FOR MODEL INTERACTION
	
	public void setSelectedCustomer(Customer currCustomer) {
		this.currCustomer = currCustomer;
		view.setEditFrame();
		view.customerToText(currCustomer);
	}
	
	public Customer getCurrentCustomer(){
		return currCustomer;
	}
	
	public Customer getSelectedCustomer() {
		return currCustomer;
	}

	public void saveDB() {
		customerDB.saveDB(fileName);
	}
	
	public void loadDB() {
		customerDB.loadDB(fileName);

	}

	public String outputDB() {
		return customerDB.outputDB();
	}
	
	public ArrayList<Customer> getCurrentCustomerArray(int type){
		switch(type){
		case ARRAYFULL:
			return customerDB.getArray();
		case ARRAYPART:
			// do nothing until search is implemented
			return null;
		}
		return null;
	}
	

	// EVENTOCCURED
	
	public void editCustomer() {
		if (view.editCustomer(currCustomer)){
			//
		}else{
			JOptionPane.showMessageDialog(view, "Ingen kund vald!");
		}
	}
	
	
	@Override
	public void formEventOccured(FormEvent e) {
		switch (e.getFormType()) {
		case "newCustomer":
			currCustomer = new Customer(e.getFirstName(), e.getLastName(), e.getPersNumber(), e.getDepositAmount());
			customerDB.addToDB(currCustomer);
			break;
		}

	}
	
	

}
