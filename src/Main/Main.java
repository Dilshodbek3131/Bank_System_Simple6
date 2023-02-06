package Main;

import BankServices.Account;
import BankServices.Bank;
import BankServices.Deposit;
import BankServices.InvalidCode;
import BankServices.InvalidValue;
import BankServices.Operation;
import BankServices.Withdrawal;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidCode, InvalidValue {
        Bank b1 = new Bank("Uncle-$crooge");
        int c1 = b1.createAccount("John", 5, 500.5);
        int c2 = b1.createAccount("Mary", 10,  1000.);
        int c3 = b1.createAccount("John", 20,  800.);
        int c4 = b1.createAccount("Paul", 30, 252.4);
        Account a1=null, a3=null;

        b1.deposit(c1, 7, 360.0);
        b1.deposit(c4, 35, 270.0);
        b1.withdraw(c3, 28, 350.0);
        b1.withdraw(c2, 19, 350.0);
        b1.withdraw(c3, 41, 158.0);
        b1.transfer(c1, c3, 8, 400.0);
        a1 = b1.getAccount(c1);
        a3 = b1.deleteAccount(c3,50);

        List<Account> accounts = b1.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            System.out.println("account: " + a);
            System.out.println(" movements:");
            List<Operation> movements = a.getMovements();
            for (int j = 0; j < movements.size(); j++) {
                Operation o = movements.get(j);
                System.out.println(o);
            }
            System.out.println();
        }

//		for(Account a : b1.getAccounts()) {
//			System.out.println("account: " + a);
//			System.out.println(" movements:");
//			for(Operation o : a.getMovements())
//				System.out.println(o);
//			System.out.println();
//		}

/*		account: 1,John,8,460.5
		 movements:
		8,400.0-
		7,360.0+
		5,500.5+

		account: 2,Mary,19,650.0
		 movements:
		19,350.0-


		10,1000.0+

		account: 4,Paul,35,522.4
		 movements:
		35,270.0+
		30,252.4+
*/
        System.out.println("deleted account: " + a3);
        System.out.println(" movements:");
        List<Operation> movements = a3.getMovements();
        for (int i = 0; i < movements.size(); i++) {
            Operation o = movements.get(i);
            System.out.println(o);
        }
//		for(Operation o : a3.getMovements())
//			System.out.println(o);
        System.out.println();

/*		deleted account: 3,John,50,0.0
		 movements:
		50,692.0-
		41,400.0+
		41,158.0-
		28,350.0-
		20,800.0+
*/
        System.out.println("account: " + a1);
        System.out.println(" movements:");

        movements = a1.getMovements();
        for (int i = 0; i < movements.size(); i++) {
            Operation o = (Operation) movements.get(i);
            System.out.println(o);
        }
//		for(Operation o : a1.getMovements())
//			System.out.println(o);


        System.out.println(" deposits:");
        List<Deposit> deposits = a1.getDeposits();
        for (int i = 0; i < deposits.size(); i++) {
            Deposit d = deposits.get(i);
            System.out.println(d);
        }
//		for(Deposit d : a1.getDeposits())
//			System.out.println(d);
        System.out.println(" withdrawals:");
        List<Withdrawal> withdrawals = a1.getWithdrawals();
        for (int i = 0; i < withdrawals.size(); i++) {
            Withdrawal w = withdrawals.get(i);
            System.out.println(w);
        }
//		for(Withdrawal w : a1.getWithdrawals())
//			System.out.println(w);
        System.out.println();

/*		account: 1,John,8,460.5
		 movements:
		8,400.0-
		7,360.0+
		5,500.5+
		 deposits:
		5,500.5+
		7,360.0+
		 withdrawals:
		8,400.0-
*/
        System.out.println("total deposit in the " + b1.getName() + " bank: " + b1.getTotalDeposit());
        System.out.println();

//		total deposit in the Uncle-$crooge bank: 1632.9

        System.out.println("accounts with balance higher than 500: " + b1.getNumberHigher(500));
        System.out.println();

//		accounts with balance higher than 500: 66.0 %

        System.out.println("accounts with balance in range 500..700 :");
        List<Account> accountsByBalance = b1.getAccountsByBalance(500, 700);
        for (int i = 0; i < accountsByBalance.size(); i++) {
            Account a = (Account) accountsByBalance.get(i);
            System.out.println(a);
        }
//		for(Account a : b1.getAccountsByBalance(500, 700))
//			System.out.println(a);
        System.out.println();

/*		accounts with balance in range 500..700 :
			2,Mary,19,650.0
			4,Paul,35,522.4
*/
    }

}