package pl.sise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DFS {
    Stack<Puzzle> puzzles;
    ArrayList<String> order;
    int depthLimit = 5000;
    Set<Puzzle> visited;
    int numberOfRecursion = 0;

    public DFS(Puzzle startingPuzzle, ArrayList<String> order) {
    	puzzles = new Stack<Puzzle>();
        this.order = order;
        startingPuzzle.setOrder(order);
        puzzles.push(startingPuzzle);
        visited = new HashSet<Puzzle>();
    }

    public SolvingInfo solvePuzzle() {
        SolvingInfo info = new SolvingInfo();
        while (!puzzles.isEmpty()) {
    		 		
           // visited.add(puzzle);
           for(int i = 0; i < depthLimit; i++) {
            Puzzle puzzle = puzzles.pop();   
                for(String move : puzzle.showPossibleMoves()) {
                    Puzzle p = new Puzzle(puzzle);
                    p.makeMove(move);
                    puzzles.push(p);
                // p.showFormattedPuzzle();
                     visited.add(p);
                     if (p.isSolved()) {
                        info.setUsedAlgorithm("SOLVED");
                        System.out.println("SOLVED");
                        return info;
                    }
                }
        }
        for(int j = 0; j < puzzles.size(); j++) {
            Puzzle p = puzzles.pop();
            for(String move : p.showPossibleMoves()) {
                Puzzle p2 = new Puzzle(p);
                p2.makeMove(move);
                if(!visited.contains(p2)) {
                  //  System.out.println("TRUEEE");
                    puzzles.clear();
                    puzzles.push(p2);
                    numberOfRecursion++;
                    System.out.println(numberOfRecursion);
                    solvePuzzle();
                }
            }
    }
        }
        return info;
    }
}
