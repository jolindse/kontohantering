package kontohantering.data;

import java.util.HashMap;

/*
 * Class with constants used in model
 */

public final class ModelConstants {

	private ModelConstants() {}
	
	// Constant arrays for customer and accounts
	public static final double[] INTEREST_RATES = { 0.001, 0.01, 0.013, 0.015, 0.02 };
	public static final char[] CUSTOMER_RATINGS = { 'e','d','c','b','a' };
	public static final double[] LOAN_RATES = { 0.10, 0.08, 0.08, 0.06, 0.045 };
	public static final double[] LOAN_RATIO = { 2, 2.5, 3, 4, 5 };
	
	// Bonds value per type
	
	public static final String[] BONDS_NAME = { "[b]ank Futura","[b]ank Europe", "[b]ank Sweden", "[b]ank Unethic" };
	public static final double[] BONDS_VALUE = { 114.34, 178.98, 267.65, 467.72 };
	
}
