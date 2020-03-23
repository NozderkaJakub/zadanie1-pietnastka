package pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Puzzle {
    int dimX;
    int dimY;
    int[][] puzzleMatrix;
    String[] order = {"L", "R", "U", "D"};

    public Puzzle(String starting_file) throws FileNotFoundException {
        this.readData(starting_file);
    }

    public Puzzle(int[][] puzzleMatrix) {
        this.puzzleMatrix = puzzleMatrix;
        dimX = puzzleMatrix[0].length;
        dimY = puzzleMatrix.length;
    }

    public int[][] getMatrix() {
        return puzzleMatrix;
    }

    public void readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        this.dimX = Integer.parseInt(dims[0]);
        this.dimY = Integer.parseInt(dims[1]);
        this.puzzleMatrix = new int[dimY][dimX];
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            for (int j = 0; j < this.dimX; j++) {
                this.puzzleMatrix[i][j] = Integer.parseInt(line[j]);
            }
        }
     //   this.showFormattedPuzzle(this.puzzle);
     //   System.out.println(this.showPossibleMoves().toString());
//        System.out.println("original");
//        this.showFormattedPuzzle(this.puzzle);
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    // private int calculateCost(int x, int y, int number) {
    //     int i = 0;
    //     int j;
    //     for (ArrayList<Integer> row : this.puzzleModel) {
    //         if (row.contains(number)) {
    //             i = this.puzzleModel.indexOf(row);
    //         }
    //     }
    //     j = this.puzzleModel.get(i).indexOf(number);
    //     return Math.abs(i - y) + Math.abs(j - x);
    // }

    public int[] locateNumber(int[][] puzzle, int number) {
        int[] localization = new int[2];
        for (int i = 0; i < puzzle.length; i++) {
        	for (int j = 0; j < puzzle[j].length; j++) {
            	if (puzzle[i][j] == number) {
            		localization[0] = j;
            		localization[1] = i;
            	}
            }
        }
        return localization;
    }

    public void showFormattedPuzzle() {
    	for (int i = 0; i < puzzleMatrix.length; i++) {
    		for (int j = 0; j < puzzleMatrix[i].length; j++) {
                System.out.println(puzzleMatrix[i][j] + " ");
        	}
    	}
        System.out.println('\n');
    }

    // public int calculateFullCostOfPuzzle(ArrayList<ArrayList<Integer>> puzzle) {
    //     int fullCost = 0;
    //     for (int i = 0; i < this.dimY; i++) {
    //         for (int j = 0; j < this.dimX; j++) {
    //             if (puzzle.get(i).get(j) != 0) {
    //                 fullCost += calculateCost(j, i, puzzle.get(i).get(j));
    //             }
    //         }
    //     }
    //     return fullCost;
    // }

    protected static int[][] makePuzzleModel(int dimX, int dimY) { //Tworzy model ułożonych puzzli o rozmiarach odpowiadajacych grze do ułożenia
        int number = 1;
        int[][] puzzleModel = new int[dimY][dimX];
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                if (number <= dimX * dimY -1) {
                    puzzleModel[i][j] = number;
                    number++;
                } else {
                    puzzleModel[i][j] = 0;
                }
            }
        }
        return puzzleModel;
    }

    public static Puzzle makeMove(Puzzle originalPuzzle, String move) {
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
       // puzzle = makeCopyOfPuzzle(originalPuzzle);
        return puzzle;
    }

    public static Puzzle makeCopyOfPuzzle(Puzzle originalPuzzle) {
        int[][] puzzle = new int[originalPuzzle.puzzleMatrix.length][originalPuzzle.puzzleMatrix[0].length];
        for (int i = 0; i < puzzle.length; i++) {
        	for (int j = 0; j < puzzle.length; j++) {
            	puzzle[i][j] = originalPuzzle.puzzleMatrix[i][j];
            }
        }
        return new Puzzle(puzzle);
    }

    public void swap(int x1, int y1, int x2, int y2) {
       // ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>(originalPuzzle);
        int tmp = puzzleMatrix[y1][x1];
        puzzleMatrix[y1][x1] = puzzleMatrix[y2][x2];
        puzzleMatrix[y2][x2] = tmp;
    }

    public ArrayList<String> showPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        int[] coords = locateNumber(puzzleMatrix, 0);

        if (coords[0] >= 0 && coords[0] < dimX-1) {
            moves.add("R");
        }
        if (coords[0] > 0 && coords[0] <= dimX-1) {
            moves.add("L");
        }
        if (coords[1] >= 0 && coords[1] < dimY-1) {
            moves.add("D");
        }
        if (coords[1] > 0 && coords[1] <= dimY-1) {
            moves.add("U");
        }
        return moves;
    }

}
