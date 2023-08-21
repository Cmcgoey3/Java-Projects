/**
 * ShapeTree method creates a tree of Shape tree objects and acts upon it.
 * @author Connor McGoey
 * Date: April 11, 2021
 */

public class ShapeTree {
	private LinkedNaryTree<Shape> tree; // LinkedNaryTree<Shape> tree, a tree node that has been pre-passed with Shape objects
										// this is also the tree itself.
	
	/**
	 * Method that returns the tree
	 * @return LinkedNaryTree<Shape> tree, the linked node tree containing Shape objects
	 */
	public LinkedNaryTree<Shape> getTree() {
		return tree;
	}
	
	/**
	 * Returns the tree's root node
	 * @return the tree's root node
	 */
	public NaryTreeNode<Shape> getRoot() {
		return tree.getRoot();
	}
	
	/**
	 * Adds a shape node to the tree by first checking where that node would go based on the rules in addShapeNodeHelper
	 * @param shape the shape object to be added into the tree. If the tree is null, the addShapeNodeHelper method is not called.
	 */
	public void addShapeNode(Shape shape) {
		NaryTreeNode<Shape> shapeNode = new NaryTreeNode<Shape>(shape);
		if (tree == null) {
			tree = new LinkedNaryTree<Shape>(shapeNode);
		} else {
			if (addShapeNodeHelper(shape) != null) {
				NaryTreeNode<Shape> v = addShapeNodeHelper(shape);
				v.addChild(shapeNode);
			}
		}
	}
	
	/**
	 * Helper method that goes through each node of the preorder traversal to find where the shape will go
	 * @param shape the shape to be added to the tree
	 * @return null if there is no suitable place in the tree for the node to be added
	 */
	public NaryTreeNode<Shape> addShapeNodeHelper(Shape shape) {
		if (tree.getRoot() != null) {
			ArrayStack<NaryTreeNode<Shape>> S = new ArrayStack<NaryTreeNode<Shape>>();
			S.push(tree.getRoot());
			while (!S.isEmpty()) {
				NaryTreeNode<Shape> v = S.pop();
				if (checkNode(v, shape)) return v;
				for (int i = v.getNumChildren() - 1; i >= 0; i--) {
					if (v.getChild(i) != null) S.push(v.getChild(i));
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks if a given shape can be placed within the given node's children. Checks for: colour similarity to the parent shape. 
	 * Number of children not exceeding the number of sides the shape has, no sibling nodes having the same colour.
	 * @param ParentShape shape node that will be checked for the new node's placement under it
	 * @param shapeToCheck the shape that will be checked to be placed as a child to the parent node
	 * @return boolean value true if the shape can be placed under the parent node, false otherwise
	 */
	public boolean checkNode(NaryTreeNode<Shape> ParentShape, Shape shapeToCheck) {
		int sides = ParentShape.getData().getNumSides();
		if (ParentShape.getNumChildren() == sides) return false;
		if (ParentShape.getData().getColour().equals(shapeToCheck.getColour())) return false;
		for (int i = 0; i < ParentShape.getNumChildren(); i++) {
			if (ParentShape.getChild(i).getData().getColour().equals(shapeToCheck.getColour())) return false;
		}
		return true;
	}
}
