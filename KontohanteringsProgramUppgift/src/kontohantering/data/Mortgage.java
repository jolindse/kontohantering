package kontohantering.data;
import static kontohantering.data.ModelConstants.CUSTOMER_RATINGS;
import static kontohantering.data.ModelConstants.LOAN_RATES;
import static kontohantering.data.ModelConstants.LOAN_RATIO;

/*
 * Mortage class
 * ---------------------
 * Provides assets for Customer-objects and
 * methods for mortages.
 */

public class Mortgage {
	
	private boolean hasMortage;
	private double amount;
	private double maxAmount;
	private Customer currCustomer;
	private char custRating;
	private int ratingIndex;
	private int years;
	
	public Mortgage (Customer currCustomer){
		this.currCustomer = currCustomer;
		setIndex();
		amount = 0;
		years = 0;
		hasMortage = false;
	}
	
	public Mortgage (Customer currCustomer, double amount, int years){
		this.currCustomer = currCustomer;
		this.amount = amount;
		this.years = years;
		setIndex();
		if (amount < 1){
			hasMortage = false;
		} else {
			hasMortage = true;
		}
	}
	
	public boolean applyForMortgage(int aYears, double aAmount){
		/*
		 * Method to check if mortgage can be applied.
		 */
		
		boolean mortOk = false;
		double maxAmount = getMaxAmount();
		if (aAmount <= maxAmount && aYears < 13){
			amount = aAmount;
			years = aYears;
			hasMortage = true;
			mortOk = true;
		} 
		return mortOk;
	}
	
	public void payOffMortgage(){
		/*
		 * Method to nullify mortgage
		 */
		amount = 0;
		years = 0;
		hasMortage = false;
	}
	
	private void setIndex(){
		/*
		 * Sets the index value so that correct interest can be applied
		 */
		custRating = currCustomer.getCustomerRating();
		ratingIndex = new String(CUSTOMER_RATINGS).indexOf(custRating);
	}
	
	public double getInterest(){
		return LOAN_RATES[ratingIndex];
	}
	
	public double getAmount(){
		return amount;
	}
	
	public int getYears(){
		return years;
	}
	
	public boolean getHasMortgage(){
		return hasMortage;
	}
	
	public double calculateMonthlyPayments(int currYears, double currAmount){
		/*
		 *  Calculates the monthly cost of a mortgage
		 */
		int months = currYears * 12;
		double totalCost = calculateTotalCost(currYears, currAmount);
		double monthlyPayment = totalCost/months;
		return monthlyPayment;
	}
	
	public double calculateTotalCost(int currYears, double currAmount){
		/*
		 * Calculates the total cost of mortgage including interest
		 */
		int months = currYears * 12;
		double interest = getInterest() / 12;
		double totalCost = (currAmount * (interest*Math.pow((1+interest),months))/((Math.pow(1+interest, months))-1))*months;
		return totalCost;
	}
	
	
	public double getMaxAmount(){
		/*
		 * Calculates the max amount a customer can borrow based on customer rating and balance
		 */
		double totalBalance = currCustomer.getTotalBalance();
		double ratio = LOAN_RATIO[ratingIndex];
		maxAmount = totalBalance * (1+ratio);
		return maxAmount;
	}

	@Override
	public String toString() {
		/*
		 * Returns nicely formatted string for output of mortgage
		 */
		if (!hasMortage){
			return "Inget lån.";
		} else {
		double monthPayment = 0;
		double unusedMaxAmount = getMaxAmount() - amount;
		if (years > 0 && amount > 0){
		monthPayment = calculateMonthlyPayments(years, amount);
		}
		return String.format("Lånebelopp:\t\t%.2f SEK"
				+ "\nOutnyttjat lånebelopp:\t%.2f SEK"
				+ "\nMaxbelopp:\t\t%.2f SEK"
				+ "\n\nMånatlig betalning:\t%.2f SEK"
				+ "\nÅr:\t\t\t"+years
				+ "\n",amount,unusedMaxAmount,getMaxAmount(),monthPayment);
		}
	}
	
	
}
