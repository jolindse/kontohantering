package kontohantering.logic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.data.Search;
import kontohantering.view.FormEvent;
import kontohantering.view.IFormListener;
import kontohantering.view.OutputPanel;
import kontohantering.view.StandardFrame;

/*
 *  Controller class
 *  -------------------------
 *  This will be started from mainloop and handles
 *  all connections between GUI and model.
 *  
 *  It contains one static method to get a reference
 *  to it from every class in order to maintain this
 *  relationship.
 */

public class Controller implements IFormListener {

	
	private static Controller controllerHandler;

	private String fileName = "test.csv";
	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;
	private OutputPanel tableHandler;

	public Controller() {

	}

	public void initControllerDB(CustomerDB customerDB) {
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

	public static Controller getController() {
		/*
		 * Static method to return a handler to the controller.
		 */
		return controllerHandler;
	}

	// Get handler for current customer selection

	public void setTable(OutputPanel tableHandler) {
		this.tableHandler = tableHandler;
	}

	// METHODS FOR MODEL INTERACTION

	public void setSelectedCustomer(Customer currCustomer) {
		this.currCustomer = currCustomer;
		view.setEditFrame();
		view.customerToText(currCustomer);
	}

	public Customer getCurrentCustomer() {
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
	
	public ArrayList<Customer> getCustomerArray() {
		return customerDB.getArray();
	}
	
	public void SearchDB (String strToMatch) {
		Search currSearch = new Search();
		ArrayList<Customer> currSearchArray = currSearch.getMatches(strToMatch);
		tablePart(currSearchArray);
	}

	
	// METHODS CALLED BY DIRECT USER ACTION

	public void editCustomer() {
		if (tableHandler.setSelectedCustomer()) {
			view.editCustomer();
		} else if (currCustomer != null) {
			view.editCustomer();
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!");
		}
	}

	public void tableFull(){
		tableHandler.showTableFull(customerDB.getArray());
	}
	
	
	public void updateOutput() {
		if (tableHandler.isTabelMode()) {
			tableHandler.dataChanged();
		} else {
			view.customerToText(currCustomer);
		}
	}

	@Override
	public void formEventOccured(FormEvent e) {
		switch (e.getFormType()) {
		case "newCustomer":
			currCustomer = new Customer(e.getFirstName(), e.getLastName(), e.getPersNumber(), e.getDepositAmount());
			customerDB.addToDB(currCustomer);
			updateOutput();
			break;
		}

	}

	// PRIVATE METHODS
	
	private void tablePart(ArrayList<Customer> customerPartArray){
		tableHandler.showTablePart(customerPartArray);
	}
	
}
