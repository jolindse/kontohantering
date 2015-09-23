package kontohantering.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	// Set constant CSV indexes
	private static final int ACCOUNT_NUMBER = 0;
	private static final int FIRSTNAME = 1;
	private static final int LASTNAME = 2;
	private static final int PERSNUMBER = 3;
	private static final int ACCOUNTBALANCE = 4;
	private static final int MORTAGEYEARS = 5;
	private static final int MORTAGEAMOUNT = 6;
	private static final int CUSTOMERRATING = 7;
	private static final int NUMBEROFBONDTYPES = 8;

	private String fileName;

	public Csv(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList<Customer> readDB() {
		BufferedReader br;
		int indexNr = 0;
		// Init objects to populate with data
		ArrayList<Customer> customerDB = new ArrayList<>();
		
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = "";

			while ((line = br.readLine()) != null) {
				Customer currCustomer = new Customer();
				Bonds currBonds = new Bonds(currCustomer);
				// Split CSV into array that contains the values
				String[] tokens = line.split(COMMA_DELIMITER);

				if (tokens.length > 0){

					// Populate the mandatory Customer fields
					currCustomer.setAccountNumber(Long.parseLong(tokens[ACCOUNT_NUMBER]));
					currCustomer.setName(tokens[FIRSTNAME]);
					currCustomer.setLastName(tokens[LASTNAME]);
					currCustomer.setPersNumber(Long.parseLong(tokens[PERSNUMBER]));
					currCustomer.setAccountBalance(Double.parseDouble(tokens[ACCOUNTBALANCE]));

					int numberOfBonds = Integer.parseInt(tokens[NUMBEROFBONDTYPES]);
					int bondsCounter = NUMBEROFBONDTYPES + 1;
					Map<String,Integer> bondsMap = currBonds.getBondMap();
					for (int i = 0; i < numberOfBonds; i++ ){
						String key = tokens[bondsCounter];
						bondsCounter++;
						int value = Integer.parseInt(tokens[bondsCounter]);
						bondsCounter++;
						bondsMap.put(key, value);
					}
					// Set populated bonds  map to currCustomer
					currCustomer.setBonds(currBonds);
					
					currCustomer.updateCustRating();
					// Set mortgage info
					double mortAmount = Double.parseDouble(tokens[MORTAGEAMOUNT]);
					int mortYears = Integer.parseInt(tokens[MORTAGEYEARS]);
					Mortgage currMortage = new Mortgage(currCustomer, mortAmount, mortYears);
					currCustomer.setMortgage(currMortage);
					currCustomer.setIndex(indexNr);

					// Add currCustomer to the DB
					customerDB.add(currCustomer);
					indexNr++;
				}
				
			}
			br.close();
		} catch (FileNotFoundException e) {
			//System.out.println("DB-file not found");
		} catch (NumberFormatException e) {
			System.out.println("Number problem!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O error");
			e.printStackTrace();
		}
		return customerDB;
	}

	public void writeDB(ArrayList<Customer> customerDB) {

		PrintWriter pw;
		try {
			pw = new PrintWriter(fileName);
			// Format mandatory fields in output string
			for (Customer currCustomer : customerDB) {
				Bonds currBonds = currCustomer.getBonds();
				String currLine = currCustomer.getAccountNumber() 
						+ COMMA_DELIMITER + currCustomer.getName()
						+ COMMA_DELIMITER + currCustomer.getLastName() 
						+ COMMA_DELIMITER + currCustomer.getPersNumber()
						+ COMMA_DELIMITER + currCustomer.getAccountBalance()
						+ COMMA_DELIMITER + currCustomer.getMortgage().getYears()
						+ COMMA_DELIMITER + currCustomer.getMortgage().getAmount()
						+ COMMA_DELIMITER + currCustomer.getCustomerRating()
						+ COMMA_DELIMITER + currBonds.getNumberOfBondTypes();
				// Put optional bonds fields in output string
				for (Map.Entry<String, Integer> current : currBonds.getBondMap().entrySet()) {
					currLine = currLine 
							+ COMMA_DELIMITER + current.getKey() 
							+ COMMA_DELIMITER + current.getValue();
				}
				// Put current customer to file
				pw.println(currLine);
				}
			pw.close();
		} catch (FileNotFoundException e) {
				System.out.println("File not found");
		}
	}
	}
