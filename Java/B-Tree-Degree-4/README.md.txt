# B-Tree Implementation in Java

## Introduction

This repository contains a Java implementation of a B-Tree with a degree of 4. The B-Tree is designed to handle insertion of elements while maintaining the B-Tree properties, such as the maximum number of children and elements per node.

## Class Structure

### `mWayTree`

This class represents the B-Tree implementation. It includes the following methods:

- `B_TreeInsert`: Inserts a new element into the B-Tree while handling overflow and parent node splits if necessary.
- `insertNotFull`: Inserts an element into a node that is not full, shifting elements as needed.
- `childFull`: Checks if a child node is full.
- `updateChildren`: Updates the children of a node after inserting new nodes.
- `findInsertIndex`: Finds the appropriate index to insert a new element into a node.
- `isLowestInternal`: Checks if a node is the lowest internal node where the key should be inserted.
- `isInTree`: Recursively checks if a key is already present in the B-Tree.
- `isInNode`: Checks if a key is present within a node.
- `getChild`: Finds the index of the child where a key should be inserted.
- Various methods for printing the B-Tree structure.

### `Node`

This nested class represents a node within the B-Tree. It includes methods for constructing nodes and managing their properties.

## Usage

The provided `main` method in the `B_Tree` class demonstrates the usage of the B-Tree implementation. It inserts various elements into the B-Tree and prints the B-Tree structure after each insertion. The implementation handles cases where nodes are not yet full, as well as cases where overflow occurs, leading to node splits and adjustments in the B-Tree structure.

## Important Notes

- The implementation uses the `Node` class to represent nodes within the B-Tree, and the `mWayTree` class as the primary B-Tree class.
- The `Node.children` array has indices 0-3, with specific meanings: 0 represents children with values less than all parent elements, 1 represents values between the first and second parent element, 2 between the second and third, and 3 greater than all parent elements.
- Overflow in the root node leads to the creation of a new root node.
- The implementation demonstrates various scenarios of insertion and overflow handling within a B-Tree.

## Example Output

The output of the provided `main` method demonstrates the structure of the B-Tree as elements are inserted and overflow is handled. Each tree structure printout shows the nodes and their relationships.

## Conclusion

This Java implementation provides a demonstration of how B-Trees work for maintaining sorted data and handling overflow in a balanced way. The provided code and examples can be helpful for learning about B-Tree data structures and their operations.
