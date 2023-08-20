

/* Name: Connor McGoey
 * Date: July 29, 2022
 */


public class B_Tree {

	/*
	 * This is my primary mWayTree class which contains the Node data structure, B_TreeInsert method and helper methods to aid in insertion.
	 * This mWayTree and B-Tree insert is specifically for one of degree 4, meaning each Node can only contain 3 elements and each Node
	 * can only have 4 children.
	 */
	public static class mWayTree{
	
		/*
		 * Node data structure for the mWayTree of degree 4. Each node contains an integer for keeping track of the numChildren, an integer array
		 * containing the Node's values/keys, a Node array containing the Node's children, and a Node parent containing information about the 
		 * Node's parent. By default, nodeValues array is initialized with four spots all equal to maximum integer value, which is used to traverse
		 * the array and know when the array is full or not. It it initialized with 4 spots to handle overflow. children array is initialized with 
		 * 5 children all set to null for the same reason. parent is set to null as well.
		 */
		public static class Node {
			int numChildren = 0;
			int[] nodeValues = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
			Node[] children = new Node[5];
			Node parent = null;
			
			/*
			 * The following two are Node constructors which handle the events of creating a new node with only the nodeValues known AND the condition
			 * where all information about the node is known.
			 */
			public Node(Node root, int[] nodeValues) {
				for(int i = 0; i < nodeValues.length; i++) {
					this.nodeValues[i] = nodeValues[i];
				}
				this.parent = null;
			}
			
			public Node(Node root, int insertIndex, int numChildren, int[] nodeValues) {
				for(int i = 0; i < nodeValues.length; i++) {
					root.children[insertIndex].nodeValues[i] = nodeValues[i];
				}
				root.children[insertIndex].parent = root;
				root.children[insertIndex].numChildren = numChildren;
			}
		}	
		
		/*
		 * The following B_TreeInsert method is used to insert a new element into the B-Tree at the lowest level and test for overflow. It
		 * repeatedly handles overflow of subsequent parent nodes if necessary by calling helper methods.
		 */
		public Node B_TreeInsert(mWayTree tree, Node root, int key) {
			if (root == null) {
				int[] values = {key};
				root = new mWayTree.Node(root, values);
			} else {
				if (isInTree(root, key)) return root;
				int childIndex = getChild(root, key);
				isLowestInternal(root, key);
				if(root.children[childIndex] != null) childFull(root.children[childIndex]);
				while ((isLowestInternal(root, key) == false) && (childFull(root.children[childIndex]) == false)) {
					childIndex = getChild(root, key);
					root = root.children[childIndex];
				}
				root = insertNotFull(root, key);
				while (root.nodeValues[3] != Integer.MAX_VALUE) {
					if (root == tree.root) {
						int[] values = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
						mWayTree.Node newRoot = new mWayTree.Node(root, values);
						root.parent = newRoot;
						tree.root = newRoot;
					}
					int middle = root.nodeValues[1];
					root.parent = insertNotFull(root.parent, middle);
					root.nodeValues[1] = Integer.MAX_VALUE;
					int[] values = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
					mWayTree.Node leftNode = new mWayTree.Node(root, values);
					mWayTree.Node rightNode = new mWayTree.Node(root, values);
					leftNode = insertNotFull(leftNode, root.nodeValues[0]);
					rightNode = insertNotFull(rightNode, root.nodeValues[2]);
					rightNode = insertNotFull(rightNode, root.nodeValues[3]);
					leftNode.children[0] = root.children[0];
					leftNode.children[1] = root.children[1];
					rightNode.children[0] = root.children[2];
					rightNode.children[1] = root.children[3];
					rightNode.children[2] = root.children[4];
					root.parent = updateChildren(root.parent, leftNode, rightNode);
					root = root.parent;
				}
			}
			while (root.parent != null) root = root.parent;
			return root;
		}
		
