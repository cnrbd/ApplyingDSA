

package main;

public class Rider {
	private String riderID;
	private String startingStation;
	private String destinationStation;
	private int direction;

	/**
	 * This is the constructor of the riders
	 *
	 * @param riderID            the id of the rider
	 * @param startingStation    the riders starting station
	 * @param destinationStation the riders destination station
	 */
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
		this.direction = 1;
		// must start by travelling south
		// 1= south, 0 = nouth
	}

	/**
	 * This method returns the starting station of the rider
	 * O(1) runtime
	 *
	 * @return starting station
	 */
	public String getStarting() {
		return this.startingStation;
	}

	/**
	 * This method returns the destination station of the rider
	 * O(1) runtime
	 *
	 * @return destination station
	 */
	public String getDestination() {
		return this.destinationStation;
	}

	/**
	 * This method returns the rider's ID
	 * O(1) runtime
	 *
	 * @return rider's ID
	 */
	public String getRiderID() {
		return this.riderID;
	}

	/**
	 * This method returns a boolean if the rider is going north
	 * O(1) runtime
	 *
	 * @return true if the rider is going north else false
	 */
	public boolean goingNorth() {
		return this.direction == 0;
	}

	/**
	 * This getter gets the direction of the rider
	 * O(1) runtime
	 *
	 * @return the direction of the rider
	 */
	public int getDirection() {
		return this.direction;
	}

	/**
	 * This method swaps the direction of the rider from north to south and vice
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
	 * This setter sets the direction of the riders
	 * O(1) runtime
	 *
	 * @param direction the direction to change
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * To string representation of a Rider
	 * O(1) runtime
	 *
	 * @return the string form of a rider
	 */
	@Override
	public String toString() {
		return "Rider " + this.riderID + " going " + this.direction + " to " + this.destinationStation
				+ " starting from " + this.startingStation;
	}

	/**
	 * This is the equals method implementation for the rider. compares by rider id
	 * O(1) runtime
	 *
	 * @return if the rider ids are equal to other
	 */
	@Override
	public boolean equals(Object s) {
		// riders are equal if their id matches up
		if (s instanceof Rider) {
			Rider other = (Rider) s;
			return this.riderID == other.riderID;
		} else {
			return false;
		}
	}
}
