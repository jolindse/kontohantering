package kontohantering.data;
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
	private String fileName;
	
	public Csv (String fileName) {
		this.fileName = fileName;
	}
	
	public boolean readDB (){
		return true;
	}
	
	public boolean writeDB (){
		return true;
	}
}
