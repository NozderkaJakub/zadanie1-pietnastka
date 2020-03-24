package pl.sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
	public void read() throws InterruptedException, IOException {
		for(int i = 0; i < 3; i++) {
		for ( String file : getAllFiles()) {
	        long timeStart;
	        long timeStop;
	        
//			System.out.println(file);
			Puzzle puzzle = new Puzzle(readData(file), new ArrayList<String>(Arrays.asList()));
//	        AStar astar = new AStar(puzzle);
			
			
			
			AStar bfs = new AStar(puzzle);
//	        System.out.println("Przed: ");
//	        puzzle.showFormattedPuzzle();
	        timeStart = System.nanoTime();
	        bfs.solve();
	        timeStop = System.nanoTime();
	        System.out.println(((timeStop - timeStart) / 1000) / 1000.0);
		}
		}
	}
	
    public int[][] readData(String starting_file) throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File("./15s/" + starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        int dimX = Integer.parseInt(dims[0]);
        int dimY = Integer.parseInt(dims[1]);
        int [][] puzzleMatrix = new int[dimY][dimX];
        for (int i = 0; i < dimY; i++){
            String[] line = fileRead.nextLine().split(" ");
            for (int j = 0; j < dimX; j++) {
                puzzleMatrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        fileRead.close();
        return puzzleMatrix;
    }
    
    public ArrayList<String> getAllFiles() {
    	File folder = new File("./15s");
    	File[] listOfFiles = folder.listFiles();
    	ArrayList<String> files = new ArrayList<String>();

    	for (int i = 0; i < listOfFiles.length; i++) {
    	  if (listOfFiles[i].isFile()) {
    	    files.add(listOfFiles[i].getName());
    	  } else if (listOfFiles[i].isDirectory()) {
    	    System.out.println("Directory " + listOfFiles[i].getName());
    	  }
    	}
    	return files;
    }

}
