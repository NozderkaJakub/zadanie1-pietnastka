package pl.sise;
import org.junit.Test;

import java.io.FileNotFoundException;

import org.junit.Before;    
    
public class ModelPuzzleTest {

    @Before
    public void setup(){

    }
        
    @Test
    public void test() throws FileNotFoundException {
        ModelPuzzle m = new ModelPuzzle(4, 4);
        m.showFormattedPuzzle();
        System.out.println(m.isSolved(m));
        String starting_file = "4.txt";
        Puzzle puzzle = new Puzzle(starting_file);
        System.out.println(m.isSolved(puzzle));
        System.out.println(m.getDimX() + " " + m.getDimY());
    }
}
    