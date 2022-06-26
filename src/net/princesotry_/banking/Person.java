package net.princesotry_.banking;

public class Person {
    //Fields
    public String firstName;
    public String middleName;
    public String lastName;
    private String socialSecurityNumber;

    //Constructor
    public Person(String firstName, String middleName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
