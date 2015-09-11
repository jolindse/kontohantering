package kontohantering.logic;

import java.util.ArrayList;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.view.CustomerTableModel;
import kontohantering.view.FormEvent;
import kontohantering.view.IFormListener;
import kontohantering.view.StandardFrame;

public class Controller implements IFormListener {

	private static final int FULL = 0;
	private static final int PART = 1;
	
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
		view.setController(this);
	}

	// METHODS FOR MODELINTERACTION

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
		case FULL:
			return customerDB.getArray();
		case PART:
			// do nothing until search is implemented
			return null;
		}
		return null;
	}
	

	// EVENTOCCURED

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
