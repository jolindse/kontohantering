package kontohantering.view.eventlistners;

import kontohantering.data.Customer;
/*
 * Interface for use between controller and bonds actions.
 */

public interface IBondsListener {

	public boolean bondBuyEventOccured(Customer currCustomer, int amount, String key);
	public boolean bondSellEventOccured(Customer currCustomer, int amount, String key);
}
