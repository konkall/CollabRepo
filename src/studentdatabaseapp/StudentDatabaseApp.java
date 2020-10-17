package studentdatabaseapp;

import java.util.Scanner;

public class StudentDatabaseApp {

    static int studentNo = 0;

    public static void main(String[] args) {
        //int studentNo = 0;

        //Ask how many students that we'd like to add
        Scanner in = new Scanner(System.in);

        System.out.println("Please insert number of students: ");


        while (true) {
            try {
                studentNo = in.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("That is not a number, dummy! Try again.");
                in.nextLine();
            }
        }

        System.out.println("The number of students is: " + studentNo);


        //Loop where students are created.

        Student[] students = new Student[studentNo];
        for(int i = 0; i < studentNo; i++){
            students[i] = new Student();
            students[i].enroll();
            students[i].payTuition();
            students[i].showInfo();
        }

    }
}
