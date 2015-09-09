package kontohantering.data;

import java.util.ArrayList;

public class CustomerDB {

	private ArrayList<Customer> customerBase;
	
	public CustomerDB () {
		customerBase = new ArrayList<>();
	}
	
	public void addToDB (Customer currCust) {
		customerBase.add(currCust);
	}
	
	public Customer getFromDB(int index){
		return customerBase.get(index);
	}
	
	public String outputDB() {
		String strOut = null;
		for (Customer i: customerBase){
			strOut = strOut + i;
		}
		return strOut;
	}
}
