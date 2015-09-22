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
	private Mortgage mortgage;
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
		bonds = new Bonds(this);
		this.customerRating = setCustRating();
		mortgage = new Mortgage(this);
	}

	public Customer(String name, String lastName, long persNumber, double accountBalance) {
		this.name = name;
		this.lastName = lastName;
		this.persNumber = persNumber;
		this.accountBalance = accountBalance;
		accountNumber = generateAccountNumber();
		numAccount++;
		bonds = new Bonds(this);
		this.customerRating = setCustRating();
		mortgage = new Mortgage(this);
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
	
	public Mortgage getMortgage() {
		return mortgage;
	}

	public void setMortgage(Mortgage mortgage) {
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
	
	public void updateCustRating(){
		this.customerRating = setCustRating();
	}
	
	public void setIndex(int i){
		indexNr = i;
	}
	
	public int getIndex(){
		return indexNr;
	}
	
	public double getTotalBalance(){
		return accountBalance + bonds.getTotalBondsValue();
	}
	
	private char setCustRating(){
		char chReturn = 'e';
		double totalBalance = getTotalBalance();
		if (totalBalance < 60001){
		}
		if (totalBalance > 60000 && totalBalance < 100001){
			chReturn = 'd';
		}
		if (totalBalance > 100000 && totalBalance < 250001){
			chReturn = 'c';
		}
		if (totalBalance > 250000 && totalBalance < 500001){
			chReturn = 'b';
		}
		if (totalBalance > 500000){
			chReturn = 'a';
		}
		return chReturn;
	}
	
	@Override
	public String toString() {
		String strReturn = name + " " + lastName + "\nPersonnummer:\t" + persNumber + "\nKontonummer:\t" + accountNumber
				+ "\nSaldo:\t\t" + accountBalance +" SEK" + "\nKundklass:\t" + customerRating + "\n\nFonder:\n" + bonds.getBondsOwned();
		return strReturn;
	}




}
