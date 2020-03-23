package pl.sise;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
	Queue<Puzzle> puzzles;
    ArrayList<String> order;

    public BFS(Puzzle startingPuzzle, ArrayList<String> order) {
    	puzzles = new LinkedList<>();
    	this.order = order;
    	puzzles.add(startingPuzzle);
    }
    
    public Puzzle solvePuzzle() {
    	int i = 0;
    	while (!puzzles.isEmpty()) {
    		Puzzle puzzle = puzzles.poll();    		
    		
    		if (puzzle.isSolved()) {
    			return puzzle;
    		}
    		for (String c : puzzle.showPossibleMoves()) {
    			Puzzle p = new Puzzle(puzzle);
    			p.makeMove(c);
    			puzzles.add(p);
    		}
    		if (puzzle.path.length() - i >= 1) {
    			i++;
    		}
    	}
    	
    	return null;
    }

}
