package kontohantering.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import kontohantering.logic.Controller;

public class Search {

	private ArrayList<Customer> customerDB;
	private Controller controller;
	private Set<Customer> customerSet;
	private ArrayList<Customer> returnList;
	
	
	public Search () {
		
		controller = Controller.getController();
		customerDB = controller.getCustomerArray();
		returnList = new ArrayList<>();
		customerSet = new HashSet<>();
	}
	
	public ArrayList<Customer> getMatches(String strToMatch){
		searchTerms(strToMatch);
		returnList = new ArrayList<>(customerSet);
		return returnList;	
	}
	
	private void searchTerms(String strToMatch) {
	
	Pattern p = Pattern.compile(strToMatch,Pattern.CASE_INSENSITIVE);
	
	for (Customer currCust: customerDB) {
		String firstName = currCust.getName();
		String lastName = currCust.getLastName();
		String persNumber = Long.toString(currCust.getPersNumber());
		String accountNumber = Long.toString(currCust.getAccountNumber());
		
		if(p.matcher(firstName).matches()){
			customerSet.add(currCust);
		} else if (p.matcher(lastName).matches()){
			customerSet.add(currCust);
		} else if (p.matcher(persNumber).matches()) {
			customerSet.add(currCust);
		} else if (p.matcher(accountNumber).matches()) {
			customerSet.add(currCust);
		}
	}
	
	}
	
	
}
