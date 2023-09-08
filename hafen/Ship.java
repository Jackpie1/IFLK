package hafen;

import java.util.Arrays;

import org.jcp.xml.dsig.internal.dom.DOMXMLObject;

import gui.GUI;

public class Ship {
	private String name;
	private double maxWeight;	
	private Container[] loadedContainers;
	private String destination;
	private int crewMember;

	// Das folgende Attribut dient nur zu Testzwecken
	private Container testcontainer = new Container(26.3, 15.6);


	public Ship(String name, double maxWeight, String destination, int crewMember) {
		this.name = name;
		this.crewMember = crewMember;
		this.maxWeight = maxWeight;
		this.destination = destination;
		loadedContainers = new Container[6];
		testcontainer.setDestination("Hamburg");
		testcontainer.setLoadedProduct("LEGO Sets");

	}

	// TODO: automatisch getters und setters generieren lassen (Source -> Generate getters and setters)
	// Alle getters generieren und überlegen, welche setters man braucht

	public String getName() {
		return name;
	}
	
	public void hireCrewMembers(int numberOfNewMembers) {
		if(numberOfNewMembers > 0)
		{
			crewMember += numberOfNewMembers;
		}
	}

	public void fireOneCrewMember() {
		if(crewMember > 0)
		{
			crewMember -=1;	
		}
	}

	public boolean unloadContainer(int position) {
		if(position < loadedContainers.length && position >= 0 && loadedContainers[position] != null)
		{
			loadedContainers[position] = null;
			return true;
		}
		return false;
	}

	public double calculateCurrentWeight() {
		double a= 0;
		for(int i = 0; i < loadedContainers.length; i++)
		{
			if(loadedContainers[i] != null)
			{
				a += loadedContainers[i].getLoadedWeight();
			}
		}
		return a;
	}

	public int numberOfFirstEmptyPosition() {
		for(int i = 0; i < loadedContainers.length; i++)
		{
			if(loadedContainers[i] == null)
			{
				return i;
			}
		}
		return -1;

	}

	public int emptyPositions() {
		int result = 0;
		for(int i = 0; i < loadedContainers.length; i++)
		{

			if(loadedContainers[i] == null)
			{
				result ++;
			}
		}
		return result;

	}

	public boolean checkItFits(Container newContainer) {
		double a = maxWeight -= calculateCurrentWeight();
		if(newContainer.getLoadedWeight() < a && numberOfFirstEmptyPosition() != -1)
		{
			return true;
		}
		return false;
	}

	public void load(Container newContainer) {
		if(checkItFits(newContainer) == true)
		{
			loadedContainers[numberOfFirstEmptyPosition()] = newContainer;
		}
	}

	public void tauschen(int indexA, int indexB) {
		Container a = loadedContainers[indexA];
		Container b = loadedContainers[indexB];
		loadedContainers[indexA] = b;
		loadedContainers[indexB] = a;
	}
	public void durchlaufen() {
		int emptyP = emptyPositions();
		for(int i = loadedContainers.length-1 ; i>0 ; i--) {
			tauschen(i,numberOfFirstEmptyPosition());
		}
		
		for (int i = emptyP+1 ; i > loadedContainers.length; i++)
		{
			double aktuell = loadedContainers[i].getLoadedWeight();
			double nachfolgend = loadedContainers[i+1].getLoadedWeight();
			if(aktuell > nachfolgend)
			{
				tauschen(i,i-1);
			}
		}
	}


	public void sortiern() {
		for(int i = 0; i < loadedContainers.length; i++)
		{

			if(loadedContainers[i].getLoadedWeight() > loadedContainers[i+1].getLoadedWeight())
			{

			}
		}
	}



	@Override
	public String toString() {
		return name + ", maxWeight " + maxWeight + "t, Crew "
				// + numberOfCrewMembers
				+ ", goes to " + destination;
	}

	/*
	 * Hilfsmethode zum Ausgeben der Ladung
	 */
	public void printLoad() {
		for(int i=0; i< loadedContainers.length; i++) {
			System.out.println(loadedContainers[i]);
		}
	}

	/*
	 * Hilfsmethode zum Erzeugen einer Beispielbeladung
	 */
	private void createExampleoad() {
		Container c1 = new Container(24.2, 12.3);
		c1.setDestination("Hamburg");
		c1.setLoadedProduct("Oil");
		Container c2 = new Container(23.2, 23.2);
		c2.setDestination("Lisbon");
		c2.setLoadedProduct("Computer chips");
		Container c3 = new Container(15.0, 4.2);
		c3.setDestination("Antwerpen");
		c3.setLoadedProduct("Hop");
		loadedContainers[0] = c1;
		loadedContainers[2] = c2;
		loadedContainers[5] = c3;
	}


	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public static void main(String[] args) {
		Ship ship = new Ship("Floating Sibi", 111.2, "Lisbon",5);
		ship.createExampleoad();
		new GUI(ship);
	}

}
