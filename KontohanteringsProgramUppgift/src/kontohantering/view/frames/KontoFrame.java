package kontohantering.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public abstract class KontoFrame extends JFrame {

	public KontoFrame (String frameName, int width, int height) {
	super(frameName);
	Dimension size = new Dimension(width, height);
	
	// Set displayed name of windows and layout
	this.setIconImage(Toolkit.getDefaultToolkit().getImage("prgresources/bankLogoSymbolSmall.png"));
	
	// Set native platform look And feel.
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
		e.printStackTrace();
	}

	// Set size and closure behavior
	this.setSize(size);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	}
}