		/*
		 * Method to handle the case of inserting a new element into a node that is not full. It shifts the children nodes and the nodeValues
		 * according to where the new element is to be inserted. Returns the root of the node.
		 */
		public Node insertNotFull(Node root, int key) {
			int insertIndex = findInsertIndex(root, key);
			if (insertIndex == 0) {
				Node tempChild = root.children[0];
				int tempValue = root.nodeValues[0];
				root.children[0] = null;
				root.nodeValues[0] = key;
				root.children[3] = root.children[2];
				root.children[2] = root.children[1];
				root.children[1] = tempChild;
				root.nodeValues[3] = root.nodeValues[2];
				root.nodeValues[2] = root.nodeValues[1];
				root.nodeValues[1] = tempValue;
			}
			if (insertIndex == 1) {
				root.nodeValues[3] = root.nodeValues[2];
				root.nodeValues[2] = root.nodeValues[1];
				root.nodeValues[1] = key;
				root.children[3] = root.children[2];
				root.children[2] = null;
			}
			if (insertIndex == 2) {
				root.nodeValues[3] = root.nodeValues[2];
				root.nodeValues[2] = key;
			}
			if (insertIndex == 3) {
				root.nodeValues[3] = key;
			}
			return root;
		}
		
		/*
		 * Helper method to determine whether the child of a node is full. Uses Integer.MAX_VALUE to see if the last index is still available.
		 */
		public Boolean childFull(Node root) {
			if (root.nodeValues[3] == Integer.MAX_VALUE) return false;
			return true;
		}
		
		/*
		 * Method to update the children of a node as well as the children's parents depending on where the new Nodes are to go. Parameters are
		 * the node, and two new children leftNode and rightNode. Similar to placing a new value into an array, this tests where the child should go
		 * based off the values leftNode holds. The new rightNode then goes to the subsequent position. Finally, all children nodes are updated such
		 * that their parent is the current node.
		 */
		public Node updateChildren(Node root, Node leftNode, Node rightNode) {
			int childIndex = findInsertIndex(root, leftNode.nodeValues[0]);
			if (childIndex == 0) {
				root.children[3] = root.children[2];
				root.children[2] = root.children[1];
				root.children[1] = rightNode;
				root.children[0] = leftNode;
			}
			if (childIndex == 1) {
				root.children[3] = root.children[2];
				root.children[2] = rightNode;
				root.children[1] = leftNode;
			}
			if (childIndex == 2) {
				root.children[3] = rightNode;
				root.children[2] = leftNode;
			}
			if (childIndex == 3) {
				root.children[3] = leftNode;
				root.children[4] = rightNode;
			}
			for (int j = 0; j < 5; j++) {
				if (root.children[j] != null) root.children[j].parent = root;
			}
			return root;
		}
		
		public Node root;
		
		/*
		 * Helper method to determine where a new element is to be inserted into an array already containing nodes. This is also where MAX_INTEGER
		 * becomes useful as we test the order from left to right.
		 */
		public int findInsertIndex(Node rootNode, int key) {
			int i = 0;
			while ((i < rootNode.nodeValues.length) && (key > rootNode.nodeValues[i])) i++;
			return i;
		}
		
		/*
		 * Helper method for traversing the tree. Determines whether or not a node has a child in the spot where the current key is to be placed.
		 */
		public Boolean isLowestInternal(Node root, int key) {
			int nextIndex = getChild(root, key);
			if (root.children[nextIndex] == null) return true;
			return false;
		}
		
		/*
		 * Helper method that recursively determines whether or not a key/element already exists within a tree. Calls isInNode() method to detemine
		 * if the key exists in the current node.
		 */
		public Boolean isInTree(Node root, int key) {
			if (isInNode(root, key)) return true;
			else if (isLowestInternal(root, key)) return false;
			int nextNode = getChild(root, key);
			return isInTree(root.children[nextNode], key);
		}
		
		/*
		 * Helper method to determine whether a key exists in the current node. Does a traversal over the nodeValues array to do this.
		 */
		public static Boolean isInNode(Node root, int key) {
			for (int i = 0; i < root.nodeValues.length; i++) {
				if (root.nodeValues[i] == key) return true;
			}
			return false;
		}
		
		/*
		 * Helper method to return the position of the node's children in which the current key should be placed. Does so by testing 
		 * the nodeValues array because the nodeValues array and children array represent an order of elements that are the same.
		 */
		public static int getChild(Node root, int key) {
			int i = 0;
			while ((i < root.nodeValues.length) && (root.nodeValues[i] != Integer.MAX_VALUE) && (root.nodeValues[i] < key)) {
				i++;
			}
			return i;
		}
		
