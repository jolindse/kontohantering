package kontohantering.view.frames;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;

import kontohantering.view.panels.BottomButtonPanel;
import kontohantering.view.panels.OutputPanel;
import kontohantering.view.panels.SideButtonPanel;
import kontohantering.view.panels.TopLogoPanel;

/* Mainframe class
 * ----------------
 * Set up the main JFrame thats the program hub
 */

public class StandardFrame extends KontoFrame {

	private JMenuBar standardMenu;
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private TopLogoPanel topLogoPanel;

	public StandardFrame() {

		// Set displayed name of windows and layout
		super("[b]ank kontohantering v0.3",900, 800);
		setLayout(new BorderLayout());
		
		// Add elements to frame
		
		sideButtonPanel = new SideButtonPanel();
		bottomButtonPanel = new BottomButtonPanel();
		outputPanel = new OutputPanel();
		topLogoPanel = new TopLogoPanel();
		standardMenu = new StandardMenu(this, bottomButtonPanel, sideButtonPanel, outputPanel).getMenu();
		
		setJMenuBar(standardMenu);
		
		getContentPane().add(sideButtonPanel, BorderLayout.WEST);
		getContentPane().add(bottomButtonPanel, BorderLayout.SOUTH);
		getContentPane().add(outputPanel, BorderLayout.EAST);
		getContentPane().add(topLogoPanel, BorderLayout.NORTH);
		

	}

	// METHODS TO CHANGE MODES
	
	public void setEditFrame() {
		sideButtonPanel.setEditMode();
		bottomButtonPanel.setActive();
	}
	
	public void setViewFrame() {
		sideButtonPanel.setViewMode();
		bottomButtonPanel.setDisabled();
	}
	
	public void setText(String strOutput){
		outputPanel.textAreaView();
		outputPanel.putTextTxtArea(strOutput);		
	}
	
	public void editCustomer() {
		EditCustomerFrame ed = new EditCustomerFrame();
	}
}