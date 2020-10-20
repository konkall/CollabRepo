package bankaccountapp;

import utilities.SelectFile;
import utilities.SyntaxCheck;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static utilities.SyntaxCheck.*;

public class BankAccountApp {
    public static void main(String[] args) throws SQLException {
        Scanner inputObj = new Scanner(System.in);

        DatabaseHandler dbHandler = new DatabaseHandler("localhost", 3306, "bankapp", "root", "Mmetalplast7&");

        System.out.println("Welcome to your bank managing application!");
        label:
        while(true){
            System.out.println("\nPlease enter the desired command (Input 'help' for...help!):");
            String inputCommand = inputObj.nextLine();
            String[] tokens = inputCommand.split(" ");


            switch (tokens[0]) {
                case "help":
                    System.out.println("The available commands are:\n\n" +
                            "printall: Prints all clients.\n\n" +
                            "insert: Inserts a client.\n" +
                            "(Syntax: insert [First Name] [Last Name] [SSN] [Account type] [Balance])\n\n" +
                            "insertfile: Select a CSV file that includes clients from a dialog window.\n\n" +
                            "deleteall: Deletes all clients from the database\n\n" +
                            "exit: Closes application.\n\n");
                    break;
                case "printall":
                    dbHandler.showDatabase();
                    break;
                case "insert":
                    if (SyntaxCheck.insertSyntaxOK(tokens)) {
                        dbHandler.insertClient(tokens);
                    } else {
                        System.out.println("Wrong syntax.\n" +
                                "CORRECT SYNTAX: insert [First Name] [Last Name] [SSN] [Account type] [Balance]\n");
                    }
                    break;
                case "insertfile":
                    String filePath = SelectFile.OpenWindow();
                    if (filePath != null) {
                        List<String[]> newAccountHolders = utilities.CSV.read(filePath);
                        dbHandler.insertClient(newAccountHolders);
                    } else {
                        System.out.println("No file selected.\n");
                    }
                    break;
                case "deleteall":
                    dbHandler.deleteAll();
                    break;
                case "exit":
                    dbHandler.closeConnection();
                    break label;
                default:
                    System.out.println("'" + tokens[0] + "' is not a recognized command.");
                    break;
            }
        }

    }
}
