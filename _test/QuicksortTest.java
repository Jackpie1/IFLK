package _test;


import gui.GUI;

import java.util.Random;

import linear.List;
import linear.ListWithViewer;

public class QuicksortTest {
	private List<String> avengers;
	private int anzahlVergleiche;

	public QuicksortTest(){
		anzahlVergleiche = 0;
	}

	public ListWithViewer<String> quicksort(List<String> pStrings){
		if (pStrings.isEmpty()) {
			return null;
		}
		pStrings.toFirst();
		pStrings.next();
		if(!pStrings.hasAccess()) {
			return (ListWithViewer<String>)pStrings;
		}
		ListWithViewer<String> linkeListe= new ListWithViewer<>();
		ListWithViewer<String> rechteListe= new ListWithViewer<>();
		ListWithViewer<String> ergebnis = new ListWithViewer<String>();
		pStrings.toFirst();
		String pivot = pStrings.getContent();
		pStrings.remove();
		
		while(pStrings.hasAccess()) {
			if(pStrings.getContent().compareTo(pivot)>=0) {
				linkeListe.append(pStrings.getContent());
				pStrings.remove();
			}
			else {
				rechteListe.append(pStrings.getContent());
				pStrings.remove();
			}
		}
		quicksort(linkeListe);
		quicksort(rechteListe);
		return ergebnis;		
	}

	public void quicksortTestKlein(){
		anzahlVergleiche = 0;
		avengers = new ListWithViewer<String>();
		avengers.append("Iron Man");
		avengers.append("Captain America");
		avengers.append("Thor");
		avengers.append("Spider Man");
		avengers.append("Black Widow");
		List<String> ergebnis = quicksort(avengers);
	}

	public void quicksortTestGross(int pAnzahl){
		anzahlVergleiche = 0;
		List<String>strings = erzeugen(pAnzahl);
		long startzeit = System.currentTimeMillis();
		List<String> ergebnis = quicksort(strings);
		long endzeit = System.currentTimeMillis();
		ausgeben(ergebnis);
		long verbrauchteZeit = endzeit - startzeit; 
		System.out.println("+++ Zeitverbrauch: "+verbrauchteZeit+"ms +++");
		System.out.println("+++ Anzahl Vergleiche: "+anzahlVergleiche);
	}

	/**
	 * erzeugt eine List mit zufaelligen Strings der Laenge 10.
	 * @param pAnzahl
	 */
	public List<String> erzeugen(int pAnzahl){
		List<String> ergebnis = new List<String>();
		Random r = new Random();
		System.out.println("*** erzeugen("+pAnzahl+") ***");
		for(int n=0; n<pAnzahl; n++){
			String s = "";
			for (int i=0; i<10;i++)
			{
				s += (char)(r.nextInt(26) + 65);
			}
			ergebnis.append(s);
			System.out.println(s);
		}
		return ergebnis;
	}

	public void ausgeben(List<String> pStrings){
		System.out.println();
		System.out.println("*** ausgeben() ***");
		for(pStrings.toFirst();pStrings.hasAccess(); pStrings.next()){
			System.out.println(pStrings.getContent());
		}
	}

	public static void main(String[] args) {
		new GUI(new QuicksortTest());
	}
}
