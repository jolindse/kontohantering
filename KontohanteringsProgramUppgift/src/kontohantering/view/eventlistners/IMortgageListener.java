package kontohantering.view.eventlistners;

import kontohantering.data.Customer;

public interface IMortgageListener {
	public boolean applyForMortgage(double amount, int years, Customer currCustomer);
	public boolean payMortgage(Customer currCustomer);
}
