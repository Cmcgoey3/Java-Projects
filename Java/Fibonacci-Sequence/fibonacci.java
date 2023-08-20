/* Name: Connor McGoey
 * Date: July 9, 2022
 */

public class fibonacci {

	/* Below is the common algorithm for the Fibonacci sequence where the value of a number
	 * N is equal to the sum of the previous two Fibonacci numbers (N-1) and (N-2).
	 * This, by nature, is a recursive definition. The base cases are as follows: when
	 *  N is 0 the Fibonacci value is 0 and when N is 1 the Fibonacci value is 1. Otherwise,
	 *  the value is the sum of the two Fibonacci sums.
	 * */
	public static int fib(int n) {
		// Base cases
		if (n == 0) return 0;
		if (n == 1) return 1;
		
		// Recursive call
		return (fib(n - 1) + fib(n - 2));
	}
	
	// Three tests cases given with N = 0, 1, and 3. Results are 0, 1, and 2 respectively.
	public static void main(String[] args) {
		int testN1 = 0;
		int testN2 = 1;
		int testN3 = 3;
		
		System.out.println(fib(testN1));
		System.out.println(fib(testN2));
		System.out.println(fib(testN3));
	}
}
