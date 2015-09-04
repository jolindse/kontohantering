package kontohantering.view;

import kontohantering.data.Customer;
import java.util.List;

import javax.swing.table.AbstractTableModel;

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
	
	private List<Customer> db;

	public CustomerTableModel() {

	}

	public void setData(List<Customer> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		if (db.size() < 1 || db == null) {
			return 0;
		}
		return db.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
