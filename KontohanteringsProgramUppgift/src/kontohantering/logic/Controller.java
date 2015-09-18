package kontohantering.logic;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.data.Search;
import kontohantering.view.eventlistners.FormEvent;
import kontohantering.view.eventlistners.IFormListener;
import kontohantering.view.frames.StandardFrame;
import kontohantering.view.panels.OutputPanel;

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
	}

	public void setSelectedCustomerText() {
		view.setText(currCustomer.toString());
	}

	public Customer getCurrentCustomer() {
		return currCustomer;
	}

	/*
	 *  Temp disabled - double function
	 * 
	 * public Customer getSelectedCustomer() {
		return currCustomer;
	}
	 */
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

	public void SearchDB(String strToMatch) {
		Search currSearch = new Search();
		view.setViewFrame();
		ArrayList<Customer> currSearchArray = currSearch.getMatches(strToMatch);
		tablePart(currSearchArray);
	}

	// METHODS CALLED BY DIRECT USER ACTION

	public void editCustomer() {
		if (tableHandler.isTabelMode()) {
			if(tableHandler.setSelectedCustomer()){
				view.editCustomer();
			}else{
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund",JOptionPane.ERROR_MESSAGE);
			}
		} else if (currCustomer != null) {
			view.editCustomer();
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setEditMode() {
		view.setEditFrame();
		if (currCustomer == null) {
			if (tableHandler.setSelectedCustomer()) {
			} else {
				// Do nothing - no customer selected
			}
		}
	}

	public void tableFull() {
		tableHandler.showTableFull(customerDB.getArray());
	}

	public void updateOutput() {
		if (tableHandler.isTabelMode()) {
			tableHandler.dataChanged();
		} else {
			view.setText(currCustomer.toString());
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

	public void removeCustomer(){
		int indexNr = 0;
		if (tableHandler.isTabelMode()) {
			if(tableHandler.setSelectedCustomer()){
				int respons = JOptionPane.showConfirmDialog(view, "Vill du avsluta "+currCustomer.getName()+" "+currCustomer.getLastName()+"s konto?","Avsluta konto",JOptionPane.YES_NO_OPTION);
				if (respons == JOptionPane.YES_OPTION){
					indexNr = currCustomer.getIndex();
					customerDB.removeFromDB(indexNr);
				}
			}else{
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund",JOptionPane.ERROR_MESSAGE);
			}
		} else if (currCustomer != null) {
			int respons = JOptionPane.showConfirmDialog(view, "Vill du avsluta "+currCustomer.getName()+" "+currCustomer.getLastName()+"s konto?","Avsluta konto",JOptionPane.YES_NO_OPTION);
			if (respons == JOptionPane.YES_OPTION){
				indexNr = currCustomer.getIndex();
				customerDB.removeFromDB(indexNr);
			}
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addFunds(String amount) {
		if (Pattern.matches("[a-zA-Z]+", amount) == false) {
			currCustomer.addFunds(Double.parseDouble(amount));
			view.setText(currCustomer.toString());
		} else {
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Insättning ej utförd!", "Fel vid insättning",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void withdrawFunds(String amount) {
		if (Pattern.matches("[a-zA-Z]+", amount) == false){
			if (currCustomer.withdrawFunds(Double.parseDouble(amount))){
				view.setText(currCustomer.toString());
			} else {
				JOptionPane.showMessageDialog(view, "Det saknas täckning för uttaget", "Saknas täckning", JOptionPane.ERROR_MESSAGE);
		} 
		}else{
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Uttag ej utfört!", "Fel vid uttag",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// PRIVATE METHODS

	private void tablePart(ArrayList<Customer> customerPartArray) {
		tableHandler.showTablePart(customerPartArray);
	}

}
