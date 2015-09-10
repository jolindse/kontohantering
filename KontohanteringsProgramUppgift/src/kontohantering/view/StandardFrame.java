package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import kontohantering.data.CustomerDB;
import kontohantering.logic.Controller;

/* Mainframe class
 * ----------------
 * Set up the main JFrame thats the program hub
 */

public class StandardFrame extends JFrame {

	private static Controller controller;
	
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private TopLogoPanel topLogoPanel;

	public StandardFrame() {

		// Set displayed name of windows and layout
		super("[b]ank kontohantering v0.07");
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
		
		setJMenuBar(createMenu());
		
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
	
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		// File menu
		
		JMenu fileMenu = new JMenu("Arkiv");
		JMenuItem exportCustomers = new JMenuItem("Exportera databas...");
		JMenuItem exitProgram = new JMenuItem("Avsluta");
		fileMenu.add(exportCustomers);
		fileMenu.addSeparator();
		fileMenu.add(exitProgram);
		
		// Test menu
		
		JMenu testMenu = new JMenu("Test");
		JMenuItem editFrame = new JMenuItem("Visa redigerafönster");
		JMenuItem outputDB = new JMenuItem("Skriv ut DB");
		JCheckBoxMenuItem editMode = new JCheckBoxMenuItem("Visa redigeringsläge");
		JCheckBoxMenuItem tableShow = new JCheckBoxMenuItem("Visa tabellvy");
		JCheckBoxMenuItem bottomEnabled = new JCheckBoxMenuItem("Bottenpanel aktiverad");
		JMenuItem saveDB = new JMenuItem("Spara DB");
		JMenuItem loadDB = new JMenuItem("Ladda DB");
		JMenuItem tableLoad = new JMenuItem("Starta tabell");
		
		tableShow.setSelected(false);
		
		testMenu.add(editFrame);
		testMenu.addSeparator();
		testMenu.add(loadDB);
		testMenu.add(saveDB);
		testMenu.add(outputDB);
		testMenu.addSeparator();
		testMenu.add(editMode);
		testMenu.add(bottomEnabled);
		testMenu.addSeparator();
		testMenu.add(tableLoad);
		testMenu.add(tableShow);
		
		menuBar.add(fileMenu);
		menuBar.add(testMenu);
		
		// Actions for test menu
		
		
		bottomEnabled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem chkbxBottomPanel = (JCheckBoxMenuItem) e.getSource();
				if(chkbxBottomPanel.isSelected()){
					bottomButtonPanel.setActive();;
				} else {
					bottomButtonPanel.setDisabled();
				}
				
			}
		});
		
		
		editMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem chkbxEditMode = (JCheckBoxMenuItem) e.getSource();
				if(chkbxEditMode.isSelected()){
					sideButtonPanel.setEditMode();
				} else {
					sideButtonPanel.setViewMode();
				}
				
			}
		});
			
			
		
		editFrame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditCustomerFrame editCustomerFrame = new EditCustomerFrame();
				
			}
		});
		
		tableShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem showTableOnOff = (JCheckBoxMenuItem) e.getSource();
				
				if(showTableOnOff.isSelected()){
					outputPanel.tableView();
				} 
				if(!showTableOnOff.isSelected()){
					outputPanel.textAreaView();
				}
			}
		});
		
		outputDB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				outputPanel.putTextTxtArea(controller.outputDB());
				
			}
		});
		
		saveDB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveDB();
			}
		});
		
		loadDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadDB();
			}
		});
		
		
		return menuBar;
	}
	
	// CONTROLLER GET AND SET
	
	public void setController(Controller controller){
		this.controller = controller;
	}

	public static Controller getController() {
		return controller;
	}
	
	// METHODS TO AFFECT COMPONENTS
	
	public void setTableDataModel(CustomerDB customerDB){
		outputPanel.setTableDataModel(customerDB);
	}
}
