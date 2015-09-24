package kontohantering.view.formstables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
/*
 * The model for use with JTable in OutputPanel
 */
public class CustomerTableModel extends AbstractTableModel {

	private String[] colName = { 
			"Förnamn", 
			"Efternamn", 
			"Personnummer", 
			"Kontonummer", 
			"Saldo", 
			"Lån", 
			"Fonder",
			"Kundklass" };
	
	private ArrayList<Customer> customerDB;

	public CustomerTableModel() {
	}

	public void setDB(ArrayList<Customer> customerDB) {
		this.customerDB = customerDB;
	}
		
	public Customer getSelectedCustomer (int row) {
		return customerDB.get(row);
	}
	
	private String formatPersNr(long persNr){
		/*
		 * Class to format person nummer to a more readable version
		 */
		String toFormat = Long.toString(persNr);
		StringBuilder sb = new StringBuilder(toFormat);
		return sb.insert(8, '-').toString();
		
	}
	
	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		if (customerDB.size() < 1 || customerDB == null) {
			return 0;
		}
		return customerDB.size();
		
	}

	@Override
	public String getColumnName(int column) {
		
		return colName[column];
	}

	
	@Override
	public Object getValueAt(int row, int col) {
		Customer currCustomer = customerDB.get(row);
		switch(col) {
		case 0:
			return currCustomer.getName();
		case 1:
			return currCustomer.getLastName();
		case 2:
			return formatPersNr(currCustomer.getPersNumber());
		case 3:
			return currCustomer.getAccountNumber();
		case 4:
			return currCustomer.getAccountBalance();
		case 5:
			return currCustomer.getMortgage().getAmount();
		case 6:
			return currCustomer.getBondsAmount();
		case 7:
			return currCustomer.getCustomerRating();
		}
		return null;
	}
}
