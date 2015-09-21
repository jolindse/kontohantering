package kontohantering.view.eventlistners;

import kontohantering.data.Customer;

public interface IBondsListener {

	public void bondBuyEventOccured(Customer currCustomer, int amount, String key);
	public void bondSellEventOccured(Customer currCustomer, int amount, String key);
}
