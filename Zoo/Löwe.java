package Zoo;

public class L�we extends Raubkatze{

	public L�we(String name, double gewicht, boolean isWeiblich, int danger) {		
		super(name, gewicht, isWeiblich, 2.5, danger);

	}

	@Override
	public void br�llen() {
		System.out.println("Roar!");
	}


}
