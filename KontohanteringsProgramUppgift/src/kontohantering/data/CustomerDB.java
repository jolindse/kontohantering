package kontohantering.data;

import java.util.ArrayList;
/*
 * Class to handle the customer "database"
 */
public class CustomerDB {
	
	
	private ArrayList<Customer> customerBase;
	
	public CustomerDB () {
		customerBase = new ArrayList<>();
	}
	
	public CustomerDB (ArrayList<Customer> customerBase){
		this.customerBase = customerBase;
	}
	
	public ArrayList<Customer> getArray() {
		return customerBase;
	}
	
	public void addToDB (Customer currCust) {
		customerBase.add(currCust);
	}
	
	public Customer getFromDB(int index){
		return customerBase.get(index);
	}
	
	public String outputDB() {
		String strOut = "";
		for (Customer i: customerBase){
			strOut = strOut + i + "\n\n";
		}
		return strOut;
	}

}
