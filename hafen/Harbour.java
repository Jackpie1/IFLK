package hafen;

import java.util.Random;
import gui.GUI;
import linear.QueueWithViewer;
import linear.Stack;
import linear.StackWithViewer;

public class Harbour {
	private String country;
	private String city;
	private Ship[] shipsAtAnchor;
	private StackWithViewer<Container> cargoStack;
	private StackWithViewer<Container> cargoStack1;
	private StackWithViewer<Container> cargoStack2;
	private StackWithViewer<Container> cargoStack3;
	private QueueWithViewer<Ship> waitingLine;
	// das folgende Attribut ist nur zu Testzwecken vorhanden, es muss ansonsten nicht beachtet werden
	Ship testship = new Ship("Testship I", 187.45, "Hamburg",5);
	Ship ts3;


	public Harbour(String country, String city, int size) {		
		this.country = country;
		this.city = city;
		this.cargoStack = new StackWithViewer<Container>();
		this.cargoStack1 = new StackWithViewer<Container>();
		this.cargoStack2 = new StackWithViewer<Container>();
		this.cargoStack3 = new StackWithViewer<Container>();
		this.waitingLine = new QueueWithViewer<Ship>();
		this.shipsAtAnchor = new Ship[size];
		testship.load(new Container(15.5, 13.3));
		testship.load(new Container(22.3, 0.2));
		createCargoStack();

		ts3 =createWaitingLine();
	}
	public Ship createWaitingLine() {
		Ship s1= new Ship("s1",30.0,"Antwerpen",1);
		Ship s2= new Ship("s2",30.0,"Lisbon",5);
		Ship s3= new Ship("s3",30.0,"Shanghai",3);
		Ship s4= new Ship("s4",30.0,"Antwerpen",2);
		Ship s5= new Ship("s5",30.0,"Shanghai",45);
		waitingLine.enqueue(s1);
		waitingLine.enqueue(s2);
		waitingLine.enqueue(s3);
		waitingLine.enqueue(s4);
		waitingLine.enqueue(s5);
		return s3;
	}
	public void testShips() {
		Ship s1= new Ship("s1",30.0,"Antwerpen",1);
		Ship s2= new Ship("s2",30.0,"Lisbon",5);
		Ship s3= new Ship("s3",30.0,"Shanghai",3);
		Ship s4= new Ship("s4",30.0,"Antwerpen",2);
		shipsAtAnchor[1] = s1;
		shipsAtAnchor[2] = s2;
		shipsAtAnchor[3] = s3;
		shipsAtAnchor[4] = s4;
	}

	public void createCargoStack()
	{
		Container container1 =new Container(19.6, 15.1);
		Container container2 =new Container(21.3, 18.7);
		Container container3 =new Container(25.7, 15.4);
		Container container4 =new Container(18.5, 13.3);
		container1.setDestination("Antwerpen");
		container2.setDestination("Lisbon");
		container3.setDestination("Antwerpen");
		container4.setDestination("Shanghai");
		cargoStack.push(container1);
		cargoStack.push(container2);
		cargoStack.push(container3);
		cargoStack.push(container4);
	}

	public int countContainer(String pDestinaton) {
		int result = 0;
		while(cargoStack.isEmpty() == false) {
			Container a =cargoStack.top();
			if (a.getDestination().equals(pDestinaton)) {
				result ++;
			}
			cargoStack1.push(a);
			cargoStack.pop();
		}
		while(cargoStack1.isEmpty() == false) {
			cargoStack.push(cargoStack1.top());
			cargoStack1.pop();
		}
		return result;
	}

	public double containerWeight() {
		double result = 0;
		while(cargoStack.isEmpty() == false) {
			Container a = cargoStack.top();
			result += a.getLoadedWeight();
			cargoStack1.push(a);
			cargoStack.pop();
		}
		while(cargoStack1.isEmpty() == false) {
			cargoStack.push(cargoStack1.top());
			cargoStack1.pop();
		}
		return result;
	}

	public boolean loadCargoStack() {
		Container a = cargoStack.top();
		for(int i = 0; i < shipsAtAnchor.length; i++){
			if (shipsAtAnchor[i] != null) {
				if (a.getDestination().equals(shipsAtAnchor[i].getDestination())){
					shipsAtAnchor[i].printLoad();
					shipsAtAnchor[i].load(a);
					System.out.println(shipsAtAnchor[i].getDestination());
					shipsAtAnchor[i].printLoad();
					cargoStack.pop();
					return true;
				}
			}
		}
		return false;
	}

