package net.princesotry_.banking;

import java.util.Map;
import java.util.Scanner;


public class Bank {

    public static Scanner scanner = new Scanner(System.in);

    //Fields
    public Map<Person, CheckingAccount> customersAndAccounts;
    public static double maximumOverdraftAmount = 100.00;

    //Constructor
    public Bank(Map<Person, CheckingAccount> customersAndAccounts) {
        this.customersAndAccounts = customersAndAccounts;
    }

    public void addBotAccounts() {
        //Initialising Bot Profiles
        Person person1 = new Person("Naman", "Kumar", "Goyal", "AB123456C");
        Person person2 = new Person("Grim", "Saj", "Defence", "AB123456D");
        Person person3 = new Person("Pixie", "Angel", "Mayor", "AB123456E");

        //Initialising Bot Accounts
        CheckingAccount account1 = new CheckingAccount(200.00);
        CheckingAccount account2 = new CheckingAccount(300.00);
        CheckingAccount account3 = new CheckingAccount(1000.00);

        //Adding them to the Bank
        this.customersAndAccounts.put(person1, account1);
        this.customersAndAccounts.put(person2, account2);
        this.customersAndAccounts.put(person3, account3);
    }

    public static void addProfileAndAccount(Bank bank, Person person, CheckingAccount account) {
        bank.customersAndAccounts.put(person, account);
    }

    public static CheckingAccount createNewAccount(double deposit) {
        return new CheckingAccount(deposit);
    }

    public static void logInProcedure() {
        System.out.println("Please enter your first name");
        String inputFirstName = scanner.nextLine();

    }

    @Override
    public String toString() {
        return "Bank{" + "customersAndAccounts=" + customersAndAccounts + '}';
    }
}
