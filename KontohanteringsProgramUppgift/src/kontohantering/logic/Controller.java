package kontohantering.logic;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;
import kontohantering.data.Search;
import kontohantering.view.eventlistners.FormEvent;
import kontohantering.view.eventlistners.IBondsListener;
import kontohantering.view.eventlistners.IFormListener;
import kontohantering.view.eventlistners.IMortgageListener;
import kontohantering.view.eventlistners.ITableListener;
import kontohantering.view.eventlistners.IUpdateObserver;
import kontohantering.view.eventlistners.IUpdateSub;
import kontohantering.view.eventlistners.TableEvent;
import kontohantering.view.frames.BondsFrame;
import kontohantering.view.frames.MortgageFrame;
import kontohantering.view.frames.StandardFrame;
import static kontohantering.view.GuiConstants.SOFTWARE_VERSION;

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

public class Controller implements IFormListener, IUpdateSub, ITableListener, IBondsListener, IMortgageListener {

	private static Controller controllerHandler;

	private ArrayList updateObservers;
	private String fileName = "test.csv";
	private Customer currCustomer;
	private StandardFrame view;
	private CustomerDB customerDB;
	
	private ArrayList<Customer> tableArray;

	private boolean tableMode;
	private boolean customerSelected;
	private Log eventLog;
	
	public Controller() {
		updateObservers = new ArrayList<IUpdateObserver>();
		tableMode = false;
		customerSelected = false;
	}

	/*
	 * The init methods 
	 * ----------------- 
	 * Started from application class and
	 * exist to have starting of different parts of MVC in order.
	 */

	public void initControllerDB(CustomerDB customerDB) {

		this.customerDB = customerDB;
		tableArray = customerDB.getArray();
		loadDB();
		eventLog = new Log();
		addLogEntry("Program startat.");
		addLogEntry("Databas laddad med "+customerDB.getNumberOfEntries()+" stycken poster.");
	}

	public void initControllerView(StandardFrame view) {
		this.view = view;
		addLogEntry("GUI startat.");
		welcomeMsg();
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
		/*
		 * Adds an observer to the list
		 */
		updateObservers.add(o);

	}

	@Override
	public void removeObserver(IUpdateObserver o) {
		/*
		 * Removes an observer
		 */
		int index = updateObservers.indexOf(o);
		if (index >= 0) {
			updateObservers.remove(index);
		}

	}

	@Override
	public void updateObservers() {
		/*
		 * Function call to update the observers GUI.
		 */
		if (currCustomer != null){
			view.setCurrentString(currCustomer.toString());
		} else {
			view.setCurrentString("Ingen kund vald.");
		}
		
		for (int i = 0; i < updateObservers.size(); i++) {
			IUpdateObserver observer = (IUpdateObserver) updateObservers.get(i);
			observer.updateGui();
		}

	}
	
	// METHODS FROM TABLE INTERFACE

	@Override
	public void tableEventOccured(TableEvent t) {
		/*
		 * Sets the current user based on selection in table
		 * in OutputPanel.
		 */
		tableMode = t.isTableActive();
		int clicks = t.getClicks();

		if (t.isCustomerSelected()) {
			Customer eventCustomer = t.getCurrCustomer();
			currCustomer = eventCustomer;
			customerSelected = true;
			view.setCurrentString(currCustomer.toString());
		} else if (currCustomer != null) {
			customerSelected = true;
			view.setCurrentString(currCustomer.toString());
		} else {
			customerSelected = false;
			view.setCurrentString("Ingen kund vald.");
		}

		if (customerSelected) {
			switch (clicks) {
			case 1:
				view.editMode();
				break;
			case 2:
				view.editMode();
				view.setCurrentString(currCustomer.toString());
				view.textMode();
				break;
			}
		}
	}
	
	// METHODS FROM FORMINTERFACE - NEW USER AND EDIT USER
	
