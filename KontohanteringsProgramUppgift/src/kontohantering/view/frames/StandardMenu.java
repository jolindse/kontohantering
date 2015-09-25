package kontohantering.view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import kontohantering.logic.Controller;
import kontohantering.view.panels.BottomButtonPanel;
import kontohantering.view.panels.OutputPanel;
import kontohantering.view.panels.SideButtonPanel;

/*
 *  Class for menubar in standard frame
 */

public class StandardMenu extends JMenuBar {

	private StandardFrame view;
	private SideButtonPanel sideButtonPanel;
	private BottomButtonPanel bottomButtonPanel;
	private OutputPanel outputPanel;
	private Controller controller;

	public StandardMenu(StandardFrame view, BottomButtonPanel bottomButtonPanel, SideButtonPanel sideButtonPanel,
			OutputPanel outputPanel) {

		this.view = view;
		this.bottomButtonPanel = bottomButtonPanel;
		this.sideButtonPanel = sideButtonPanel;
		this.outputPanel = outputPanel;
		controller = Controller.getController();

		JMenuBar menuBar = this;

		// FILE MENU

		JMenu fileMenu = new JMenu("Arkiv");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem exportCustomers = new JMenuItem("Exportera databas");
		JMenuItem saveDB = new JMenuItem("Spara databas");
		JMenuItem exitProgram = new JMenuItem("Avsluta");
		exportCustomers.setMnemonic(KeyEvent.VK_E);
		saveDB.setMnemonic(KeyEvent.VK_S);
		exitProgram.setMnemonic(KeyEvent.VK_Q);

		saveDB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.saveDB();
			}
		});
		
		exitProgram.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exitProgram();
				
			}
		});

		fileMenu.add(exportCustomers);
		fileMenu.add(saveDB);
		fileMenu.addSeparator();
		fileMenu.add(exitProgram);
		
		// VIEW MENU
		
		JMenu viewMenu = new JMenu("Visa");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		JMenuItem showLog = new JMenuItem("Visa log");
		JMenuItem about = new JMenuItem("Om");
		
		showLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.outputLog();
			}
		});
		
		viewMenu.add(showLog);
		viewMenu.addSeparator();
		viewMenu.add(about);
		
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);

	}

	public JMenuBar getMenu() {
		return this;
	}
}
