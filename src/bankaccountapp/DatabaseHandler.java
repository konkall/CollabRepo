package bankaccountapp;

import java.sql.*;

public class DatabaseHandler {

    private String connectionIP;
    private int port;
    private String dbName;
    private String dbUsername;
    private String dbPassword;

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public DatabaseHandler(String connectionIP, int port, String dbName, String dbUsername, String dbPassword){
        this.connectionIP = connectionIP;
        this.port = port;
        this.dbName = dbName;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;

        try{
            String url = "jdbc:mysql://" + this.connectionIP + ":" + this.port + "/" + this.dbName + "?characterEncoding=latin1&useConfigs=maxPerformance";

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, this.dbUsername, this.dbPassword);


            System.out.println("Connection Successful!");

        }catch(Exception e){ System.out.println(e);}

    }

    //INSERT INTO `bankapp`.`clients` (`id`, `name`, `ssn`, `type`, `balance`) VALUES ('2', 'jsadja', '83748374', 'saving', '4444');
    public void insertClient(int id, String name, String sSN, String accountType, double balance){

        Account account;

        if (accountType.equals("Savings")){
            account = new Savings(name, sSN, balance);
        }
        else if (accountType.equals("Checking")){
            account = new Checking(name, sSN, balance);
        }
        else{
            //todo
        }

        String query = "INSERT INTO `bankapp`.`clients` (`id`, `name`, `ssn`, `type`, `balance`) VALUES ('" + id +
                "', '" + name + "', '" + sSN + "', '" + accountType + "', '" + balance + "');";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        }catch (Exception e){
            System.out.println(e);

        }

    }

    public void deleteAll(){
        String query = "DELETE FROM `bankapp`.`clients`";

        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e){
            System.out.println(e);

        }
    }

    public void showDatabase() throws SQLException {

        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from clients");

            while(rs.next()) {
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getDouble(5));
            }
        }catch (Exception e){
            System.out.println(e);

        }

    }

    public void closeConnection() throws SQLException {

        con.close();
        System.out.println("Connection closed");


    }
}
