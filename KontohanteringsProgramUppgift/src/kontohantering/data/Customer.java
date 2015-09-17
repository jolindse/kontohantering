package kontohantering.data;

/* Class for customers
 * --------------------
 * Holds all required accounting information:
 * 
 * Full name
 * Social security number (personnummer)
 * Account number
 * Account balance
 * 
 * Also hold optional information such as:
 * 
 * * Mortgage
 * * Bonds
 * * Customer rating (calculated value depending on the amount of business in bank)
 */

public class Customer {

	// Class counter used for generating account number
	private static int CLEARINGNUMBER = 7777;
	private static int numAccount = 0;

	private String name, lastName;
	private char customerRating;
	private long persNumber;
	private long accountNumber;
	private double accountBalance;
	private double mortgage;
	private Bonds bonds;
	private int indexNr;
	
	public Customer() {
		
	}
	
	// Two constructors based on initial account setup
	public Customer(String name, String lastName, long persNumber) {
		this.name = name;
		this.lastName = lastName;
		this.persNumber = persNumber;
		accountBalance = 0.0;
		accountNumber = generateAccountNumber();
		numAccount++;
		mortgage = 0;
		bonds = new Bonds(0, 0, this);
		this.customerRating = setCustRating();

	}

	public Customer(String name, String lastName, long persNumber, double accountBalance) {
		this.name = name;
		this.lastName = lastName;
		this.persNumber = persNumber;
		this.accountBalance = accountBalance;
		accountNumber = generateAccountNumber();
		numAccount++;
		mortgage = 0;
		bonds = new Bonds(0, 0, this);
		this.customerRating = setCustRating();
	}

	// GETTERS/SETTERS

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

	public void setPersNumber(int number) {
		this.persNumber = number;
	}

	public long getPersNumber() {
		return persNumber;
	}

	public char getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(char customerRating) {
		this.customerRating = customerRating;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Bonds getBonds() {
		return bonds;
	}
	
	public double getBondsAmount() {
		return bonds.getTotalBondsValue();
	}
	
	public void setBonds(Bonds bonds) {
		this.bonds = bonds;
	}
	
	public double getMortgage() {
		return mortgage;
	}

	public void setMortgage(double mortgage) {
		this.mortgage = mortgage;
	}

	public void setPersNumber(long persNumber) {
		this.persNumber = persNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	

	// METHODS

	private long generateAccountNumber() {
		/*
		 * Generate a unique 9 digits account number based on current system
		 * time and counter + clearingnumber
		 */
		String strNumAccount = Integer.toString(numAccount);
		int digitsBack = 10 - strNumAccount.length();
		long currTime = System.currentTimeMillis();
		String longStrNumber = String.valueOf(currTime);
		String generatedNumber = CLEARINGNUMBER
				+ longStrNumber.substring(longStrNumber.length() - digitsBack, longStrNumber.length()) + numAccount;

		return Long.parseLong(generatedNumber);
	}

	public void addFunds(double amount) {
		/*
		 * Add funds to account
		 */
		accountBalance = accountBalance + amount;
	}

	public boolean withdrawFunds(double amount) {
		/*
		 * Withdraw funds
		 */
		if (accountBalance >= amount) {
			accountBalance = accountBalance - amount;
			return true;
		}
		return false;
	}

	public void setIndex(int i){
		indexNr = i;
	}
	
	public int getIndex(){
		return indexNr;
	}
	
	private char setCustRating(){
		return 'e';
	}
	
	@Override
	public String toString() {
		String strReturn = name + " " + lastName + "\nPersonnummer:\t" + persNumber + "\nKontonummer:\t" + accountNumber
				+ "\nSaldo:\t\t" + accountBalance +" SEK" + "\nKundklass:\t" + customerRating;
		return strReturn;
	}




}
