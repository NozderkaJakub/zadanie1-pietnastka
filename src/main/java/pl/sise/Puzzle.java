package main.java.pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {
    int dimX;
    int dimY;
    ArrayList<ArrayList<Integer>> puzzle, puzzleModel;

    public Puzzle(String starting_file) throws FileNotFoundException {
        this.readData(starting_file);
    }

    public void readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        this.dimX = Integer.parseInt(dims[0]);
        this.dimY = Integer.parseInt(dims[1]);
        makePuzzleModel();
        this.puzzle = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < this.dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            this.puzzle.add(new ArrayList<Integer>());
            for (int j = 0; j < this.dimX; j++) {
                this.puzzle.get(i).add(Integer.parseInt(line[j]));
            }
        }
        System.out.println(this.puzzle);
        System.out.println(calculateFullCostOfPuzzle());
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

    public int calculateFullCostOfPuzzle() {
        int fullCost = 0;
        for (int i = 0; i < this.dimY; i++) {
            for (int j = 0; j < this.dimX; j++) {
                if (this.puzzle.get(i).get(j) != 0) {
                    fullCost += calculateCost(j, i, this.puzzle.get(i).get(j));
                }
            }
        }
        return fullCost;
    }

    private void makePuzzleModel() { //Tworzy model ułożonych puzzli o rozmiarach odpowiadajacych grze do ułożenia
        int number = 1;
        this.puzzleModel = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < this.dimY; i++) {
            this.puzzleModel.add(new ArrayList<Integer>());
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
}
