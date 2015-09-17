package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.text.StyleContext.SmallAttributeSet;
import static kontohantering.view.GuiConstants.*;
import kontohantering.data.Customer;

/* Mainframe class
 * ----------------
 * Set up the main JFrame thats the program hub
 */

public class StandardFrame extends JFrame {

	private JMenuBar standardMenu;
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private TopLogoPanel topLogoPanel;

	public StandardFrame() {

		// Set displayed name of windows and layout
		super("[b]ank kontohantering v0.3");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("prgresources/bankLogoSymbolSmall.png"));
		setLayout(new BorderLayout());
		// Set native platform look And feel.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		
		// Set size and closure behavior
		setSize(900, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

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