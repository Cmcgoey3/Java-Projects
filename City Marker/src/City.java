
/*
 	This class creates a City, which contains information regarding the name, coordinates, and a marker.
 	Author: Connor McGoey
 	Date: February 9, 2021
*/
public class City {
	private String name; // the name of the city
	private int x; // the x coordinate of the city
	private int y; // the y coordinate of the city
	private CityMarker marker; // the marker of type CityMarker for the city

	/**
	 * Constructor initializing a new object of type City
	 * @param newname the name of the city
	 * @param a the x coordinate of the city
	 * @param b the y coordinate of the city
	 */
	public City(String newname, int a, int b) { 
		name = newname;
		x = a;
		y = b;
		marker = new CityMarker();
	}
	
	// Returns the name of the city
	public String getName() {
		return name;
	}
	
	//Returns the x coordinate of the City
	public int getX() {
		return x;
	}
	
	// Returns the y coordinate of the city
	public int getY() {
		return y;
	}
	
	//Returns the marker for the city
	public CityMarker getMarker() {
		return marker;
	}
	
	/**
	 * Sets the name of the city to newSetName
	 * @param newSetName new name for the city
	 */
	public void setName(String newSetName) {
		name = newSetName;
	}
	
	/**
	 * Sets the x coordinate for the city using newSetX
	 * @param newSetX new X coordinate
	 */
	public void setX(int newSetX) {
		x = newSetX;
	}
	
	/**
	 * Sets the y coordinate for the city using newSetY
	 * @param newSetY new Y coordinate
	 */
	public void setY(int newSetY) {
		y = newSetY;
	}

	/**
	 * Sets the CityMarker for the city using newSetCityMarker
	 * @param newSetCityMarker new marker for the city
	 */
	public void setMarker(CityMarker newSetCityMarker) {
		marker = newSetCityMarker;
	}
	
	// Returns the name of the city
	public String toString() {
		return name;
	}

}

