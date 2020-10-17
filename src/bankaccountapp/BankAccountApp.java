package bankaccountapp;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BankAccountApp {
    public static void main(String[] args) throws SQLException {
        int i = 1;

        DatabaseHandler dbHandler = new DatabaseHandler("localhost", 3306, "bankapp", "root", "Mmetalplast7&");

        dbHandler.showDatabase();


        List<Account> accounts = new LinkedList<Account>();

        //Read a csv file and then create new accounts based on that data
        String file = "C:\\Users\\Konstantinos\\Desktop\\NewBankAccounts.csv";
        List<String[]> newAccountHolders = utilities.CSV.read(file);
        for (String[] accountHolder : newAccountHolders){


            String name = accountHolder[0];
            String sSN = accountHolder[1];
            String accountType = accountHolder[2];
            double initDeposit = Double.parseDouble(accountHolder[3]);

            dbHandler.insertClient(i, name, sSN, accountType, initDeposit);
            i++;

            /*

            if (accountType.equals("Savings")){
                accounts.add(new Savings(name, sSN, initDeposit));
            }
            else if (accountType.equals("Checking")){
                accounts.add(new Checking(name, sSN, initDeposit));
            }
            else{
                //todo
            }
            */

        }

        for(Account acc : accounts){
            acc.showInfo();
        }

        //DatabaseHandler dbHandler = new DatabaseHandler("localhost", 3306, "bankapp", "root", "Mmetalplast7&");

        dbHandler.showDatabase();

        //dbHandler.deleteAll();

        dbHandler.closeConnection();


        /*
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankapp?characterEncoding=latin1&useConfigs=maxPerformance","root","Mmetalplast7&");
//here bankapp is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from clients");
            System.out.println("BEFORE WHILE LOOP.");

            while(rs.next()) {
                System.out.println("I ACCESS WHILE LOOP.");
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5));
            }
            System.out.println("I EXIT WHILE LOOP.");

            con.close();
        }catch(Exception e){ System.out.println(e);}

         */
    }
}
