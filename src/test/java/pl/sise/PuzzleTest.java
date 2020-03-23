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
     //  String starting_file = "4.txt";
        Puzzle puzzle = new Puzzle();
        ArrayList<String> order = new ArrayList<String>(Arrays.asList("L", "D", "R", "U"));
        puzzle.setOrder(order);
        puzzle.showFormattedPuzzle();
        puzzle.makeMove("D");
        puzzle.showFormattedPuzzle();
    }

    @Test
    public void isSolvedTest() throws FileNotFoundException {
//        String starting_file = "4.txt";
//        Puzzle puzzle = new Puzzle(starting_file);
//       // System.out.println(puzzle.isSolved());
    }
}
    