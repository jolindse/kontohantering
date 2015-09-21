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
		return bondsMap;
	}
	
	public int getNumberOfBondTypes(){
		return bondsMap.size();
	}
	
	public double getBondPrice(String key){
		return bondsDB.getBondValue(key);
	}

	public int getBondsOwnedAmount(String key){
		return bondsMap.get(key);
	}
	
	public double totalValue(int amount, String key) {
		return bondsDB.getBondValue(key) * amount;
	}
	
	public double getTotalBondsValue() {
		double totalValue = 0;
		for (Map.Entry<String, Integer> current : bondsMap.entrySet()){
			String key = current.getKey();
			int value = current.getValue();
			totalValue += totalValue(value, key);
		}
		return totalValue;
	}
	
	// ONLY FOR TESTING PURPOSE TO BE REMOVED
	
	public String[] getBondsDBNames(){

		String[] strReturn = bondsDB.getNames(); 
		return strReturn;
	}
	
	public String[] getOwnedBondsNames(){
		String[] strReturn = new String[bondsMap.size()];
		int index = 0;
		for (Map.Entry<String, Integer> currentBond: bondsMap.entrySet()){
			strReturn[index] = currentBond.getKey();
			index++;
		}
		return strReturn;
	}
	
	public String getBondsOwned(){
		String strReturn = "";
		for (Map.Entry<String, Integer> currentBond: bondsMap.entrySet()){
			String currKey = currentBond.getKey();
			int currNum = currentBond.getValue();
			String totValue = Double.toString(totalValue(currNum, currKey));
			strReturn += currKey + "\nAntal: " + currNum + "\nVärde" + totValue + "\n\n";
		}
		return strReturn;
	}

}
