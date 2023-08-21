import java.util.Iterator;
/**
 * This class is a linked node tree, where each node can can hold n elements as children (no limit) within the rules stated.
 * @author Connor McGoey
 * Date: April 11, 2021
 * @param <T> this class works with generic types
 */

public class LinkedNaryTree<T> {
	private NaryTreeNode<T> root; // generic NaryTreeNode root is the root of the tree
	
	/**
	 * Default constructor of the class, which sets the root of the tree to null
	 */
	public LinkedNaryTree() {
		root = null;
	}
	
	/**
	 * Second constructor of the tree, which sets the root of the tree to the given input variable
	 * @param root generic NaryTreeNode data type to be placed into the root variable
	 */
	public LinkedNaryTree(NaryTreeNode<T> root) {
		this.root = root;
	}
	
	/**
	 * addNode method adds a child node to the given parent node
	 * @param parent the parent node to be given a child
	 * @param child the child node to be made a child of the parent node
	 */
	public void addNode(NaryTreeNode<T> parent, NaryTreeNode<T> child) {
		parent.addChild(child);
	}
	
	/**
	 * Method that returns the root node of the tree
	 * @return NaryTreeNode<T> root, returns the NODE root
	 */
	public NaryTreeNode<T> getRoot() {
		return root;
	}
	
	/**
	 * Returns the data of the root node
	 * @return the data of the root node
	 */
	public T getRootElement() {
		return root.getData();
	}
	
	/**
	 * Returns boolean value, whether or not the root is null or not
	 * @return root equals null
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/**
	 * (Approved by Dr. Sarlo) Helper method using reccursion to return the number of nodes within a tree without the root
	 * @param node the node we are determining the number of nodes below
	 * @return integer numb, the number of nodes below the parameter node
	 */
	private int sizeWithoutRoot(NaryTreeNode<T> node) {
		int numb = node.getNumChildren();
		for (int i = 0; i < node.getNumChildren(); i++) {
			numb = numb + sizeWithoutRoot(node.getChild(i));
		}
		return numb;
	}
	
	/**
	 * Actual size method that calls the sizeWithoutRoot method and then adds 1 to the number to include the parent node
	 * @param node : the node to determine the number of nodes within its tree
	 * @return integer representing the number of nodes within the tree
	 */
	public int size(NaryTreeNode<T> node) {
		return sizeWithoutRoot(node) + 1;
	}
	
	/**
	 * preorder method that goes through the tree in a preorder manner
	 * @param node the node that will represent the "root" of the tree
	 * @param list the list to add the preorder to
	 */
	public void preorder(NaryTreeNode<T> node, ArrayUnorderedList<T> list) {
		if (node != null) {
			list.addToRear(node.getData());
			for (int i = 0; i < node.getNumChildren(); i++) {
				preorder(node.getChild(i), list);
			}
		}
	}
	
	/**
	 * Method to return the preorder iterator of the tree
	 * @return
	 */
	public Iterator<T> iteratorPreorder() {
		ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
		preorder(root, tempList);
		
		return tempList.iterator();
	}
	
	/**
	 * toString method to return a string representation of a tree's preorder iteration 
	 * @return str the string representation of the tree's iteration
	 */
	public String toString() {
		String str = "";
		if (isEmpty()) return "Tree is empty.";
		while (iteratorPreorder().hasNext()) {
			str += iteratorPreorder().next() + "\n";
			iteratorPreorder().remove();
		}
		return str;
	}
}
