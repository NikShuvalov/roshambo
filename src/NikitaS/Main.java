package NikitaS;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<String> results = new ArrayList<>();
    static String header = "============Welcome to Game==============";//FixMe: Get title
    static String mainPrompt = "Type in one of the following: \n \"Play\" \n \"History\" \n \"Quit\" \n";
    static String[] validEntries = {"rock", "scissors", "paper", "r", "s", "p"}; // 0 beats 1, 1 beats 2, 2 beats 0 Keep that dynamic or everything falls apart.
    static String playerActionMessage = "Enter \"" + validEntries[0] + "\", \"" + validEntries[1] + "\" or \"" + validEntries[2] + "\" or just the first letter:";

    static int playerScore;
    static int cpuScore;

    static String player = "Player";
    static String cpu = "CPU";

    static String bestOfPrompt = "Best of how many rounds do you want to play? (It must be an odd number between 1-21";


    static int round;
    static int bestOf;


//ToDo: Make a basic AI
    /*
    Create array of players past 3 moves.
    -If all moves are the same assume the player is spamming so computer anticipates
     the next move to be the same and plays the counter to it.
     -If all moves are different, assume that the player is cycling through the list, so play the counter to the next anticipated one.
     */
    //ToDo: Audio? Graphics?
    //

    public static void main(String[] args) {
        round = 0;//ToDo: change this value to get the value from load if I'm going to do that.
        cpuScore = 0;
        playerScore = 0;

        displayMainMenu();


    }

    public static void displayMainMenu() {
        playerScore =0;
        cpuScore=0;
        System.out.println(header);
        userMainEntry(removeCaseSensitive(getInput(mainPrompt)));
    }

    public static void userMainEntry(String userInput) {
        if (userInput.equals("play") || userInput.equals("p")) {
            askBestOf();
            playRound();
        } else if (userInput.equals("history") || userInput.equals("h")) {
            displayHistory();
        } else if (userInput.equals("quit") || userInput.equals("q")) {
            endGame();
        } else {
            System.out.println("That is not a valid entry.\n");
            displayMainMenu();
        }
    }

    public static String getInput(String string) {//Taken from integer calculator
        System.out.println(string + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String removeCaseSensitive(String string) {
        return string.toLowerCase();
    }

    public static void displayHistory() {
        if (results.size() == 0) {
            System.out.println("There is no history to display yet. \n Why don't you play a few rounds?");
        } else {
            for (String s : results) {
                System.out.println(s);
            }
        }
        displayMainMenu();
    }

    public static void endGame() {
        System.out.println("This will do a thing too. End Game");
        displayMainMenu();
    }

    public static void playRound() {
        if (playerScore < (bestOf/2)+1 && cpuScore <(bestOf/2)+1) {//Checks that both players have less than required score. e.g. 2 of 3, 3 of 5, 4 of 7
            round++;
            String playerThrow = removeCaseSensitive(getInput(playerActionMessage));
            boolean validInput = false;
            for (int i = 0; i < validEntries.length; i++) {
                if (playerThrow.equals(validEntries[i])) {
                    validInput = true;
                    break;
                }
            }
            if (validInput) {
                switch (playerThrow) {
                    case "r":
                        playerThrow = "rock";
                        break;
                    case "s":
                        playerThrow = "scissors";
                        break;
                    case "p":
                        playerThrow = "paper";
                        break;
                }
                System.out.println(checkOutcome(playerThrow));
                playRound();
            } else {
                System.out.println("Not a valid option");
                playRound();
            }
        } else{
            System.out.println("Match is over");
            String matchResultMessage;
            if (playerScore>cpuScore){
                matchResultMessage= String.format("The player won the match with a score of %s : %s \n", String.valueOf(playerScore), String.valueOf(cpuScore));
            }else{
                matchResultMessage= String.format("The computer won the match with a score of %s : %s \n",String.valueOf(cpuScore), String.valueOf(playerScore));
            }
            results.add(matchResultMessage);
            displayMainMenu();
        }
    }

    public static String checkOutcome(String playerThrows) {
        String winner;
        Random randomGen = new Random();
        int compThrowIndex = randomGen.nextInt(3);

        if (playerThrows.equals(validEntries[compThrowIndex])) {
            winner = "No one";
        } else if (playerThrows.equals(validEntries[1])) {
            if (compThrowIndex == 0) {
                winner = cpu;
                cpuScore ++;
            } else {
                winner = player;
                playerScore++;
            }
        } else if (playerThrows.equals(validEntries[2])) {
            if (compThrowIndex == 0) {
                winner = player;
                playerScore++;
            } else {
                winner = cpu;
                cpuScore++;
            }
        } else {
            if (compThrowIndex == 1) {
                winner = player;
                playerScore++;
            } else {
                winner = cpu;
                cpuScore++;
            }
        }
        String message = "CPU threw: " + validEntries[compThrowIndex] + "  Player threw: " + playerThrows + "\n " + winner + " wins!\n";
        message+= "\n Current Score \n CPU: "+ cpuScore + "\nPlayer: "+ playerScore;
        if (winner.equals("No one")) {
            winner = "Tie";
        }
        logResults(winner, validEntries[compThrowIndex], playerThrows);
        return message;
    }

    public static void logResults(String winner, String computersThrow, String playersThrow) {
        //ToDO: Add round number if I do matches.
        String logMessage = String.format("Round#%s|| Win went to: %s | Player threw: %s | Computer threw: %s",String.valueOf(round), winner, computersThrow, playersThrow);
        results.add(logMessage);
    }
    //FixMe: Something funky going on in this method that is causing it to keep userInput from invalid user input.
    public static void askBestOf() {
        String userInput = getInput(bestOfPrompt);
        try {
            Integer.valueOf(userInput);
            if (Integer.valueOf(userInput) > 21) {
                System.out.println("For arbitrary reasons that I made up, you can't play that many games. Please enter a valid number\n");
                askBestOf();
            } else if (Integer.valueOf(userInput) % 2 == 0) {
                System.out.println("That is an even number. \n(The reason for it being odd is to prevent matches ending in ties.)\n");
                askBestOf();
            } else{
                bestOf = Integer.valueOf(userInput);
            }
        } catch (Exception e) {
            System.out.println("That is not an integer. Please input an integer");
            askBestOf();
        }
    }
}
