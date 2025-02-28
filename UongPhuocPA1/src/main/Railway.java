/**
* This is the implementation of a railway on the MBTA
* Known Bugs: None
* @author Phuoc Uong
* phuocuong@brandeis.edu
* 10/11/2024
* COSI 21A PA1
*/

package main;

public class Railway {

	public DoubleLinkedList<Station> railway;
	public String[] stationNames;
	public int arrayIndex;

	/**
	 * The constructor of the railway
	 */
	public Railway() {
		this.stationNames = new String[18];
		this.arrayIndex = 0;
		this.railway = new DoubleLinkedList<Station>();

	}

	/**
	 * This method adds a station to the railways linked list
	 * O(1) runtime
	 *
	 * @param s the station to be added
	 */
	public void addStation(Station s) {
		// add to the linked list
		stationNames[arrayIndex] = s.stationName();
		arrayIndex++;
		railway.insert(s);
	}

	/**
	 * This methods add a train to its respective station
	 * O(n) runtime
	 *
	 * @param t the train to be added
	 */
	public void addTrain(Train t) {
		// add the train to the appropriate station based on?
		String currentStation = t.getStation();
		Node<Station> curr = railway.getFirst();
		while (curr != null) {
			if (curr.getData().stationName().equals(currentStation)) {
				curr.getData().addTrain(t);

			}
			curr = curr.getNext();
		}

	}

	/**
	 * This methods add a rider to their respective station on the railway
	 * O(n) runtime
	 *
	 * @param r the rider to be added
	 */

	public void addRider(Rider r) {
		// sets the rider's direction north or south
		setRiderDirection(r);
		// add the rider to the appropriate station based on??
		Node<Station> curr = railway.getFirst();
		String startingStation = r.getStarting();
		while (curr != null) {
			if (curr.getData().stationName().equals(startingStation)) {
				curr.getData().addRider(r);

			}
			curr = curr.getNext();
		}

	}

	/**
	 * This method sets the rider direction based on their final and starting stops
	 * O(1) runtime
	 *
	 * @param r the rider whose direction needs to change
	 */
	public void setRiderDirection(Rider r) {
		String startingStation = r.getStarting();
		String finalStation = r.getDestination();
		int startingIndex = getStationIndex(startingStation);
		int finalIndex = getStationIndex(finalStation);

		if (startingIndex < finalIndex) {
			// goes south
			r.setDirection(1);
		} else if (startingIndex > finalIndex) {
			// goes north
			r.setDirection(0);
		}
	}

	/**
	 * This method helps the setriderdirection method by finding the index of the
	 * station
	 * O(N) runtime
	 *
	 * @param stationName the name of the station
	 * @return the index of the station in the station name array
	 */
	public int getStationIndex(String stationName) {
		// alewife is index 0
		// braintree is index 18
		for (int i = 0; i < stationNames.length; i++) {
			if (stationNames[i].equals(stationName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method simulates one run of the railway of the redline by traversing the
	 * railway
	 * O(n^2) runtime
	 *
	 * @return logs of activity at each station as string
	 */
	public String simulate() {

		// the head of the linked list is alewife. northernmost station
		Node<Station> curr = railway.getFirst();
		String simulation = "Simulating one run of MBTA.\n";
		// traverse until the last station, braintree
		while (curr != null) {
			simulation += curr.getData().toString() + "\n";

			if (curr.getData().stationName().equals("Alewife")) {
				Train south = curr.getData().southBoardTrain();
				// check if there is a train heading south in the queue
				if (south != null) {
					// move the train into the next station
					String departures = curr.getNext().getData().addTrain(south);
					simulation += departures;
				}
			} else if (curr.getData().stationName().equals("Braintree")) {
				Train north = curr.getData().northBoardTrain();
				if (north != null) {
					// move the train into the next station
					String departures = curr.getPrev().getData().addTrain(north);
					simulation += departures;
				}
			} else {
				Train south = curr.getData().southBoardTrain();
				Train north = curr.getData().northBoardTrain();
				if (south != null) {
					String departures = curr.getNext().getData().addTrain(south);
					simulation += departures;
				}
				if (north != null) {
					String departures = curr.getPrev().getData().addTrain(north);
					simulation += departures;
				}
			}
			if (curr.getData().stationName().equals("Alewife") &&
					curr.getData().northBoundTrains.size() != 0) {
				curr.getData().moveTrainNorthToSouth();
			} else if (curr.getData().stationName().equals("Braintree") &&
					curr.getData().southBoundTrains.size() != 0) {
				curr.getData().moveTrainSouthToNorth();
			}
			curr = curr.getNext();
		}
		return simulation;
	}

	/**
	 * This method returns the string representation of the railway
	 * O(n) runtime
	 *
	 * @return the string form of the railway
	 */
	@Override
	public String toString() {
		return railway.toString();
	}
}
