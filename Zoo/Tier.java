package Zoo;

import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.TimeUtil;

public abstract class Tier {
	protected String name;
	protected double gewicht;
	protected boolean isWeiblich;
	protected double portionsGroeße;
	protected boolean isRaubkatze;


	public Tier(String name, double gewicht, boolean isWeiblich, double portionsGroeße, boolean isRaubkatze) {
		super();
		this.name = name;
		this.gewicht = gewicht;
		this.isWeiblich = isWeiblich;
		this.portionsGroeße = portionsGroeße;
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

	public double getPortionsGroeße() {
		return portionsGroeße;
	}

	public void setPortionsGroeße(double portionsGroeße) {
		this.portionsGroeße = portionsGroeße;
	}

	public void füttern() {
		gewicht += gewicht/100*portionsGroeße;

	}


	public abstract void bürsten();

	@Override
	public String toString() {
		return "Tier [name=" + name + ", gewicht=" + gewicht + ", isWeiblich=" + isWeiblich + ", portionsGroeße="
				+ portionsGroeße + ", isRaubkatze=" + isRaubkatze + "]";
	}
}
