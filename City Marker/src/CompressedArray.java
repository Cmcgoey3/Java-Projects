/*
 * This program creates a compressed array from a given 2D array matrix
 * Author: Connor McGoey
 * Date: February 9, 2021
 */


public class CompressedArray {
	private int origArraySize;
	private double[] array;
	
	/**
	 * Constructor for a CompressedArray, which adds lower left values of Matrix to 'this.array'
	 * @param origArray original 2D array matrix to be compressed
	 */
	public CompressedArray(double origArray[][]) {
		this.origArraySize = origArray.length;
		this.array = new double [((origArray.length)*(origArray.length) - origArray.length) / 2]; //creates new array size based on given 2D array
		int j = 0;
		int l = 0;
		for (int i = 1; i < origArray.length; i++) {
			j++;
			for (int k = 0; k < j; k++, l++)
				if (origArray[i][k] != 0)
					array[l] = origArray[i][k];
				}
		// This line of code ensures there are no zero distances in the code
		double[] container = new double[getLength()];
		int contindx = 0;
		for (int n = 0; n < this.array.length; n++) {
			if (this.array[n] != 0) {
				container[contindx] = this.array[n];
				contindx++;
				}	
			}
		//Finalized this.array with container to ensure no zeros
		this.array = new double[container.length];
		for (int h = 0; h < container.length; h++) {
			this.array[h] = container[h];
		}
		
		}
		
		
	
			

	// Returns the length of the array
	public int getLength() {
		int arraylength = 0;
		for (int i = 0; i < this.array.length; i++) {
			if (this.array[i] != 0) {
				arraylength++;
			}
		}
		return arraylength;
	}

	/**
	 * Returns the value at an element of the array
	 * @param eltoget the index of the array to grab
	 * @return the value at given index 'eltoget'
	 */
	public double getElement(int eltoget) {
		return array[eltoget];
	}

	/**
	 * Takes in an object of type CompressedArray and compares it to the original compressed array
	 * @param arraytocompare
	 * @return true if they are the same and false if they are different
	 */
	public boolean equals(CompressedArray arraytocompare) {
		if ((array.length != arraytocompare.getLength()))
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] !=  arraytocompare.getElement(i))
				return false;
		}
		return true;
	}
	
	//Returns a String 'line' that has been appended with newline characters and correct spacing
	public String toString() {
		String line = "";
		int i;
		int numtoprint = 1;
		int indx = 0;
		line = line + "\n";
		for (i = 0; i < array.length; i++)
			{
			for (int j = 0; j < numtoprint; j++)
				{
				if (indx < array.length)
				{
					line = line.format(line + "%8.2f", array[indx]); //formats the input to 8 total characters with 2 decimal places
					indx++;
				}
				
				}
			line = line + "\n";
			if (indx == array.length)
				break;
			
			numtoprint++;
			}
		
		return line;
	}

}


		

	