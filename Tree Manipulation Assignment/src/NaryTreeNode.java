/**
 * This class is a node class of generic type, with each object holding information about the number of children of that node,
 * its data, and a generic array holding its children (also of a generic type)
 * @author Connor McGoey
 * Date: April 11, 2021
 *
 * @param <T> this class works with generic types
 */

public class NaryTreeNode<T> {
	private T data;	// generic type T data holding the information of the node	
	private int numChildren;	// integer numChildren holding the number of children
	private NaryTreeNode<T>[] children;	// Generic NaryTreeNode array holding the node's children's nodes
	
	/**
	 * Constructor for the class, taking in a generic data and setting the children to null
	 * @param data generic data type for the node's data to that parameter
	 */
	public NaryTreeNode(T data) {
		this.data = data;
		children = null;
	}
	
	/**
	 * addChild takes in a generic NaryTreeNode and makes that parameter the node's child, expands capacity of the children array OR, if need be,
	 * creates that children array with size of 3, and finally increments the numChildren variable
	 * @param node to be made into the child
	 */
	public void addChild(NaryTreeNode<T> node) {
		if (children == null) {
			children = new NaryTreeNode[3];
		}
		if (children.length == numChildren) {
			expandCapacity();
		}
		children[numChildren] = node;
		numChildren++;
	}
	
	/**
	 * method expands the capacity of the children array by 3 by first creating a helper array to hold the array and then copying the information over
	 */
	public void expandCapacity() {
		NaryTreeNode<T>[] temp = new NaryTreeNode[children.length];
		temp = children;
		children = new NaryTreeNode[temp.length + 3];
		for (int i = 0; i < temp.length; i++) {
			children[i] = temp[i];
		}
	}
	
	/**
	 * Method to return the node's number of children
	 * @return numChildren the number of children the node has
	 */
	public int getNumChildren() {
		return numChildren;
	}
	
	/**
	 * Method to return the node of the parent node's child at the given index of the children array
	 * @param index the index of the children array
	 * @return children[index] the parent node's child at the given index of the children array
	 */
	public NaryTreeNode<T> getChild(int index) {
		return children[index];
	}
	
	/**
	 * Returns the data within a given node
	 * @return data the data of the node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Returns the string of the node with a preface stating "Node containing "
	 */
	public String toString() {
		return "Node containing " + data.toString();
	}
	
}
