package Zoo;

import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.TimeUtil;

public abstract class Tier {
	protected String name;
	protected double gewicht;
	protected boolean isWeiblich;
	protected double portionsGroe�e;
	protected boolean isRaubkatze;


	public Tier(String name, double gewicht, boolean isWeiblich, double portionsGroe�e, boolean isRaubkatze) {
		super();
		this.name = name;
		this.gewicht = gewicht;
		this.isWeiblich = isWeiblich;
		this.portionsGroe�e = portionsGroe�e;
		this.isRaubkatze = isRaubkatze;

	}

	public boolean isRaubkatze() {
		return isRaubkatze;
	}

	public void setRaubkatze(boolean isRaubkatze) {
		this.isRaubkatze = isRaubkatze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGewicht() {
		return gewicht;
	}

	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}

	public boolean isWeiblich() {
		return isWeiblich;
	}

	public void setWeiblich(boolean isWeiblich) {
		this.isWeiblich = isWeiblich;
	}

	public double getPortionsGroe�e() {
		return portionsGroe�e;
	}

	public void setPortionsGroe�e(double portionsGroe�e) {
		this.portionsGroe�e = portionsGroe�e;
	}

	public void f�ttern() {
		gewicht += gewicht/100*portionsGroe�e;

	}


	public abstract void b�rsten();

	@Override
	public String toString() {
		return "Tier [name=" + name + ", gewicht=" + gewicht + ", isWeiblich=" + isWeiblich + ", portionsGroe�e="
				+ portionsGroe�e + ", isRaubkatze=" + isRaubkatze + "]";
	}
}
