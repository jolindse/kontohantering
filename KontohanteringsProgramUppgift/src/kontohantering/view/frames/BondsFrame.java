package kontohantering.view.frames;

public class BondsFrame extends KontoFrame {

	public BondsFrame () {
		super("Fonder",500,400);
		
	}

	@Override
	void closureBehaviour() {
		this.dispose();
		
	}
}
