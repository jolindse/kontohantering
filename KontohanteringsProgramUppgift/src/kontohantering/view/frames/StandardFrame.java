package kontohantering.view.frames;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JMenuBar;

import kontohantering.data.Customer;
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

	// METHODS TO CHANGE BUTTON PANELS
	
	public void editMode() {
		sideButtonPanel.setEditMode();
		bottomButtonPanel.setActive();
	}
	
	public void viewMode() {
		sideButtonPanel.setViewMode();
		bottomButtonPanel.setDisabled();
	}
	
	// METHODS TO CHANGE OUTPUT PANEL
	
	public void textMode(String strOutput){
		outputPanel.textAreaView();
		outputPanel.setText(strOutput);		
	}
	
	public void tableMode(ArrayList<Customer> currArray){
		outputPanel.showTable(currArray);
	}
	
	// OTHER METHODS
	
	public void editCustomerFrame(Customer currCustomer) {
		EditCustomerFrame editFrame = new EditCustomerFrame(currCustomer);
	}

	@Override
	void closureBehaviour() {
		System.exit(0);
		
	}
}