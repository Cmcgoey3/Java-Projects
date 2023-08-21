import java.io.*;

/**
 * This class takes a String input from a user and encodes or decodes the string depending on what they select
 * @author Connor McGoey
 * Date: March 23, 2021
 */

public class WesternCipher {
	private CircularArrayQueue<Character> encodingQueue; // queue for the string's characters to be held before they are encoded, if encode is selected
	private CircularArrayQueue<Character> decodingQueue; // queue for the string's characters to be held before they are encoded, if decode is selected
	
	// default constructor, creating a new object of type WesternCipher, which has both the encodingQueue and decodingQueue set to a capacity of 10
	public WesternCipher() {
		encodingQueue = new CircularArrayQueue<Character>(10);
		decodingQueue = new CircularArrayQueue<Character>(10);
	}
	
	/**
	 * second constructor, creating a new object of type WesternCipher, which has both the encodingQueue and decodingQueue set to a capacity of the given integer input
	 * @param capacity, the value that the capacity of the two queues will be set to
	 */
	public WesternCipher(int capacity) {
		encodingQueue = new CircularArrayQueue<Character>(capacity);
		decodingQueue = new CircularArrayQueue<Character>(capacity);
	}
	
	/**
	 * encodes the string input by first breaking the string into characters, and encoding each character according to the western cipher.
	 * as each character is dequeued from the queue, it is ciphered.
	 * @param input the string to be encoded
	 * @return the encoded string
	 */
	public String encode(String input) {
		// the following lines of code create variables to be used as the cipher checks to see what the index of the character is or if the previous character was a vowel.
		// additionally, the string is broken up and placed into the encodingQueue with type Character
		boolean prevVowel = false;
		int shiftVal = 0;
		int vowelVal = 0;
		String str = "";
		String cipher = "";
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			encodingQueue.enqueue(chars[i]);
		}
		Character ch = encodingQueue.dequeue();
		int index = 0;
		for (int i = 0; i < chars.length; i++) { 			// for each character of the string to be decoded
			if ((ch == 'A') || (ch == 'E') || (ch == 'I') || (ch == 'O') || (ch == 'U') || (ch == 'Y')) { // if the character is a vowel
				if (prevVowel) { 		// if the previous character was a vowel as well, set the ciphered character to the relative number, and the value of that number
					if (ch == 'A') {
						cipher = "3";
						vowelVal = 3;
					}
					if (ch == 'E') {
						cipher = "4";
						vowelVal = 4;
					}
					if (ch == 'I') {
						cipher = "5";
						vowelVal = 5;
					}
					if (ch == 'O') {
						cipher = "6";
						vowelVal = 6;
					}
					if (ch == 'U') {
						cipher = "1";
						vowelVal = 1;
					}
					if (ch == 'Y') {
						cipher = "2";
						vowelVal = 2;
					}
				} else { 				// if the previous character was not a vowel, set the ciphered character to the relative number, and the value of that number
					if (ch == 'A') {
						cipher = "1";
						vowelVal = 1;
					}
					if (ch == 'E') {
						cipher = "2";
						vowelVal = 2;
					}
					if (ch == 'I') {
						cipher = "3";
						vowelVal = 3;
					}
					if (ch == 'O') {
						cipher = "4";
						vowelVal = 4;
					}
					if (ch == 'U') {
						cipher = "5";
						vowelVal = 5;
					}
					if (ch == 'Y') {
						cipher = "6";
						vowelVal = 6;
					}
				}
				prevVowel = true; 	// this ensures that the program knows that the character that was just ciphered was a vowel
			} else {				// if the character being handled is not a vowel
				int ascii = (int) ch;	// assign the character with an ASCII value
				if (prevVowel) {		// if the previous character was a vowel, shift accordingly AND shift backwards twice the amount of the previous numerical value
					shiftVal = shiftVal + 5;
					shiftVal = shiftVal + (2 * index);
					shiftVal = shiftVal - (2 * vowelVal);
					boolean done = false;
					while (done == false) { 	// this loop shifts the value of the letter, but also ensures that the ASCII value stays within the range of the capital letters
						if (shiftVal < 0) {		// (65-90)
							for (int j = 0; j > shiftVal; j--) {
								ascii--;
								if (ascii == 64) { // if the value is to drop below the range, go to the top range of capital letters ('Z')
									ascii = 90;
								}
							}
						}
						if (shiftVal > 0) {
							for (int k = 0; k < shiftVal; k++) {
								ascii++;
								if (ascii == 91) { // // if the value is to go above the range, go to the lower range of capital letters ('A')
									ascii = 65;
								}
							}
						}
						done = true;
					}
					cipher = Character.toString((char) ascii); // the ciphered character's ASCII value is converted to a string by first converting it into a character
				} else {			// if the previous character was not a vowel, shift accordingly
					shiftVal = shiftVal + 5;
					shiftVal = shiftVal + (2 * index);
					boolean done = false;
					while (done == false) {
						if (shiftVal < 0) {
							for (int j = 0; j > shiftVal; j--) {	// see aobe comments regarding dropping below or going above the ASCII range for capital characters
								ascii--;
								if (ascii == 64) {
									ascii = 90;
								}
							}
						}
						if (shiftVal > 0) {
							for (int k = 0; k < shiftVal; k++) {
								ascii++;
								if (ascii == 91) {
									ascii = 65;
								}
							}
						}
						done = true;
					}
					cipher = Character.toString((char) ascii); 	// converts the ASCII numerical value into a string by first converting it into a character
				}
				prevVowel = false;
			}
			if (ch == ' ') cipher = " ";						// if the character in the string is a space, leave it as a space
			str = str + cipher;									// Concatenating the string being returned with the string representation of the converted character
			if (encodingQueue.isEmpty() == false) {				// if there are more characters to be converted, get the next character, increment index, and reset the shifting value
				ch = encodingQueue.dequeue();
				index++;
				shiftVal = 0;
			}
		}
		return str;
	}
	
	/**
	 * Similarly to the encode method, this decode method takes in a string that has been encoded, or is in the encoded format, and returns a string that reverses the encoded string
	 * @param input the string to be encoded
	 * @return the string representation of the decoded string
	 * refer to the above comments regarding the 'encode' method for specifics about enqueueing and dequeueing each character, shifting values, and keeping the ASCII value in range
	 */
	public String decode(String input) {
		boolean prevVowel = false;
		int shiftVal = 0;
		int vowelVal = 0;
		String str = "";
		String charac = "";
		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			decodingQueue.enqueue(chars[i]);
		}
		Character ch = decodingQueue.dequeue();
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if ((ch == '1') || (ch == '2') || (ch == '3') || (ch == '4') || (ch == '5') || (ch == '6')) {	// if the character being handled is a number
				if (prevVowel) {
					if (ch == '3') {
						charac = "A";
						vowelVal = 3;
					}
					if (ch == '4') {
						charac = "E";
						vowelVal = 4;
					}
					if (ch == '5') {
						charac = "I";
						vowelVal = 5;
					}
					if (ch == '6') {
						charac = "O";
						vowelVal = 6;
					}
					if (ch == '1') {
						charac = "U";
						vowelVal = 1;
					}
					if (ch == '2') {
						charac = "Y";
						vowelVal = 2;
					}
				} else {
					if (ch == '1') {
						charac = "A";
						vowelVal = 1;
					}
					if (ch == '2') {
						charac = "E";
						vowelVal = 2;
					}
					if (ch == '3') {
						charac = "I";
						vowelVal = 3;
					}
					if (ch == '4') {
						charac = "O";
						vowelVal = 4;
					}
					if (ch == '5') {
						charac = "U";
						vowelVal = 5;
					}
					if (ch == '6') {
						charac = "Y";
						vowelVal = 6;
					}
				}
				prevVowel = true;
			} else {
				int ascii = (int) ch;
				if (prevVowel) {					// reversing the shift according to the previous values and rules
					shiftVal = shiftVal - 5;
					shiftVal = shiftVal - (2 * index);
					shiftVal = shiftVal + (2 * vowelVal);
					boolean done = false;
					while (done == false) {
						if (shiftVal < 0) {
							for (int j = 0; j > shiftVal; j--) {
								ascii--;
								if (ascii == 64) {
									ascii = 90;
								}
							}
						}
						if (shiftVal > 0) {
							for (int k = 0; k < shiftVal; k++) {
								ascii++;
								if (ascii == 91) {
									ascii = 65;
								}
							}
						}
						done = true;
					}
					charac = Character.toString((char) ascii);
				} else {
					shiftVal = shiftVal - 5;
					shiftVal = shiftVal - (2 * index);
					boolean done = false;
					while (done == false) {
						if (shiftVal < 0) {
							for (int j = 0; j > shiftVal; j--) {
								ascii--;
								if (ascii == 64) {
									ascii = 90;
								}
							}
						}
						if (shiftVal > 0) {
							for (int k = 0; k < shiftVal; k++) {
								ascii++;
								if (ascii == 91) {
									ascii = 65;
								}
							}
						}
						done = true;
					}
					charac = Character.toString((char) ascii);
				}
				prevVowel = false;
			}
			if (ch == ' ') charac = " ";			// if the character is a space, keep it a space
			str = str + charac;
			if (decodingQueue.isEmpty() == false) {
				ch = decodingQueue.dequeue();
				index++;
				shiftVal = 0;
			}
		}
		return str;
	}
	
	/**
	 * This main method prompts the user for a string and whether or not they would like that string to be encoded or decoded, it also keeps asking for these questions until
	 * the user enters the string 'no'.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		WesternCipher main = new WesternCipher();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a string:");
		String userString = reader.readLine();
		System.out.println();
		System.out.println("Would you like to encode or decode the string? Enter 'ENCODE' to encode the string\n"
				+ "or 'DECODE' to decode the string:");
		String userIn = reader.readLine();
		System.out.println();
		while (userIn.equals("no") != true) {		// while loop until the user enters 'no' for their answer
			if (userIn.equals("ENCODE")) {
				System.out.println("Your string encoded is: " + main.encode(userString));
				System.out.println();
			}
			if (userIn.equals("DECODE")) {
				System.out.println("Your string decoded is: " + main.decode(userString));
				System.out.println();
			}
			System.out.println("Would you like to continue? Type 'no' to stop. Type anything else to continue:");
			userIn = reader.readLine();
			if (userIn.equals("no") != true) {
				System.out.println("Enter another string:");
				userString = reader.readLine();
				System.out.println();
				System.out.println("Would you like to encode or decode the string? Enter 'ENCODE' to encode the string\n"
						+ "or 'DECODE' to decode the string:");
				userIn = reader.readLine();
				System.out.println();
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
