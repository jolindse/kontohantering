package kontohantering.view.eventlistners;
/*
 * Interface for the update subject
 */
public interface IUpdateSub {
	public void registerObserver(IUpdateObserver o);
	public void removeObserver(IUpdateObserver o);
	public void updateObservers();
}
