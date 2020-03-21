package pl.sise;
import org.junit.Test;

import java.io.FileNotFoundException;

import org.junit.Before;    
    
public class PuzzleTest {

    @Before
    public void setup(){

    }
        
    @Test
    public void testSwap() throws FileNotFoundException {
        String starting_file = "4.txt";
        Puzzle puzzle = new Puzzle(starting_file);
        puzzle.showFormattedPuzzle();
        Puzzle newPuzzle = Puzzle.makeMove(puzzle, "U");
        newPuzzle.showFormattedPuzzle();
    }

    @Test
    public void isSolvedTest() throws FileNotFoundException {
        String starting_file = "4.txt";
        Puzzle puzzle = new Puzzle(starting_file);
       // System.out.println(puzzle.isSolved());
    }
}
    