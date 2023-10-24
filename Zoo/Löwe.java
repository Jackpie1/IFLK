package Zoo;

public class Löwe extends Raubkatze{

	public Löwe(String name, double gewicht, boolean isWeiblich, int danger) {		
		super(name, gewicht, isWeiblich, 2.5, danger);

	}

	@Override
	public void brüllen() {
		System.out.println("Roar!");
	}


}