		/*
		 * Constructor setting the root of a new mWayTree to null.
		 */
		mWayTree(){
			root = null;
		}
		
		
		/*
		 * NOTE: THE Node.children VARIABLE HAS INDECES 0-3, IF A CHILD IS AT INDEX 0 OF A PARENT, THEN ITS VALUES ARE LESS THAN ALL ELEMENTS IN THE PARENT
		 * ARRAY. IF THE INDEX IS 1, ITS VALUES ARE GREATER THAN THE PARENT'S FIRST VALUE, BUT LESS THAN THE SECOND. IF THE INDEX IS 2, ITS VALUES ARE 
		 * GREATER THAN THE SECOND VALUE OF THE PARENT NODE, BUT LESS THAN THE LAST. FINALLY, IF THE INDEX IS 3, THEN ITS VALUES ARE GREATER THAN ALL VALUES
		 * OF THE PARENT NODE.
		 * 
		 * The following four methods work in tandem to print the information of each node. This includes its nodeValues array, what its parent node is, 
		 * and where this current node lies as a child of its parent. 
		 */
		
		/*
		 * This method returns a string representation of the elements in a Node's nodeValues array WITHOUT the INTEGER.MAX_VALUE elements.
		 * Format:   "[element1, element2, element3]"
		 */
		public static String arrayOfValuesString(mWayTree.Node root) {
			int j = 0;
			while (root.nodeValues[j] != Integer.MAX_VALUE) {
				j++;
			}
			int[] values = new int[j];
			for (int i = 0; i < j; i ++) {
				values[i] = root.nodeValues[i];
			}
			String nodeValuesString = "[";
			for (int i = 0; i < values.length; i++) {
				if (i == values.length - 1) {
					nodeValuesString = nodeValuesString + values[i] + "]";
				} else {
					nodeValuesString = nodeValuesString + values[i] + ", ";
				}
			}
			return nodeValuesString;
		}
		
		/*
		 * This method is called as a helper method in order to print a string corresponding to a non-root node. Parameters are the node 'root', the index it
		 * is from the parent node, and the string representation of the parent's node values.
		 * Format: "This Node is the child of Node containing [parentNodeValue1, parentNodeValue2, etc.] at index "index". The Node contains [currentNodeValue1, currentNodeValue2, etc.]
		 */
		public static void printNodeInformation(mWayTree.Node root, int index, String parentNodeValues) {
			String nodeVals = arrayOfValuesString(root);
			String printStatement = "This Node is the child of Node containing " + parentNodeValues + " at index " + index + ". The Node contains " + nodeVals;
			System.out.println(printStatement);
		}
		
		/*
		 * This method is called first for the root of a tree which prints a specific message regarding the fact that this is the root of the tree and therefore
		 * has no index of the parent. Additionally, the parent is null. It then calls the second printMWayTree() method on each child of the root, passing
		 * the string representation of its values, the index that the child lies in, and the child itself.
		 */
		public static void printMWayTree(mWayTree.Node root) {
			String rootValues = arrayOfValuesString(root);
			String rootString = "Root of the tree. Node Values: " + rootValues + ". Parent is: " + root.parent;
			System.out.println(rootString);
			int i = 0;
			while (root.children[i] != null) {
				printMWayTree(root.children[i], i, rootValues);
				i++;
			}
		}
		
		/*
		 * This is the method called on each subsequent child node. It is called on every node in the tree but the root node. It prints the current node 
		 * information by calling the printNodeInformation() method (different format from root node) and then calls itself recursively for every child of
		 * the current node. This prints information in a pre-order traversal of the tree, but provides enough information about the tree to determine the
		 * structure as a whole. Before calling itself on children nodes, it creates a string representation of its values and passes it on.
		 */
		public static void printMWayTree(mWayTree.Node root, int index, String parentNodeValues) {
			printNodeInformation(root, index, parentNodeValues);
			String currentNodeValues = arrayOfValuesString(root);
			int i = 0;
			while (root.children[i] != null) {
				printMWayTree(root.children[i], i, currentNodeValues);
				i++;
			}
		}
		
