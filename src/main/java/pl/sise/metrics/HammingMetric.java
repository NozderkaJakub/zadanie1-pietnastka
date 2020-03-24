package pl.sise.metrics;


import pl.sise.Puzzle;

public class HammingMetric implements Metrics {
	public static int calculate (Puzzle puzzle) {
		int sum = 0;
		for (int i = 0; i < puzzle.getDimY(); i++) {
			for (int j = 0; j < puzzle.getDimX(); j++) {
				if (puzzle.getMatrix()[i][j] != puzzle.getModelMatrix()[i][j]) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	public static int calculate (int[][] puzzle, int[][] model) {
		int sum = 0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				if (puzzle[i][j] != model[i][j]) {
					sum++;
				}
			}
		}
		return sum;
	}
}
