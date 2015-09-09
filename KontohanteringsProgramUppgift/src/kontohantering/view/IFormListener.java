package kontohantering.view;

import java.util.EventListener;

import kontohantering.view.FormEvent;

public interface IFormListener extends EventListener {
	public void formEventOccured(FormEvent e);

}
