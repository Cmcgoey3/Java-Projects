/* Name: Connor McGoey
 * Date: July 29, 2022
 */

public class BST_and_AVL {

	
	/*
	 *  This is my main binary search tree class that allows for both the implementation of a binary tree and a binary search tree.
	 *  The default data structure is a Node class that contains information about a Node's data and its left and right children.
	 *  By default when creating a new Node, its data will be set as the parameter passed and the left/right children will be null, 
	 *  this will be used to tell if a Node is a leaf node or not. The BST() constructor creates a new root node set to null.
	*/

	public static class BST {
		
		// Default Node class with constructor for root
		public static class Node{  
	        int data;  
	        Node left;  
	        Node right;  
	  
	        public Node(int data){  
	            //Assign data to the new node, set left and right children to null  
	            this.data = data;  
	            this.left = null;  
	            this.right = null;  
	            }  
	        }
		
	    public Node root;  
	    
	    BST(){  
	        root = null;  
	    }
	    
	    // Get method returns the Node if present in the binary search tree, and null if not.
	    public Node get(Node root, int k) {
	    	if (root == null) return root;
	    	else {
	    		if (k == root.data) return root;
	    		else {
	    			if (k < root.data) return get(root.left, k);
	    			else {
	    				return get(root.right, k);
	    			}
	    		}
	    	}
	    }
	    
	    // insert() method default to set the this.root to the tree's root to that with new data inserted.
	    void insert(int key)  { 
	        root = insert(root, key);
	    }
	    
	    // insert() method with root node and new data as parameters. Searches for the spot to put the node into. 
	    // Returns the tree's root after insertion.
	    public Node insert(Node root, int data) {
	    	if (root == null) {
	    		root = new Node(data);
	    		return root;
	    		}
	    	else {
	    		if (data < root.data) {
	    			root.left = insert(root.left, data);
	    		}
	    		else if (data > root.data) {
	    			root.right = insert(root.right, data);
	    		}
	    		return root;
	    	}
	    }
	    
	    // Debug method for printing the tree's nodes in an in-order traversal.
	    static void printInorder(Node node)
	    {
	        if (node == null)
	            return;
	  
	        /* first recur on left child */
	        printInorder(node.left);
	  
	        /* then print the data of node */
	        System.out.print(node.data + " ");
	  
	        /* now recur on right child */
	        printInorder(node.right);
	        
	    }
	    
	    // Helper method to return the height of the tree starting at the 'root' Node.
		public static int height(Node root) {
			if (root == null) return -1;
			else {
				int maxHeight = -1;
				int h = height(root.left);
				if (h > maxHeight) maxHeight = h;
				h = height(root.right);
				if (h > maxHeight) maxHeight = h;
				return 1 + maxHeight;
			}
		} 
	}
	
	
	/*
	 * This is the merge_binary_tree class which is used to merge two binary trees. It uses the BST class' Node data structure to traverse
	 * the Nodes in two trees recursively to create a new tree with the Nodes at each position being the sum of the two trees' Nodes.
	 */
	public static class merge_binary_tree {
		
		/* 
		 * merge() helper method to merge two Nodes together. Tests whether either node is null and if one is, the new Node value is simply
		 * the other node's value. If both nodes are null it returns null as well. Otherwise, it returns a new node with the data being set to
		 * the sum of the two Node data values.
		 */
		public static BST.Node merge(BST.Node node1, BST.Node node2){
			if (node1 == null) return node2;
			if (node2 == null) return node1;
			BST.Node newNode = new BST.Node(node1.data + node2.data);
			return newNode;
		}
		
		/*
		 * The mergeTrees() method merges two trees fully and returns the root node of the newly-merged trees. To do this, the method calls
		 * on the merge() method for the nodes. First calling it on the root of the two trees and then on each subsequent sub-Node for both
		 * trees. The method is recursive to set the left and right of the new tree node to the merging of the left and right sub-trees. The
		 * method also tests whether or not there exists a subtree to merge with by seeing if either of the tree's root nodes is null. If so,
		 * the method simply passes a null value to the merge() method.
		 */
		public static BST.Node mergeTrees(BST.Node tree1Root, BST.Node tree2Root) {
			BST.Node newTree = merge(tree1Root, tree2Root);
			if (tree1Root == null && tree2Root == null) return null;
			if (tree1Root == null) {
				newTree.left = mergeTrees(null, tree2Root.left);
				newTree.right = mergeTrees(null, tree2Root.right);
			}
			if (tree2Root == null) {
				newTree.left = mergeTrees(tree1Root.left, null);
				newTree.right = mergeTrees(tree1Root.left, null);
			}
			else if (tree1Root != null && tree2Root != null) {
				newTree.left = mergeTrees(tree1Root.left, tree2Root.left);
				newTree.right = mergeTrees(tree1Root.right, tree2Root.right);
			}
			return newTree;
		}
	}
	
