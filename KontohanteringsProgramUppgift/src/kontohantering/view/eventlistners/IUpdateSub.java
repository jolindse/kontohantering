package kontohantering.view.eventlistners;

public interface IUpdateSub {
	public void registerObserver(IUpdateObserver o);
	public void removeObserver(IUpdateObserver o);
	public void updateObservers();
}
