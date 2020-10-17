package studentdatabaseapp;

import java.util.Scanner;

public class Student {
    private String firstName;
    private String lastName;
    private String gradeYear;
    private int gradeNumber;
    private String studentID;
    private String courses = "";
    private int tuitionBalance;
    private static int costOfCourse = 600;
    private static int id = 1000;


    //Constructor prompts user to enter name and year.
    public Student() {
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("Please enter your first name:");
        this.firstName = in.nextLine();

        System.out.println("Please enter your last name:");
        this.lastName = in.nextLine();

        System.out.println("1 - Freshman\n2 - Sophomore\n3 - Junior\n4 - Senior\nPlease enter your year: ");
        while (true) {
            try {
                option = in.nextInt();
                if (option == 1) {
                    this.gradeYear = "Freshman";
                    this.gradeNumber = 1;
                    break;
                } else if (option == 2) {
                    this.gradeYear = "Sophomore";
                    this.gradeNumber = 2;
                    break;
                } else if (option == 3) {
                    this.gradeYear = "Junior";
                    this.gradeNumber = 3;
                    break;
                } else if (option == 4) {
                    this.gradeYear = "Senior";
                    this.gradeNumber = 4;
                    break;
                } else {
                    System.out.println("Please enter a valid option:");
                    in.nextLine();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("That is not a valid option, dummy! Try again.");
                in.nextLine();
            }
        }

        setStudentID();

        //System.out.println("Name: " + this.firstName + " " + this.lastName + "\nYear: " + this.gradeYear + "\nStudent ID: " + this.studentID);

    }
    //Generate an ID
    private void setStudentID(){
        //Grade level + ID
        id++;
        this.studentID = gradeNumber + "" + id;
    }
    //Enroll in courses
    public void enroll(){
        //Get inside a loop, user hits 0 to stop.


        do{
            System.out.println("Enter course to enroll, \"Q\" to quit: ");
            Scanner in = new Scanner(System.in);
            String course = in.nextLine();
            if(!course.equals("Q")){
                courses = courses + "\n" + course;
                tuitionBalance = tuitionBalance + costOfCourse;
            }
            else{
                break;
            }
        }while(true);


    }
    //View balance
    public void viewBalance(){
        System.out.println("Your balance is: $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition(){
        viewBalance();
        System.out.println("Enter your payment:");

        Scanner in = new Scanner(System.in);
        int payment = in.nextInt();

        tuitionBalance = tuitionBalance - payment;
        System.out.println("Thank you for your payment of $" + payment);
        viewBalance();
    }
    //Status print
    public void showInfo(){
        System.out.println("Name: " + this.firstName + " " + this.lastName +
                "\nGrade Level: " + gradeYear +
                "\nStudent ID: " + studentID +
                "\nCourses Enrolled: " + courses +
                "\nBalance: $" + tuitionBalance);
    }
}