	/*
	 * The AVL class calls on the Rotation class's methods to rotate the nodes of the tree recursively in order to convert the given
	 * tree into an AVL tree. Additionally, a check_balance() method recursively checks if the right and left subtree of any given node 
	 * in the tree differs by any number greater than 1.
	 */
	public static class AVL {
		
		/*
		 *  check_balance() method recursively checks if any possibly sub-tree in the tree has their left and right sub-tree heights differing
		 *  by any number greater than 1.
		 */
		public static int check_balance(BST.Node root) {
			if (root == null) return 0;
			
			int leftHeight = check_balance(root.left);
			if (leftHeight == -1) return -1;
			
			int rightHeight = check_balance(root.right);
			if (rightHeight == -1) return -1;
			
			if (rightHeight > leftHeight + 1) return -1;
			if (leftHeight > rightHeight + 1) return -1;
			
			if (leftHeight > rightHeight) return leftHeight + 1;
			else return rightHeight + 1;
		}
		
		/* 
		 * makeAVL() method implements a while loop to repeatedly make the tree balanced if there exists an unbalanced sub-tree.
		 * To do so it calls the Rotation class' balanceTree() method which searches for the lowest possible unbalanced subtree.
		 */
		public static BST.Node makeAVL(BST.Node root) {
			while (check_balance(root) == -1) {
				root = Rotation.balanceTree(root);
			}
			return root;
		}
		
	}

	/*
	 * Rotation class implements RR, LL, LR, and RL rotations by first implementing LL and RR rotations. RL and LR rotations are then
	 * simply combinations of LL and RR rotations respectively.
	 */
	public static class Rotation {

		/*
		 *  RR rotation means the tree is rotating left. First create a temporary BST.Node variable to hold the root's right child.
		 *  Set the right child to the left child of the root's right->left child. Finally, change the left child of the root's right 
		 *  child to the root itself.
		 */
		public static BST.Node RR(BST.Node root) {
			BST.Node rootRight = root.right;
			root.right = rootRight.left;
			rootRight.left = root;
			
			return rootRight;
		}
		
		/*
		 * Similar to the RR rotation, LL rotation does the opposite by working with the root's left child.
		 */
		public static BST.Node LL(BST.Node root) {
			BST.Node rootLeft = root.left;
			root.left = rootLeft.right;
			rootLeft.right = root;
			
			return rootLeft;
		}
		
		/*
		 *  Helper method to determine which side of the subtree has greater weigh. Returns string: "L" if the left subtree has greater height
		 *  or "R" if the right subtree has greater height.
		 */
		public static String weighBalance(BST.Node root) {
			if (BST.height(root.left) > BST.height(root.right)) return "L";
			else return "R";
		}
		
		/*
		 * Method to balance the subtree. This method is only called if we know for a fact that the difference in height between the two subtrees
		 * of root is greater than 1. Therefore, this method determines which side is "heavier", and then does the same on that side's child
		 * to determine if a LL, RR, RL, or LR rotation is needed. The method returns the root of the newly balanced tree.
		 */
		public static BST.Node balanceSubTree(BST.Node root) {
			if (weighBalance(root) == "L") {
				if (weighBalance(root.left) == "L") {
					root = LL(root);
					return root;
				} else {
					root.left = RR(root.left);
					root = LL(root);
					return root;
				}
			}
			if (weighBalance(root) == "R") {
				if (weighBalance(root.right) == "R") {
					root = RR(root);
					return root;
				} else {
					root.right = LL(root.right);
					root = RR(root);
					return root;
				}
			}
			return root;
		}
		
		/*
		 * Recursively searches for the tree's lowest unbalanced subtree. If there exists one, call the balanceSubTree() method to determine
		 * what rotation it needs and apply it. Returns the root of the tree after a single balance.
		 */
		public static BST.Node balanceTree(BST.Node root) {
			if (root.left != null) {
				root.left = balanceTree(root.left);
			}
			if (root.right != null) {
				root.right = balanceTree(root.right);
			}
			if (AVL.check_balance(root) == -1) {
				root = balanceSubTree(root);
			}
			return root;
		}
	}
	

