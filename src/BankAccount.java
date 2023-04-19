import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BankAccount {
    private String accountNumber;
    private String accountType;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public int getFileIndex() {
        return fileIndex;
    }

    private int fileIndex = 0;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    private String accountName;

    public BankAccount(AccountHolder accountHolder, String accountNumber, String accountType, int index) {
        this.accountName = accountHolder.getFullName();
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.fileIndex = index;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public ArrayList<Transaction> getSortedDescendingTransactions() {
        ArrayList<Transaction> toSort = (ArrayList<Transaction>) this.getTransactions().clone();
        Collections.sort(toSort, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.getTransactionDate().compareTo(o1.getTransactionDate());
            }
        });
        return toSort;
    }

    public float getCurrentBalance() {
        ArrayList<Transaction> sortedDescendingTransactions = getSortedDescendingTransactions();
        if (sortedDescendingTransactions.size() > 0) {
            Transaction tail = sortedDescendingTransactions.remove(0);
            return tail.getEndingBalance();
        } else {
            return 0;
        }
    }
}