	@Override
	public void editUserFormEvent(FormEvent e) {
		/*
		 *  Called by the edit account button
		 */
		currCustomer = e.getCurrCustomer();
		currCustomer.setName(e.getFirstName());
		currCustomer.setLastName(e.getLastName());
		currCustomer.setPersNumber(e.getPersNumber());
		view.setCurrentString(currCustomer.toString());
		addLogEntry(currCustomer.getName()+" "+currCustomer.getLastName()+" fick sina uppgifter redigerade.");
		updateObservers();
		
	}

	
	@Override
	public void newUserFormEvent(FormEvent e) {
		/*
		 * Called by new account button
		 */

		currCustomer = new Customer(e.getFirstName(), e.getLastName(), e.getPersNumber(), e.getDepositAmount());
		customerDB.addToDB(currCustomer);
		tableArray.add(currCustomer);
		view.setCurrentString(currCustomer.toString());
		addLogEntry("Ny kund: "+currCustomer.getName()+" "+currCustomer.getLastName()+" tillagd med kontonummer: "+currCustomer.getAccountNumber()+".");
		updateObservers();

	}

	// METHODS FROM BONDS INTERFACE

	@Override
	public boolean bondBuyEventOccured(Customer bondsCustomer, int amount, String key) {
		/*
		 * Method for bond purchase
		 */
		boolean allOk = (bondsCustomer.getBonds().buyBonds(amount, key));
		addLogEntry(bondsCustomer.getName()+" "+bondsCustomer.getLastName()+" köpte "+amount+" stycken "+key+".");
		updateObservers();
		return allOk;
	}

	@Override
	public boolean bondSellEventOccured(Customer bondsCustomer, int amount, String key) {
		/*
		 * Method for bond sell
		 */
		boolean allOk = bondsCustomer.getBonds().sellBonds(amount, key);
		addLogEntry(bondsCustomer.getName()+" "+bondsCustomer.getLastName()+" sålde "+amount+" stycken "+key+".");
		updateObservers();
		return allOk;
	}

	// METHODS FROM MORTGAGE INTERFACE
	
	@Override
	public boolean applyForMortgage(double amount, int years, Customer mortCustomer) {
		/*
		 * Method for taking a loan
		 */
		boolean allOk = false;
			if (mortCustomer.getMortgage().applyForMortgage(years, amount)){
				mortCustomer.addFunds(amount);
				addLogEntry(mortCustomer.getName()+" "+mortCustomer.getLastName()+" tog ett lån på "+amount+" att återbetala under "+years+" år.");
				allOk = true;
			}
		updateObservers();
		return allOk;
	}

	@Override
	public boolean payMortgage(Customer mortCustomer) {
		/*
		 * Method for paying off debt
		 */
		boolean allOk = false;
		int years = mortCustomer.getMortgage().getYears();
		double amount = mortCustomer.getMortgage().getAmount();
		if (mortCustomer.getAccountBalance() >= mortCustomer.getMortgage().calculateTotalCost(years, amount)){
			double totalAmount = mortCustomer.getMortgage().calculateTotalCost(years, amount);
			mortCustomer.getMortgage().payOffMortgage();
			mortCustomer.withdrawFunds(totalAmount);
			addLogEntry(mortCustomer.getName()+" "+mortCustomer.getLastName()+" betalade av sitt lån (inklusive ränta) på "+totalAmount+" SEK." );
			allOk = true;
		}
		updateObservers();
		return allOk;
	}



	// METHODS FOR MODEL INTERACTION

	public void saveDB() {
		customerDB.saveDB(fileName);
	}

	public void loadDB() {
		customerDB.loadDB(fileName);

	}

	private ArrayList<Customer> getCustomerArray() {
		return customerDB.getArray();
	}

	// METHODS CALLED BY BUTTONS

	public void SearchDB(String strToMatch) {
		/*
		 * Called by Search button
		 */
		Search currSearch = new Search(getCustomerArray());
		view.viewMode();
		tableArray = currSearch.getMatches(strToMatch);
		setTableData(tableArray);
	}

