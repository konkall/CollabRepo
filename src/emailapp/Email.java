package emailapp;

import java.util.Scanner;

public class Email {
    private String firstName;
    private String lastName;
    private String password;
    private int passwordLength = 8;
    private String department;
    private String emailAddress;
    private int mailboxCapacity = 500;
    private String alternateEmail;
    private String companySuffix = "cmpsuffix.com";

    //Constructor to receive the first and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        //System.out.println("EMAIL CREATED: " + this.firstName + " " + this.lastName);

        //call a method to get department.
        this.department = setDepartment();
        //System.out.println("Department is: " + department);

        //Generate password
        this.password = randomPassword(passwordLength);
        //System.out.println("Password is: " + password);

        //generate email address
        generateEmail();
        //System.out.println("Email is: " + this.emailAddress);

    }

    //Ask for department
    private String setDepartment() {
        System.out.println("DEPARTMENT CODES\n1 for Sales\n2 for Development\n3 for Accounting\n0 for none\nEnter department code: ");
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                int depChoice = in.nextInt();
                if (depChoice == 1) {
                    return "sales";
                } else if (depChoice == 2) {
                    return "dev";
                } else if (depChoice == 3) {
                    return "accnt";
                } else {
                    return "";
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("That is not a valid option, dummy! Try again.");
                in.nextLine();
            }
        }
    }

    //generate a random pw
    private String randomPassword(int length){
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%";

        char[] password = new char[length];
        for(int i=0; i<length ; i++){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    //Generate email address
    private void generateEmail(){
        if( department == ""){
            this.emailAddress = this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() + "@" + companySuffix;
        }
        else{
            this.emailAddress = this.firstName.toLowerCase() + "." + this.lastName.toLowerCase() + "@" + department + "." + companySuffix;
        }
    }

    public void getInfo(){
        System.out.println("Name: " + this.firstName + " " + this.lastName +
                            "\nEmail Address: " + this.emailAddress +
                            "\nPassword: " + this.password);
    }
    //Set the mailbox capacity
    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity = capacity;
    }
    //Set alternate email
    public  void setAlternateEmail(String alternateEmail){
        this.alternateEmail = alternateEmail;
    }
    //change the pw
    public void changePassword(String password){
        this.password = password;
    }

    //Get mailbox capacity
    public int getMailboxCapacity(){ return mailboxCapacity;}

    //get alternate email
    public String getAlternateEmail(){ return alternateEmail;}

    //get password
    public String getPassword(){ return  password;}
}
