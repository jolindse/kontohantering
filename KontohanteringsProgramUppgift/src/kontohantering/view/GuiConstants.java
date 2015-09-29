package kontohantering.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;

/* Class to store GUI constants such as handlers for logos,
 * button dimensions and other GUI-wide variables.
 */

public final class GuiConstants {

	
	// Prevents instansiation.	
	private GuiConstants(){}
	
	// Software version
	
	public static final String SOFTWARE_VERSION = "1.0";
	
	// Dimensions
	public static final Dimension BUTTONDIM = new Dimension(120,40);
	
	// Logos
	public static final ImageIcon LOGOMINI = new ImageIcon("prgresources/bankLogoSymbolSmall.png");
	public static final ImageIcon LOGOSMALL = new ImageIcon("prgresources/bankLogoSymbol.png");
	public static final ImageIcon LOGOFULL = new ImageIcon("prgresources/bankLogoFull.png");

	// Fonts
	public static final Font FONTSMALL = new Font("Lato", Font.BOLD, 14);
	public static final Font FONTMEDIUM = new Font("Lato", Font.BOLD, 20);
	public static final Font FONTLARGE = new Font("Lato", Font.BOLD, 36);
	
	// Colors
	public static final Color BANKBLUE = new Color(28,106,133);
	
}
