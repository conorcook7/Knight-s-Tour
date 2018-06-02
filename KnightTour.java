
/**
 * This is the driver class of the Knights Tour Problem
 * To run this program it takes in 4 constraints.
 * 1st: Choose solving method between Brute Force w/ Backtracking (0)
 * 		OR Heuristic (Warnsdorf Method) (1)
 * 		
 * 2nd: Choose your size of N for a NxN grid.
 * 3rd: Choose your starting X-Coordinate
 * 4th: Choose your starting Y-Coordinate
 * 
 * Ex:
 *  java KnightTour <0/1 (no/heuristicI search)> <n> <x> <y>
 *  OR
 *  java KnightTour 0 6 0 0
 *  
 * @author conor cook
 *
 */
public class KnightTour {

	public static void main(String[] args) {
		
		if(args.length > 0 && args.length == 4 && (Integer.parseInt(args[0]) == 0 ||
				Integer.parseInt(args[0]) == 1)) {
			int option = Integer.parseInt(args[0]);
			int n = Integer.parseInt(args[1]);
			int x = Integer.parseInt(args[2]);
			int y = Integer.parseInt(args[3]);
			new KnightBoard(n, x, y, option);
		}else {
			System.out.println("Please enter in the correct arguments.");
			System.out.println("The correct format is: ");
			System.out.println("java KnightTour <0/1 (no/heuristic search)> <n> <x> <y>");
			System.exit(0);
		}		
	}
}
