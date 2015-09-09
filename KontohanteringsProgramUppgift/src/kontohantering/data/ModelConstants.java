package kontohantering.data;

import java.util.HashMap;

/*
 * Class with constants used in model
 * ----------------------------------
 * 
 */

public final class ModelConstants {

	private ModelConstants() {}
	
	// Constant arrays for customer and accounts
	private static final double[] INTEREST_RATES = { 0.001, 0.01, 0.013, 0.015, 0.02 };
	private static final char[] CUSTOMER_RATINGS = { 'e','d','c','b','a' };
	
	// Bonds value per type
	
	// Mortgages 
	
	private static final double[] LOAN_RATES = { 0.10, 0.08, 0.08, 0.06, 0.045 };
	
}
