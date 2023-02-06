package BankServices;

import javax.xml.transform.stax.StAXResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bank {

    private String name;
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Account> deleteAccounts = new ArrayList<>();


    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int createAccount(String name, int date, double initial) {
        Account account = new Account(accounts.size() + 1, date, name, initial);
        accounts.add(account);
        return accounts.size();
    }

    public Account deleteAccount(int code, int date) throws InvalidCode {
        Account account = getAccount(code);
        account.withdraw(date, account.getBalance());
        accounts.remove(account);
        deleteAccounts.add(account);
        return account;
//           deleteAccount() methodi barcha mavjud pullarni yechib olish orqali hisobni yopish imkonini beradi;
//     u argument sifatida hisob raqami va sanani qabul qiladi
//    va yopiq Account ni qaytaradi ; agar hisob raqamlaridan birortasi faol hisob qaydnomasiga mos kelmasa,
//     InvalidCode istisnosi chiqariladi; agar berilgan sana hisobdagi
//    oxirgi operatsiyadan oldin bo'lsa, yopilish oxirgi operatsiya bilan bir xil sanada amalga oshiriladi.
    }

    public Account getAccount(int code) throws InvalidCode{
        try {
            Account account = accounts.get(code - 1);
            return account;
        }catch (Exception e) {
            throw new InvalidCode();
        }
    }

    public void deposit(int code, int date, double value) throws InvalidCode {
        Account account = getAccount(code);
        account.deposit(date, value);
//          deposit() methodi hisob raqamiga omonatni amalga oshirish imkonini beradi; u
//    argument sifatida hisob raqami, omonat sanasi va summasini qabul qiladi;
//    agar raqam biron bir faol hisob qaydnomasiga mos kelmasa, InvalidCode istisnosi tashlanadi;
//     agar ko'rsatilgan sana hisobvaraq bo'yicha oxirgi operatsiyadan
//    oldin bo'lsa, depozit oxirgi operatsiya bilan bir xil sanada amalga oshiriladi.
    }

    public void withdraw(int code, int date, double value) throws InvalidCode, InvalidValue {
        Account account = getAccount(code);
        if (value > account.getBalance()) throw new InvalidValue();
        account.withdraw(date, value);
    }

//        withdraw() methodi hisobdan pul yechib olish imkonini beradi; u argument sifatida hisob raqamini,
//     yechib olingan sanani va yechib olinadigan summani qabul qiladi;
//    agar raqam biron bir faol hisob qaydnomasiga mos kelmasa, InvalidCode istisnosi chiqariladi;
//     agar berilgan summa hisobdagi qoldiqdan katta bo'lsa,
//    InvalidValue istesnosi tashlanadi; agar berilgan sana hisobdagi oxirgi operatsiyadan oldin bo‘lsa,
//     yechib olish oxirgi operatsiya bilan bir xil sanada amalga oshiriladi.


    public void transfer(int fromCode, int toCode, int date, double value) throws InvalidCode, InvalidValue {
        withdraw(fromCode, date, value);
        deposit(toCode, date, value);
//    transfer() methodi xuddi shu bankning boshqa hisob raqamiga bank o'tkazmasini amalga oshirish imkonini beradi; u
//    argument sifatida jo'natuvchi hisob raqamini,
//    qabul qiluvchi hisob raqamini, sana va summani qabul qiladi; agar hisob raqamlaridan birortasi faol hisob
//    qaydnomasiga mos kelmasa,
//    InvalidCode istisnosi chiqariladi;
//    agar berilgan summa manba hisobidagi balansdan katta bo'lsa, InvalidValue istisnosi tashlanadi;
//    jo'natuvchi va qabul qiluvchi hisobvaraqlar bo'yicha operatsiyalarni
//    amalga oshirish sanalari yuqorida keltirilgan mezonlar bilan belgilanadi;
//    har qanday holatda ham qabul qiluvchi hisob bo'yicha operatsiya sanasi jo'natuvchi
//    hisobidagi sanadan kattaroq yoki teng bo'lishi kerak.
    }

    public double getTotalDeposit() {
        double sum = 0;
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            sum += account.getBalance();
        }
        return sum;
//        getTotalDeposit() hozirda bankka qo'yilgan pulning umumiy miqdorini qaytaradi (barcha hisoblardagi qoldiqlar yig'indisi);
    }


    public List<Account> getAccounts() {
        sortByCodeAsc(accounts);
        return accounts;
//     getAccounts() hisob raqamining o'sishi bo'yicha tartiblangan barcha faol hisoblar ro'yxatini qaytaradi;
    }

    public List<Account> getZeroAccounts() {
        return deleteAccounts;
    }

    public List<Account> getAccountsByBalance(double low, double high) {
        List<Account> accountsByBalance = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getBalance() >= low && account.getBalance() <= high) {
                accountsByBalance.add(account);
            }
        }
        sortByBalanceDesc(accountsByBalance);
        return accountsByBalance;
//   getAccountsByBalance() summalar diapazonini qabul qiladi va qoldiqlar kamayishi bo‘yicha tartiblangan berilgan
//    diapazondagi balansga ega faol hisoblar
//     ro‘yxatini qaytaradi;
    }

    public long getNumberHigher(double min) {
        long count = 0;
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (account.getBalance() >= min) {
                count++;
            }
        }
        return count;
//       getNumberHigher() summani qabul qiladi va balansi berilgan summadan kam bo'lmagan hisoblar sonini qaytaradi.
    }

    private static void sortByCodeAsc(List<Account> accounts) {
        for (int i = 0; i < accounts.size() - 1; i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < accounts.size(); j++) {
                Account account1 = accounts.get(j);
                Account account2 = accounts.get(currentMinIndex);
                if (account1.getCode() < account2.getCode()) {
                    currentMinIndex = j;
                }
            }
            if (i != currentMinIndex) {
                Account temp = accounts.get(i);
                accounts.set(i, accounts.get(currentMinIndex));
                accounts.set(currentMinIndex, temp);
            }
        }
    }
        private void sortByBalanceDesc (List < Account > accounts) {
            for (int i = 0; i < accounts.size() - 1; i++) {
                int currentMinIndex = i;
                for (int j = i + 1; j < accounts.size(); j++) {
                    if (accounts.get(j).getBalance() > accounts.get(currentMinIndex).getBalance()) {
                        currentMinIndex = j;
                    }
                }
                if (i != currentMinIndex) {
                    Account temp = accounts.get(i);
                    accounts.set(i, accounts.get(currentMinIndex));
                    accounts.set(currentMinIndex, temp);
                }
            }
        }
    }

