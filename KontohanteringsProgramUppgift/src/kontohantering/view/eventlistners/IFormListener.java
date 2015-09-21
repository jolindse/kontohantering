package kontohantering.view.eventlistners;

import java.util.EventListener;

public interface IFormListener extends EventListener {
	public void newUserFormEvent(FormEvent e);
	public void editUserFormEvent(FormEvent e);

}
