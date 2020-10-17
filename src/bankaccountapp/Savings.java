package bankaccountapp;

public class Savings extends Account {
    //List properties specific to Savings account
    private int safetyDepositBoxID;
    private int SafetyDepositBoxKey;

    //Constructor to initialize Savings account properties
    public Savings(String name, String sSN, double initDeposit){
        super(name, sSN, initDeposit, 1);
        //System.out.println("New savings account.\n");

        setSafetyDepositBox();

    }

    @Override
    public void setRate(){

        //System.out.println("Implement rate for Savings");
        rate = getBaseRate() - 0.25;
    }

    private void setSafetyDepositBox(){
        safetyDepositBoxID = (int) (Math.random() * Math.pow(10, 3));
        SafetyDepositBoxKey = (int) (Math.random() * Math.pow(10, 4));

    }

    //List any methods specific to the Savings account
    public void showInfo(){
        super.showInfo();
        System.out.println("Account type: Savings");
        System.out.println("Account features:" +
                "\n     Safety Deposit Box ID: " + String.format("%03d", safetyDepositBoxID) +
                "\n     Safety Deposit Box Key: " + String.format("%04d", SafetyDepositBoxKey) + "\n");
    }
}
