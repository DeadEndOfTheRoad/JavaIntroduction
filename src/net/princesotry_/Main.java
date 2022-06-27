package net.princesotry_;

import net.princesotry_.banking.Bank;
import net.princesotry_.banking.Person;

import java.util.*;

public class Main {

    static Bank javaBank = setBank();
    static boolean loggedIn = false;
    static Person temporaryPerson = null;

    //Setter for temporaryPerson
    public static void setTemporaryPerson(Person temporaryPerson) {
        Main.temporaryPerson = temporaryPerson;
    }

    //Setter for loggedIn
    public static void setLoggedIn(boolean loggedIn) {
        Main.loggedIn = loggedIn;
    }

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        javaBank.addBotAccounts();


        //The program
        while(true) {
            while(loggedIn) {
                Bank.askForLogOutOrInteractWithAccount();
            }
            System.out.println("Welcome to the Java Industry Bank!");
            askForSignUpOrLogIn();

        }

    }


    public static void askForSignUpOrLogIn() {
        System.out.println("Would you like to:\na) Log in\nb) Sign up\n\n Please enter either a or b :)");
        String choice = scanner.nextLine();
        System.out.println(choice);
        if (validateStringInput(choice, "a", "b", null)) {
            if (choice.equalsIgnoreCase("a")) {
                Bank.logInProcedure(javaBank);
            } else if (choice.equalsIgnoreCase("b")) {
                createNewProfile();
                askForInitialDeposit(temporaryPerson);
                setTemporaryPerson(null);
            } else {
                System.out.println("Invalid input, please enter a or b.");
            }
        } else {
            System.out.println("Invalid input, please enter a or b.");
            askForSignUpOrLogIn();
        }
    }




    public static void askForInitialDeposit(Person person) {
        if(temporaryPerson != null) {
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
    }


    public static Bank setBank() {
        return new Bank(new HashMap<>());

    }


    public static boolean validateStringInput(String choice, String correctChoice1, String correctChoice2, String correctChoice3) {
        return choice.equalsIgnoreCase(correctChoice1) || choice.equalsIgnoreCase(correctChoice2) || choice.equalsIgnoreCase(correctChoice3);
    }


    public static void createNewProfile() {
        boolean foundSameSSN = false;
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter your middle name");
        String middleName = scanner.nextLine();
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter your social security number");
        String socialSecurityNumber = scanner.nextLine();
        for (Person person: javaBank.customersAndAccounts.keySet()) {
            if(javaBank.getSocialSecurityNumber(person).equalsIgnoreCase(socialSecurityNumber) && !foundSameSSN) {
                System.out.println("Sorry, that social security number already has an account.");
                setTemporaryPerson(null);
                foundSameSSN = true;
            } else if(foundSameSSN) {
                //
            } else {
                setTemporaryPerson(new Person(firstName, middleName, lastName, socialSecurityNumber));
            }
        }
    }

    public static void outputBank(Bank bank) {
        for (Person person: bank.customersAndAccounts.keySet()) {

            Set<Person> key = Bank.getKey(bank, person);
            System.out.println(key + " --> " + bank.customersAndAccounts.get(person));
        }
        }
    }
