package kontohantering.logic;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.data.Search;
import kontohantering.view.eventlistners.FormEvent;
import kontohantering.view.eventlistners.IFormListener;
import kontohantering.view.eventlistners.ITableListener;
import kontohantering.view.eventlistners.IUpdateObserver;
import kontohantering.view.eventlistners.IUpdateSub;
import kontohantering.view.eventlistners.TableEvent;
import kontohantering.view.frames.StandardFrame;
import kontohantering.view.panels.OutputPanel;

/*
 *  Controller class
 *  -------------------------
 *  This will be started from application class and handles
 *  all connections between GUI and model.
 *  
 *  It contains one static method to get a reference
 *  to it from every class in order to maintain this
 *  relationship.
 */

public class Controller implements IFormListener, IUpdateSub, ITableListener {

	private static Controller controllerHandler;

	private ArrayList updateObservers;
	private String fileName = "test.csv";
	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;
	
	private boolean tableMode;
	private boolean customerSelected;

	public Controller() {
		updateObservers = new ArrayList<IUpdateObserver>();
		tableMode = false;
		customerSelected = false;
	}

	/*  The init methods
	 *  -----------------
	 *  Started from application class and exist to have starting of 
	 *  different parts of MVC in order.
	 */
	
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

	// METHODS TO GET AND RETURN HANDLES
	
	public static Controller getController() {
		/*
		 * Static method to return a handler to the controller.
		 */
		return controllerHandler;
	}

	// METHODS FROM GUI UPDATE INTERFACE
	
	@Override
	public void registerObserver(IUpdateObserver o) {
		updateObservers.add(o);
		
	}

	@Override
	public void removeObserver(IUpdateObserver o) {
		int index = updateObservers.indexOf(o);
			if (index >= 0){
				updateObservers.remove(index);
			}
		
	}

	@Override
	public void updateObservers() {
		for (int i = 0; i < updateObservers.size(); i++) {
			IUpdateObserver observer = (IUpdateObserver)updateObservers.get(i);
				observer.updateGui();
		}
		
	}

	// METHODS FROM TABLE INTERFACE
	
	@Override
	public void tableEventOccured(TableEvent t) {
		
		tableMode = t.isTableActive();
		int clicks = t.getClicks();
		
		if (t.isCustomerSelected()){
			Customer eventCustomer = t.getCurrCustomer();
			currCustomer = eventCustomer;
			customerSelected = true;
		} else if (currCustomer != null) {
			customerSelected = true;
		} else {
			customerSelected = false;
		}
		
		if (customerSelected){
		switch(clicks){
		case 1:
			view.editMode();
			break;
		case 2:
			view.editMode();
			view.textMode(currCustomer.toString());
			break;
		}
		}
		
	}

	
	// METHODS FOR MODEL INTERACTION

	public void saveDB() {
		customerDB.saveDB(fileName);
	}

	public void loadDB() {
		customerDB.loadDB(fileName);

	}

	public String outputDB() {
		// Depricated - still in for testing. To be removed
		return customerDB.outputDB();
	}


	private ArrayList<Customer> getCustomerArray() {
		return customerDB.getArray();
	}

	// METHODS CALLED BY BUTTONS


	public void SearchDB(String strToMatch) {
		/*
		 *  Called by Search button
		 */
		Search currSearch = new Search(getCustomerArray());
		view.viewMode();
		ArrayList<Customer> currSearchArray = currSearch.getMatches(strToMatch);
		setTableData(currSearchArray);
	}

	
	public void editCustomer() {
		/*
		 *  Called by edit button
		 */
		if (tableMode) {
			if(customerSelected){
				view.editCustomerFrame(currCustomer);
			}else{
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "V�lj kund",JOptionPane.ERROR_MESSAGE);
			}
		} else if (customerSelected) {
			view.editCustomerFrame(currCustomer);
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "V�lj kund",JOptionPane.ERROR_MESSAGE);
		}
	}
		
	@Override
	public void formEventOccured(FormEvent e) {
		/*
		 *  Called by new account button
		 */
		
		currCustomer = new Customer(e.getFirstName(), e.getLastName(), e.getPersNumber(), e.getDepositAmount());
			customerDB.addToDB(currCustomer);
			//updateOutput();

	}

	public void removeCustomer(){
		/*
		 *  Called by terminate account button
		 */
		
		int indexNr = 0;
		if (tableMode) {
			if(customerSelected){
				int respons = JOptionPane.showConfirmDialog(view, "Vill du avsluta "+currCustomer.getName()+" "+currCustomer.getLastName()+"s konto?","Avsluta konto",JOptionPane.YES_NO_OPTION);
				if (respons == JOptionPane.YES_OPTION){
					indexNr = currCustomer.getIndex();
					customerDB.removeFromDB(indexNr);
				}
			}else{
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "V�lj kund",JOptionPane.ERROR_MESSAGE);
			}
		} else if (currCustomer != null) {
			int respons = JOptionPane.showConfirmDialog(view, "Vill du avsluta "+currCustomer.getName()+" "+currCustomer.getLastName()+"s konto?","Avsluta konto",JOptionPane.YES_NO_OPTION);
			if (respons == JOptionPane.YES_OPTION){
				indexNr = currCustomer.getIndex();
				customerDB.removeFromDB(indexNr);
			}
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "V�lj kund",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addFunds(String amount) {
		/*
		 *  Called by add funds button
		 */
		if (Pattern.matches("[a-zA-Z]+", amount) == false) {
			currCustomer.addFunds(Double.parseDouble(amount));
			view.textMode(currCustomer.toString());
		} else {
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Ins�ttning ej utf�rd!", "Fel vid ins�ttning",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void withdrawFunds(String amount) {
		/*
		 *  Called by withdraw funds button
		 */
		
		if (Pattern.matches("[a-zA-Z]+", amount) == false){
			if (currCustomer.withdrawFunds(Double.parseDouble(amount))){
				view.textMode(currCustomer.toString());
			} else {
				JOptionPane.showMessageDialog(view, "Det saknas t�ckning f�r uttaget", "Saknas t�ckning", JOptionPane.ERROR_MESSAGE);
		} 
		}else{
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Uttag ej utf�rt!", "Fel vid uttag",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// OTHER METHODS CALLED BY USER
	
	public void tableFull() {
		/*
		 *  Show full db in table.
		 */
		setTableData(customerDB.getArray());
	}
	
	
	// PRIVATE METHODS

	private void setTableData(ArrayList<Customer> customerArray) {
		view.tableMode(customerArray);
	}

}
