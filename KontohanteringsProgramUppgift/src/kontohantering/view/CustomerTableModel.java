package kontohantering.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;

public class CustomerTableModel extends AbstractTableModel {

	private static final int ARRAYFULL = 0;
	private static final int ARRAYPART = 1;
	
	private String[] colName = { 
			"F�rnamn", 
			"Efternamn", 
			"Personnummer", 
			"Kontonummer", 
			"Saldo", 
			"L�n", 
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
			return currCustomer.getPersNumber();
		case 3:
			return currCustomer.getAccountNumber();
		case 4:
			return currCustomer.getAccountBalance();
		case 5:
			return currCustomer.getMortgage();
		case 6:
			return currCustomer.getBondsAmount();
		case 7:
			return currCustomer.getCustomerRating();
		}
		return null;
	}
}
