package bacheloristin;

public class Kandidat {
	private String name;
	private int gebDatum;
	private boolean weiblich;
	private int quotenPunkte;
	private boolean nochDabei;



	public Kandidat(String name, int gebDatum, boolean weiblich) {
		super();
		this.name = name;
		this.gebDatum = gebDatum;
		this.weiblich = weiblich;
		this.quotenPunkte = 0;
		this.nochDabei = true;
	}public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getGebDatum() {
		return gebDatum;
	}


	public void setGebDatum(int gebDatum) {
		this.gebDatum = gebDatum;
	}


	public boolean isWeiblich() {
		return weiblich;
	}


	public void setWeiblich(boolean weiblich) {
		this.weiblich = weiblich;
	}


	public int getQuotenPunkte() {
		return quotenPunkte;
	}


	public void setQuotenPunkte(int quotenPunkte) {
		this.quotenPunkte = quotenPunkte;
	}


	public boolean isNochDabei() {
		return nochDabei;
	}


	public void setNochDabei(boolean nochDabei) {
		this.nochDabei = nochDabei;
	}

	public void fliegtRaus() {
		nochDabei = false;
	}

	public void resetQuotenPunkte() {
		quotenPunkte = 0;
	}

	public void erhöheQuotenPunkte(int pPunkte) {
		if(pPunkte>0) {
			quotenPunkte += pPunkte;
		}
	}
	
	public void senkeQuotenPunkte(int pPunkte) {
		if(quotenPunkte-pPunkte<0) {
			quotenPunkte -= pPunkte;
		}
	}
	
	public boolean istAelterAls(Kandidat pKandidat) {
		if(pKandidat.getGebDatum()>gebDatum) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Kandidat [name=" + name + ", gebDatum=" + gebDatum + ", weiblich=" + weiblich + ", quotenPunkte="
				+ quotenPunkte + ", nochDabei=" + nochDabei + "]";
	}
}
