package pl.sise;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;    
    
public class PuzzleTest {

    @Before
    public void setup(){

    }
        
    @Test
    public void testSwap() throws FileNotFoundException {
      //String starting_file = "4.txt";
        // 
        // ArrayList<String> order = new ArrayList<String>(Arrays.asList("R", "L", "D", "U"));
        // puzzle.setOrder(order);
        // puzzle.showFormattedPuzzle();  
        // System.out.println(puzzle.showPossibleMoves());
    }

    @Test
    public void isSolvedTest() throws FileNotFoundException {
//        String starting_file = "4.txt";
//        Puzzle puzzle = new Puzzle(starting_file);
//       // System.out.println(puzzle.isSolved());
    }

    @Test
    public void puzzleAsStringTest() throws FileNotFoundException {
        Puzzle puzzle = new Puzzle();
        String s = puzzle.getAsString();
        ArrayList<String> order = new ArrayList<String>(Arrays.asList("R", "L", "D", "U"));
        Puzzle p2 = new Puzzle(s, order, 4, 4);
        p2.showFormattedPuzzle();
        System.out.println(puzzle.hashCode());
        System.out.println(p2.hashCode());
    }
}
    