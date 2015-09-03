package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 *  Class for Logo panel on top of application
 */
public class TopLogoPanel extends JPanel {

	private JLabel lblBankName;

	public TopLogoPanel() {
		setPreferredSize(new Dimension(900, 100));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// Logo area init and properties

		lblBankName = new JLabel("BankX");
		lblBankName.setForeground(Color.BLACK);
		lblBankName.setFont(new Font("SanSerif", Font.BOLD, 36));
		add(lblBankName, BorderLayout.CENTER);

	}

}
