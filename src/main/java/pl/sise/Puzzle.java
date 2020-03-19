package main.java.pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Puzzle {
    int dimX;
    int dimY;
    ArrayList<ArrayList<Integer>> puzzle, puzzleModel;
    ArrayList<String> order = new ArrayList<>(Arrays.asList("L", "R", "U", "D"));

    public Puzzle(String starting_file) throws FileNotFoundException {
        this.readData(starting_file);
    }

    public void readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        this.dimX = Integer.parseInt(dims[0]);
        this.dimY = Integer.parseInt(dims[1]);
        makePuzzleModel();
        this.puzzle = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            this.puzzle.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++) {
                this.puzzle.get(i).add(Integer.parseInt(line[j]));
            }
        }
        this.showFormattedPuzzle(this.puzzle);
        System.out.println(this.showPossibleMoves(this.puzzle).toString());
//        System.out.println("original");
//        this.showFormattedPuzzle(this.puzzle);
    }

    private int calculateCost(int x, int y, int number) {
        int i = 0;
        int j;
        for (ArrayList<Integer> row : this.puzzleModel) {
            if (row.contains(number)) {
                i = this.puzzleModel.indexOf(row);
            }
        }
        j = this.puzzleModel.get(i).indexOf(number);
        return Math.abs(i - y) + Math.abs(j - x);
    }

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

    public void showFormattedPuzzle(ArrayList<ArrayList<Integer>> puzzle) {
        for (ArrayList<Integer> row : puzzle) {
            System.out.println(row);
        }
        System.out.println('\n');
    }

    public int calculateFullCostOfPuzzle(ArrayList<ArrayList<Integer>> puzzle) {
        int fullCost = 0;
        for (int i = 0; i < this.dimY; i++) {
            for (int j = 0; j < this.dimX; j++) {
                if (puzzle.get(i).get(j) != 0) {
                    fullCost += calculateCost(j, i, puzzle.get(i).get(j));
                }
            }
        }
        return fullCost;
    }

    private void makePuzzleModel() { //Tworzy model ułożonych puzzli o rozmiarach odpowiadajacych grze do ułożenia
        int number = 1;
        this.puzzleModel = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++) {
            this.puzzleModel.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++) {
                if (number <= this.dimX * this.dimY -1) {
                    this.puzzleModel.get(i).add(number);
                    number++;
                } else {
                    this.puzzleModel.get(i).add(0);
                }
            }
        }
    }

    public boolean isSolved(ArrayList<ArrayList<Integer>> puzzle) {
        return puzzle.equals(this.puzzleModel);
    }

//    public void makeMove(ArrayList<ArrayList<Integer>> originalPuzzle) {
//        ArrayList<ArrayList<Integer>> puzzle = makeCopyOfPuzzle(originalPuzzle);
//        ArrayList<Integer> coords = this.locateNumber(puzzle, 0);
//        for (String move : this.showPossibleMoves(puzzle)) {
//            if (move == "R") {
//                this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0) + 1, coords.get(1));
//            } else if (move == "L") {
//                this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0) - 1, coords.get(1));
//            } else if (move == "U") {
//                this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0), coords.get(1) - 1);
//            } else if (move == "D") {
//                this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0), coords.get(1) + 1);
//            }
//            System.out.println("original");
//            this.showFormattedPuzzle(this.puzzle);
//            System.out.println("not original");
//            this.showFormattedPuzzle(puzzle);
//            puzzle = makeCopyOfPuzzle(originalPuzzle);
//        }
//    }

    public ArrayList<ArrayList<Integer>> makeMove(ArrayList<ArrayList<Integer>> originalPuzzle, String move) {
        ArrayList<ArrayList<Integer>> puzzle = makeCopyOfPuzzle(originalPuzzle);
        ArrayList<Integer> coords = this.locateNumber(puzzle, 0);
        if (move == "R") {
            this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0) + 1, coords.get(1));
        } else if (move == "L") {
            this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0) - 1, coords.get(1));
        } else if (move == "U") {
            this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0), coords.get(1) - 1);
        } else if (move == "D") {
            this.swap(puzzle, coords.get(0), coords.get(1), coords.get(0), coords.get(1) + 1);
        }
        puzzle = makeCopyOfPuzzle(originalPuzzle);
        return puzzle;
    }

    public ArrayList<ArrayList<Integer>> makeCopyOfPuzzle(ArrayList<ArrayList<Integer>> originalPuzzle) {
        ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>();
        for (ArrayList<Integer> row : originalPuzzle) {
            puzzle.add((ArrayList<Integer>) row.clone());
        }
        return puzzle;
    }

    public void swap(ArrayList<ArrayList<Integer>> originalPuzzle, int x1, int y1, int x2, int y2) {
        ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>(originalPuzzle);
        int tmp = puzzle.get(y1).get(x1);
        puzzle.get(y1).set(x1, puzzle.get(y2).get(x2));
        puzzle.get(y2).set(x2, tmp);
    }

    public ArrayList<String> showPossibleMoves(ArrayList<ArrayList<Integer>> puzzle) {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<Integer> coords = locateNumber(puzzle, 0);

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
