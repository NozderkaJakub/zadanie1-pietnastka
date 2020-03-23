package pl.sise;

import java.util.ArrayList;
import java.util.List;

class SolvingInfo {
    private String usedAlgorithm;
    private List<String> correctMoves;
    private int numberOfVisitedStates;
    private int numberOfProcessedStates;
    private int level;
    private double timeOfWork;

    SolvingInfo() {
        correctMoves = new ArrayList<String>();
    }

    public void addMove(String move) {
        correctMoves.add(move);
    }


    public String getUsedAlgorithm() {
        return this.usedAlgorithm;
    }

    public void setUsedAlgorithm(String usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    public List<String> getCorrectMoves() {
        return this.correctMoves;
    }

    public void setCorrectMoves(List<String> correctMoves) {
        this.correctMoves = correctMoves;
    }

    public int getNumberOfVisitedStates() {
        return this.numberOfVisitedStates;
    }

    public void setNumberOfVisitedStates(int numberOfVisitedStates) {
        this.numberOfVisitedStates = numberOfVisitedStates;
    }

    public int getNumberOfProcessedStates() {
        return this.numberOfProcessedStates;
    }

    public void setNumberOfProcessedStates(int numberOfProcessedStates) {
        this.numberOfProcessedStates = numberOfProcessedStates;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getTimeOfWork() {
        return this.timeOfWork;
    }

    public void setTimeOfWork(double timeOfWork) {
        this.timeOfWork = timeOfWork;
    }

}