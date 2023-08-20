Binary Search Tree Operations and AVL Tree Conversion
This repository contains Java code for performing various operations on binary search trees (BSTs) and converting a BST into an AVL tree. The provided code includes implementations of binary search trees, merging of binary trees, AVL tree checks, and rotations.

Binary Search Tree (BST) Implementation
The code defines a BST class that represents a binary search tree. The Node class represents a node within the tree, containing data, left, and right pointers. The following operations are implemented:

- insert(int key): Inserts a new node with the specified data into the BST.
- get(Node root, int k): Searches for a node with the given key in the BST.
- printInorder(Node node): Prints the nodes of the BST in an in-order traversal.
- height(Node root): Returns the height of the tree rooted at the specified node.

Merging Binary Trees
The merge_binary_tree class provides a method mergeTrees to merge two binary trees. It recursively merges nodes from both trees to create a new tree, where each node's data is the sum of the corresponding nodes from the input trees.

AVL Tree Conversion
The AVL class facilitates AVL tree conversion. It includes methods for checking tree balance and performing AVL rotations. The Rotation class defines rotation methods, such as RR (right rotation) and LL (left rotation), and provides utilities to balance subtrees. The makeAVL method in the AVL class converts a given tree into an AVL tree by applying rotations to balance the tree.

Output
The code provides a demonstration of the implemented functionality by creating and printing three trees: tree1, tree2, and tree3AVL.

- tree1 and tree2: These trees are constructed based on the provided test cases.
- tree3: This tree is the result of merging tree1 and tree2.
- tree3AVL: This tree is obtained by converting tree3 into an AVL tree using rotation operations.

Note: The output also includes a visual representation of the trees with proper indentation to indicate tree structure.

Please note that the code is provided as a Java file (BST_and_AVL.java) and can be compiled and executed using a Java development environment.