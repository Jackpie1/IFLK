package bacheloristin;

import java.sql.Date;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;

/**
 * 
 * 
 * @author Manuel Grifka
 * @version v0.2 2019-10-28
 */
public class BacheloristinStaffel {


	// Attribute 
	private int jahr; 
	private Kandidat bacheloristin; 
	private ListWithViewer<Kandidat> jungsListe;

	// Attribut testkandidat dient nur zum Testen der Methoden, die einen
	private Kandidat testkandidat;


	public BacheloristinStaffel(int pJahr)
	{
		jahr = pJahr;		
		jungsListe = new ListWithViewer<Kandidat>();

		initJungsListeTestkandidatBacheloristin(pJahr);


	}

	public void rausschmeissen(Kandidat pKandidat) {
		pKandidat.fliegtRaus();
	}

	public boolean isKandidat(String pKandidatName) {
		if(jungsListe== null) {
			return false;
		}
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			if(pKandidatName.equals(jungsListe.getContent().getName())) {
				return true;
			}
			jungsListe.next();
		}
		return false;
	}
	
	public void alleResetten() {
		if(jungsListe== null) {
			return;
		}
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			jungsListe.getContent().resetQuotenPunkte();
			jungsListe.next();
		}
	}

	public int anzahlAusgeschiedener() {
		int result = 0;
		if(jungsListe== null) {
			return result;
		}

		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			if(!jungsListe.getContent().isNochDabei()) {
				result++;
			}
			jungsListe.next();
		}
		return result;
	}



	public void hatGelaestert(String pNameWer, String pNameUeberWen) {
		if(jungsListe== null) {
			return;
		}
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			if(jungsListe.getContent().getName().equals(pNameWer)) {
				jungsListe.getContent().erhöheQuotenPunkte(20);
				break;
			}
			jungsListe.next();
		}
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			if(jungsListe.getContent().getName().equals(pNameUeberWen)) {
				jungsListe.getContent().erhöheQuotenPunkte(10);
				break;
			}
			jungsListe.next();
		}
	}


	public void knutschtBacheloristin(String pName) {
		if(jungsListe== null) {
			return;
		}
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			if(jungsListe.getContent().getName().equals(pName)) {
				jungsListe.getContent().erhöheQuotenPunkte(50);
				return;
			}
			jungsListe.next();
		}
	}


	public Kandidat gibQuotenLetzten() {
		if(jungsListe== null) {
			return null;
		}
		jungsListe.toFirst();
		Kandidat result = jungsListe.getContent();
		while(jungsListe.hasAccess()) {
			if(jungsListe.getContent().getQuotenPunkte()<result.getQuotenPunkte()) {
				result = jungsListe.getContent();
			}
			jungsListe.next();
		}
		return result;
	}

	public int getDate() {
		long m= System.currentTimeMillis();
		Date date= new Date(m);
		String s = date.toString();
		s = s.replaceAll("-","");
		return Integer.parseInt(s);
	}

	public int getAlter(Kandidat pKandidat) {
		int d = getDate();
		return d-=pKandidat.getGebDatum();
	}

	public List<Kandidat> gibRentnerListe() {
		if(jungsListe== null) {
			return null;
		}
		ListWithViewer<Kandidat> nL = new ListWithViewer<>();
		jungsListe.toFirst();
		while(jungsListe.hasAccess()) {
			Kandidat k = jungsListe.getContent();
			if(getAlter(k)>300000) {
				nL.append(k);
			}
			jungsListe.next();
		}
		return nL;
	}
	




	/*
	 * -------------------------------------------------------------------------------------------
	 * ab hier nur noch init und main
	 * -------------------------------------------------------------------------------------------
	 */

	private void initJungsListeTestkandidatBacheloristin(int pJahr) {
		switch (pJahr) {
		case 2019:
			bacheloristin = new Kandidat("Fiona Diaz", 19900106, true);

			jungsListe.append(new Kandidat("Diego de Deus", 19601030, false)); 
			jungsListe.append(new Kandidat("George Coolknee", 19610506, false));
			jungsListe.append(new Kandidat("Shria Ekstroem", 19900102, false));
			jungsListe.append(new Kandidat("Edward Heran", 19910217, false));
			jungsListe.append(new Kandidat("Adam Douglas", 19520311, false));
			jungsListe.append(new Kandidat("Wolfgang Howowitz", 19810229, false));
			jungsListe.append(new Kandidat("Stefan Falking", 19420108, false));
			jungsListe.append(new Kandidat("Thomas Enis", 19980906, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Don Dump", 19460614, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Christian Ronald", 19850205, false));
			break;

		case 2018:
			bacheloristin = new Kandidat("Bibi Botox", 19910909, true);

			jungsListe.append(new Kandidat("Paul Poser", 19860107, false)); 
			jungsListe.append(new Kandidat("Leo Lackaffe", 19891117, false));
			jungsListe.append(new Kandidat("Alex Honey", 19920102, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Anton Donis", 19821223, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Robert Geissbock", 19720429, false));
			jungsListe.append(new Kandidat("Magnus Dautit", 19890802, false));
			jungsListe.append(new Kandidat("Thomas Hayopai", 19711109, false));
			jungsListe.append(new Kandidat("Shawn Schoenling", 19990107, false));
			jungsListe.append(new Kandidat("Gustave Giro", 20000505, false));
			jungsListe.append(new Kandidat("Alfons Bergmeier", 19990431, false));
			break;

		case 2017:
			bacheloristin = new Kandidat("Anna Krohn-Ismus", 19900722, true);

			jungsListe.append(new Kandidat("Kai Pirinha", 19970324, false)); 
			jungsListe.append(new Kandidat("Bill Dung", 19791009, false));
			jungsListe.append(new Kandidat("Jim Panse", 20000315, false));
			jungsListe.append(new Kandidat("Dieter Moskanne", 19670419, false));

			// Attribut testkandidat zum Testen von Methoden, die ein Kandidat-Objekt als Parameter erhalten
			testkandidat = new Kandidat("Ernst Haft", 19990101, false);
			jungsListe.append(testkandidat);

			jungsListe.append(new Kandidat("Lars Vegas", 19930602, false));
			jungsListe.append(new Kandidat("Andi Arbeit", 19890501, false));
			jungsListe.append(new Kandidat("Gerd Raenkaux-Tomate", 19871223, false));
			jungsListe.append(new Kandidat("Ismael Lieba", 19871030, false));
			jungsListe.append(new Kandidat("Karl Auer", 19931111, false));
			break;

		}
	}



	@Override
	public String toString() {
		return "Jahr " + jahr + ", Bacheloristin: " + bacheloristin;
	}



	public static void main(String[] args) {
		BacheloristinStaffel bs = new BacheloristinStaffel(2019);
		new GUI(bs);
	}

}
