package BankServices;

public class Deposit extends Operation{
    public Deposit(int date, double balance) {
        super(date, balance);
    }

    public String toString() {
        return super.getDate() + "," + super.getBalance() + "+";
    }
}


