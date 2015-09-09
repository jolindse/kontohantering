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
	private Map<Integer, Integer> bondsMap;
	private int amount;
	private int type;
	private Customer currCustomer;

	public Bonds(int amount, int type, Customer currCustomer) {
		bondsMap = new HashMap<Integer, Integer>();
		this.currCustomer = currCustomer;
		this.amount = amount;
		this.type = type;
	}

	public void buyBonds(int amount, int type) {
		if (type == this.type) {
			int currentBonds = bondsMap.get(type) + amount;
			bondsMap.put(type, currentBonds);
		} else {
			bondsMap.put(type, amount);
		}
	}

	public double sellBonds(int amount, int type) {
		int currentBonds = bondsMap.get(type) - amount;
		if (currentBonds < 1){
			bondsMap.remove(type);
		}
		return totalValue(amount, type);
	}
	
	public Map<Integer,Integer> getBondMap() {
		return bondsMap;
	}
	
	public int getNumberOfBondTypes(){
		return bondsMap.size();
	}

	public double totalValue(int amount, int type) {
		return BONDS_VALUE[type] * amount;
	}
	
	public String getBondName(int type){
		return BONDS_NAME[type];
	}

}
