package pl.sise;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static String starting_file = "4.txt";
    static String ending_file = "rozwiazanie.txt";
    static String additional_file = "informacje_dodatkowe.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Puzzle puzzle = new Puzzle();
        puzzle.setOrder(new ArrayList<String>(Arrays.asList("L", "D", "R", "U")));
        BFS bfs = new BFS(puzzle, new ArrayList<String>(Arrays.asList("L", "D", "R", "U")));
        System.out.println("Przed: ");
        puzzle.showFormattedPuzzle();
        Puzzle solvedPuzzle = bfs.solvePuzzle();
        System.out.println("Po: ");
        solvedPuzzle.showFormattedPuzzle();
    }
}
