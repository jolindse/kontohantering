package kontohantering.view.eventlistners;

import java.util.EventObject;

import kontohantering.data.Customer;

/*
 *  Bean for form events
 *  ---------------------------------------------------
 *  
 */

public class FormEvent extends EventObject {
	
	private String firstName;
	private String lastName;
	private long persNumber;
	private boolean initialDeposit;
	private double depositAmount;
	
	private Customer currCustomer;
	
	
	public FormEvent (Object source,String firstName, String lastName, long persNumber, double depositAmount){
		super(source);
		this.firstName = firstName;
		this.lastName = lastName;
		this.persNumber = persNumber;
		this.depositAmount = depositAmount;
	}
	
	public FormEvent (Object source, Customer currCustomer, String firstName, String lastName, long persNumber) {
		super(source);
		this.currCustomer = currCustomer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.persNumber = persNumber;
	}

	// GETTERS/SETTERS
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public long getPersNumber() {
		return persNumber;
	}

	public boolean isInitialDeposit() {
		return initialDeposit;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public Customer getCurrCustomer() {
		return currCustomer;
	}

}
