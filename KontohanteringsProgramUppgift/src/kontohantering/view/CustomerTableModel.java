package kontohantering.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import kontohantering.data.Customer;
import kontohantering.data.CustomerDB;

public class CustomerTableModel extends AbstractTableModel {

	private String[] colName = { 
			"F�rnamn", 
			"Efternamn", 
			"Personnummer", 
			"Kontonummer", 
			"Saldo", 
			"L�n", 
			"Fonder",
			"Kundklass" };
	
	private CustomerDB currDB;
	private ArrayList<Customer> customerDB;

	public CustomerTableModel() {

	}

	public void setData(CustomerDB customerDB) {
		this.customerDB = customerDB.getArray();
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
