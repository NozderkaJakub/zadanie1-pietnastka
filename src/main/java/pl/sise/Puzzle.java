package pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
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
        configure();
    }

    public String getAsString() {
        String toRet = "";
        for(int[] row : puzzleMatrix) {
            for(int val : row) {
                String c = "";
                if(val >= 10) {
                    int temp = val + 55;
                    c = Character.toString((char) temp);
                }
                else {
                    c = Integer.toString(val);
                }
                toRet += c;
            }
        }
        return toRet;
    }

    public Puzzle(String arrayAsString, ArrayList<String> order, int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        puzzleMatrix = new int[dimX][dimY];
        int strIt = 0;
        for (int i = 0; i < puzzleMatrix.length; i++) {
        	for (int j = 0; j < puzzleMatrix[i].length; j++) {
                char c = arrayAsString.charAt(strIt);
                int val = 0;
                String s = "" + c;
                if(Character.isDigit(c)) val = Integer.parseInt(s);
                else {
                    int temp = (int) c;
                    val = temp - 55;
                }
                puzzleMatrix[i][j] = val;
                strIt++;
        	}
        }
        this.order = order;
        configure();
    }

    public Puzzle(int[][] puzzleMatrix, ArrayList<String> order) {
        this.puzzleMatrix = puzzleMatrix;
        this.order = order;
        configure();
    }

    public Puzzle(Puzzle puzzle) {
    	this.puzzleMatrix = new int [puzzle.getDimY()][puzzle.getDimX()];
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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Puzzle)) {
            return false;
        }
        Puzzle puzzle = (Puzzle) o;
        return dimX == puzzle.dimX && dimY == puzzle.dimY && Objects.equals(puzzleMatrix, puzzle.puzzleMatrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(puzzleMatrix);
    }


}
