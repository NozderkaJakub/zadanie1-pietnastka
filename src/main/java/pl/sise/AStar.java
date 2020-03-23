package pl.sise;

import java.util.ArrayList;

import pl.sise.metrics.HammingMetric;
import pl.sise.metrics.ManhattanMetric;

public class AStar {
	ArrayList<Puzzle> puzzles;
	
	public AStar(Puzzle puzzles) {
		this.puzzles = new ArrayList<Puzzle>();
		this.puzzles.add(puzzles);
	}
	
	public void solve() {
		boolean isSolved = false;
		String previousMove = "";
		while (!isSolved) {
			int distance = 10000;
			String moveToMake = "";
			for (String move : puzzles.get(puzzles.size() - 1).showPossibleMoves()) {
				Puzzle newPuzzle = new Puzzle(puzzles.get(puzzles.size() - 1));
				newPuzzle.makeMove(move);
				int metricDistance = HammingMetric.calculate(newPuzzle);
				if (metricDistance < distance && move != previousMove) {
					distance = metricDistance;
					moveToMake = move;
					previousMove = reverseMove(move);
				}
			}
			Puzzle newPuzzle = new Puzzle(puzzles.get(puzzles.size() - 1));
			newPuzzle.makeMove(moveToMake);
			newPuzzle.showFormattedPuzzle();
			puzzles.add(newPuzzle);
			if (newPuzzle.isSolved()) {
				isSolved = true;
				System.out.println("SOLVED");
			}
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
