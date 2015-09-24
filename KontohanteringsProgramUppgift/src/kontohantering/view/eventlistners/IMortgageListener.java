package kontohantering.view.eventlistners;

import kontohantering.data.Customer;
/*
 * Interface between mortgage actions and controller
 */

public interface IMortgageListener {
	public boolean applyForMortgage(double amount, int years, Customer currCustomer);
	public boolean payMortgage(Customer currCustomer);
}
