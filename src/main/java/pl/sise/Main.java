package pl.sise;

import java.io.FileNotFoundException;

public class Main {
    static String starting_file = "3.txt";
    static String ending_file = "rozwiazanie.txt";
    static String additional_file = "informacje_dodatkowe.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Puzzle puzzle = new Puzzle(starting_file);
        BFS bfs = new BFS(puzzle);
        bfs.solvePuzzle();
    }
}
