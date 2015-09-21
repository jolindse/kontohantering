package kontohantering.view.frames;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.JMenuBar;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.IUpdateObserver;
import kontohantering.view.eventlistners.IUpdateSub;
import kontohantering.view.panels.BottomButtonPanel;
import kontohantering.view.panels.OutputPanel;
import kontohantering.view.panels.SideButtonPanel;
import kontohantering.view.panels.TopLogoPanel;

/* Mainframe class
 * ----------------
 * Set up the main JFrame thats the program hub
 */

public class StandardFrame extends KontoFrame implements IUpdateObserver {

	private JMenuBar standardMenu;
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private TopLogoPanel topLogoPanel;
	private IUpdateSub updateListener;
	private StandardFrame frameHandler;
	private String currentString;
	
	public StandardFrame() {

		// Set displayed name of windows and layout
		super("[b]ank kontohantering v0.7",900, 800);
		setLayout(new BorderLayout());
		
		// Set listener for GUI updates
		
		updateListener = Controller.getController();
		updateListener.registerObserver(this);
		frameHandler = this;
		
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
		
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				updateListener.removeObserver(frameHandler);
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				updateListener.registerObserver(frameHandler);
				updateGui();
				
			}
		});

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
	
	public void textMode(){
		outputPanel.textAreaView();
		outputPanel.setText(currentString);		
	}
	
	public void tableMode(ArrayList<Customer> currArray){
		outputPanel.showTable(currArray);
	}
	
	// OTHER METHODS
	
	public void editCustomerFrame(Customer currCustomer) {
		EditCustomerFrame editFrame = new EditCustomerFrame(currCustomer);
	}
	
	public void setCurrentString(String newString){
		currentString = newString;
	}

	@Override
	void closureBehaviour() {
		System.exit(0);
		
	}

	@Override
	public void updateGui() {
		outputPanel.updateInfo(currentString);
	}
}