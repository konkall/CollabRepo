package bankaccountapp;

public class Checking extends Account {

    //List properties specific to Checking account
    private int debitCardNumber;
    private int debitCardPin;

    //Constructor to initialize checking account properties
    public Checking(String name, String sSN, double initDeposit){
        super(name, sSN, initDeposit, 2);
        //System.out.println("New checking account.\n");
        setDebitCard();
    }

    @Override
    public void setRate(){

        //System.out.println("Implement rate for Checking");
        rate = getBaseRate() * .15;
    }


    //List any methods specific to the checking account
    private void setDebitCard(){
        debitCardNumber = (int) (Math.random() * Math.pow(10, 12));
        debitCardPin = (int) (Math.random() * Math.pow(10, 4));

    }

    public void showInfo(){
        super.showInfo();
        System.out.println("Account type: Checking");
        System.out.println("Account features:" +
                "\n     Debit Card number: " + String.format("%012d", debitCardNumber) +
                "\n     Debit Card PIN: " + String.format("%04d", debitCardPin) + "\n");
    }
}
