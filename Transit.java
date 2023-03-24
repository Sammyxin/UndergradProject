package transit;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 zixin.qu@rutgers.edu
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}

	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {

	    // UPDATE THIS METHOD
		//set three lists head
		TNode prevWalkPtr = new TNode();
		trainZero =prevWalkPtr;
		TNode prevBusPtr = new TNode();
		trainZero = prevBusPtr;
		TNode prevTrainPtr = new TNode();
		trainZero = prevTrainPtr;

		//TNode walkPtr; 
		
		TNode pres = prevWalkPtr;
		for(int i =0; i<locations.length;  i++){
			pres.setNext(new TNode(locations[i], null, null));
			pres = pres.getNext();
		}

		//for bus pointer
		prevBusPtr.setDown(prevWalkPtr);
	    pres = prevBusPtr ;
		TNode currWalk = prevWalkPtr;
		for(int i = 0; i < busStops.length;  i++){
			while(currWalk.getLocation() != busStops[i]){
			  currWalk = currWalk.getNext();
			}
			pres.setNext(new TNode(busStops[i], null, currWalk));
			pres = pres.getNext();
		}

		prevTrainPtr.setDown(prevBusPtr);
		pres = prevTrainPtr;

		//create a new node
		TNode currBus = prevBusPtr;

		for(int i =0; i < trainStations.length;  i++){
			while(currBus.getLocation() !=  trainStations[i]){
			  currBus = currBus.getNext();}
			pres.setNext(new TNode(trainStations[i], null, currBus));
			pres = pres.getNext();//define
		}
		trainZero = prevTrainPtr;}

		////
		private int[] collectLocations(TNode first) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			first = first.getNext();

			while (first != null) {
			  line.add( first.getLocation() );
			  first = first.getNext();}

			  int[] daxiao = new int[ line.size()];
			for (int i = 0; i < line.size(); i++) {
				daxiao[i] = line.get(i);}

			return daxiao;

		}


	
	
	/**
	 * Modifies the layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param station The location of the train station to remove
	 */
	public void removeTrainStation(int station) {

	for (TNode temp = this.trainZero; temp.getNext() != null; temp = temp.getNext() ) {
		if ( temp.getNext().getLocation() == station )  {

			 temp.setNext(temp.getNext().getNext());

			break;
		}
	}

}
      private TNode findNode(TNode first, int num) {
	    for (TNode t = first; t != null; t = t.getNext()) {
		  if (t.getLocation() == num) {
			return t;
		}
	}
	     return null;
}
	/**
	 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param busStop The location of the bus stop to add
	 */
	public void addBusStop(int busStop) {
	    // UPDATE THIS METHOD

		addLocAtLayer(busStop, this.trainZero.getDown());
    }

    
    private void addLocAtLayer(int busStop, TNode first) {

        TNode temp = first;
        while (temp.getNext() != null && temp.getNext().getLocation() < busStop) {

            temp = temp.getNext();
        }

        if (temp.getNext() != null && temp.getNext().getLocation() == busStop) {
            return;
        }
        TNode low = findNode(first.getDown(), busStop);
        TNode ans = new TNode(busStop, temp.getNext(), low);

        temp.setNext(ans);    
		   
    }
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param destination An int representing the destination
	 * @return
	 */
	public ArrayList<TNode> bestPath(int destination) {
		//create a new arraylist for best route
        ArrayList<TNode> bestroute = new ArrayList<TNode>();

		//create a new node
        TNode pres = this.trainZero;

		//check whether it is empty
        while (pres != null) {
            bestroute.add(pres);
            if (pres.getNext() != null && pres.getNext().getLocation() <= destination){
                pres = pres.getNext();

            } else {
                pres = pres.getDown();
            }
        }
        return bestroute;
    }

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @return A reference to the train zero node of a deep copy
	 */
	public TNode duplicate() {

	    // UPDATE THIS METHOD

				int[] trainStations = this.collectLocations(trainZero);
				int[] busStops = this.collectLocations(trainZero.getDown());
				int[] locations = this.collectLocations(trainZero.getDown().getDown());
				
				TNode tmp = trainZero;
				makeList(trainStations, busStops, locations);
				TNode daxiao = trainZero;
				trainZero = tmp;
				return daxiao;
			}

	private TNode findScooterNode(int destination){
		TNode prev = null;
		TNode pres = trainZero.getDown().getDown();
		while(pres.getLocation() != destination){
			prev = pres;
			pres = pres.getNext();
			if (pres ==null){
				return prev;
			}		
		}
		return pres;
	}

	private TNode findWalkNode(int destination){
		TNode pres = trainZero.getDown().getDown().getDown();
		TNode prev = null;
		while(pres.getLocation() != destination){
			prev = pres;			
			pres = pres.getNext();
			if(pres == null) return prev;
		}
		return pres;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public void addScooter(int[] scooterStops) {

		TNode firstScooters = new TNode();
		firstScooters.setLocation(0);
		TNode pres = firstScooters;
		firstScooters.setDown(trainZero.getDown().getDown());

		//set up the list
		for(int i = 0; i<scooterStops.length; i++){
			TNode node1 = new TNode();
			node1.setLocation(scooterStops[i]);
			
			pres.setNext(node1);
			pres = pres.getNext();
		}
//add location
		pres = trainZero.getDown();
		pres.setDown(firstScooters);
		pres = pres.getNext();
		while(pres != null){
			pres.setDown( findScooterNode ( pres.getLocation()));
			pres = pres.getNext();
		}
		
		
		pres = firstScooters;
		while(pres !=null){
			pres.setDown(findWalkNode(pres.getLocation()));
			pres = pres.getNext();
		}


	}

	 
	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
