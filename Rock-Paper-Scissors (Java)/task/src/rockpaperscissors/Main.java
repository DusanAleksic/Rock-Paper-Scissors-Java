package rockpaperscissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void game(String userInput){
        Random random = new Random();
        String computerChoice=null;
        int randomNumber = random.nextInt(3   );
        if(randomNumber==0)
            computerChoice="rock";
        if(randomNumber==1)
            computerChoice="paper";
        if(randomNumber==2)
            computerChoice="scissors";
        if(computerChoice.equals(userInput)){
            System.out.println("There is a draw ("+userInput+")");
            rating+=50;
        }

        else if(computerChoice.equals("rock") && userInput.equals("paper")){
            System.out.println("Well done. The computer chose rock and failed");
            rating+=100;
        }
        else if(computerChoice.equals("rock") && userInput.equals("scissors"))
            System.out.println("Sorry, but the computer chose rock");
        else if(computerChoice.equals("paper") && userInput.equals("scissors")){
            System.out.println("Well done. The computer chose paper and failed");
            rating+=100;
        }
        else if(computerChoice.equals("paper") && userInput.equals("rock"))
            System.out.println("Sorry, but the computer chose paper");
        else if(computerChoice.equals("scissors") && userInput.equals("rock")){
            System.out.println("Well done. The computer chose scissors and failed");
            rating+=100;
        }
        else if(computerChoice.equals("scissors") && userInput.equals("paper"))
            System.out.println("Sorry, but the computer chose scissors");

    }
    public static String chooseOptions(){
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        return choice;
    }
    public static void gameplay(String choices[],String userChoice){
        int length = choices.length;
        Random random = new Random();
        String computerChoice=null;
        int randomNumber = random.nextInt(length  );
        computerChoice = choices[randomNumber];
        int userChoiceOrder=0;

        for(int i=0;i<choices.length;i++){
            if(choices[i].equals(userChoice))
                userChoiceOrder=i;
        }
        if(userChoice.equals(computerChoice)){
            System.out.println("There is a draw ("+userChoice+")");
            rating+=50;
            return;
        }
        if(userChoiceOrder<length/2){
            if(userChoiceOrder<randomNumber && (randomNumber-userChoiceOrder)<=(length/2)){

               System.out.println("Sorry, but the computer chose "+computerChoice);

            }
            else {
                rating+=100;
                System.out.println("Well done. The computer chose "+computerChoice+" and failed");
            }
        } else if (userChoiceOrder>length/2){
            if(userChoiceOrder>randomNumber && (userChoiceOrder-randomNumber)<=(length/2)){
                System.out.println("Well done. The computer chose "+computerChoice+" and failed");
                rating+=100;
            }
            else {
                System.out.println("Sorry, but the computer chose "+computerChoice);
            }
        }
        else{
            if(userChoiceOrder>randomNumber){
              System.out.println("Well done. The computer chose "+computerChoice+" and failed");
                rating+=100;
            }
            else
               System.out.println("Sorry, but the computer chose "+computerChoice);
        }
    }

    static int  rating=350;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String computerChoice = "";

        String userChoice="";
        System.out.println("Enter your name: ");
        String  userName = scanner.nextLine();
        System.out.println("Hello, "+userName);
        String filePath = "D:\\Kodovi\\Rock-Paper-Scissors (Java)\\Rock-Paper-Scissors (Java)\\task\\src\\rockpaperscissors\\rating.txt";
        File file = new File(filePath);
        boolean take = false;
        try (Scanner scanner1 = new Scanner(file)) {
            while (scanner1.hasNext()) {
                if(scanner1.next().equals(userName)){
                    rating=scanner1.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + filePath);
        }
        String choices[] = chooseOptions().split(",");

        System.out.println("Okay, let's start");

        boolean validInput=false;

        while(true){
            userChoice=scanner.nextLine();
            if(userChoice.equals("!exit"))
                break;
            if(userChoice.equals("!rating")){
                System.out.println("Your rating: "+rating);
                continue;
            }
            if(choices.length==1 && choices[0].equals("")){
              switch (userChoice){
                case "scissors":
                    game("scissors");
                    break;
                case "paper":
                    game("paper");
                    break;
                case "rock":
                    game("rock");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            continue;
            }

            for(int i=0;i<choices.length;i++){
                if(userChoice.equals(choices[i]))
                    validInput=true;
            }
            if(!validInput){
                System.out.println("Invalid input");
                break;
            }
            gameplay(choices,userChoice);
        }
        System.out.println("Bye!");
    }
}
