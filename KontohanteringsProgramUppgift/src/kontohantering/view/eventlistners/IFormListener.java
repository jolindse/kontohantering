package kontohantering.view.eventlistners;

import java.util.EventListener;
/*
 * Interface between form panels and controller
 */

public interface IFormListener extends EventListener {
	public void newUserFormEvent(FormEvent e);
	public void editUserFormEvent(FormEvent e);

}
