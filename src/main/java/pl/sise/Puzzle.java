package pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static pl.sise.Main.starting_file;

public class Puzzle {
    int dimX;
    int dimY;
    int[][] puzzleMatrix, modelMatrix;
    ArrayList<String> order;
    int x;
    int y;
    String path = "";

    public Puzzle() throws FileNotFoundException {
        this.readData(starting_file);
        configure();
    }

    public Puzzle(int[][] puzzleMatrix, ArrayList<String> order) {
        this.puzzleMatrix = puzzleMatrix;
        this.order = order;
        configure();
    }

    public Puzzle(Puzzle puzzle) {
    	this.puzzleMatrix = new int [getDimY()][getDimX()];
        this.puzzleMatrix = makeCopyOfPuzzle(puzzle).getMatrix();
        this.order = puzzle.order;
        configure();
    }

    public Puzzle(int[][] puzzleMatrix, Puzzle puzzle) {
        this.puzzleMatrix = puzzleMatrix;
        this.order = puzzle.order;
        configure();
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int[][] getMatrix() {
        return puzzleMatrix;
    }

    public int[][] getModelMatrix() {
        return modelMatrix;
    }
    
    public void setOrder(ArrayList<String> order) {
    	this.order = order;
    }
    

//     public int calculateFullCostOfPuzzle(ArrayList<ArrayList<Integer>> puzzle) {
//         int fullCost = 0;
//         for (int i = 0; i < this.dimY; i++) {
//             for (int j = 0; j < this.dimX; j++) {
//                 if (puzzle.get(i).get(j) != 0) {
//                     fullCost += calculateCost(j, i, puzzle.get(i).get(j));
//                 }
//             }
//         }
//         return fullCost;
//     }

//     private int calculateCost(int x, int y, int number) {
//         int i = 0;
//         int j;
//         for (ArrayList<Integer> row : this.puzzleModel) {
//             if (row.contains(number)) {
//                 i = this.puzzleModel.indexOf(row);
//             }
//         }
//         j = this.puzzleModel.get(i).indexOf(number);
//         return Math.abs(i - y) + Math.abs(j - x);
//     }

    protected void configure() {
        this.dimX = puzzleMatrix[0].length;
        this.dimY = puzzleMatrix.length;
        this.modelMatrix = makePuzzleModel();
        x = locateNumber(0)[0];
        y = locateNumber(0)[1];
    }
    
    public boolean isSolved() {
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix()[i].length; j++) {
                if (getMatrix()[i][j] != getModelMatrix()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] locateNumber(int[][] puzzle, int number) {
        int[] localization = new int[2];
        for (int i = 0; i < puzzle.length; i++) {
        	for (int j = 0; j < puzzle[i].length; j++) {
        		if (puzzle[i][j] == number) {
        			localization[0] = j;
        			localization[1] = i;
        		}
        	}
        }
        return localization;
    }

    public int[] locateNumber(int number) {
        int[] localization = new int[2];
        for (int i = 0; i < getMatrix().length; i++) {
        	for (int j = 0; j < getMatrix()[i].length; j++) {
        		if (puzzleMatrix[i][j] == number) {
        			localization[0] = j;
        			localization[1] = i;
        		}
        	}
        }
        return localization;
    }

	public Puzzle makeCopyOfPuzzle(Puzzle originalPuzzle) {
        int[][] puzzle = new int[originalPuzzle.getDimY()][originalPuzzle.getDimX()];
        for (int i = 0; i < puzzle.length; i++) {
        	for (int j = 0; j < puzzle[i].length; j++) {
        		puzzle[i][j] = originalPuzzle.puzzleMatrix[i][j];
        	}
        }
//        showFormattedPuzzle(puzzle);
        return new Puzzle(puzzle, this);
    }

    public Puzzle makeMove(Puzzle originalPuzzle, String move) {
        Puzzle puzzle = makeCopyOfPuzzle(originalPuzzle);
        int[] coords = puzzle.locateNumber(puzzle.getMatrix(), 0);
        //System.out.println(coords.toString());
        if (move == "R") {
            puzzle.swap(coords[0], coords[1], coords[0] + 1, coords[1]);
        } else if (move == "L") {
            puzzle.swap(coords[0], coords[1], coords[0] - 1, coords[1]);
        } else if (move == "U") {
            puzzle.swap(coords[0], coords[1], coords[0], coords[1] - 1);
        } else if (move == "D") {
            puzzle.swap(coords[0], coords[1], coords[0], coords[1] + 1);
        }
        puzzle.path += move;
        return puzzle;
    }

    public void makeMove(String move) {
        if (move == "R") {
            swap(x, y, x + 1, y);
        } else if (move == "L") {
        	swap(x, y, x - 1, y);
        } else if (move == "U") {
        	swap(x, y, x, y - 1);
        } else if (move == "D") {
        	swap(x, y, x, y + 1);
        }
        this.path += move;
    }

    protected int[][] makePuzzleModel() {
        int number = 1;
        int[][] puzzleModel = new int[getDimY()][getDimX()];
        for (int i = 0; i < getDimY(); i++) {
            for (int j = 0; j < getDimX(); j++) {
                if (number <= getDimX() * getDimY() - 1) {
                    puzzleModel[i][j] = number;
                    number++;
                } else {
                    puzzleModel[i][j] = 0;
                }
            }
        }
        return puzzleModel;
    }

    public void readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        this.dimX = Integer.parseInt(dims[0]);
        this.dimY = Integer.parseInt(dims[1]);
        this.puzzleMatrix = new int[getDimY()][getDimX()];
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            for (int j = 0; j < this.dimX; j++) {
                this.puzzleMatrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        fileRead.close();
    }

    public void showFormattedPuzzle() {
    	for (int i = 0; i < getMatrix().length; i++) {
        	for (int j = 0; j < getMatrix()[i].length; j++) {
                System.out.print(getMatrix()[i][j] + " ");
        	}
        	System.out.print('\n');
        }
    	System.out.print("\n\n");
    }

    public void showFormattedPuzzle(int[][] puzzle) {
    	for (int i = 0; i < puzzle.length; i++) {
        	for (int j = 0; j < puzzle[i].length; j++) {
                System.out.print(puzzle[i][j] + " ");
        	}
        	System.out.print('\n');
        }
    	System.out.print("\n\n");
    }

    public ArrayList<String> showPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        int[] coords = locateNumber(puzzleMatrix, 0);
        for (String move : this.order) {
        	if (move == "R" && coords[0] < dimX-1) {
	            moves.add("R");
	        }
	        if (coords[0] > 0 && move == "L") {
	            moves.add("L");
	        }
	        if (move == "D" && coords[1] < dimY-1) {
	            moves.add("D");
	        }
	        if (coords[1] > 0 && move == "U") {
	            moves.add("U");
	        }
        }
        return moves;
    }

    public void swap(int x1, int y1, int x2, int y2) {
        int tmp = puzzleMatrix[y1][x1];
        puzzleMatrix[y1][x1] = puzzleMatrix[y2][x2];
        puzzleMatrix[y2][x2] = tmp;
    }

}
