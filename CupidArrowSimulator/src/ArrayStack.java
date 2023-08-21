/**
 * This class creates an array stack of generic type, keeps track of its size, and performs methods on the stack. 
 * It implements ArrayStackADT.
 * @author Connor McGoey
 * Date: March 5, 2021
 */

public class ArrayStack<T> implements ArrayStackADT<T> {
	
	private T[] stack; // initializes the instance array
	private int top; // initializes the top value
	public static String sequence=""; // initializes sequence
	
	/*
	 * Default constructor for the class which creates a stack with 14 elements
	 */
	public ArrayStack() {
		stack = (T[])(new Object[14]);
	}
	
	/**
	 * Constructor for the class which creates a stack with given capacity
	 * @param initialCapacity the desired capacity for the stack
	 */
	public ArrayStack(int initialCapacity) {
		stack = (T[])(new Object[initialCapacity]);
	}
	
	/**
	 * Push method that pushes the given item into the stack.
	 * If the stack is at capacity, the stack is made larger.
	 * @param dataItem item to push into the stack
	 */
	public void push(T dataItem) {
		if (top == stack.length) { // if the stack is at capacity
			if (stack.length < 50) { // if the length of the stack is less than 50, stack length increased by 10
				T[]larger = (T[])(new Object[stack.length + 10]); // copy array for storing stack's values
				for (int index = 0; index < stack.length; index++) {
					larger[index] = stack[index];
				}
				stack = larger;
			} else { // if the array is equal to or larger than 50
				T[]larger = (T[])(new Object[stack.length * 2]);
				for (int index = 0; index < stack.length; index++) {
					larger[index] = stack[index];
				}
				stack = larger;
			}
		}
		stack[top] = dataItem; // placing the given item into the stack
		top++; //incrementing the top index by 1
		
		if (dataItem instanceof MapCell) {
			sequence += "push" + ((MapCell)dataItem).getIdentifier();
		} else {
			sequence += "push" + dataItem.toString();
		}
	}
	
	/**
	 * Method for popping and returning the top value in the stack.
	 * The stack is resized based on the amount of values and their ratio to the overall size of the stack.
	 * @throws EmptyStackException if attempting to pop a value from an empty stack
	 * @return result the top value from the stack
	 */
	public T pop() throws EmptyStackException {
			if (top == 0) { // if the stack is empty
				throw new EmptyStackException("Empty Stack");
			}
			top--; // decrementing top to the value to be returned, but also for when it is popped
			T result = stack[top]; // top value of the stack
			stack[top] = null; // removing the value from the stack
			if (top < (stack.length / 4)) { //if the size of the stack is less than a quarter of the capacity, resize.
				if ((stack.length / 2) < 14) { // if half of the capacity of the stack is less than 14, resize to 14
					T[]smaller = (T[])(new Object[14]);
					for (int index = 0; index < 14; index++) {
						smaller[index] = stack[index];
					}
					stack = smaller;
				}
				else { // else, resize the stack to half of the original capacity
					T[]smaller = (T[])(new Object[stack.length / 2]);
					for (int index = 0; index < (stack.length / 2); index++) {
						smaller[index] = stack[index];
					}
					stack = smaller;
				}
			}
			if (result instanceof MapCell) {
				sequence += "pop" + ((MapCell)result).getIdentifier();
			}
			else {
				sequence += "pop" + result.toString();
			}
			return result;
	}
	
	/**
	 * Method for looking and returning the top item of the stack without removing it.
	 * @throws EmptyStackException is thrown if the stack is empty
	 * @return result returns the top value of the stack
	 */
	public T peek() throws EmptyStackException {
		if (top == 0) { // if the stack is empty
			throw new EmptyStackException("Empty Stack");
		}
		return stack[top-1]; 
	}
	
	/**
	 * Method for checking if the stack is empty or not.
	 * @return boolean value. true if the stack is empty, false if it is not empty
	 */
	public boolean isEmpty() {
		return (top == 0); 
	}
	
	/**
	 * Method for obtaining the amount of items in the stack
	 * @return top, represents the amount of items in the stack at a given moment
	 */
	public int size() {
		return top;
	}
	
	/**
	 * Method for returning the capacity of the stack
	 * @return stac.length, the capacity of the stack
	 */
	public int length() {
		return stack.length;
	}
	
	/**
	 * Method for returning the stack in string form with a comma between each value, except for the last value
	 * @return result, the stack as a string
	 */
	public String toString() {
		String result = "Stack: ";
		for (int index = 0; index < (top - 1); index++) {
			result = result + stack[index].toString() + ", ";
		}
		result = result + stack[top-1].toString();
		return result;
	}
	
}
