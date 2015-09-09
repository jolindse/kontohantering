package kontohantering.data;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 *  Class to read and write objects from CSV-file
 *  
 *  CSV file contains:
 *  AccountNumber	long
 *  FirstName		String
 *  LastName		String
 *  PersNumber		long
 *  AccountBalance	double
 *  Mortgage amount	double
 *  Bonds number	int
 *  Bonds type		int
 *  CustomerRating	char
 * 
 */
public class Csv {
	// Declare constants for file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE = "\n";
	
	private String fileName;
	
	public Csv (String fileName) {
		this.fileName = fileName;
	}
	
	public boolean readDB (ArrayList<Customer> customerDB){
		boolean readOK = false;
		
		
		
		
		return readOK;
	}
	
	public boolean writeDB (ArrayList<Customer> customerDB){
		
		boolean writeOK = false;
		
		PrintWriter pw = new PrintWriter(fileName);
		
		for (Customer currCustomer: customerDB){
			String currLine = 
					currCustomer.getAccountNumber() 
					+ COMMA_DELIMITER 
					+ currCustomer.getName()
					+ COMMA_DELIMITER 
					+ currCustomer.getLastName()
					+ COMMA_DELIMITER
					+ currCustomer.getAccountBalance()
					+ COMMA_DELIMITER
					
					
			;
		}
		
		return writeOK;
	}
}
