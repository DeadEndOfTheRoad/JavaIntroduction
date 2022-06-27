package net.princesotry_.banking;

import java.nio.charset.CharacterCodingException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class CheckingAccount {
    static Scanner scanner = new Scanner(System.in);

    //Fields
    protected double currentDeposit;

    //Constructor
    protected CheckingAccount(double currentDeposit) {
        this.currentDeposit = currentDeposit;
    }

    public static void transferToCurrentDeposit(CheckingAccount account) {
        System.out.println("How much would you like to deposit?");
        account.currentDeposit += scanner.nextDouble();
        scanner.nextLine();
    }

    public static void withdrawFromCurrentDeposit(CheckingAccount account) {
        if(account.currentDeposit < 0) {
            System.out.println("Your current balance is negative, and thus you cannot withdraw any money. Please deposit some money.");
        } else {
            System.out.println("How much would you like to withdraw?");
            double withdrawAmount = scanner.nextDouble();
            scanner.nextLine();
            if(withdrawAmount < LoggedInProfileAccount.loggedCheckingAccount.currentDeposit) {
                account.currentDeposit -= withdrawAmount;
            } else {
                if(withdrawAmount < LoggedInProfileAccount.loggedCheckingAccount.currentDeposit + Bank.maximumOverdraftAmount) {
                    account.currentDeposit -= withdrawAmount;
                    System.out.println("Maximum overdraft allowed you to withdraw £100.00 extra ");
                } else {
                    System.out.println("Not enough balance");
                }
            }
        }
    }

    @Override
    public String toString() {
        return " currentDeposit = " + currentDeposit;
    }
}
