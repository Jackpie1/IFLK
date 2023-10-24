package Zoo;

public class Nilpferd extends Tier{

	

	public Nilpferd(String name, double gewicht, boolean isWeiblich) {
		super(name, gewicht, isWeiblich, 1,false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bürsten() {
		System.out.println("Schnauben");
		
	}

}
