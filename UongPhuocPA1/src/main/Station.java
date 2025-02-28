/**
* This is the implementation of a station on the MBTA
* Known Bugs: None
* @author Phuoc Uong
* phuocuong@brandeis.edu
* 10/11/2024
* COSI 21A PA1
*/

package main;

public class Station {

	public Queue<Rider> northBoundRiders;
	public Queue<Rider> southBoundRiders;
	public Queue<Train> northBoundTrains;
	public Queue<Train> southBoundTrains;
	private String name;

	/**
	 * This is the constructor of a station
	 *
	 * @param name the station name
	 */
	public Station(String name) {
		this.name = name;
		// construct a station with the given name and make empty queues of everything
		northBoundRiders = new Queue<>(20);
		southBoundRiders = new Queue<>(20);
		northBoundTrains = new Queue<>(20);
		southBoundTrains = new Queue<>(20);
	}

	/**
	 * This method adds a rider to their respective station and north or south queue
	 * O(1) runtime
	 *
	 * @param r the rider to be added to the station
	 * @return true if can be added else false
	 */
	public boolean addRider(Rider r) {
		// add rider to respective to queue if they want to go north or south
		int direction = r.getDirection();
		String riderStartingStation = r.getStarting();
		if (riderStartingStation.equals(this.name)) {
			// for people wanting to go north
			if (direction == 0) {
				northBoundRiders.enqueue(r);
			} else {
				southBoundRiders.enqueue(r);
			}
			return true;
		}
		return false;
	}

	/**
	 * This methods adds a train to the station by disembarking all the passengers
	 * and then placing the train their correct queue
	 * O(n) runtime
	 *
	 * @param t the train to be added
	 * @return the disembarking passengers if any
	 */
	public String addTrain(Train t) {
		// update station name first then disembark
		t.updateStation(this.name);
		String disembarkingPassengers = t.disembarkPassengers();
		// if the train is going north enq it into north train q
		if (t.goingNorth()) {
			// System.out.println(t.direction);
			northBoundTrains.enqueue(t);
		} else {
			southBoundTrains.enqueue(t);
		}
		// removes all passengers that are meant to leave at this station.
		// place the train in the correct queue. depends on direciotn
		// return string of the passengers that are disembark
		// use the disembarkPassengers method from train
		if (disembarkingPassengers.length() == 0) {
			return "";
		} else {
			return this.name + " Disembarking Passenger: \n" + disembarkingPassengers;
		}
	}

	/**
	 * This method boards the first train of the south train queue with as many
	 * passengers as possible
	 * O(N) runtime
	 *
	 * @return the train that is getting boarded
	 */
	public Train southBoardTrain() {

		if (southBoundTrains.size() == 0) {
			return null;
		}
		Train south = southBoundTrains.front();
		southBoundTrains.dequeue();
		// passenger direction and train direction matches up
		// passenger starting station and train current stattion matches up
		// jus need to check if the train is full or not

		// checks if the rider q is empty. iterate until the rider q is empty
		// or
		// until the passenger array in the train is full
		while (southBoundRiders.size() > 0 && south.addPassenger(southBoundRiders.front())) {
			southBoundRiders.dequeue();
		}
		return south;
	}

	/**
	 * This method boards the first train of the north train queue with as many
	 * passengers as possible
	 * O(N) runtime
	 *
	 * @return the train that is getting boarded
	 */
	public Train northBoardTrain() {
		// same as south
		if (northBoundTrains.size() == 0) {
			return null;
		}
		Train north = northBoundTrains.front();
		northBoundTrains.dequeue();
		while (northBoundRiders.size() > 0 && north.addPassenger(northBoundRiders.front())) {
			northBoundRiders.dequeue();
		}
		return north;
	}

	/**
	 * This method moves a train from the north queue to the south queue and swaps
	 * its direction
	 * O(1) runtime
	 */

	public void moveTrainNorthToSouth() {
		// change the front of the northBoundTrains to go south
		// enqueue that train into northBoundTrains
		Train north = northBoundTrains.front();
		northBoundTrains.dequeue();
		north.swapDirection();
		southBoundTrains.enqueue(north);

	}

	/**
	 * This method moves a train from the south queue to the nirth queue and swaps
	 * its direction
	 * O(1) runtime
	 */
	public void moveTrainSouthToNorth() {
		// same as above
		Train south = southBoundTrains.front();
		southBoundTrains.dequeue();
		south.swapDirection();
		northBoundTrains.enqueue(south);
	}

	/**
	 * The to string representation of a station
	 * O(1) runtime
	 *
	 * @return the string form of the station
	 */
	@Override
	public String toString() {
		// Station: Alewife
		// 1 north-bound trains waiting
		// 0 south-bound trains waiting
		// 0 north-bound passengers waiting
		// 1 south-bound passengers waiting
		return "Station: " + this.name + "\n" + northBoundTrains.size() + " north-bound trains waiting\n"
				+ southBoundTrains.size() + " south-bound trains waiting\n" + northBoundRiders.size()
				+ " north-bound passengers waiting\n" + southBoundRiders.size() + " south-bound passengers waiting\n";

	}

	/**
	 * This method returns the stations name
	 * O(1) runtime
	 *
	 * @return station name
	 */
	public String stationName() {
		return this.name;
	}

	/**
	 * This method compares if two stations are equal based on their name
	 * O(1) runtime
	 *
	 * @return true if the two objects are equal else false
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Station) {
			Station other = (Station) o;
			return this.name == other.name;
		} else {
			return false;
		}
		// stations are equal if their names matches up
	}
}
