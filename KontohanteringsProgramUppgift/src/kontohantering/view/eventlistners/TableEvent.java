package kontohantering.view.eventlistners;

import java.util.EventObject;

import kontohantering.data.Customer;
/*
 * Bean class for the table events
 */
public class TableEvent extends EventObject {

	private boolean tableActive;
	private boolean customerSelected;
	private Customer currCustomer;
	private int clicks;
	
	public TableEvent (Object source, Customer currCustomer, boolean tableActive, boolean customerSelected, int clicks) {
		super(source);
		this.currCustomer = currCustomer;
		this.tableActive = tableActive;
		this.customerSelected = customerSelected;
		this.clicks = clicks;
		
	}

	public boolean isTableActive() {
		return tableActive;
	}

	public boolean isCustomerSelected() {
		return customerSelected;
	}

	public Customer getCurrCustomer() {
		return currCustomer;
	}
	
	public int getClicks() {
		return clicks;
	}
}
