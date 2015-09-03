package kontohantering.logic;

import javax.swing.SwingUtilities;

import kontohantering.view.StandardFrame;

public class Application {
	
	public static void main (String[] args) {
	
		/* Start swing code. Use method runApplication() to
		 * start view, model and controller.
		 */
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				runApplication();
			}
		});

	}

	public static void runApplication() {

		// Initialize view
		StandardFrame view = new StandardFrame();

	}
	
}
