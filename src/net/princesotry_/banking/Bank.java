package net.princesotry_.banking;

import net.princesotry_.Main;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;


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

    public static void logInProcedure(Bank bank) {
        System.out.println("Please enter your first name");
        String inputFirstName = scanner.nextLine();
        System.out.println("Please enter your social security number");
        String inputSocialSecurityNumber = scanner.nextLine();
        int i = 0;
        for (Person person : bank.customersAndAccounts.keySet()) {
            Set<Person> key = getKey(bank, person);
            i = searchAccountsForLoginDetails(inputFirstName, inputSocialSecurityNumber, i, key, bank);
        }
        if (i == bank.customersAndAccounts.size()) {
            System.out.println("Sorry, your login details are incorrect");
        }

    }

    public static int searchAccountsForLoginDetails(String inputFirstName, String inputSocialSecurityNumber, int i, Set<Person> key, Bank bank) {
        if (inputFirstNameEqualsProfileName(inputFirstName, key) && inputSocialSecurityNumberEqualsSocialSecurityNumber(inputSocialSecurityNumber, key) && i != bank.customersAndAccounts.size()+1)  {
            Main.setLoggedIn(true);
            LoggedInProfileAccount.setLoggedPerson(key.stream().toList().get(0));
            LoggedInProfileAccount.setLoggedCheckingAccount(bank.customersAndAccounts.get(key.stream().toList().get(0)));
            i = bank.customersAndAccounts.size()+1;
        } else {
            i++;
        }
        return i;
    }

    public static boolean inputSocialSecurityNumberEqualsSocialSecurityNumber(String inputSocialSecurityNumber, Set<Person> key) {
        return key.stream().toList().get(0).socialSecurityNumber.equalsIgnoreCase(inputSocialSecurityNumber);
    }

    public static boolean inputFirstNameEqualsProfileName(String inputFirstName, Set<Person> key) {
        return key.stream().toList().get(0).firstName.equalsIgnoreCase(inputFirstName);
    }

    @NotNull
    public static Set<Person> getKey(Bank bank, Person person) {
        return bank.customersAndAccounts.entrySet().stream().filter((entry) -> Objects.equals(bank.customersAndAccounts.get(person), entry.getValue())).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Bank{" + "customersAndAccounts=" + customersAndAccounts + '}';
    }

    public String getSocialSecurityNumber(Person person) {
        return person.socialSecurityNumber;
    }

    public static void askForLogOutOrInteractWithAccount() {
        System.out.println("Would you like to:\na) Log out\nb) Transfer money to bank account\nc) Withdraw money from bank account\n\n Please enter either a, b or c:)");
        String choice = scanner.nextLine();
        if(Main.validateStringInput(choice, "a", "b", "c")) {
            if(choice.equalsIgnoreCase("a")) {
                Main.setLoggedIn(false);
            } else if(choice.equalsIgnoreCase("b")) {
                CheckingAccount.transferToCurrentDeposit(LoggedInProfileAccount.loggedCheckingAccount);
                System.out.println("Your current balance is £" + LoggedInProfileAccount.loggedCheckingAccount.currentDeposit);
            } else if(choice.equalsIgnoreCase("c")) {
                CheckingAccount.withdrawFromCurrentDeposit(LoggedInProfileAccount.loggedCheckingAccount);
                System.out.println("Your current balance is £" + LoggedInProfileAccount.loggedCheckingAccount.currentDeposit);
            }
        } else {
            System.out.println("Invalid input, please enter a, b or c.");
            askForLogOutOrInteractWithAccount();
        }


    }
}
