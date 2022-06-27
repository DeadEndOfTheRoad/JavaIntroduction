package net.princesotry_.banking;

public class LoggedInProfileAccount {

    static Person loggedPerson;
    static CheckingAccount loggedCheckingAccount;

    public static void setLoggedPerson(Person loggedPerson) {
        LoggedInProfileAccount.loggedPerson = loggedPerson;
    }

    public static void setLoggedCheckingAccount(CheckingAccount loggedCheckingAccount) {
        LoggedInProfileAccount.loggedCheckingAccount = loggedCheckingAccount;
    }
}
