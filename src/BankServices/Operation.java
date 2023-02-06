package BankServices;

public abstract class Operation {
    private int date;
    private double balance;

    public Operation(int date, double balance) {
        this.date = date;
        this.balance = balance;
    }

    public int getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }


    public abstract String toString();
}


