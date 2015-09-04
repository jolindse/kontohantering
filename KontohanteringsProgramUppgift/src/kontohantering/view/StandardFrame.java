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

/* Mainframe class
 * ----------------
 * Set up the main JFrame thats the program hub
 */

public class StandardFrame extends JFrame {

	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private TopLogoPanel topLogoPanel;

	public StandardFrame() {

		// Set displayed name of windows and layout
		super("BankX kontohantering v0.05");
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
		JCheckBoxMenuItem tableShow = new JCheckBoxMenuItem("Visa tabellvy");
		tableShow.setSelected(false);
		testMenu.add(tableShow);
		
		menuBar.add(fileMenu);
		menuBar.add(testMenu);
		
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
		
		return menuBar;
	}

}
