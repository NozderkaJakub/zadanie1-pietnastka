package pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String starting_file = "4.txt";
    static String ending_file = "rozwiazanie.txt";
    static String additional_file = "informacje_dodatkowe.txt";
    static ArrayList<ArrayList<Integer>> puzzle, puzzleModel;

    public static void main(String[] args) throws FileNotFoundException {
        puzzle = new ArrayList<ArrayList<Integer>>();
        puzzleModel = new ArrayList<ArrayList<Integer>>();
        readData();
    }

    private static void makePuzzleModel() { //Tworzy model ułożonych puzzli o rozmiarach odpowiadajacych grze do ułożenia
        int number = 1;
        for (int i = 0; i < dimY; i++) {
            puzzleModel.add(new ArrayList<Integer>());
            for (int j = 0; j < dimX; j++) {
                if (number <= dimX * dimY -1) {
                    puzzleModel.get(i).add(number);
                    number++;
                } else {
                    puzzleModel.get(i).add(0);
                }
            }
        }
    }

    public static void readData() throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        dimX = Integer.parseInt(dims[0]);
        dimY = Integer.parseInt(dims[1]);
        makePuzzleModel();
        for (int i = 0; i < dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            puzzle.add(new ArrayList<Integer>());
            for (int j = 0; j < dimX; j++) {
                puzzle.get(i).add(Integer.parseInt(line[j]));
            }
        }
        System.out.println(puzzle);
        System.out.println(calculateFullCostOfPuzzle());
    }

    private static int calculateCost(int x, int y, int number) {
        int i = 0;
        int j;
        for (ArrayList<Integer> row : puzzleModel) {
            if (row.contains(number)) {
                i = puzzleModel.indexOf(row);
            }
        }
        j = puzzleModel.get(i).indexOf(number);
        return Math.abs(i - y) + Math.abs(j - x);
    }

    public static int calculateFullCostOfPuzzle() {
        int fullCost = 0;
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                if (puzzle.get(i).get(j) != 0) {
                    fullCost += calculateCost(j, i, puzzle.get(i).get(j));
                }
            }
        }
        return fullCost;
    }

}
