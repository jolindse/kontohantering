package kontohantering.view;

import java.util.EventObject;

import kontohantering.data.Customer;

/*
 *  Bean for form events
 *  ---------------------------------------------------
 *  
 */

public class FormEvent extends EventObject {
	
	private String formType;
	private String firstName;
	private String lastName;
	private long persNumber;
	private boolean initialDeposit;
	private double depositAmount;
	
	private Customer currCustomer;
	
	
	public FormEvent (Object source,String firstName, String lastName, long persNumber, double depositAmount, String formType){
		super(source);
		this.firstName = firstName;
		this.lastName = lastName;
		this.persNumber = persNumber;
		this.depositAmount = depositAmount;
		this.formType = formType;
	}
	
	public FormEvent (Object source, Customer currCustomer, String firstName, String lastName, long persNumber, String formType) {
		super(source);
		this.currCustomer = currCustomer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.persNumber = persNumber;
		this.formType = formType;
	}

	// GETTERS/SETTERS
	
	public String getFormType() {
		return formType;
	}

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
