package kontohantering.data;

/* Class for customers
 * --------------------
 * Holds all required accounting information:
 * 
 * Full name
 * Social security number (personnummer)
 * Account number (must be individual.
 * 
 * Also hold optional information such as:
 * 
 * * Account balance
 * * Mortage
 * * Bonds
 * * Customer rating (calculated value dependning on the ammount of buisness in bank)
 */

public class Customer {

	// Class counter used for generating account number
	private static int numAccount = 0;
	
	private String name, lastName;
	private char customerRating;
	private int accountNumber;
	private double accountBalance, mortage, bonds;
	
	// Various constructors based on inital accountsetup
	public Customer (String name, String lastName, int accountNumber) {
		this.name = name;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		accountBalance = 0;
		mortage = 0;
		bonds = 0;
	}
	
	public Customer (String name, String lastName, int accountNumber, double accountBalance) {
		this.name = name;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		mortage = 0;
		bonds = 0;
	}
	
	public Customer (String name, String lastName, int accountNumber, double accountBalance, double mortage) {
		this.name = name;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.mortage = mortage;
		bonds = 0;
	}
	
	public Customer (String name, String lastName, int accountNumber, double accountBalance, double mortage, double bonds) {
		this.name = name;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.mortage = mortage;
		this.bonds = bonds;
	}

	// Getters and setters for the values
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(char customerRating) {
		this.customerRating = customerRating;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getMortage() {
		return mortage;
	}

	public void setMortage(double mortage) {
		this.mortage = mortage;
	}

	public double getBonds() {
		return bonds;
	}

	public void setBonds(double bonds) {
		this.bonds = bonds;
	}

	public static int getNumAccount() {
		return numAccount;
	}

}
