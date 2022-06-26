package net.princesotry_;

import net.princesotry_.banking.Bank;
import net.princesotry_.banking.Person;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Bank javaBank = setBank();
    static boolean loggedIn = false;

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        javaBank.addBotAccounts();

        //The program
        while(true) {
            outputBank(javaBank);
            System.out.println("Welcome to the Java Industry Bank!");
            askForSignUpOrLogIn(loggedIn);
            if(loggedIn) {
                //
            }
        }

    }


    public static void askForSignUpOrLogIn(boolean loggedIn) {
        System.out.println("Would you like to:\na) Log in\nb) Sign up\n\n Please enter either a or b :)");
        String choice = scanner.nextLine();
        System.out.println((String)choice);
        if (validateStringInput(choice, "a", "b")) {
            if (choice.equals("a")) {
                Bank.logInProcedure();
            } else {
                Person temporaryPerson = createNewProfile();
                askForInitialDeposit(temporaryPerson);
                loggedIn = true;
            }
        } else {
            System.out.println("Invalid input, please enter a or b.");
            askForSignUpOrLogIn(loggedIn);
        }
    }




    public static void askForInitialDeposit(Person person) {
        System.out.println("Please enter your initial deposit, minimum deposit is £100.00.");
        double deposit = scanner.nextDouble();
        scanner.nextLine();
        if(deposit >= 100) {
            System.out.println("Thank you for choosing JavaBank");
            Bank.addProfileAndAccount(javaBank, person, Bank.createNewAccount(deposit));
        } else {
            System.out.println("Sorry the minimum deposit is £100.00");
            askForInitialDeposit(person);
        }
    }


    public static Bank setBank() {
        return new Bank(new HashMap<>());

    }


    public static boolean validateStringInput(String choice, String correctChoice1, String correctChoice2) {
        return choice.equals(correctChoice1) || choice.equals(correctChoice2);
    }


    public static Person createNewProfile() {
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter your middle name");
        String middleName = scanner.nextLine();
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter your social security number");
        String socialSecurityNumber = scanner.nextLine();

       return new Person(firstName, middleName, lastName, socialSecurityNumber);
    }

    public static void outputBank(Bank bank) {
        for (Person person: javaBank.customersAndAccounts.keySet()) {
            System.out.println(javaBank.customersAndAccounts.get(person) + " --> " + "insert Key");
        }
        }
    }
