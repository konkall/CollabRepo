package bankaccountapp;

public abstract class Account implements IBaseRate{
    //List common properties for savings and checking accounts
    private String name;
    private String sSN;
    private double balance;

    private static int index = 10000;
    private int type;
    private String accountNumber;
    protected double rate;


    //Constructor to set base properties and initialize the account
    public Account(String name, String sSN, double initDeposit, int type){
        this.name = name;
        this.sSN = sSN;
        this.balance = initDeposit;
        this.type = type;

        //System.out.println("Name is: " + this.name);
        //System.out.println("SSN: " + this.sSN);
        //System.out.println("Balance is: " + balance);

        //System.out.print("New account: ");

        //Set account number
        index++;
        this.accountNumber = setAccountNumber();

        setRate();
        //System.out.println("ACCOUNT NUMBER: " + this.accountNumber);
    }

    public abstract void setRate();

    private String setAccountNumber(){
        String lastTwoOfSSN = sSN.substring(sSN.length()-2, sSN.length());
        int uniqueID = index;
        int randomNumber = (int) (Math.random() * Math.pow(10, 3));
        return this.type + lastTwoOfSSN + uniqueID + randomNumber;
    }

    public void compound(){
        double accruedInterest = balance * (rate/100);
        balance = balance + accruedInterest;
        System.out.println("Accrued Interest: %" + accruedInterest);
        printBalance();
    }

    //List of common methods
    public void deposit(double amount){
        balance = balance + amount;
        System.out.println("Depositing $" + amount);
        printBalance();
    }

    public void withdraw(double amount){
        balance = balance - amount;
        System.out.println("Withdrawing $" + amount);
        printBalance();
    }

    public void transfer(String toWhere, double amount){
        balance = balance - amount;
        System.out.println("Transferring $" + amount + " to " + toWhere);
        printBalance();
    }

    public void printBalance(){
        System.out.println("Your balance is $" + balance);

    }

    public void showInfo(){
        System.out.println(
                        "Name: " + name +
                        "\nAccount Number: " + accountNumber +
                        "\nBalance: " + balance +
                        "\nRate: " + rate + "%"
                );
    }
}
