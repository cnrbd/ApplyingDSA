

package main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MBTA {

	public static final int SOUTHBOUND = 1;
	public static final int NORTHBOUND = 0;

	static final int TIMES = 6;
	static Railway r;

	/**
	 * The main method for the MBTA class where everything is constructed
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		r = new Railway();

		initStations("redLine.txt");
		initTrains("trains.txt");
		initRiders("riders.txt");
		runSimulation();
	}

	/**
	 * This method runs the simulation of the MBTA TIMES times.
	 * O(TIMES*n^2) runtime
	 */
	public static void runSimulation() {

		for (int i = 0; i < TIMES; i++) {
			System.out.println(r.simulate());
			System.out.println("------------ \n");
		}
	}

	/**
	 * This method creates the train that will be operating on the red line by
	 * reading the file
	 * o(n^2)
	 *
	 * @param trainsFile the trains file name
	 * @throws FileNotFoundException
	 */
	public static void initTrains(String trainsFile) throws FileNotFoundException {
		File file = new File(trainsFile);
		Scanner scanner = new Scanner(file);
		int count = 0;

		String station = "";
		int direction = -1;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextInt()) {
				direction = lineScan.nextInt();
			} else {
				station = line;
			}
			count++;
			if (count % 2 == 0) {
				Train t = new Train(station, direction);
				// System.out.println(t);
				r.addTrain(t);

			}
			lineScan.close();
		}
		scanner.close();

	}

	/**
	 * This method creates the riders that will be using the red line by
	 * reading the file
	 * O(n^2)
	 *
	 * @param ridersFile the reader file name
	 * @throws FileNotFoundException
	 */
	public static void initRiders(String ridersFile) throws FileNotFoundException {
		File file = new File(ridersFile);
		Scanner scanner = new Scanner(file);
		String start = "";
		String end = "";
		String id = "";
		while (scanner.hasNextLine()) {
			id = scanner.nextLine();
			start = scanner.nextLine();
			end = scanner.nextLine();
			Rider rider = new Rider(id, start, end);
			r.addRider(rider);
		}
		scanner.close();
	}

	/**
	 * This method creates the stations on the Redline by reading the file name
	 * O(n)
	 *
	 * @param stationsFile the file of station name
	 * @throws FileNotFoundException
	 */
	public static void initStations(String stationsFile) throws FileNotFoundException {
		File file = new File(stationsFile);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			String stationName = scanner.nextLine();
			Station s = new Station(stationName);
			r.addStation(s);
		}
		scanner.close();
	}
}
