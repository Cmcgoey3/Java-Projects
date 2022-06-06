
public class QuickSort {
	
	public int[] sort(int[] array, int n) {
		if (n > 1) {
			// Initialize arrays for values smaller, equal, and larger
			// All are the size of the original array
			int[] smaller = new int[n];
			int[] equal = new int[n];
			int[] larger = new int[n];
			
			// Initialize values for number of values in each array
			int nSmaller = 0;
			int nEqual = 0;
			int nLarger = 0;
			
			// Initialize pivot value to first element of array
			int pivot = array[0];
			
			// For loop for each value in the array. If statements test for the value and 
			// compares to the pivot value, adding the new value to the corresponding array.
			for (int i = 0; i < n; i++) {
				if (array[i] == pivot) {
					equal[nEqual] = array[i];
					nEqual++;
				}
				else if (array[i] < pivot) {
					smaller[nSmaller] = array[i];
					nSmaller++;
				}
				else {
					larger[nLarger] = array[i];
					nLarger++;
				}
			}
			// Call the sorting algorithm on the smaller and larger arrays recursively
			sort(smaller, nSmaller);
			sort(larger, nLarger);
			
			// Initialize array iterator to be used to populate final array in all 3 of 
			// the following for-loops. Return the final array
			int i = 0;
			for (int j = 0; j <= nSmaller; j++) {
				array[i] = smaller[j];
				i++;
			}
			for (int j = 0; j <= nEqual; j++) {
				array[i] = equal[j];
				i++;
			}
			for (int j = 0; j <= nLarger; j++) {
				array[i] = larger[j];
				i++;
			}
		}
		return array;
	}
}
	
