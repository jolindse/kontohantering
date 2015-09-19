package kontohantering.view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import kontohantering.data.Customer;
import kontohantering.logic.Controller;
import kontohantering.view.eventlistners.ITableListener;
import kontohantering.view.eventlistners.TableEvent;
import kontohantering.view.formstables.CustomerTableModel;

/*
 * Class for output field
 * ----------------------
 * Default behavior is a JTextArea for output.
 * Alternative behavior is a JTable for presenting multiple accounts and allow for choice.
 */

public class OutputPanel extends JPanel {

	private JTextArea txtAOutput;
	private JTable tblOutput;
	private CustomerTableModel tblModel;
	private JScrollPane scrPaneOut;
	private ITableListener tableListener;
	private boolean tableInit;
	private boolean tableMode;
	private String buttonAction;

	public OutputPanel() {

		// Set size panel and add some padding
		setPreferredSize(new Dimension(770, 600));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 20, 0, 20));

		// Set controller as listener
		tableListener = Controller.getController();

		// Make sure table model isn't initiated before data is available
		tableInit = false;
		tableMode = false;

		// Init the text output area and set properties.
		txtAOutput = new JTextArea();
		txtAOutput.setEditable(false);
		add(txtAOutput, BorderLayout.SOUTH);
		txtAOutput.setVisible(true);

		scrPaneOut = new JScrollPane();
		scrPaneOut.setPreferredSize(new Dimension(700, 560));
		scrPaneOut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrPaneOut.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrPaneOut);

		scrPaneOut.setVisible(true);

		// Start with text view as default.
		textAreaView();
	}

	private void tableView() {
		if (tableInit) {
			scrPaneOut.setViewportView(tblOutput);
		} else {
			// Init the table output for search queries
			tableMode = true;
			tableInit = true;
			tblModel = new CustomerTableModel();
			tblOutput = new JTable(tblModel);
			tblOutput.setPreferredSize(new Dimension(700, 560));
			tblOutput.setVisible(true);
			add(tblOutput, BorderLayout.SOUTH);
			scrPaneOut.setViewportView(tblOutput);
			tblOutput.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					boolean customerSelected = false;
					int row = tblOutput.getSelectedRow();
					TableEvent te = null;
					if (row >= 0) {
						customerSelected = true;
						if (e.getClickCount() == 1) {
							te = new TableEvent(e.getSource(), tblModel.getSelectedCustomer(row), tableMode, customerSelected, 1);
						}
						if (e.getClickCount() >= 2) {
							te = new TableEvent(e.getSource(), tblModel.getSelectedCustomer(row), tableMode, customerSelected, 2);
						}
					} else {
						te = new TableEvent(e.getSource(), null , tableMode, false , 0);
					}
					
					tableListener.tableEventOccured(te);
				}
			});

		}
	}

	public void textAreaView() {
		scrPaneOut.setViewportView(txtAOutput);
		tableMode = false;
	}

	public void setText(String outputText) {
		txtAOutput.setText(outputText);
	}

	public void showTable(ArrayList<Customer> customerArrayPart) {
		tableView();
		tblModel.setDB(customerArrayPart);
		tblModel.fireTableDataChanged();
	}

	public void dataChanged() {
		tblModel.fireTableDataChanged();
	}

/*	public boolean isTabelMode() {
		return tableMode;
	}
*/
/*	public boolean setSelectedCustomer() {
		if (tableMode && tblOutput.getSelectedRow() >= 0) {
			int row = tblOutput.getSelectedRow();
			controller.setSelectedCustomer(tblModel.getSelectedCustomer(row));
			return true;
		} else {
			return false;
		}
	}
*/
}
