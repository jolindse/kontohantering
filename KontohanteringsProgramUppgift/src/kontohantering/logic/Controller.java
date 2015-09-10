package kontohantering.logic;

import kontohantering.data.Csv;
import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.view.FormEvent;
import kontohantering.view.IFormListener;
import kontohantering.view.StandardFrame;

public class Controller implements IFormListener {

	private String fileName = "test.csv";
	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;
	private Csv csv;

	public Controller(StandardFrame view, CustomerDB customerDB) {
		this.view = view;
		this.customerDB = customerDB;
		csv = new Csv(fileName);
		// Set reference to this controller in view for listeners
		view.setController(this);

	}

	// METHODS

	public void saveDB() {
		if (csv.writeDB(customerDB.getArray())) {
			System.out.println("Database written!");
		}
	}

	public void loadDB() {
		customerDB = new CustomerDB(csv.readDB());

	}

	public String outputDB() {
		return customerDB.outputDB();
	}
	
	/*
	 public void tableInit () {
		view.setTableDataModel(customerDB);
		view.
	}
	*/

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
