package NikitaS;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<String> results;

    public static void main(String[] args) {
        displayMainMenu();


    }
    public static void displayMainMenu(){
        String header = "============Welcome to Game==============";//FixMe: Get better title
        String mainPrompt = "Type in one of the following: \n \"Play\" \n \"History\" \n \"Quit\" \n";

        System.out.println(header);
        userMainEntry(removeCaseSensitive(getInput(mainPrompt)));

    }

    public static void userMainEntry(String userInput){
        if (userInput == "play" || userInput == "p"){
            askBestOf();
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
}
