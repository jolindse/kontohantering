package kontohantering.view.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
/*
 * Abstract class that all frames in program inherits from.
 * --------------------------------------------------------
 * Used to set a few common values and add the abstract method of closureBehavoir.
 */
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

	this.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			closureBehaviour();
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	// Set size and closure behavior
	this.setSize(size);
	setLocationRelativeTo(null); 
	this.setResizable(false);
	this.setVisible(true);
	}
	
	abstract void closureBehaviour();
}