package Zoo;

public abstract class Raubkatze extends Tier{
	protected int danger;
	
	public Raubkatze(String name, double gewicht, boolean isWeiblich, double portionsGroeße, int danger) {
		super(name, gewicht, isWeiblich, portionsGroeße,true);
		this.danger = danger;
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	@Override
	public void bürsten() {
		System.out.println("Purrpurrpurr");
	}

	
	public abstract void brüllen();
}
