
/*
 * This program takes in a text file, reads lines from the file, creates City objects with the names, x values, and y values
 * and creates City objects with the information, which is then transfered into an array
 */


public class Program {
	private int cityCount;
	private City[] cityArray;
	private CompressedArray array;
	
	/**
	 * This constructor takes in a file and a boolean true/false for the city map and then proceeds to create a 2D array from the given
	 * file, which then turns into a linear array based on the assumption that it is a matrix
	 * @param file is the file to read from
	 * @param showMap is the boolean true or false for showing the marker on the map
	 */
	public Program(String file, boolean showMap) {
		this.cityArray = new City[3];
		MyFileReader filetoread = new MyFileReader(file);
		filetoread.readString(); // skipping the header of the file
		int indx = 0;
		while(filetoread.endOfFile() == false){   // reads from the file in order to get the values for each city
			String name = filetoread.readString();
			int xinput = filetoread.readInt();
			int yinput = filetoread.readInt();
			City newcity = new City(name, xinput, yinput); // creates a new city object based on what is read from the file
			this.cityArray[indx] = newcity; 
			
			if ((indx + 1) == this.cityArray.length) //if more space is needed for additional inputs
			{
				expandCapacity(); // expands the array by three to make room for more values 
			}
			indx++;
		}
		if (showMap == true) { 		// creates a map object for each city (if true)
			Map themap = new Map();
			for (int i = 0; i < this.cityArray.length; i++) {
				if(this.cityArray[i] != null)
					themap.addCity(this.cityArray[i]);
				}
		}	
	}
	
	// Returns the cityArray array that contains the list of cities
	public City[] getCityList() {
		return this.cityArray;
	}

	/**
	 * Creates more space for the array by 3 slots
	 * First, a copy of the array is made and stored in copyarray so that cityArray can be made into a new array with 3 more slots
	 * Then, each component in copyarray is transfered back into cityArray
	 */
	private void expandCapacity() {
		int origlength = this.cityArray.length;
		City[] copyarray = this.cityArray.clone();
		this.cityArray = new City[origlength + 3];
		for (int i = 0; i < copyarray.length; i++)
			this.cityArray[i] = copyarray[i];
	}
	
	/**
	 * Calculates the Euclidean distance between two cities using the Euclidean distance formula
	 * @param city1 First city used in calculation
	 * @param city2 Second city used in calculation
	 * @return Returns the unformatted double distance between the two cities
	 */
	public double distBetweenCities(City city1, City city2) {
		double city1x = city1.getX();
		double city1y = city1.getY();
		double city2x = city2.getX();
		double city2y = city2.getY();
		double xs = (city2x) - (city1x);
		double ys = (city2y) - (city1y);
		xs = xs * xs;
		ys = ys * ys;
		double distance = Math.sqrt(xs + ys);
		return distance;
		
	}

	// Appends the distances to this.array by using CompressedArray() 
	public void compareDistances() {
		double[][] twodimenarray = new double[this.cityArray.length][this.cityArray.length]; // Creates an array of size [N][N] where N == length
		for (int i = 0; i < cityArray.length; i++) {										 // of the original CityArray
			if (cityArray[i] != null) {
				for (int j = 0; j < cityArray.length; j++) {
					if (cityArray[j] != null){
						double distance = distBetweenCities(cityArray[i], cityArray[j]); // Calls distBetweenCities() on each City combination
						twodimenarray[i][j] = distance;
					}
				}
		this.array = new CompressedArray(twodimenarray);
			}
		}
	}
	
	//Returns this.array
	public CompressedArray getArray() {
		return this.array;
	}
}
