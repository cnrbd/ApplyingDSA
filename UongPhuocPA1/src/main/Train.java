/**
* This is the implementation of a train on the MBTA
* Known Bugs: None
* @author Phuoc Uong
* phuocuong@brandeis.edu
* 10/11/2024
* COSI 21A PA1
*/

package main;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers; // array of the trains riders
	public int passengerIndex; // the count of passengers
	public String currentStation;
	public int direction;

	/**
	 * This is the constructor of the train
	 *
	 * @param currentStation the current station of the train
	 * @param direction      the direction the train
	 */
	public Train(String currentStation, int direction) {
		this.direction = direction;
		this.currentStation = currentStation;
		this.passengers = new Rider[TOTAL_PASSENGERS];

	}

	/**
	 * This method checks if the train is going north
	 * O(1) runtime
	 *
	 * @return a boolean if the train is going north
	 */
	public boolean goingNorth() {
		return this.direction == 0;
	}

	/**
	 * This method swaps the direction of the train from north to south and vice
	 * versa
	 * O(1) runtime
	 */
	public void swapDirection() {
		if (this.direction == 0) {
			this.direction = 1;
		} else if (this.direction == 1) {
			this.direction = 0;
		}
	}

	/**
	 * This method checks the current passengers in the train and returns a string
	 * of their id and destination
	 * O(n) runtime
	 *
	 * @return the string representation of all the passengers in the train
	 */
	public String currentPassengers() {
		String passengersString = "";
		// might have to change the check of i.
		for (int i = 0; i < passengerIndex; i++) {
			passengersString += passengers[i].getRiderID() + ", " + passengers[i].getDestination() + "\n";
		}
		return passengersString;
	}

	/**
	 * This method adds a passenger onto a train with the conditions that the
	 * stations and directions matches up and if the train has space
	 * O(1) runtime
	 *
	 * @param r the rider to be added
	 * @return true if can be added else false
	 */
	public boolean addPassenger(Rider r) {
		// check if the
		// 1) the rider curent station matches up with the train
		// 2) the direction of the rider and the train matches up
		// 3) passengeriNDEX is less than 10, if there is space
		if ((r.getStarting().equals(this.currentStation)) && hasSpaceForPassengers()
				&& (r.getDirection() == this.direction)) {
			passengers[passengerIndex] = r;
			passengerIndex++;
			return true;
		}
		return false;
	}

	/**
	 * This method checks if the train still has space
	 * O(1) runtime
	 *
	 * @return true if there is space else false
	 */
	public boolean hasSpaceForPassengers() {
		// check if passengerindex is less than 10
		return passengerIndex < TOTAL_PASSENGERS;
	}

	/**
	 * This method disembarks the passengers on the train if they got to their
	 * destination
	 * O(N) runtime
	 *
	 * @return a string representation of all the disembarked passengers
	 */
	public String disembarkPassengers() {
		// return the passengers id and the current station if their destination station
		// matches
		// up with the current station of the train
		String remove = "";
		int disembark = 0;
		for (int i = 0; i < passengerIndex; i++) {
			if (passengers[i].getDestination().equals(this.currentStation)) {
				remove += passengers[i].getRiderID() + ", " + passengers[i].getDestination() + '\n';
				passengers[i] = null;
				disembark++;
				// rearrange the passengers array.
			}
		}
		// rearrange the passengers array here
		rearrangePassengers();
		reAssignIndex(disembark);
		// reset the passengerIndex pointer
		return remove;
	}

	/**
	 * This method sets the station name
	 * O(1) runtime
	 *
	 * @param s the new current station name
	 */
	public void updateStation(String s) {
		this.currentStation = s;
	}

	/**
	 * This method gets the current station
	 * O(1) runtime
	 *
	 * @return the current station
	 */
	public String getStation() {
		return this.currentStation;
	}

	/**
	 * This is a helper method for the disembark passengers method. this method
	 * rerranges the array of passengers. non nulls on the left and nulls on the
	 * right
	 * O(N) runtime
	 */
	public void rearrangePassengers() {
		// move all the non null values to the left of the array
		int nonNull = 0;
		for (int i = 0; i < passengerIndex; i++) {
			if (passengers[i] != null) {
				passengers[nonNull] = passengers[i];
				nonNull++;
			}
		}
		// turn remaining ones to null
		for (int i = nonNull; i < passengerIndex; i++) {
			passengers[i] = null;
		}
	}

	/**
	 * This method resets the index for the passengers array
	 * O(1) runtime
	 *
	 * @param disembark the amount of people who disembark to shift the index back
	 */
	public void reAssignIndex(int disembark) {
		this.passengerIndex -= disembark;
	}

	/**
	 * To String of the train
	 * O(n) runtime
	 *
	 * @return the string representation of the train
	 */
	@Override
	public String toString() {
		return "Train at station " + this.currentStation + " with passengers: " + currentPassengers();
	}
}
