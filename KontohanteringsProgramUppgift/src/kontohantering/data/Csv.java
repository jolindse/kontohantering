package kontohantering.data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

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
 *  CustomerRating	char
*   FOR EACH BOND TYPE:
 *  Bonds type		int
 *  Bonds number	int
 * 
 */
public class Csv {
	// Declare constants for file
	private static final String COMMA_DELIMITER = ",";

	private String fileName;

	public Csv(String fileName) {
		this.fileName = fileName;
	}

	public boolean readDB(ArrayList<Customer> customerDB) {
		boolean readOK = false;

		return readOK;
	}

	public boolean writeDB(ArrayList<Customer> customerDB) {

		boolean writeOK = false;

		PrintWriter pw;
		try {
			pw = new PrintWriter(fileName);
			// Format mandatory fields in output string
			for (Customer currCustomer : customerDB) {
				Bonds currBonds = currCustomer.getBonds();
				String currLine = currCustomer.getAccountNumber() 
						+ COMMA_DELIMITER + currCustomer.getName()
						+ COMMA_DELIMITER + currCustomer.getLastName() 
						+ COMMA_DELIMITER + currCustomer.getAccountBalance()
						+ COMMA_DELIMITER + currCustomer.getMortage() 
						+ COMMA_DELIMITER + currCustomer.getCustomerRating()
						+ COMMA_DELIMITER + currBonds.getNumberOfBondTypes();
				// Put optional bonds fields in output string
				for (Map.Entry<Integer, Integer> current : currBonds.getBondMap().entrySet()) {
					currLine = currLine 
							+ COMMA_DELIMITER + current.getKey() 
							+ COMMA_DELIMITER + current.getValue();
				}
				// Put current customer to file
				pw.println(currLine);
				writeOK = true;
			}
		} catch (FileNotFoundException e) {
				writeOK = false;
				System.out.println("File not found");
		}
		return writeOK;
		}
	}
