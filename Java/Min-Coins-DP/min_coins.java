/* Name: Connor McGoey
 * Date: July 9, 2022
 */

import java.util.Arrays;

public class min_coins {
	
	/* This algorithm for finding the minimum number of coins simply creates a new array of values
	 * from 0 to the amount given (included). Starting from 1, it then analyzes the coins for each value
	 * leading up to V and returns the minimum number of coins between the pathLength of itself 
	 * (if the coin exists for that number) and the number of coins it took to create subsequent values
	 * leading up to that coin + 1. When it reaches the value V in the array (at the last index), it 
	 * finally checks if there is a sum of coins that add up to 30 and if there is, then it returns that amount. 
	 * If there is no amount that exists, it returns -1 because the array has been loaded with default values
	 * greater than the value V to know when a specific amount cannot be created with those coins.
	 * */
	
	public static int minimumCoins(int[] coins, int V) {
		// Create new array with indices from 0 to V, each storing a maximum value V + 1 to test.
		// if a path/number of coins exists.
		int[] pathLengths = new int[V + 1];
		Arrays.fill(pathLengths, V + 1);
		
		// Initialize the number of coins to make change for 0 to 0 coins.
		pathLengths[0] = 0;
		
		// Loop over each value from 0 to V.
		for (int i = 1; i < V + 1; i++) {
			// Loop over each coin, test if a number of coins can create that amount, return the minimum if so.
			for (int j = 0; j < coins.length; j++) {
				if ((coins[j]) <= i) {
					pathLengths[i] = Math.min(pathLengths[i], pathLengths[i - coins[j]] + 1);
				}
			}
		}
		
		// If there exists no path for the value V, the pathLengths array at position V will still contain the 
		// default max value given. Otherwise, an amount of coins exists which is stored at that index.
		if (pathLengths[V] == V + 1) return -1;
		else return pathLengths[V];
	}
	
	// Test case from assignment sheet: coins={25,10,5}, V=30, result is 2
	public static void main(String[] args) {
		int[] testCoins = {25, 10, 5};
		int testV = 30;
		int result = minimumCoins(testCoins, testV);
		
		System.out.println(result);
	}
}
