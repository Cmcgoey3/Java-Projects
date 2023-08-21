/**
 * 
 * @author Connor McGoey
 * This class is a circular array queue that can have its capacity expanded if needed.
 * It also takes generic types for the array.
 * Date: MArch 23, 2021
 * @param <T>
 */



public class CircularArrayQueue<T> implements QueueADT<T>{
	private int front; 	// integer front, indicating the index of the front element of the queue
	private int rear; 	// integer rear, indicating the index of the rear element of the queue
	private int count; 	// integer count, indicating the number of elements in the queue
	private T[] queue;	// queue, the array of generic type
	private final int DEFAULT_CAPACITY = 20; // integer DEFAULT_CAPACITY, indicating the default capacity of a given queue
	
	/**
	 * Default constructor, sets the front to index 1, rear to the default capacity, count to 0, and creates a queue with capacity of DEFAULT_CAPACITY (20)
	 */
	public CircularArrayQueue() {
		front = 1;
		rear = DEFAULT_CAPACITY;
		count = 0;
		queue = (T[])(new Object[DEFAULT_CAPACITY]);
	}
	
	/**
	 * Constructor that creates an array with the length of the integer parameter
	 * @param capacity the capacity of the queue
	 */
	public CircularArrayQueue(int capacity) {
		front = 1;
		rear = capacity;
		count = 0;
		queue = (T[])(new Object[capacity]);
	}
	
	/**
	 * enqueue adds an element to the queue, adjusts the rear and count accordingly, and increases the capacity if the queue is full
	 * @param element of generic type to be added into the queue
	 */
	public void enqueue(T element) {
		if (size() == getLength()) {
			expandCapacity();
		}
		rear = (rear + 1) % getLength();
		queue[rear] = element;
		++count;
	}
	
	/**
	 * dequeue removes and returns the element from the front of the queue, adjusts that element's position to be null, count, and the front element accordingly
	 * @throws EmptyCollectionException if the queue is empty
	 * @return result, generic element from the queue
	 */
	public T dequeue() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}
		T result = queue[front];
		queue[front] = null;
		count = count - 1;
		front = (front + 1) % getLength();
		return result;
	}
	
	/**
	 * returns the first element in the queue without removing it
	 * @throws EmptyCollectionException if the queue has no first element, (is empty)
	 * @return the front element of the queue
	 */
	public T first() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("Empty");
		}
		return queue[front];
	}
	
	/**
	 * returns a boolean value, true if the queue is empty, false otherwise
	 * @return boolean true/false if empty
	 */
	public boolean isEmpty() {
		return (count == 0);
	}
	
	// returns the size of the queue (count)
	public int size() {
		return count;
	}
	
	// returns the front index of the queue, not the element
	public int getFront() {
		return front;
	}
	
	// returns the rear index of the queue, not the rear element
	public int getRear() {
		return rear;
	}
	
	// returns the total capacity of the queue at a given time
	public int getLength() {
		return queue.length;
	}
	
	// returns the string format of a queue in a single line with format: "QUEUE: 'element1', 'element2', (...) .", ending with a period after the last element
	public String toString() {
		String strng = "QUEUE: ";
		int place = front;
		if (isEmpty()) {
			return "The queue is empty";
		}
		while (place < count) {
			if ((queue[place] != null) && (place != rear)) {
				strng = strng + queue[place].toString() + ", ";
			}
			place++;
		}
		strng = strng + queue[place].toString() + ".";
		return strng;
	}
	
	// expands the capacity of the queue by 20, uses a generic array to hold elements while the queue is expanded with null values
	public void expandCapacity() {
		T[] larger = (T[])(new Object[queue.length + 20]);
		for (int place = 1; place < count + 1; place ++) {
			larger[place] = queue[front];
			front = (front + 1) % queue.length;
		}
		front = 1;
		rear = count;
		queue = larger;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
