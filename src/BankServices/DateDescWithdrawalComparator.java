package BankServices;

import java.util.Comparator;

public class DateDescWithdrawalComparator implements Comparator<Withdrawal> {
    @Override
    public int compare(Withdrawal o1, Withdrawal o2) {
        return o1.getDate() - o2.getDate();
    }
}
