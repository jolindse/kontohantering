package kontohantering.data;

import java.util.HashMap;
import java.util.Map;

/*
 * Class to handle bonds sold and bought by bank
 * 
 */


import static kontohantering.data.ModelConstants.*;

public class BondsDB {

	private Map<String,Double> bondsDB;
	
	public BondsDB (){
		bondsDB = new HashMap<>();
		loadBondsValues();
	}
	
	private void loadBondsValues(){
		/*
		 *  Method to load data into the bondsDB.
		 * 	Could be more of a DAO-procedure if development continues.
		 */
		
		for (int i = 0; i < BONDS_NAME.length; i++){
			bondsDB.put(BONDS_NAME[i],BONDS_VALUE[i]);
		}
	}
/*	
	public String outPutBondsDB(){
		String strOutput = "";
		for (Map.Entry<String, Double> currentBond: bondsDB.entrySet()){
			strOutput += "Fond: " + currentBond.getKey() + ": a' " + currentBond.getValue() + " kr/st.\n";
		}
		return strOutput;
	}
	*/
	public String[] getNames(){
		/*
		 * Used to return bonds names in string array
		 */
		String[] returnArray = new String[bondsDB.size()];
		int indexCounter = 0;
		for (Map.Entry<String, Double> currentBond: bondsDB.entrySet()){
			returnArray[indexCounter] = currentBond.getKey();
			indexCounter++;
		}
		return returnArray;
	}
	
	public double getBondValue(String key){
		/*
		 * Used to return value of specific bond
		 */
		return bondsDB.get(key);
	}
	
}
