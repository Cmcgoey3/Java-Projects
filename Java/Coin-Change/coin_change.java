/* Name: Connor McGoey
 * Date: July 9, 2022
 */

public class coin_change {
	
	/* Below is an algorithm to solve the Coin Change Problem where S is a set of coins, m 
	 * is the number of coins in the set S, and n is a value to be tested with the coins. 
	 * The algorithm returns the amount of different combinations of coins given from A 
	 * that can add up to the value n. This solution takes the following approach: for each
	 * coin there are two possible paths: the first being that there is a solution with at least
	 * one of the coin, the second being that there is no solution with that amount of coin.
	 * Recursively, this means that the solution will eventually reach all possible amounts of each
	 * coin by taking a path of adding one of that specific coin or none.
	 * */

	public static int count(int[] S, int m, int n) {
		/* Base cases: if the value n is negative (the coin subtracted from the amount n 
		 * makes the remainder negative) OR if the number of coins remaining to be examined
		 * is 0, return 0 as there cannot be an amount of no coins. Otherwise, if the amount
		 * remaining reached 0, return 1 because the previous invocation of count(...) has
		 * achieved a result.
		 * 
		 * Recursive part: return the sum of two calls to count(...), the first with the value
		 * of n being reduced by the amount of the coin and the second with the same amount 
		 * remaining in n, but with a different coin.
		*/
		if (n < 0 || m == 0) return 0;
		if (n == 0) return 1;
		else return count(S, m, n - S[m - 1]) + count(S, m - 1, n);
	}
	
	public static void main(String[] args) {
		// Test case 1 from assignment sheet: n=6, S={1,2,3}, result is 7
		int testN1 = 6;
		int[] testS1 = {1, 2, 3};
		int result1 = count(testS1, testS1.length, testN1);
		System.out.println(result1);
		
		// Test case 2 from assignment sheet: n=10, S={2,5,3,6}, result is 5
		int testN2 = 10;
		int[] testS2 = {2, 5, 3, 6};
		int result2 = count(testS2, testS2.length, testN2);
		System.out.println(result2);
	}
}
