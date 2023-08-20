/* Name: Connor McGoey
 * Date: July 9, 2022
 */

public class permutation_coefficient {
	
	/* This algorithm returns the permutation coefficient for creating a subset of k 
	 * elements from a larger set of n elements. The base case is if the subset is to be 
	 * larger than the original set, which returns 0 as there cannot exist a subset larger
	 * than the original set. Otherwise, the algorithm returns the sum of the recursive 
	 * calls from multiplying k by the permutation coefficient of the n - 1 value and
	 * the k - 1 value AND the permutation coefficient of n - 1 value and k. This is the 
	 * binomial coefficient formula.
	 * */
	
	public static int per(int n, int k) {
		if (k > n) return 0;
		if (k == 0) return 1;
		return (k * per(n - 1, k - 1) + per(n - 1, k));
	}

	// Test case from assignment sheet: with N = 10, K = 3, result is 720
	public static void main(String[] args) {
		int testN1 = 10;
		int testK1 = 3;
		int result1 = per(testN1, testK1);
		
		System.out.println(result1);
		
		int testN2 = 10;
		int testK2 = 5;
		int result2 = per(testN2, testK2);
		
		System.out.println(result2);
	}
}
