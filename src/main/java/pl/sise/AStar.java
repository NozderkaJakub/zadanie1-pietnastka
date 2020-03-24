package pl.sise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import pl.sise.metrics.HammingMetric;
import pl.sise.metrics.ManhattanMetric;

public class AStar {
	ArrayList<String> puzzles;
	static ArrayList<String> order = new ArrayList<String>(Arrays.asList("L", "D", "R", "U"));
	final int dimX;
	final int dimY;
	Set<Integer> visited;
	
	public AStar(Puzzle puzzles) {
		this.puzzles = new ArrayList<String>();
		this.puzzles.add(puzzles.getAsString());
		this.dimX = puzzles.getDimX();
		this.dimY = puzzles.getDimY();
		visited = new HashSet<Integer>();
		visited.add(puzzles.hashCode());
	}
	
	public void solve() throws InterruptedException {
		boolean isSolved = false;
		long i = 0;
		String previousMove = "";
		Queue<String> previousMoves = new LinkedList<String>();
		while (!isSolved) {
			int distance = 10000;
			String moveToMake = "";
			String queueMove = "";
			if (i > 3) {
				queueMove = previousMoves.poll();
			}
			String s = puzzles.get(puzzles.size() - 1);
			Puzzle puzzle = new Puzzle(s, order, dimX, dimY);
			for (String move : puzzle.showPossibleMoves()) {
				Puzzle newPuzzle = new Puzzle(puzzle);
				newPuzzle.makeMove(move);
				int metricDistance = HammingMetric.calculate(newPuzzle);
				if(visited.contains(newPuzzle.hashCode())) continue;
				if (metricDistance < distance) {
					distance = metricDistance;
					moveToMake = move;
					previousMove = reverseMove(move);
					//System.out.println("chuj");
				}
			}
			previousMoves.add(moveToMake);
			//if(reverseMove(moveToMake) == previousMove) continue;
			String s2 = puzzles.get(puzzles.size() - 1);
			Puzzle newPuzzle = new Puzzle(s2, order, dimX, dimY);
			newPuzzle.makeMove(moveToMake);
			//newPuzzle.showFormattedPuzzle();
			//TimeUnit.SECONDS.sleep(1);
			puzzles.add(newPuzzle.getAsString());
			visited.add(newPuzzle.hashCode());
			if (newPuzzle.isSolved()) {
				isSolved = true;
				System.out.println("SOLVED");
			}
			i += 1;
		}
	}
	
	public String reverseMove(String moveToReverse) {
        if(moveToReverse == "R") {
            return "L";
        }
        if(moveToReverse == "L") {
            return "R";
        }
        if(moveToReverse == "U") {
            return "D";
        }
        if(moveToReverse == "D") {
            return "U";
        }
        return "";
    }
}
