package bankaccountapp;

import java.sql.*;
import java.util.List;

public class DatabaseHandler {

    private Connection con;
    private Statement stmt;

    public DatabaseHandler(String connectionIP, int port, String dbName, String dbUsername, String dbPassword){

        try{
            String url = "jdbc:mysql://" + connectionIP + ":" + port + "/" + dbName + "?characterEncoding=latin1&useConfigs=maxPerformance";

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, dbUsername, dbPassword);


            System.out.println("Connection Successful!");

        }catch(Exception e){ System.out.println(e);}

    }

    //INSERT INTO `bankapp`.`clients` (`id`, `name`, `ssn`, `type`, `balance`) VALUES ('2', 'jsadja', '83748374', 'saving', '4444');
    public void insertClient( String name, String sSN, String accountType, double balance){

        //Local creation of "account" objects is unnecessary at the current state of the application.
        /*
        Account account;
        if (accountType.equals("Savings")){
            account = new Savings(name, sSN, balance);
        }
        else if (accountType.equals("Checking")){
            account = new Checking(name, sSN, balance);
        }
        else{
            //
        }*/

        dbInsert(name,  sSN,  accountType, balance);

    }

    public void insertClient(String[] newAccountHolder){

        String name = newAccountHolder[1]+ " " + newAccountHolder[2];
        String sSN = newAccountHolder[3];
        String accountType = newAccountHolder[4];
        double balance = Double.parseDouble(newAccountHolder[5]);

        dbInsert(name,  sSN,  accountType, balance);

        //Local creation of "account" objects is unnecessary at the current state of the application.
        /*Account account;
        if (accountType.equals("Savings")){
            account = new Savings(name, sSN, balance);
        }
        else if (accountType.equals("Checking")){
            account = new Checking(name, sSN, balance);
        }
        else{
            //todo
        }*/



    }

    public void insertClient(List<String[]> newAccountHolders){

        for (String[] accountHolder : newAccountHolders){

            String name = accountHolder[0];
            String sSN = accountHolder[1];
            String accountType = accountHolder[2];
            double balance = Double.parseDouble(accountHolder[3]);

            dbInsert(name,  sSN,  accountType, balance);
            //Local creation of "account" objects is unnecessary at the current state of the application.
            /*Account account;
            if (accountType.equals("Savings")){
                account = new Savings(name, sSN, balance);
            }
            else if (accountType.equals("Checking")){
                account = new Checking(name, sSN, balance);
            }
            else{
                //
            }*/

        }
    }

    public void deleteAll(){
        String query = "DELETE FROM `bankapp`.`client`";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("\nEntry deletion successful!\n");

        } catch (Exception e){
            System.out.println(e);

        }
    }

    public void showDatabase() throws SQLException {

        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from client");

            while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+ rs.getString(2)+"  "+ rs.getString(3)+"  "+ rs.getString(4)+"  "+ rs.getDouble(5));
            }
        }catch (Exception e){
            System.out.println(e);

        }

    }

    public void closeConnection() throws SQLException {

        con.close();
        System.out.println("Connection closed");


    }

    private void dbInsert(String name, String sSN, String accountType, double balance){
        String query = "INSERT INTO `bankapp`.`client` ( `name`, `ssn`, `type`, `balance`) VALUES ('" + name + "', '" + sSN + "', '" + accountType + "', '" + balance + "');";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            //System.out.println("\nEntry insertion successful!\n");

        }catch (Exception e){
            System.out.println(e);

        }
    }
}
