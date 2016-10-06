package NikitaS;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static ArrayList<String> results;
    static String header="============Welcome to Game==============";//FixMe: Get title
    static String mainPrompt = "Type in one of the following: \n \"Play\" \n \"History\" \n \"Quit\" \n";
    static String[] validEntries = {"rock", "scissors", "paper", "r", "s", "p"};
    static String playerActionMessage="Enter \""+validEntries[0]+"\", \""+ validEntries[1]+"\" or \""+validEntries[2]+"\" or just the first letter:";

    static Integer playerScore;
    static Integer computerScore;

    static String player = "Player";
    static String cpu = "CPU";

    public static void main(String[] args) {
        computerScore = 0;
        playerScore = 0;

        int rounds;
        displayMainMenu();


    }
    public static void displayMainMenu(){
        System.out.println(header);
        userMainEntry(removeCaseSensitive(getInput(mainPrompt)));
    }

    public static void userMainEntry(String userInput){
        if (userInput.equals("play") || userInput.equals("p")){
            playRound();
        } else if (userInput.equals("history")|| userInput.equals("h")) {
            displayHistory();
        }else if (userInput.equals("quit")|| userInput.equals("q")) {
            endGame();
        }else {
            System.out.println("That is not a valid entry.\n");
            displayMainMenu();
        }
    }

    public static String getInput(String string){//Taken from integer calculator
        System.out.println(string + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String removeCaseSensitive(String string){
        return string.toLowerCase();
    }

    public static void displayHistory(){
        System.out.println("This will do a thing, History");
        displayMainMenu();
    }
    public static void endGame(){
        System.out.println("This will do a thing too. End Game");
        displayMainMenu();
    }

    public static void playRound(){
        String playerThrow = removeCaseSensitive(getInput(playerActionMessage));
        boolean validInput= false;
        for (int i = 0; i < validEntries.length ; i++) {
            if (playerThrow.equals(validEntries[i])){
                validInput=true;
            }
        }
        if (validInput){
            switch(playerThrow){
                case "r":
                    playerThrow="rock";
                    break;
                case "s":
                    playerThrow="scissors";
                    break;
                case "p":
                    playerThrow = "paper";
                    break;
            }
            System.out.println(checkOutcome(playerThrow));
            playRound();
        }else {
            System.out.println("Not a valid option");
            playRound();
        }
    }
    public static String checkOutcome(String playerThrows){
        String winner;
        Random randomGen = new Random();
        int compThrowIndex = randomGen.nextInt(3);

        if (playerThrows.equals(validEntries[compThrowIndex])){
            winner = "No one";
        }else if (playerThrows.equals("scissors")) {
            if (compThrowIndex == 0) {
                winner = cpu;
            } else {
                winner = player;
            }
        }
            else if(playerThrows.equals("paper")) {
            if (compThrowIndex == 0) {
                winner = player;
            } else {
                winner = cpu;
            }
        }else{
            if (compThrowIndex==1){
                winner = player;
            }else{
                winner= cpu;
            }
        }
        String message = "CPU threw: "+ validEntries[compThrowIndex]+ "  Player threw: "+playerThrows+ "\n "+ winner + " wins!\n";
        return message;
    }


//    public static String askBestOf(){
//        String message = "Best of how many rounds do you want to play? (It must be an odd number between 1-21\n Decimal numbers will be rounded down)";
//        String userInput = getInput(message);
//        try{
//            Integer.valueOf(userInput);
//            if (Integer.valueOf(userInput)>21){
//                System.out.println("I'm sorry. For arbitrary reasons that I made up, you can't play that many games. Please enter a valid number\n");
//                askBestOf();
//            }else if (Integer.valueOf(userInput)%2==0){
//                System.out.println("That is an even number. \n(The reason for it being odd is to prevent matches ending in ties.)\n");
//                askBestOf();
//            }
//        }catch(Exception e){
//            System.out.println("That is not an integer. Please input an integer");
//            askBestOf();
//        }
//        return userInput;



}
