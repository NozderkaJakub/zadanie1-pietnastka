package pl.sise;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static String starting_file = "3.txt";
    static String ending_file = "rozwiazanie.txt";
    static String additional_file = "informacje_dodatkowe.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        Puzzle puzzle = new Puzzle();
        puzzle.setOrder(new ArrayList<String>(Arrays.asList("L", "D", "R", "U")));
        AStar astar = new AStar(puzzle);
        System.out.println("Przed: ");
        puzzle.showFormattedPuzzle();
        astar.solve();
//        DFS dfs = new DFS(puzzle, new ArrayList<String>(Arrays.asList("L", "D", "R", "U")));
//        SolvingInfo info = dfs.solvePuzzle();
//        System.out.println("Po: ");
//        System.out.println(info.getUsedAlgorithm());
    }
}
