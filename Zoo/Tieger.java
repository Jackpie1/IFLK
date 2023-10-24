package Zoo;

public class Tieger extends Raubkatze{
	private int streifenAnzahl;


	public Tieger(String name, double gewicht, boolean isWeiblich, int danger, int streifenAnzahl) {		
		super(name, gewicht, isWeiblich, 2, danger);
		
		this.streifenAnzahl = streifenAnzahl;
	}

	public int getStreifenAnzahl() {
		return streifenAnzahl;
	}

	public void setStreifenAnzahl(int streifenAnzahl) {
		this.streifenAnzahl = streifenAnzahl;
	}


	@Override
	public void brüllen() {
		System.out.println("Chchchau");
	}


}
