// Imports two exceptions: FileNotFoundException & IOException
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class takes in a file and executes a Cupid simulation which attempts to find targets while also following limitations such as
 * the number of arrows, inertia of each arrow, number of turns, and number of backtracks
 * @author Connor MCGoey
 * Date: March 5, 2021
 */
public class StartSearch {
	private Map targetMap; // Map object 
	private int numArrows; // integer for number of arrows cupid has
	private int inertia; // integer inertia counter for each arrow
	private int direction; // integer direction, between 0-3 which corresponds with  North (0) / East (1) / South (2) / West (3)
	
	/**
	 * Constructor method that creates a new object of type Map. If an error occurs, it catches the error and exits
	 * @param filename file to be made into map
	 */
	public StartSearch(String filename) {
		try {
			targetMap = new Map(filename);
		} catch (InvalidMapException e) {
			System.out.println("Invalid Map Exception" + e.getMessage());
			System.exit(0);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception" + e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Input Output Exception " + e.getMessage());
			System.exit(0);
		} catch (EmptyStackException e) {
			System.out.println("Empty Stack Exception" + e.getMessage());
			System.exit(0);
		}

	}
	
	/**
	 * Main method for the program, which attempts to find a target within the limitations
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayStack.sequence = "";
		if (args.length < 1) { // If no string is given to check for file
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		int maxPathLength; 
		if (args.length > 1) {
			maxPathLength = Integer.parseInt(args[1]);
		} else {
			maxPathLength = -1;
		}
		String mapFileName = args[0]; // initializes the string to be checked as a Map object
		StartSearch search = new StartSearch(mapFileName); // creating a StartSearch object called search
		search.numArrows = search.targetMap.quiverSize(); // number of arrows
		int targetsFound = 0; // targets found
		int backTracks = 0; // backtracks taken
		int travelDistance = 0;
		boolean done = false; // boolean for whether each path has finished within the map
		boolean inertiaCheckPass = true; // boolean for whether or not the arrow can turn
		ArrayStack<MapCell> stack = new ArrayStack<MapCell>(); // creating new ArrayStack object that stores data of type MapCell
		stack.push(search.targetMap.getStart()); // push cupid's location into the stack
		search.targetMap.getStart().markInStack(); // mark cupid's location as in stack
		while ((stack.isEmpty() == false) && (search.numArrows != 0)) { // while the stack is not empty & Cupid has arrows left
			MapCell currCell = stack.peek(); // current cell in the path is the top item of Stack
			int initialDirection = search.direction; // initialize the direction of each path
			if (search.inertia > 2) { // if the inertia has reached max speed in same direction
				if (currCell.getNeighbour(search.direction) == null) { // if the neighbour cell in the arrow's direction does not exist
					inertiaCheckPass = false;
				}
				if (currCell.getNeighbour(search.direction) != null) { // if if the neighbour cell in the arrow's direction does  exist
					if (search.checkCell(currCell, search.direction) == false) { // if the cell does exist, but the arrow cannot travel to it
						inertiaCheckPass = false;
					}
					if (currCell.getNeighbour(search.direction).isBlackHole()) { // if the path is blocked
						inertiaCheckPass = false;
					}
				}
				if (inertiaCheckPass == false) { // if the arrow has reached max inertia, and satisfies any of the above statements, the path is done
					done = true;
				}
				else { // if not, the arrow may turn/backtrack
					inertiaCheckPass = true;
				}
			}
			MapCell next = search.nextCell(currCell); // the next cell of the path according to NextCell()
			if (currCell.getNeighbour(search.direction) != null) { // more inertia checking code
				if ((search.inertia > 2) && ((currCell.getNeighbour(search.direction) == null)) || (currCell.getNeighbour(search.direction).isBlackHole())) {
					done = true;
				}
				if ((search.inertia > 2) && (search.checkCell(currCell, search.direction) == false)) {
					done = true;
				}
			}
			if ((next != null) && (inertiaCheckPass)) { // if the next cell exists and passes the inertia check 
				// if the top of the stack is not cupid's location AND the arrow can travel to that cell 
				if (stack.peek() != search.targetMap.getStart() && (search.checkCell(currCell, search.direction) == true)) { 
					search.inertia++; // increase inertia
					if (initialDirection != search.direction) { // if the arrow is making a turn
						search.inertia--; // decrease the increase that was just made
					}
					}
				stack.push(next); // push next cell into the stack
				next.markInStack(); // mark the next cell as in stack
				if (maxPathLength != -1) { 
					travelDistance++;
				}
				if (next.isTarget()) { // if the next cell is a target
					targetsFound++; // increase number of targets found
					done = true; // path is done
				}
			} else {
				if ((backTracks != 3) && (stack.size() != 1)) { // if the path can still make backtracks AND does not ONLY contain cupid's location
					stack.pop(); // return to previous cell (backtrack)
					travelDistance++; // increment the distance traveled
					backTracks++; // increment the amount of backtracks
				}
			}
			if (stack.size() == 1) { // if the stack has one element in it (cupid's start location)
				done = true; // path is finished
			}
			
			if (done == true) { // if the path is done
				while (stack.isEmpty() == false) { // while the stack is not empty
					if (stack.size() == 2) { // executes at the stack's value which is always the cell next to Cupid
						stack.peek().markOutStack(); // mark the cell next to Cupid as out of stack
					}
					stack.pop(); // pop each element until the stack is empty
				}
				
				search.numArrows--; // decrement the number of arrows after each path
				if (search.numArrows > 0) { // if there are arrows left, push cupid's start location into the stack
					stack.push(search.targetMap.getStart()); 
				}
				search.inertia = 0; // reset inertia for the path to 0
				backTracks = 0; // reset the path's backtracks to 0
				done = false; // reset to the path not being done
				inertiaCheckPass = true; // reset inertia check to true
			}
		}
		System.out.println("Targets Found: " + targetsFound); // print out the number of targets found within limitation
	}

	/**
	 * Custom helper method for checking if the arrow can travel in the given direction.
	 * The arrow passes the test as long as it is not attempting to go from vertical to horizontal, horizontal to vertical, from a cross path cell
	 * to a vertical cell if the vertical cell is to the left or right of the current cell, 
	 * or from a cross path cell to a horizontal cell if the horizontal cell is above or below the current cell
	 * @param cell the current cell that the program is moving from
	 * @param direction the direction that the program is attempting to go 
	 * @return boolean value, true if it passes above restrictions, false if it does not
	 */
	private boolean checkCell(MapCell cell, int direction) {
		if (cell.isHorizontalPath()) { // for if current cell is a horizontal path
			if ((direction == 0) || (direction == 2)) { // cannot travel north or south 
				return false;
			}
			if (cell.getNeighbour(direction).isVerticalPath()) { // cannot travel to a vertical path
				return false;
			}
		}
		if (cell.isVerticalPath()) { // for if the current cell is a vertical path
			if ((direction == 1) || (direction == 3)) { // cannot travel east or west
				return false;
			}
			if (cell.getNeighbour(direction).isHorizontalPath()) { // cannot travel to horizontal path
				return false;
			}
		}
		if (cell.isCrossPath()) { // for if the current cell is a cross path
			if ((direction == 0) || (direction == 2)) { // if direction is north/south
				if (cell.getNeighbour(direction).isHorizontalPath()) { // if attempting to travel to a horizontal path
					return false;
				}
			}
			if ((direction == 1) || (direction == 3)) { // if direction is east/west
				if (cell.getNeighbour(direction).isVerticalPath()) { // if attempting to travel to a vertical path
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Method for returning the best adjacent cell within limitations, if none exist, returns null.
	 * Most if-statements are similar in that they check whether or not the cell can be traveled to, that it is not a black hole/blocked path,
	 * and that it is not marked.
	 * In order the program will return a cell if: it is in the same direction that the path is going, it is a target cell, it is a cross-path cell,
	 * and finally if it can be traveled to.
	 * @param cell the current cell of the path
	 * @return null or best cell within limitations
	 */
	public MapCell nextCell(MapCell cell) {
		if (cell.getNeighbour(direction) != null) { // ensures the cell in the direction exists
			// following two if statements prioritize the cell in the direction that the path is going first
			if ((cell.isStart() == false) && ((direction == 0) || (direction == 2)) && (checkCell(cell, direction) && (cell.getNeighbour(direction).isBlackHole() == false)) && 
					(cell.getNeighbour(direction).isMarked() == false) && cell.getNeighbour(direction) != null) {
				return cell.getNeighbour(direction);
			}
			if ((cell.isStart() == false) && ((direction == 1) || (direction == 3)) && (checkCell(cell, direction)) && (cell.getNeighbour(direction).isBlackHole() == false) && 
					(cell.getNeighbour(direction).isMarked() == false) && cell.getNeighbour(direction) != null) {
				return cell.getNeighbour(direction);
			}
		}
			for (int i = 0; i < 4; i++) { // if the path cannot travel in the direction it was heading, prioritize a target cell
				if (cell.getNeighbour(i) != null) {
					if ((cell.getNeighbour(i).isBlackHole() == false)) {
						if (cell.getNeighbour(i).isTarget() && (checkCell(cell, i)) && (cell.getNeighbour(i).isMarked() == false)) {
							direction = i;
							return cell.getNeighbour(i);
						}
					}
				}
			}
			for (int i = 0; i < 4; i++) { // next, prioritize a target cell
				if (cell.getNeighbour(i) != null) {
					if (cell.getNeighbour(i).isBlackHole() == false) {
						if (cell.getNeighbour(i).isCrossPath() && (checkCell(cell, i)) && (cell.getNeighbour(i).isMarked() == false)) {
							direction = i;
							return cell.getNeighbour(i);
						}
					}
				}
			}
			for (int i = 0; i < 4; i++) { //finally, prioritize a cell that is can travel to (checkCell) with the lowest index
				if (cell.getNeighbour(i) != null) {
					if ((cell.getNeighbour(i).isBlackHole() == false) && (cell.getNeighbour(i) != null)) {
						if ((checkCell(cell, i)) && (cell.getNeighbour(i).isMarked() == false)) {
							direction = i;
							return cell.getNeighbour(i);
						}
					}
				}
			}
		return null; // return null if there is no cell that it can travel to 
	}	
}
