package kontohantering.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 *  Class for Logo panel on top of application
 */
public class TopLogoPanel extends JPanel {

	private JLabel lblBankName;
	private JLabel lblLogo;

	public TopLogoPanel() {
		setPreferredSize(new Dimension(900, 100));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 10, 20, 20));
		
		// Logo area init and properties

		ImageIcon iconLogo = new ImageIcon("img/bankLogoFull.png");
		lblLogo = new JLabel();
		lblLogo.setIcon(iconLogo);
		
		lblBankName = new JLabel("Kontohantering");
		lblBankName.setForeground(Color.BLACK);
		lblBankName.setFont(new Font("SanSerif", Font.BOLD,24));
		add(lblLogo, BorderLayout.LINE_START);
		add(lblBankName, BorderLayout.EAST);

	}

}
