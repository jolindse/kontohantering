package kontohantering.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/*
 * Class to handle the customer "database"
 */

import kontohantering.logic.Controller;

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
	
	public void removeFromDB (int indexNr){
		customerBase.remove(indexNr);
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

	// Read and save from/to file methods using Csv-class for parsing
	
	public void saveDB (String fileName) {
		Csv csv = new Csv(fileName);
		csv.writeDB(customerBase);
	}
	
	public void loadDB (String fileName) {
		Csv csv = new Csv(fileName);
		customerBase = csv.readDB();
	}

	public int getNumberOfEntries(){
		return customerBase.size();
	}
	
	public boolean exportDB(String currFile){
		/*
		 * Outputs all customers to textfile
		 */
		boolean allOk = false;
		Controller controller = Controller.getController();
		try {
			PrintWriter pw = new PrintWriter(currFile);
			for (Customer currCustomer: customerBase){
				pw.println(currCustomer+"\n\n\n");
			}
			pw.close();
			allOk = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			controller.addLogEntry(e.getMessage());
			allOk = false;
		}
		return allOk;
	}
}
