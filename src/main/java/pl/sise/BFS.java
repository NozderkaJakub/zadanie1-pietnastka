package pl.sise;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BFS {
    Node<String> rootNode;
    Puzzle startingPuzzle;

    public BFS(Puzzle startingPuzzle) {
        this.startingPuzzle = startingPuzzle;
        rootNode = new Node<String>("root", null);
        ArrayList<String> firstMoves = startingPuzzle.showPossibleMoves();
        for (String move : firstMoves) {
            rootNode.addChild(move);
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

    public void solvePuzzle() {
        startingPuzzle.showFormattedPuzzle();
        ArrayList<String> correctMoves = new ArrayList<String>();
        Queue<Puzzle> puzzles = new LinkedList<Puzzle>();
        Queue<String> parentMoves = new LinkedList<String>();
        boolean isSolved = false;
        ModelPuzzle model = new ModelPuzzle(startingPuzzle.getDimX(), startingPuzzle.getDimY());
        for (Node<String> node : rootNode.children) {
            System.out.println(node.label);
            Puzzle afterMove = Puzzle.makeMove(startingPuzzle, node.getLabel());
            parentMoves.add(node.label);
            puzzles.add(afterMove);
            isSolved = model.isSolved(afterMove);
            if(isSolved) {
                correctMoves.add(node.label);
                System.out.println("SOLVED");
            }
        }
        while(!puzzles.isEmpty() && !isSolved) {
            Puzzle puzzle = puzzles.poll();
            String parentMove = parentMoves.poll();
            ArrayList<String> moves = puzzle.showPossibleMoves();
            for(String move : moves) {
                if(move == reverseMove(parentMove)) {
                    continue;
                }
                Puzzle newPuzzle = Puzzle.makeMove(puzzle, move);
                isSolved = model.isSolved(newPuzzle);
                if(isSolved) {
                    System.out.println("SOLVED!!!!");
                    newPuzzle.showFormattedPuzzle();
                    break;
                }
                puzzles.add(newPuzzle);
                parentMoves.add(move);
            }
            continue;
        }
    }
}