	public Container findContainer(String pDestinaton, double pWeightLimit) {
		Container result = null;
		boolean found = false;
		while(cargoStack.isEmpty() == false) {
			Container a =cargoStack.top();
			if (a.getDestination().equals(pDestinaton) && a.getLoadedWeight()<= pWeightLimit && found == false) {
				found = true;
				result = a;
			}
			cargoStack1.push(a);
			cargoStack.pop();
		}
		while(cargoStack1.isEmpty() == false) {
			cargoStack.push(cargoStack1.top());
			cargoStack1.pop();
		}
		return result;
	}

	public Container findHaviestContainer()
	{
		Container result = cargoStack.top();
		while(cargoStack.isEmpty() == false) {
			Container a = cargoStack.top();
			if(a.getLoadedWeight()>result.getLoadedWeight()) {
				result = a;
			}
			cargoStack1.push(a);
			cargoStack.pop();
		}
		while(cargoStack1.isEmpty() == false) {
			cargoStack.push(cargoStack1.top());
			cargoStack1.pop();
		}
		return result; 
	}


	public void sortContainerStack() {

		while (cargoStack.isEmpty() == false) {
			Container schwer = findHaviestContainer();
			boolean finde = false;
			while(cargoStack.isEmpty() == false && finde == false) {
				if(schwer.getLoadedWeight() == cargoStack.top().getLoadedWeight())
				{
					finde =true;
					cargoStack2.push(cargoStack.top());
				}
				else {
					cargoStack1.push(cargoStack.top());
				}
				cargoStack.pop();
			}
			while(cargoStack1.isEmpty() == false) {
				cargoStack.push(cargoStack1.top());
				cargoStack1.pop();
			}
		}
		while(cargoStack2.isEmpty() == false) {
			cargoStack.push(cargoStack2.top());
			cargoStack2.pop();
		}
	}


	public void optimalVerladen() {
		sortContainerStack();
		while(!cargoStack.isEmpty()) {
			Container aCon = cargoStack.top();
			boolean verladen = false;
			for(int i= 0;i < shipsAtAnchor.length; i++) {
				if( shipsAtAnchor[i] != null) {
					if(shipsAtAnchor[i].checkItFits(aCon) == true && verladen == false && shipsAtAnchor[i].getDestination().equals(aCon.getDestination())) {
						shipsAtAnchor[i].load(aCon);
						verladen = true;
						cargoStack.pop();
					}
				}
			}
			if(!cargoStack.isEmpty()) {
				if(aCon == cargoStack.top()) {
					cargoStack3.push(aCon);
					cargoStack.pop();
				}
			}
		}
		while(!cargoStack3.isEmpty()) {
			cargoStack.push(cargoStack3.top());
			cargoStack3.pop();
		}
	}

	public double approxWaitingTime() {
		double result = 0;
		QueueWithViewer<Ship> tmpQueue = new QueueWithViewer<Ship>();
		while(!waitingLine.isEmpty()) {
			result += 2.5;
			tmpQueue.enqueue(waitingLine.front());
			waitingLine.dequeue();
		}
		while(!tmpQueue.isEmpty()) {
			waitingLine.enqueue(tmpQueue.front());
			tmpQueue.dequeue();
		}
		return result;
	}

	public int numberOfFirstEmptyPosition() {
		for(int i = 0; i < shipsAtAnchor.length; i++)
		{
			if(shipsAtAnchor[i] == null)
			{
				return i;
			}
		}
		return -1;

	}

	public Ship findShipInLine() {
		Ship result = null;
		QueueWithViewer<Ship> tmpQueue = new QueueWithViewer<Ship>();
		boolean gefunden = false;
		while(!waitingLine.isEmpty()) {
			if(cargoStack.top().getDestination().equals(waitingLine.front().getDestination())&& gefunden == false) {
				result = waitingLine.front();
				gefunden = true;
			}
			tmpQueue.enqueue(waitingLine.front());
			waitingLine.dequeue();
		}
		while(!tmpQueue.isEmpty()) {
			waitingLine.enqueue(tmpQueue.front());
			tmpQueue.dequeue();

		}
		return result;
	}
	public int emptyPositions() {
		int result = 0;
		for(int i = 0; i < shipsAtAnchor.length; i++)
		{
			if(shipsAtAnchor[i] == null)
			{
				result ++;
			}
		}
		return result;
	}

	public boolean findeShip(Ship shipName) {
		boolean result = false;
		QueueWithViewer<Ship> tmpQueue = new QueueWithViewer<Ship>();
		while(!waitingLine.isEmpty()) {
			if (shipName.getName().equals(waitingLine.front().getName())) {
				result = true;
			}

			tmpQueue.enqueue(waitingLine.front());
			waitingLine.dequeue();

		}
		while(!tmpQueue.isEmpty()) {
			waitingLine.enqueue(tmpQueue.front());
			tmpQueue.dequeue();
		}
		return result;
	}

