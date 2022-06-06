
public class QuickSort {
	
	public int[] sort(int[] array, int n) {
		if (n > 1) {
			int[] smaller = new int[n];
			int[] equal = new int[n];
			int[] larger = new int[n];
			
			int nSmaller = 0;
			int nEqual = 0;
			int nLarger = 0;
			
			int pivot = array[0];
			
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
			sort(smaller, nSmaller);
			sort(larger, nLarger);
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
	