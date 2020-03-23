package pl.sise.metrics;

import pl.sise.Puzzle;

public class ManhattanMetric {
	public static int calculate(Puzzle puzzle) {
		int sum = 0;
		for (int i = 0; i < puzzle.getDimY(); i++) {
			for (int j = 0; j < puzzle.getDimX(); j++) {
				int[] modelTile = new int[2];
				int[] tile = new int[2];
				modelTile = Puzzle.locateNumber(puzzle.getModelMatrix(), puzzle.getMatrix()[i][j]);
				tile = puzzle.locateNumber(puzzle.getMatrix()[i][j]);
				for (int k = 0; k < modelTile.length; k++) {
					sum += Math.abs(modelTile[k] - tile[k]);
				}
			}
		}
		return sum;
	}
	
	public static int calculate(int[][] puzzle, int[][] model) {
		int sum = 0;
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				int[] modelTile = new int[2];
				int[] tile = new int[2];
				modelTile = Puzzle.locateNumber(model, puzzle[i][j]);
				tile = Puzzle.locateNumber(puzzle, puzzle[i][j]);
				for (int k = 0; k < modelTile.length; k++) {
					sum += Math.abs(modelTile[k] - tile[k]);
				}
			}
		}
		return sum;
	}
	
}
