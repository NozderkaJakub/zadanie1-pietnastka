package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String starting_file = "ukladanka.txt";
    static String ending_file = "rozwiazanie.txt";
    static String additional_file = "informacje_dodatkowe.txt";
    static int dimX, dimY;
    static ArrayList<ArrayList<Integer>> puzzle;

    public static void main(String[] args) throws FileNotFoundException {
        puzzle = new ArrayList<ArrayList<Integer>>();
        readData();
    }

    public static void readData() throws FileNotFoundException {
        Scanner fileRead = new Scanner(new File(starting_file));
        String[] dims = fileRead.nextLine().split(" ");
        dimX = Integer.parseInt(dims[0]);
        dimY = Integer.parseInt(dims[1]);
        for (int i = 0; i < dimX; i++){
            String[] line = fileRead.nextLine().split(" ");
            puzzle.add(new ArrayList<Integer>());
            for (int j = 0; j < dimY; j++) {
                puzzle.get(i).add(Integer.parseInt(line[j]));
            }
        }
        System.out.println(puzzle);
    }

}
