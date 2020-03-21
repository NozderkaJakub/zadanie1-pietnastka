package pl.sise;

class ModelPuzzle extends Puzzle {
    ModelPuzzle(int dimX, int dimY) {
        super(Puzzle.makePuzzleModel(dimX, dimY));
    }

    public boolean isSolved(Puzzle puzzle) {
        return this.getMatrix().equals(puzzle.getMatrix());
    }
}