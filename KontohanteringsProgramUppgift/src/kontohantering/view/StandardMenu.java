package kontohantering.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import kontohantering.logic.Controller;

/*
 *  Class for menubar in standard frame
 *  -----------------------------------
 */

public class StandardMenu extends JMenuBar {

	private StandardFrame view;
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private Controller controller;	
	
	public StandardMenu (StandardFrame view, BottomButtonPanel bottomButtonPanel, SideButtonPanel sideButtonPanel, OutputPanel outputPanel ) {
		
		this.view = view;
		this.bottomButtonPanel = bottomButtonPanel;
		this.sideButtonPanel = sideButtonPanel;
		this.outputPanel = outputPanel;
		controller = Controller.getController();
		
		JMenuBar menuBar = this;
		
		// File menu
		
		JMenu fileMenu = new JMenu("Arkiv");
		JMenuItem exportCustomers = new JMenuItem("Exportera databas...");
		JMenuItem exitProgram = new JMenuItem("Avsluta");
		fileMenu.add(exportCustomers);
		fileMenu.addSeparator();
		fileMenu.add(exitProgram);
		
		// Test menu
		
		JMenu testMenu = new JMenu("Test");
		JMenuItem outputDB = new JMenuItem("Skriv ut DB");
		JCheckBoxMenuItem editMode = new JCheckBoxMenuItem("Visa redigeringsläge");
		JCheckBoxMenuItem tableShow = new JCheckBoxMenuItem("Visa tabellvy");
		JCheckBoxMenuItem bottomEnabled = new JCheckBoxMenuItem("Bottenpanel aktiverad");
		JMenuItem saveDB = new JMenuItem("Spara DB");
		JMenuItem loadDB = new JMenuItem("Ladda DB");
		JMenuItem tableLoad = new JMenuItem("Starta tabell");
		
		tableShow.setSelected(false);
		
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
			
		
		tableShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem showTableOnOff = (JCheckBoxMenuItem) e.getSource();
				
				if(showTableOnOff.isSelected()){
					outputPanel.showTableFull();
					view.setEditFrame();
					
				} 
				if(!showTableOnOff.isSelected()){
					outputPanel.textAreaView();
				}
			}
		});
		
		tableLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				outputPanel.showTableFull();
				view.setEditFrame();
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
		
	}
	
	public JMenuBar getMenu(){
		return this;
	}
}
