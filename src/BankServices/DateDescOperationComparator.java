package BankServices;

import java.util.Comparator;

public class DateDescOperationComparator implements Comparator<Operation> {
    @Override
    public int compare(Operation o1, Operation o2) {
        return o1.getDate() - o2.getDate();
    }
}


