package com.mygdx.game.RoomObjects;

/**
 * Combination Lock is a type of lock that has a combination from 0-9
 * for any amount of numbers. This will be used as a puzzle.
 */
public class CombinationLock extends Lock {

    //A combination is defined by a letter and digit
    private enum combinationType {
        LETTER, DIGIT
    }

    private int combinationLength;
    private String combinationSolution;

    /**
     * Combination Lock constructor takes in a string solution and sets the solution
     * to the passed in solution and gets the length of the solution
     * @param solution:String - the string solution of the combination
     */
    public CombinationLock(String solution) {
        super();
        combinationSolution = solution;
        combinationLength = combinationSolution.length();
    }

    /**
     *  Compare solution compares the passed in parameter solution the actual solution
     *  which will be the users guess to the solution of the combination lock.
     *  combination solution
     * @param givenSolution:String - Users guess to the combination lock answer
     * @return boolean - Returns True if given solution is equal to actual solution
     */
    public boolean compareSolution(String givenSolution) {
        return (combinationSolution.equals(givenSolution));
    }

}
