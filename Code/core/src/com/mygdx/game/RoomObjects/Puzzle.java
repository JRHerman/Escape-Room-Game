///////////////////////////////////////////////////////////////////
//
// Escape Room Game
// CS 440 - Group 4 Coding Project
// Luqmaan Baiyat, Patrick Gundry, Joshua Herman, Hamza Shahid
//
//////////////////////////////////////////////////////////////////
package com.mygdx.game.RoomObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * A puzzle has a name, secret number, numberList, range, alphabets list etc.
 * Puzzles can generate numbers, generate secret numbers, convert to binary, and generate
 * binary words
 */
public class Puzzle
{
    private String puzzleName;
    private String secretNumber;
    private List<Character> setNameList;
    private List<Character> alphabetsList;
    private String WORD;
    private List<Character> hiddenAlphabetsList;
    private ArrayList<Integer> numbersList;
    private final int RANGE_OF_NUMBERS = 10;

    Puzzle () {}

    Puzzle (String name)
    {
        this.puzzleName = name;
    }

    //z
    // get the name of the puzzle
    //
    public String getPuzzleName()
    {
        return this.puzzleName;
    }

    //
    // generate a list of numbers from 0-9
    //
    public ArrayList<Integer> generateNumber()
    {
        numbersList = new ArrayList<Integer>();

        for (int i = 0; i < RANGE_OF_NUMBERS; i++)
            numbersList.add(i);
        return numbersList;
    }

    //
    // generate list of random numbers based on the length of
    // the parameter. range can be 1-10
    //
    public String generateSecretNumber(int numberLength)
    {
        Collections.shuffle(numbersList);
        secretNumber = "";

        for (int i = 0; i < numberLength; i++)
            secretNumber += numbersList.get(i).toString();
        return secretNumber;
    }

    //
    // ask user to guess the secret number
    //
    public void guessSecretNumber()
    {
        String playerGuess = "";
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            playerGuess = scanner.nextLine();
            if (!playerGuess.equalsIgnoreCase(secretNumber))
                System.out.println("INCORRECT Guess! Try Again!");
            else
            {
                System.out.println("CONGRATS! You Guessed Correctly!");
                break;
            }
        }
    }

    //
    // convert a word into binary digits
    //
    public StringBuilder convertToBinary(String convert)
    {
        this.WORD = convert;
        byte[] bytes = convert.getBytes();
        StringBuilder inBinary  = new StringBuilder();

        for (byte b : bytes)
        {
            int value = b;
            for (int i = 0; i < 8; i++)
            {
                inBinary.append((value & 128) == 0 ? 0 : 1);
                value <<= 1;
            }
            inBinary.append(' ');
        }
        return inBinary;
    }

    //
    // ask the player to guess the word that was converted into
    // binary digits until they get it right or time runs out
    //
    public void guessBinaryWord()
    {
        String word = "";
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            word = scanner.nextLine();

            if (!word.equalsIgnoreCase(WORD))
                System.out.println("Incorrect");
            else
            {
                System.out.println("Correct");
                break;
            }

        }
    }
//
//    public List<Character> generateAlphabetsRandomly()
//    {
//        alphabetsList = new ArrayList<Character>();
//        hiddenAlphabetsList = new ArrayList<Character>();
//
//        for(int i = 0; i < 26; i++)
//        {
//            alphabetsList.add((char) (97 + i));
//            hiddenAlphabetsList.add('*');
//        }
//        Collections.shuffle((alphabetsList));
//
//        return alphabetsList;
//    }
//
//    //
//    //
//    //
//    public List<Character> setWord(String word)
//    {
//        setNameList = new ArrayList<Character>();
//
//        for (char c : word.toCharArray())
//            setNameList.add(c);
//
//        return setNameList;
//    }
//
//
//
//    public void userInput()
//    {
//        int playerInput=0;
//
//        List<Character> c = new ArrayList<Character>();
//        List<Integer> inte = new ArrayList<Integer>();
//        Scanner scanner = new Scanner(System.in);
//
//        while (true)
//        {
//            while(scanner.hasNextInt())
//                inte.add(scanner.nextInt());
//
////            System.out.println(inte);
//            for (int i = 0; i < 26; i++)
//            {
//               alphabetsList.get(inte.get(i));
//                System.out.println(alphabetsList.get(inte.get(i)));
//
//            }
//            System.out.println(alphabetsList);
//            System.out.println(inte);
//            break;
//
//
//            for (int C : playerInput.)
//            {
//                inte.add(C);
//            }
//            for (char C : playerInput.toCharArray())
//                c.add(C);
//
//            for (int i = 0; i < 26; i++)
//            {
//                if (!(c.get(i).equals(setNameList.get(i))))
//                    System.out.println("Incorrect");
//                else
//                {
//                    System.out.println("Correct");
//                    break;
//                }
//            }
//        }
//    }
}
