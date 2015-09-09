package kontohantering.logic;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.view.FormEvent;
import kontohantering.view.IFormListener;
import kontohantering.view.StandardFrame;

public class Controller implements IFormListener{

	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;

	
	public Controller(StandardFrame view, CustomerDB customerDB){
		this.view = view;
		this.customerDB = customerDB;
		// Set reference to this controller in view for listeners
		view.setController(this);

	}

	
	public String outputDB (){
		return customerDB.outputDB();
	}
	
	// EVENTOCCURED
	
	@Override
	public void formEventOccured(FormEvent e) {
		switch(e.getFormType()) {
		case "newCustomer":
			currCustomer = new Customer(e.getFirstName(), e.getLastName(), e.getPersNumber(), e.getDepositAmount());
			customerDB.addToDB(currCustomer);
			break;
		}
		
	}
	
}
