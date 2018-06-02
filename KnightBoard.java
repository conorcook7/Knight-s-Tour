import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


/**
 * This class is responsible for taking in input from KnightTour and selects which
 * method you would like to solve the tour with and displays the board. 
 * @author conor cook
 *
 */
public class KnightBoard {
	private static int n;
	private final static int xMoveList[] = { 1, 1, -1, -1, 2, 2, -2, -2 };
	private final static int yMoveList[] = { 2, -2, 2, -2, 1, -1, 1, -1 };
	private final static int[][] moveList = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},
	        {-2,1},{-2,-1},{-1,-2}};
	private static int[][] grid;
	private static int total;
	
	/**
	 * Constructor for the KnightBoard Class
	 * This creates the object and solves the board if there is a solution
	 * and returns the grid printed to KnightTour
	 * 
	 * @param nIn
	 * 			N sized input
	 * @param initX
	 * 			Starting X-Coordinate
	 * @param initY
	 * 			Starting Y-Coordinate
	 * @param option
	 * 			Selects between 3 options for the 3 methods to solve
	 */
	public KnightBoard(int nIn, int initX, int initY, int option) {
		
		if(option == 0) {
			this.n = nIn;
			this.grid = new int[n][n];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					grid[x][y] = -1;
				}
			}
			grid[initX][initY] = 1;
			solveKnightsTourOne(initX, initY);
		}else if(option == 1) {
			this.n = nIn + 4;
			grid = new int[n][n];
			total = (n - 4) * (n - 4);
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					if (row < 2 || row > n - 3 || col < 2 || col > n - 3) {
						grid[row][col] = -1;
					}
				}
			}
			
			/*
			//option for random spots below
	        int row = 2 + (int) (Math.random() * (n - 4));
	        int col = 2 + (int) (Math.random() * (n - 4));
			 */
			int startX = initX + 2;
			int startY = initY + 2;
			grid[startX][startY] = 1;
			
				if(solverWarn(startX, startY, 2)) {
					printGrid();
				}else {
					System.out.println("No Solution. ");
				}
		}
	}
	
	/**
	 * This Method is Option 1 Brute Force Search w/ Backtracking
	 *
	 * @param x
	 * 		X-Coordinate
	 * @param y
	 * 		Y-Coordinate
	 * @return true/false
	 */
	private boolean solveKnightsTourOne(int x, int y) {
		if (!solverOneTool(x, y, 1)) {
			System.out.println("No Solution. ");
			return false;
		} else {
			printGrid();
		}
		return true;
	}

	/**
	 * This is a helper Method for solveKnightsTourOne.
	 * This method handles the recursive calling for checking
	 * all of the possible paths the knight can take.
	 * 
	 * @param x
	 * 		X-Coordinate
	 * @param y
	 * 		Y-Coordinate
	 * @param count
	 * 		Current move # the Knight is on
	 * @return true/false
	 */
	private boolean solverOneTool(int x, int y, int count) {
		int nextX, nextY, i;
		if (count == n * n) {
			return true;
		}
		for (i = 0; i < 8; i++) {
			nextX = x + xMoveList[i];
			nextY = y + yMoveList[i];
			if (safeMove(nextX, nextY)) {
				grid[nextX][nextY] = count+1;
				if (solverOneTool(nextX, nextY,count + 1)) {
					return true;
				} else {
					grid[nextX][nextY] = -1;
				}
			}
		}
		return false;
	}

	/**
	 * This is the method that handles Option 2, Heuristic (Warnsdorf Method)
	 * 
	 * @param x
	 * 		X-Coordinate
	 * @param y
	 * 		Y-Coordinate
	 * @param count
	 * 		Current move # the Knight is on
	 * @return true/false
	 */
	private static boolean solverWarn(int x, int y, int count) {
        if (count > total) {
        	return true;
        }
        
        List<int[]> nextMoves = possibleSpots(x, y);
        if (nextMoves.isEmpty() && count != total) {
        	return false;
        }
 
        Collections.sort(nextMoves, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
 
        for (int[] nm : nextMoves) {
            x = nm[0];
            y = nm[1];
            grid[x][y] = count;
            if (!isAlone(x, y, count) && solverWarn(x, y, count + 1)) {
            	return true;
            }
            grid[x][y] = 0;
        }
        return false;
    }
 
	/**
	 * This method is a helper method for Warnsdorf option
	 * This method handles the operation to create a list of all the possible
	 * next steps that the knight can take and returns that list.
	 * @param row
	 * 			X-Coordinate to start search from
	 * @param col
	 * 			Y-Coordinate to start search from
	 * @return nextMoves
	 * 			List of all available next coordinates
	 */
    private static List<int[]> possibleSpots(int row, int col) {
        List<int[]> nextMoves = new ArrayList<>();
 
        for (int[] m : moveList) {
            int x = m[0];
            int y = m[1];
            if (grid[row + y][col + x] == 0) {
                int num = countNextOption(row + y, col + x);
                nextMoves.add(new int[]{row + y, col + x, num});
            }
        }
        return nextMoves;
    }
 
    /**
     * This method is a helper method for Warnsdorf option
     * This method counts the current amount of next moves.
     * 
     * @param x
     * 		X-Coordinate
     * @param y
     * 		Y-Coordinate
     * @return num
     * 			number of next available spots
     */
    private static int countNextOption(int x, int y) {
        int num = 0;
        for (int[] m : moveList) {
            if (grid[x + m[1]][y + m[0]] == 0) {
                num++;
            }
        }
        return num;
    }
 
    /**
     * This is a helper method for the Warnsdorf option
     * This method checks the next moves possible next moves and
     * puts these options into a list.
     * @param x
     * 		X-Coordinate
     * @param y
     * 		Y-Coordinate
     * @param count
     * 		Current move # the Knight is on
     * @return true/false
     */
    private static boolean isAlone(int x, int y, int count) {
        if (count < total - 1) {
            List<int[]> nextMoves = possibleSpots(x, y);
            for (int[] nm : nextMoves) {
                if (countNextOption(nm[0], nm[1]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
 
    /**
     * Simple method to print out the solved grid
     */
    private static void printGrid() {
        for (int[] row : grid) {
            for (int i : row) {
                if (i == -1) continue;
                System.out.printf("%2d ", i);
            }
            System.out.println();
        }
    }
	
    /**
     * This is a helper method for the two Heuristic Options
     * This method handle getting the degree of a selected position on the 
     * board. Degree = Possible number of moves that can be made from that
     * position.
     * 
     * @param x
     * 		X-Coordinate
     * @param y
     * 		Y-Coordinate
     * @return degree
     */
    private int getDegree(int x, int y) {
    	int degree = 0;
    	int nextX, nextY;
    	for(int i = 0; i < 8; i++) {
    		nextX = x + xMoveList[i];
    		nextY = y + yMoveList[i];
    		if(safeMove(nextX, nextY)) {
    			degree++;
    		}
    	}
    	return degree;
    }
    
    /**
     * This is a helper method for all the options
     * This Method simply checks to make sure that the next space you check
     * is available for the knight to move or not.
     * 
     * @param x
     * 		X-Coordinate
     * @param y
     * 		Y-Coordinate
     * @return true/false
     */
	private boolean safeMove(int x, int y) {
		return (x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == -1);
	}
	
}