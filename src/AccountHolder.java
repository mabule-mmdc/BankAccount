import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHolderId() {
        return holderId;
    }

    private String firstName;
    private String lastName;
    private String holderId;

    public int getFileIndex() {
        return fileIndex;
    }

    private int fileIndex = 0;

    private List<String[]> accounts;
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public AccountHolder(String firstName, String lastName, String holderId, List<String[]> accounts, int index) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.holderId = holderId;
        this.accounts = accounts;
        this.fileIndex = index;

        for(int i = 0; i < this.accounts.size(); i++) {
            if (holderId.matches(this.accounts.get(i)[0])) {
                this.bankAccounts.add(new BankAccount(this, this.accounts.get(i)[1], this.accounts.get(i)[2], i));
            }
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }
}
