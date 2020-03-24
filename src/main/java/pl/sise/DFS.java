package pl.sise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {
    Stack<String> puzzles;
    ArrayList<String> order;
    int depthLimit = 50;
    Set<Integer> visited;
    int numberOfRecursion = 0;
    int currentLevel = 0;
    final int dimX;
    final int dimY;
    SolvingInfo info;

    public DFS(Puzzle startingPuzzle, ArrayList<String> order) {
        this.dimX = startingPuzzle.getDimX();
        this.dimY = startingPuzzle.getDimY();
    	puzzles = new Stack<String>();
        this.order = order;
        startingPuzzle.setOrder(order);
        puzzles.push(startingPuzzle.getAsString());
        visited = new HashSet<Integer>();
        visited.add(startingPuzzle.hashCode());
        info = new SolvingInfo();
    }

    public SolvingInfo solvePuzzle() throws IOException, InterruptedException {
        //Scanner input = new Scanner(System.in);
        while (!puzzles.isEmpty()) {
            String s = puzzles.pop();
            Puzzle puzzle = new Puzzle(s, order, dimX, dimY);
           // System.out.println(visited.size());
                for(int i = 0; i < order.size(); i++) {
                    String move2 = order.get(i);
                  //  System.out.println(puzzle.showPossibleMoves());
                   // puzzle.showFormattedPuzzle();
                    if(puzzle.showPossibleMoves().contains(move2)) {
                        Puzzle p = new Puzzle(puzzle);
                        p.makeMove(move2);
                        if(visited.contains(p.hashCode())) {
                            continue;
                        }
                       // p.showFormattedPuzzle();
                       // System.out.println(p.hashCode());
                        puzzles.push(p.getAsString());
                        visited.add(p.hashCode());
                        if (p.isSolved()) {
                            p.showFormattedPuzzle();
                            info.setUsedAlgorithm("SOLVED");
                            System.out.println("SOLVED");
                            return info;
                        }
                        currentLevel++;
                        break;
                    }
                }
         //   System.out.println(currentLevel);
            if(currentLevel > depthLimit) {
                Puzzle last;
                boolean doIterate = true;
                for(int i = 0; i < puzzles.size(); i++) {
                    if(!doIterate) break;
                    String s2 = puzzles.pop();
                    last = new Puzzle(s2, order, dimX, dimY);
                    currentLevel--;
                    for(String move3 : last.showPossibleMoves()) {
                        Puzzle p = new Puzzle(last);
                        p.makeMove(move3);
                        if(!visited.contains(p.hashCode())) {
                            puzzles.push(p.getAsString());
                            numberOfRecursion++;
                            doIterate = false;
                            break;
                          //  System.out.println(numberOfRecursion);
                           // solvePuzzle();
                        }
                    }
                }
            }
    		 		
    //        // visited.add(puzzle);
    //        for(int i = 0; i < depthLimit; i++) {   
    //         Puzzle puzzle = puzzles.pop();
    //         System.out.println("ROOT");
    //         puzzle.showFormattedPuzzle();
    //         System.out.println(puzzle.showPossibleMoves());
    //             for(String move : puzzle.showPossibleMoves()) {
    //            //     puzzle.showFormattedPuzzle();
    //                 System.out.println(move);
    //                 Puzzle p = new Puzzle(puzzle);
    //                 p.makeMove(move);
    //                 p.showFormattedPuzzle();
    //                // TimeUnit.SECONDS.sleep(5);
    //                 if(visited.contains(p)) continue;
    //                 puzzles.push(p);
    //             // p.showFormattedPuzzle();
    //                  visited.add(p);
    //                  if (p.isSolved()) {
    //                     info.setUsedAlgorithm("SOLVED");
    //                     System.out.println("SOLVED");
    //                     return info;
    //                 }
    //             }
    //     }
    //     for(int j = 0; j < puzzles.size(); j++) {
    //         Puzzle p = puzzles.pop();
    //         for(String move : p.showPossibleMoves()) {
    //             Puzzle p2 = new Puzzle(p);
    //             p2.makeMove(move);
    //             if(!visited.contains(p2)) {
    //               //  System.out.println("TRUEEE");
    //                 puzzles.clear();
    //                 puzzles.push(p2);
    //                 numberOfRecursion++;
    //                 System.out.println(numberOfRecursion);
    //                 solvePuzzle();
    //             }
    //         }
    // }
        }
        return info;
    }
}
