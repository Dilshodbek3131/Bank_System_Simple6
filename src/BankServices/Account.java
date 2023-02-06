package BankServices;

import java.util.*;


public class Account {

    private int code;
    private String ownerName;
    private int date;

    private double balance;

    private ArrayList<Operation> operations = new ArrayList<>();
    private ArrayList<Deposit> deposits = new ArrayList<>();
    private ArrayList<Withdrawal> withdrawals = new ArrayList<>();

    public Account(int code, int date, String ownerName, double balance) {
        this.code = code;
        this.date = date;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getCode(){
        return code;
    }

    public List<Operation> getMovements() {
        Collections.sort(operations, new DateDescOperationComparator()  );
        return operations;
//         getMovements() sanalar bo'yicha tartiblangan hisobda bajarilgan barcha operatsiyalar ro'yxatini qaytaradi;
    }

    public List<Deposit> getDeposits() {
        Collections.sort(deposits,new DateDescOperationComparator());
        return deposits;
    }

    public List<Withdrawal> getWithdrawals() {
        Collections.sort(withdrawals,new DateDescWithdrawalComparator());
        return withdrawals;
    }

    public void deposit(int date, double amount) {
        if (date > this.date) this.date = date;
        balance += amount;
        Deposit deposit = new Deposit(this.date, amount);
        operations.add(deposit);
        deposits.add(deposit);
    }

    public void withdraw(int date, double amount) {
        if (date > this.date) this.date = date;
        balance -= amount;
        Withdrawal withdrawal = new Withdrawal(this.date, amount);
        operations.add(withdrawal);
        withdrawals.add(withdrawal);
    }

    @Override
    public String toString() {
        return code + "," + ownerName + "," + date + "," + balance;
    }
}
