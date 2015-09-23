package kontohantering.view.eventlistners;

import kontohantering.data.Customer;

public interface IBondsListener {

	public boolean bondBuyEventOccured(Customer currCustomer, int amount, String key);
	public boolean bondSellEventOccured(Customer currCustomer, int amount, String key);
}
