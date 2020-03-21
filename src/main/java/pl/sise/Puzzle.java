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
    ArrayList<ArrayList<Integer>> puzzleMatrix;
    ArrayList<String> order = new ArrayList<>(Arrays.asList("L", "R", "U", "D"));

    public Puzzle(String starting_file) throws FileNotFoundException {
        this.readData(starting_file);
    }

    public Puzzle(ArrayList<ArrayList<Integer>> puzzleMatrix) {
        this.puzzleMatrix = puzzleMatrix;
        dimX = puzzleMatrix.get(0).size();
        dimY = puzzleMatrix.size();
    }

    public ArrayList<ArrayList<Integer>> getMatrix() {
        return puzzleMatrix;
    }

    public void readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        this.dimX = Integer.parseInt(dims[0]);
        this.dimY = Integer.parseInt(dims[1]);
        this.puzzleMatrix = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            this.puzzleMatrix.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++) {
                this.puzzleMatrix.get(i).add(Integer.parseInt(line[j]));
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

    public ArrayList<Integer> locateNumber(ArrayList<ArrayList<Integer>> puzzle, int number) {
        ArrayList<Integer> localization = new ArrayList<>();
        int y = 0;

        for (ArrayList<Integer> row : puzzle) {
            if (row.contains(0)) {
                y = puzzle.indexOf(row);
            }
        }
        localization.add(puzzle.get(y).indexOf(0));
        localization.add(y);
        return localization;
    }

    public void showFormattedPuzzle() {
        for (ArrayList<Integer> row : puzzleMatrix) {
            System.out.println(row);
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

    protected static  ArrayList<ArrayList<Integer>> makePuzzleModel(int dimX, int dimY) { //Tworzy model ułożonych puzzli o rozmiarach odpowiadajacych grze do ułożenia
        int number = 1;
        ArrayList<ArrayList<Integer>> puzzleModel = new  ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < dimY; i++) {
            puzzleModel.add(new ArrayList<>());
            for (int j = 0; j < dimX; j++) {
                if (number <= dimX * dimY -1) {
                    puzzleModel.get(i).add(number);
                    number++;
                } else {
                    puzzleModel.get(i).add(0);
                }
            }
        }
        return puzzleModel;
    }

    public static Puzzle makeMove(Puzzle originalPuzzle, String move) {
        Puzzle puzzle = makeCopyOfPuzzle(originalPuzzle);
        ArrayList<Integer> coords = puzzle.locateNumber(puzzle.getMatrix(), 0);
        //System.out.println(coords.toString());
        if (move == "R") {
            puzzle.swap(coords.get(0), coords.get(1), coords.get(0) + 1, coords.get(1));
        } else if (move == "L") {
            puzzle.swap(coords.get(0), coords.get(1), coords.get(0) - 1, coords.get(1));
        } else if (move == "U") {
            puzzle.swap(coords.get(0), coords.get(1), coords.get(0), coords.get(1) - 1);
        } else if (move == "D") {
            puzzle.swap(coords.get(0), coords.get(1), coords.get(0), coords.get(1) + 1);
        }
       // puzzle = makeCopyOfPuzzle(originalPuzzle);
        return puzzle;
    }

    public static Puzzle makeCopyOfPuzzle(Puzzle originalPuzzle) {
        ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>();
        for (ArrayList<Integer> row : originalPuzzle.puzzleMatrix) {
            puzzle.add((ArrayList<Integer>) row.clone());
        }
        return new Puzzle(puzzle);
    }

    public void swap(int x1, int y1, int x2, int y2) {
       // ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>(originalPuzzle);
        int tmp = puzzleMatrix.get(y1).get(x1);
        puzzleMatrix.get(y1).set(x1, puzzleMatrix.get(y2).get(x2));
        puzzleMatrix.get(y2).set(x2, tmp);
    }

    public ArrayList<String> showPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<Integer> coords = locateNumber(puzzleMatrix, 0);

        if (coords.get(0) >= 0 && coords.get(0) < dimX-1) {
            moves.add("R");
        }
        if (coords.get(0) > 0 && coords.get(0) <= dimX-1) {
            moves.add("L");
        }
        if (coords.get(1) >= 0 && coords.get(1) < dimY-1) {
            moves.add("D");
        }
        if (coords.get(1) > 0 && coords.get(1) <= dimY-1) {
            moves.add("U");
        }
        return moves;
    }

}