	public void shipEntfernen(String shipName) {
		QueueWithViewer<Ship> tmpQueue = new QueueWithViewer<Ship>();
		while(!waitingLine.isEmpty()) {
			if (shipName.equals(waitingLine.front().getName())) {
				waitingLine.dequeue();
			}
			else {
				tmpQueue.enqueue(waitingLine.front());
				waitingLine.dequeue();
			}
		}
		while(!tmpQueue.isEmpty()) {
			waitingLine.enqueue(tmpQueue.front());
			tmpQueue.dequeue();
		}
	}

	public void allEnterHarbour() {
		int emptyPos =emptyPositions();
		for(int i=0;i < emptyPos;i++) {
			if(!waitingLine.isEmpty()) {
				shipsAtAnchor[numberOfFirstEmptyPosition()]=waitingLine.front();
				waitingLine.dequeue();
			}
		}
	}
//sefuibzuhfbugfr
	public boolean toFront(Ship beFirstShip) {
		boolean abc = findeShip(beFirstShip);
		System.out.println(abc);
		if(abc== false) {
			return false;
		}
		QueueWithViewer<Ship> tmpQueue = new QueueWithViewer<Ship>();
		QueueWithViewer<Ship> tmpQueue1 = new QueueWithViewer<Ship>();
		while(!waitingLine.isEmpty()) {
			if (beFirstShip == waitingLine.front()) {
				tmpQueue1.enqueue(waitingLine.front());
				waitingLine.dequeue();
			}
			else {
				tmpQueue.enqueue(waitingLine.front());
				waitingLine.dequeue();
			}
		}
		while(!tmpQueue1.isEmpty()) {
			waitingLine.enqueue(tmpQueue1.front());
			tmpQueue1.dequeue();
		}
		while(!tmpQueue.isEmpty()) {
			waitingLine.enqueue(tmpQueue.front());
			tmpQueue.dequeue();
		}

		return true;
	}



	public int countShipsInHabour() {
		// TODO
		return -1;		
	}

	public int giveFirstFreeAnchorage() {
		// TODO
		return -1;		
	}

	public boolean leave(String leavingShipName) {
		// TODO
		return false;
	}

	public boolean arrive(Ship arrivingShip) {
		// TODO
		return false;
	}


	/*
	 * Hilfsmethode zum Ausgaben aller vor Anker liegenden Schiffe auf der Konsole
	 */
	public void printShips() {
		for (Ship ship : shipsAtAnchor) {
			System.out.println(ship);
		}
	}

	/*
	 * getters & setters
	 */

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public Ship[] getShipsAtAnchor() {
		return shipsAtAnchor;
	}

	// ------------------------------------------------------------------

	/*
	 * Hilfsmethode zum Erzeugen der zu Beginn im Hamburger Hafen liegenden Schiffe 
	 */
	private void createAndStoreShips() {
		String[] shipNames = {"Titanic", "Queen Mary", "Bismarck", "Yamato", "Santa Maria",
				"USS Enterprise", "HMS Victory", "Endeavour", "Mayflower", "Fitzgerald",
				"Black Pearl", "Cutty Sark", "Golden Hind", "Nautilus", "Lusitania",
				"Constitution", "Discovery", "Nina", "Pinta", "Santa Clara"};
		String[] destinationNames = {"Rotterdam", "Lisbon", "Piräus", "Shanghai", "Los Angeles", "Guangzhou", "Antwerpen", "Le Havre", "Singapur"};
		String[] countryNames = {"Netherlands", "Portugal", "Greece", "China", "USA", "China", "Belgium", "France", "Singapur"};
		double maxWeightLimit = 250.0;
		Random random = new Random();



		/* Create Harbours --> Has to be moved to administration class for the harbours later
        Harbour[] destinations = new Harbour[destinationNames.length];
        for(int i=0; i<destinationNames.length; i++) {
        	destinations[i] = new Harbour(destinationNames[i], countryNames[i], 25);
        } */       

		// Create 20 ships for Hamburg
		for (int i = 0; i < 20; i++) {
			String name = shipNames[i];            
			double maxWeight = (double)(Math.round(100 * random.nextDouble() * maxWeightLimit)+5000)/100;
			if( maxWeight > maxWeightLimit) maxWeight = maxWeightLimit;

			int harbourNumber = random.nextInt(destinationNames.length);
			Ship ship = new Ship(name, maxWeight, destinationNames[harbourNumber], random.nextInt(10));

			int randomIndex;
			do {
				randomIndex = random.nextInt(shipsAtAnchor.length);
			} while (shipsAtAnchor[randomIndex] != null);

			shipsAtAnchor[randomIndex] = ship;
		}
	}

	/*
	 * main-Methode
	 */
	public static void main(String[] args) {
		Harbour theHarbour = new Harbour("Germany", "Hamburg", 30);
		//theHarbour.createAndStoreShips();
		theHarbour.testShips();
		new GUI(theHarbour);

	}
}
