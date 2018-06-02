****************
* Project 1 - Knight's Tour
* CS421-Summer
* 05/16/2018
* Conor Cook
**************** 

OVERVIEW:

 This program solves the infamous Knight's Tour Algorithm via 3 different searching patterns. The user has 4
 inputs for this program.
 * 1st: Choose solving method between Brute Force w/ Backtracking (0)
 * 		 OR Heuristic (Warnsdorf Method) (1)
 * 		
 * 2nd: Choose your size of N for a NxN grid.
 * 3rd: Choose your starting X-Coordinate
 * 4th: Choose your starting Y-Coordinate
 
 Once these are input, this program will solve the Knight's Tour if there is indeed a solution.

INCLUDED FILES:

  The list of files needed are:
 * KnightTour.java - source file -- Driver class that calls upon KnightBoard
 * KnightBoard.java - source file -- Handles the operations to solve and print the solved board
 * README - this file -- Contains all information about this Project
  
COMPILING AND RUNNING:
  
 From the directory containing all source files, compile the required classes with the commands:
 $ javac KnightBoard.java
 $ javac KnightTour.java
 
 Run the compiled driver class with the command with the given parameters:
 $ java KnightTour <0/1 (BruteForce with Backtracking/ heuristic search)> <n> <x> <y>
 
 		ex: java KnightTour 0 6 0 0
 
 The Console output will give the results of the solved board or will print No solution.
  

 
RESULTS WITH    N = 7 , X = 1 , Y = 1 , For both options.

  TABLE 1  for Brute Force w/ Backtracking (No Heuristic):
	
	
	25 22 35 44 39 20 37 
	34  1 24 21 36 43 40 
	23 26 45  2 41 38 19 
	46 33  8 27 18  3 42 
	49 14 47  4  9 28 17 
	32  7 12 15 30  5 10 
	13 48 31  6 11 16 29 
	
	
  TABLE 3 for Warnsdorf:
	

	49 44 11  2 29 42 13 
	10  1 46 43 12  3 28 
	45 48  9 30 41 14 35 
	 8 25 40 47 36 27  4 
	39 22 31 26 17 34 15 
	24  7 20 37 32  5 18 
	21 38 23  6 19 16 33 
	
	