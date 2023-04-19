import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {
    public Date getTransactionDate() {
        return transactionDate;
    }

    private Date transactionDate;
    private String transactionType;
    private float transactionAmount;
    private float startingBalance;

    public float getEndingBalance() {
        return endingBalance;
    }

    private float endingBalance;
    private String accountNumber;
    public Transaction(BankAccount bankAccount, String transactionDate, String transactionType, String transactionAmount, String startingBalance, String endingBalance) throws ParseException {
        this.accountNumber = bankAccount.getAccountNumber();
        this.transactionType = transactionType;
        this.transactionAmount = Integer.parseInt(transactionAmount);
        this.startingBalance = Integer.parseInt(startingBalance);
        this.endingBalance = Integer.parseInt(endingBalance);

        SimpleDateFormat dtFormat = new SimpleDateFormat("mm/dd/YYYY");

        this.transactionDate = dtFormat.parse(transactionDate);
    }
}
