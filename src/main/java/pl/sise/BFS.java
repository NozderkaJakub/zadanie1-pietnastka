package pl.sise;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFS {
	Queue<String> puzzles;
	ArrayList<String> order;
	final int dimX;
	final int dimY;
	

    public BFS(Puzzle startingPuzzle, ArrayList<String> order) {
		this.dimX = startingPuzzle.getDimX();
		this.dimY = startingPuzzle.getDimY();
    	puzzles = new LinkedList<>();
		this.order = order;
		startingPuzzle.setOrder(order);
    	puzzles.add(startingPuzzle.getAsString());
    }
    
    public Puzzle solvePuzzle() {
    	int i = 0;
    	while (!puzzles.isEmpty()) {
			String s = puzzles.poll(); 
			Puzzle puzzle = new Puzzle(s, order, dimX, dimY);   		
    		
    		if (puzzle.isSolved()) {
    			return puzzle;
    		}
    		for (String c : puzzle.showPossibleMoves()) {
				Puzzle p = new Puzzle(puzzle);
				p.makeMove(c);
				//p.showFormattedPuzzle();
				puzzles.add(p.getAsString());
				//System.out.println(puzzles.size());
    		}
    		if (puzzle.path.length() - i >= 1) {
    			i++;
    		}
    	}
    	
    	return null;
    }

}
