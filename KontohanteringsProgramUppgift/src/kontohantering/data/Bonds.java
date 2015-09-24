package kontohantering.data;

import static kontohantering.data.ModelConstants.BONDS_NAME;
import static kontohantering.data.ModelConstants.BONDS_VALUE;
import java.util.HashMap;
import java.util.Map;

/*
 * Bonds class
 * ----------------------------
 * Bonds class that provides info
 * for Customer objects and methods
 */
public class Bonds {
	private BondsDB bondsDB;
	private Map<String, Integer> bondsMap;
	private int amount;
	private String key;
	private Customer currCustomer;
	
	public Bonds(Customer currCustomer){
		this.currCustomer = currCustomer;
		bondsDB = new BondsDB();
		bondsMap = new HashMap<String, Integer>();
	}
	
	// METHODS
	
	public boolean buyBonds(int amount, String key) {
		/*
		 *  Buy bonds method
		 *  ----------------
		 *  Updates the bondsMap and withdraws money from currCustomer.
		 */
		boolean buyOk = false;
		double totalAmount = totalValue(amount, key);
		if(totalAmount > 0 && totalAmount < currCustomer.getAccountBalance()){
			if (bondsMap.containsKey(key)){
				int previousAmount = bondsMap.get(key);
				int newAmount = previousAmount + amount;
				bondsMap.put(key, newAmount);
				currCustomer.withdrawFunds(totalAmount);
				buyOk = true;
			} else {
				bondsMap.put(key,amount);
				currCustomer.withdrawFunds(totalAmount);
				buyOk = true;
			}
		}
		
		return buyOk;
	}
	
	public boolean sellBonds(int amount, String key) {
		/*
		 *  Sell bonds method
		 *  -----------------
		 *  Updates the bondsMap and adds money to currCustomer.
		 */
		boolean sellOk = false;
		int bondsOwned = bondsMap.get(key);
		if (bondsOwned >= amount){
			bondsOwned -= amount;
			double totalAmount = totalValue(amount, key);
			currCustomer.addFunds(totalAmount);
			if (bondsOwned == 0){
				bondsMap.remove(key);
			} else {
				bondsMap.put(key, bondsOwned);
			}
			sellOk = true;
		}
		return sellOk;
	}
	
	public Map<String,Integer> getBondMap() {
		/*
		 * Returns the map of bonds owned
		 */
		return bondsMap;
	}
	
	public int getNumberOfBondTypes(){
		/*
		 * Returns the total number of bond types owned
		 */
		return bondsMap.size();
	}
	
	public double getBondPrice(String key){
		/*
		 *  Returns the value of specified bond 
		 */
		return bondsDB.getBondValue(key);
	}

	public int getBondsOwnedAmount(String key){
		/*
		 *  Returns the number of bonds owned of specified type
		 */
		return bondsMap.get(key);
	}
	
	public double totalValue(int amount, String key) {
		/*
		 *  Used to calculate the total value of specific bonds and amount
		 */
		return bondsDB.getBondValue(key) * amount;
	}
	
	public double getTotalBondsValue() {
		/*
		 *  Returns the total value of owned bonds
		 */
		double totalValue = 0;
		for (Map.Entry<String, Integer> current : bondsMap.entrySet()){
			String key = current.getKey();
			int value = current.getValue();
			totalValue += totalValue(value, key);
		}
		return totalValue;
	}
	
	public String[] getBondsDBNames(){
		/*
		 *  Used to feed combobox in bonds buy panel
		 */
		String[] strReturn = bondsDB.getNames(); 
		return strReturn;
	}
	
	public String[] getOwnedBondsNames(){
		/*
		 *  Used to feed combobox in bonds sell panel
		 */
		String[] strReturn = new String[bondsMap.size()];
		int index = 0;
		for (Map.Entry<String, Integer> currentBond: bondsMap.entrySet()){
			strReturn[index] = currentBond.getKey();
			index++;
		}
		return strReturn;
	}
	
	public String getBondsOwned(){
		/* 
		 *  Used in customer toString method to display owned bonds
		 */
		String strReturn = "";
		for (Map.Entry<String, Integer> currentBond: bondsMap.entrySet()){
			String currKey = currentBond.getKey();
			int currNum = currentBond.getValue();
			double totValue = totalValue(currNum, currKey);
			double bondValue = getBondPrice(currKey);
			strReturn += String.format(currKey + "\n\n"+currNum+" stycken a' %.2f SEK/st.\nTotalt värde:\t%.2f SEK\n\n", bondValue,totValue);
		}
		return strReturn;
	}

}
