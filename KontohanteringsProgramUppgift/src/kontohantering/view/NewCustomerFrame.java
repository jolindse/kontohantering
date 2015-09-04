package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/*
 * Add a new customer frame class
 * ------------------------------
 * Window to pop up to put in user details adding a new customer.
 */

public class NewCustomerFrame extends JFrame {

	private static boolean isActive;

	public NewCustomerFrame() {

		super("Lägg till ny kund");

		if (!isActive) {
			isActive = true;

			setLayout(new BorderLayout());
			setSize(500, 400);
			setResizable(false);
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			// Set native platform look And feel.
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Setup top panel

			JPanel topPanel = new JPanel();
			topPanel.setPreferredSize(new Dimension(500,50));
			topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
			topPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
			
			
			JLabel lblTopName = new JLabel("Ny kund");
			lblTopName.setForeground(Color.BLACK);
			lblTopName.setFont(new Font("SanSerif", Font.BOLD, 18));
			
			topPanel.add(Box.createGlue());
			topPanel.add(lblTopName);
			
			// Setup side panel
			
			JPanel sidePanel = new JPanel();
			sidePanel.setPreferredSize(new Dimension(76,200));
			sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
			ImageIcon smallLogo = new ImageIcon("img/bankLogoSymbol.png");
			JLabel lblLogo = new JLabel();
			lblLogo.setIcon(smallLogo);
			sidePanel.add(lblLogo);
			
			
			// Setup inputform panel

			InputForm inputForm = new InputForm();

			// Setup bottom panel and buttons that resides in it.

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(500,100));
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

			JButton btnAddCustomer = new JButton("Lägg till ny kund");
			JButton btnCancel = new JButton("Avbryt");

			Dimension btnDim = new Dimension(120, 40);

			btnAddCustomer.setPreferredSize(btnDim);
			btnAddCustomer.setMaximumSize(btnDim);
			btnAddCustomer.setMinimumSize(btnDim);
			btnCancel.setPreferredSize(btnDim);
			btnCancel.setMaximumSize(btnDim);
			btnCancel.setMinimumSize(btnDim);

			buttonPanel.add(Box.createGlue());
			buttonPanel.add(btnAddCustomer);
			buttonPanel.add(Box.createRigidArea(new Dimension(50, 40)));
			buttonPanel.add(btnCancel);
			buttonPanel.add(Box.createGlue());

			// Add panels and set visible

			add(topPanel, BorderLayout.NORTH);
			add(sidePanel, BorderLayout.WEST);
			add(inputForm, BorderLayout.CENTER);
			add(buttonPanel, BorderLayout.SOUTH);

			setVisible(true);
			
			// Make sure we set isActive to false when closing window.
			
			this.addWindowListener(new WindowListener() {
							
				@Override
				public void windowClosed(WindowEvent e) {
					isActive = false;
				}

				@Override
				public void windowActivated(WindowEvent e){}

				@Override
				public void windowClosing(WindowEvent e) {}

				@Override
				public void windowDeactivated(WindowEvent e) {}

				@Override
				public void windowDeiconified(WindowEvent e) {}

				@Override
				public void windowIconified(WindowEvent e) {}

				@Override
				public void windowOpened(WindowEvent e) {}
				}
			);
			
		} else {
			JOptionPane.showMessageDialog(null,
					"En kund håller redan på att läggas till. Endast en sådan operation åt gången är tillåten");
		}
	}

}