	public void editCustomer() {
		/*
		 * Called by edit button
		 */
		if (tableMode) {
			if (customerSelected) {
				view.editCustomerFrame(currCustomer);
			} else {
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund", JOptionPane.ERROR_MESSAGE);
			}
		} else if (customerSelected) {
			view.editCustomerFrame(currCustomer);
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void removeCustomer() {
		/*
		 * Called by terminate account button
		 */

		int indexNr = 0;
		int tableIndex = 0;
		if (tableMode) {
			if (customerSelected) {
				int respons = JOptionPane.showConfirmDialog(view,
						"Vill du avsluta " + currCustomer.getName() + " " + currCustomer.getLastName() + "s konto?",
						"Avsluta konto", JOptionPane.YES_NO_OPTION);
				if (respons == JOptionPane.YES_OPTION) {
					tableIndex = tableArray.indexOf(currCustomer);
					tableArray.remove(tableIndex);
					indexNr = currCustomer.getIndex();
					addLogEntry(currCustomer.getName()+" "+currCustomer.getLastName()+" avslutade sitt konto.");
					customerDB.removeFromDB(indexNr);
					view.setCurrentString("Ingen kund vald.");
				}
			} else {
				JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund", JOptionPane.ERROR_MESSAGE);
			}
		} else if (currCustomer != null) {
			int respons = JOptionPane.showConfirmDialog(view,
					"Vill du avsluta " + currCustomer.getName() + " " + currCustomer.getLastName() + "s konto?",
					"Avsluta konto", JOptionPane.YES_NO_OPTION);
			if (respons == JOptionPane.YES_OPTION) {
				tableIndex = tableArray.indexOf(currCustomer);
				tableArray.remove(tableIndex);
				indexNr = currCustomer.getIndex();
				addLogEntry(currCustomer.getName()+" "+currCustomer.getLastName()+" avslutade sitt konto.");
				customerDB.removeFromDB(indexNr);
				view.setCurrentString("Ingen kund vald.");
			}
		} else {
			JOptionPane.showMessageDialog(view, "Ingen kund vald!", "Välj kund", JOptionPane.ERROR_MESSAGE);
		}
		updateObservers();
	}

	public void addFunds(String amount) {
		/*
		 * Called by add funds button
		 */
		if (Pattern.matches("[a-zA-Z]+", amount) == false) {
			currCustomer.addFunds(Double.parseDouble(amount));
			view.setCurrentString(currCustomer.toString());
			addLogEntry(currCustomer.getName() + " " + currCustomer.getLastName()+" gjorde en insättning på "+amount+" SEK.");
			view.textMode();
		} else {
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Insättning ej utförd!", "Fel vid insättning",
					JOptionPane.ERROR_MESSAGE);
		}
		updateObservers();
	}

	public void withdrawFunds(String amount) {
		/*
		 * Called by withdraw funds button
		 */

		if (Pattern.matches("[a-zA-Z]+", amount) == false) {
			if (currCustomer.withdrawFunds(Double.parseDouble(amount))) {
				view.setCurrentString(currCustomer.toString());
				addLogEntry(currCustomer.getName() + " " + currCustomer.getLastName()+" gjorde ett uttag på "+amount+" SEK.");
				view.textMode();
			} else {
				JOptionPane.showMessageDialog(view, "Det saknas täckning för uttaget", "Saknas täckning",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(view, "Ickenumrerisk inmatning. Uttag ej utfört!", "Fel vid uttag",
					JOptionPane.ERROR_MESSAGE);
		}
		updateObservers();
	}

	public void bondsButton() {
		/*
		 * Called by bonds button to start bonds frame
		 */
		BondsFrame bondsFrame = new BondsFrame(currCustomer);
	}
	
	public void mortgageButton() {
		/*
		 *  Called by mortgage button to start mortgage frame
		 */
		MortgageFrame mortFrame = new MortgageFrame(currCustomer);
	}


	// OTHER METHODS CALLED BY USER

	public void tableFull() {
		/*
		 * Show full db in table.
		 */
		setTableData(customerDB.getArray());
	}
	
	public void exitProgram() {
		/*
		 * Saves database and exits application
		 * ------------------------------------
		 * Called by StandardFrame and StandardMenu
		 */
		saveDB();
		System.exit(0);
	}

	// PRIVATE METHODS

	private void setTableData(ArrayList<Customer> customerArray) {
		/*
		 * Sets the table with data called from search
		 */
		view.tableMode(customerArray);
	}

	private void addLogEntry (String logEntry){
		/*
		 * Adds new entry to log
		 */
		eventLog.addLogEntry(logEntry);
	}

	private void welcomeMsg(){
		view.setCurrentString("[b]ANK kontohanteringssystem startat v"+SOFTWARE_VERSION
				+"\nDatabas med "+customerDB.getNumberOfEntries()+" poster laddad."
				+"\n\nAnvänd knapp för ny kund eller sök efter befintlig kunder."); 
		view.textMode();
	}
	
	public void outputLog(){
		view.setCurrentString(eventLog.printLog());
		view.textMode();
	}


}