		/*
		 * Another print method to print the tree like a tree in console, labeling each node as the child of parent at a specific
		 * index. Adds a series of spaces to separate layers of the tree.
		 */
		public static void printInorder(mWayTree.Node root) {
			printInorder(0, root, 0);
		}
		
		public static void printInorder(int prefixLength, mWayTree.Node root, int index) {
			if (root == null) return;
			else {
				String spaces = " ";
				int numbSpaces = 40 * prefixLength;
				spaces = spaces.repeat(numbSpaces);
				String rootValues = arrayOfValuesString(root);
				if (root.parent == null) {
					String rootLine = "Root Node containing: " + rootValues;
					System.out.println(rootLine);
				} else {
					String output = spaces + "Child at index: " + index + " containing: " + rootValues;
					System.out.println(output);
				}
				int i = 0;
				while (root.children[i] != null) {
					printInorder(prefixLength + 1, root.children[i], i);
					i++;
				}
			}
		}
		
		
	}
	
	/*
	 * The following is the test case B-Tree for some of the values given in the M-Way Tree for Assignment 4 Question 2.
	 * I have printed the tree's information following each insert to show how overflow is being handled at each occurrence.
	 */
	public static void main(String[] args) {
		/*
		 * The following lines in main() is the test cases of inserting elements into the tree. It handles the following types of insertion:
		 * 				1. Insertion into a leaf node that is not yet full. Examples: 50, 60, 80, 35, 58, 63, 73.
		 * 				2. Insertion into a leaf node in which the node is full. Examples: 59, 70, 100
		 * 				3. Insertion and overflow when the root node is full. Example: 100 (overflow moves up to parent node which is full)
		 */
		String border = "-";
		String spaces = " ";
		spaces = spaces.repeat(40);
		border = border.repeat(150);
		System.out.println(border);
		System.out.println("NOTE: THE Node.children VARIABLE HAS INDECES 0-3, IF A CHILD IS AT INDEX 0 OF A PARENT, THEN ITS VALUES ARE LESS THAN ALL ELEMENTS IN THE PARENT");
		System.out.println("ARRAY. IF THE INDEX IS 1, ITS VALUES ARE GREATER THAN THE PARENT'S FIRST VALUE, BUT LESS THAN THE SECOND. IF THE INDEX IS 2, ITS VALUES ARE");
		System.out.println("GREATER THAN THE SECOND VALUE OF THE PARENT NODE, BUT LESS THAN THE LAST. FINALLY, IF THE INDEX IS 3, THEN ITS VALUES ARE GREATER THAN ALL VALUES");
		System.out.println("OF THE PARENT NODE.");
		System.out.println("\nTHE DISTANCE FROM THE LEFTMOST SIDE OF THE TERMINAL INDICATES THE LEVEL OF THE NODE (NODES WITH THE SAME DISTANCE ARE ON THE SAME LEVEL ON THE TREE).");
		System.out.println("HOWEVER, EACH NODE'S CHILDREN FOLLOW SUBSEQUENTLY IN A TOP-DOWN MANNER WITH REGARDS TO THE CONSOLE.");
		System.out.println(border);
		
		mWayTree testTree = new mWayTree();

		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 50);
		System.out.println(spaces + "Tree after inserting 50\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 60);
		System.out.println(spaces + "Tree after inserting 60\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 80);
		System.out.println(spaces + "Tree after inserting 80\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 30);
		System.out.println(spaces + "Tree after inserting 30\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 35);
		System.out.println(spaces + "Tree after inserting 35\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 58);
		System.out.println(spaces + "Tree after inserting 58\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 59);
		System.out.println(spaces + "Tree after inserting 59\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 63);
		System.out.println(spaces + "Tree after inserting 63\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 70);
		System.out.println(spaces + "Tree after inserting 70\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 73);
		System.out.println(spaces + "Tree after inserting 73\n\n");
		mWayTree.printInorder(testTree.root);
		System.out.println("\n\n");
		
		System.out.println(border);
		testTree.root = testTree.B_TreeInsert(testTree, testTree.root, 100);
		System.out.println(spaces + "Tree after inserting 100\n\n");
		mWayTree.printInorder(testTree.root);
	}
}