	/*
	 * NOTE: THE FOLLOWING METHOD printBinaryTree() PRINTS THE BINARY TREE THAT FOLLOWS A ROOT NODE. IF A NODE IS A LEFT CHILD OF THE 
	 * PREVIOUS NODE, IT WILL BE PRECEEDED BY A "|--". IF THE NODE IS A RIGHT CHILD, IT WILL BE PRECEEDED BY A "\\--". PLEASE DO KEEP THIS
	 * IN MIND WHILE LOOKING AT THE OUTPUT OF THE TREES IN CONSOLE.
	 * 
	 * printBinaryTree() method prints each node with a prefix symbol depending on whether or not it is a right or left child of the previous
	 * Node. It then recursively prints the Node's children. The output is a horizontal tree, which allows for printing via a pre-order 
	 * traversal of the tree.
	 */
	public static void printBinaryTree(BST.Node root) {
		printBinaryTree("", root, false);
    }

    public static void printBinaryTree(String treeString, BST.Node root, boolean isALeftChild) {
        if (root == null) return;
        else {
        	System.out.println (treeString + (isALeftChild ? "|-- " : "\\-- ") + root.data);
            printBinaryTree(treeString + (isALeftChild ? "|   " : "    "), root.left, true);
            printBinaryTree(treeString + (isALeftChild ? "|   " : "    "), root.right, false);
        }
    }


	public static void main(String[] args) {
		String border = "-";
		border = border.repeat(150);
		/*
		 * Test case tree1 from the Assignment example with root 23 and sub-trees respectively. This tree is implemented using the insert()
		 * method repeatedly, with each layer of the binary tree being inserted first to avoid the wrong tree.
		 */
		BST tree1 = new BST();
		tree1.insert(23);
		tree1.insert(17);
		tree1.insert(39);
		tree1.insert(9);
		tree1.insert(78);
		tree1.insert(61);
		
		/*
		 * Test case tree2 from the Assignment example with root 18 and sub-trees respectively. This tree is implemented using the insert()
		 * method repeatedly as well as inserting the Node '19' manually due to its placement in the tree not following the ordering
		 * of a binary search tree's insert() rule.
		 */
		BST tree2 = new BST();
		tree2.insert(18);
		tree2.insert(15);
		tree2.insert(20);
		tree2.insert(16);
		tree2.root.left.right.left = new BST.Node(19);
		tree2.insert(20);
		tree2.insert(25);
		
		// Print tree1 and tree 2
		System.out.println(border);
		System.out.println("NOTE: IF A NODE IS A LEFT CHILD OF THE PREVIOUS NODE, IT WILL BE PRECEEDED BY A \"|--\". IF THE NODE IS A RIGHT CHILD, IT WILL BE PRECEEDED BY A \"\\--\". \nPLEASE DO KEEP THIS IN MIND WHILE LOOKING AT THE OUTPUT OF THE TREES IN CONSOLE.");
		System.out.println(border);
		System.out.println("TREE 1: WITH INPUT FROM 1ST TREE ON ASSIGNMENT SHEET\n");
		printBinaryTree(tree1.root);
		System.out.println("\n\n\n");
		
		System.out.println(border);
		System.out.println("TREE 2: WITH INPUT FROM 2ND TREE ON ASSIGNMENT SHEET\n");
		printBinaryTree(tree2.root);
		System.out.println("\n\n\n");

		/*
		 * Create new tree3 which first holds the tree resulting from the merger of tree1 and tree2.
		 */
		BST tree3 = new BST();
		tree3.root =  merge_binary_tree.mergeTrees(tree1.root, tree2.root);
		
		// Print the merged tree3
		System.out.println(border);
		System.out.println("TREE 3: FROM MERGING TREE 1 AND TREE 2\n");
		printBinaryTree(tree3.root);
		System.out.println("\n\n\n");
		
		// Final tree 'tree3AVL' which holds the resulting tree after making the merged tree3 into an AVL tree.
		BST tree3AVL = new BST();
		tree3AVL.root = AVL.makeAVL(tree3.root);
		
		// Print the final AVL tree 'tree3AVL'
		System.out.println(border);
		System.out.println("TREE 3: TREE 3 AFTER MAKING IT AVL\n");
		printBinaryTree(tree3.root);
		System.out.println("\n\n\n");
		System.out.println(border);
		System.out.println("NOTE: IF A NODE IS A LEFT CHILD OF THE PREVIOUS NODE, IT WILL BE PRECEEDED BY A \"|--\". IF THE NODE IS A RIGHT CHILD, IT WILL BE PRECEEDED BY A \"\\--\". \nPLEASE DO KEEP THIS IN MIND WHILE LOOKING AT THE OUTPUT OF THE TREES IN CONSOLE.");
		}
}
