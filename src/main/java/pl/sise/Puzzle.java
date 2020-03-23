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
    ArrayList<ArrayList<Integer>> puzzleMatrix, modelMatrix;
    ArrayList<String> order;
    HashMap<Character, Integer> xCoords;
    String path = "";

    public Puzzle() throws FileNotFoundException {
        this.readData(starting_file);
        configure();
    }

    public Puzzle(ArrayList<ArrayList<Integer>> puzzleMatrix, ArrayList<String> order) {
        this.puzzleMatrix = puzzleMatrix;
        this.order = order;
        configure();
    }

    public Puzzle(Puzzle puzzle) {
        this.puzzleMatrix = makeCopyOfPuzzle(puzzle).getMatrix();
        this.order = puzzle.order;
        configure();
    }

    public Puzzle(ArrayList<ArrayList<Integer>> puzzleMatrix, Puzzle puzzle) {
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

    public ArrayList<ArrayList<Integer>> getMatrix() {
        return puzzleMatrix;
    }

    public ArrayList<ArrayList<Integer>> getModelMatrix() {
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
        this.dimX = puzzleMatrix.get(0).size();
        this.dimY = puzzleMatrix.size();
        this.modelMatrix = makePuzzleModel();
        xCoords = new HashMap<Character, Integer>();
        xCoords.put(new Character('x'), locateNumber(0).get(0));
        xCoords.put(new Character('y'), locateNumber(0).get(1));
    }
    
    public boolean isSolved() {
    	return getMatrix().equals(getModelMatrix());
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

    public ArrayList<Integer> locateNumber(int number) {
        ArrayList<Integer> localization = new ArrayList<>();
        int y = 0;

        for (ArrayList<Integer> row : getMatrix()) {
            if (row.contains(0)) {
                y = getMatrix().indexOf(row);
            }
        }
        localization.add(getMatrix().get(y).indexOf(0));
        localization.add(y);
        return localization;
    }

    @SuppressWarnings("unchecked")
	public Puzzle makeCopyOfPuzzle(Puzzle originalPuzzle) {
        ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>();
        for (ArrayList<Integer> row : originalPuzzle.puzzleMatrix) {
            puzzle.add((ArrayList<Integer>) row.clone());
        }
        return new Puzzle(puzzle, this);
    }

    public Puzzle makeMove(Puzzle originalPuzzle, String move) {
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
        puzzle.path += move;
        return puzzle;
    }

    public void makeMove(String move) {
        if (move == "R") {
            swap(xCoords.get('x'), xCoords.get('y'), xCoords.get('x') + 1, xCoords.get('y'));
        } else if (move == "L") {
        	swap(xCoords.get('x'), xCoords.get('y'), xCoords.get('x') - 1, xCoords.get('y'));
        } else if (move == "U") {
        	swap(xCoords.get('x'), xCoords.get('y'), xCoords.get('x'), xCoords.get('y') - 1);
        } else if (move == "D") {
        	swap(xCoords.get('x'), xCoords.get('y'), xCoords.get('x'), xCoords.get('y') + 1);
        }
        this.path += move;
    }

    protected ArrayList<ArrayList<Integer>> makePuzzleModel() {
        int number = 1;
        ArrayList<ArrayList<Integer>> puzzleModel = new  ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < getDimY(); i++) {
            puzzleModel.add(new ArrayList<>());
            for (int j = 0; j < getDimX(); j++) {
                if (number <= getDimX() * getDimY() -1) {
                    puzzleModel.get(i).add(number);
                    number++;
                } else {
                    puzzleModel.get(i).add(0);
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
        this.puzzleMatrix = new ArrayList<>();
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            this.puzzleMatrix.add(new ArrayList<>());
            for (int j = 0; j < this.dimX; j++) {
                this.puzzleMatrix.get(i).add(Integer.parseInt(line[j]));
            }
        }
        fileRead.close();
    }

    public void showFormattedPuzzle() {
        for (ArrayList<Integer> row : puzzleMatrix) {
            System.out.println(row);
        }
        System.out.println('\n');
    }

    public ArrayList<String> showPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<Integer> coords = locateNumber(puzzleMatrix, 0);
        for (String move : this.order) {
        	if (move == "R" && coords.get(0) < dimX-1) {
	            moves.add("R");
	        }
	        if (coords.get(0) > 0 && move == "L") {
	            moves.add("L");
	        }
	        if (move == "D" && coords.get(1) < dimY-1) {
	            moves.add("D");
	        }
	        if (coords.get(1) > 0 && move == "U") {
	            moves.add("U");
	        }
        }
        return moves;
    }

    public void swap(int x1, int y1, int x2, int y2) {
       // ArrayList<ArrayList<Integer>> puzzle = new ArrayList<>(originalPuzzle);
        int tmp = puzzleMatrix.get(y1).get(x1);
        puzzleMatrix.get(y1).set(x1, puzzleMatrix.get(y2).get(x2));
        puzzleMatrix.get(y2).set(x2, tmp);
    }

}
