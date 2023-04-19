import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadedFiles {
    public String getHolderFilePath() {
        return holderFilePath;
    }

    public void setHolderFilePath(String holderFilePath) {
        this.holderFilePath = holderFilePath;
    }

    public String getAccountsFilePath() {
        return accountsFilePath;
    }

    public void setAccountsFilePath(String accountsFilePath) {
        this.accountsFilePath = accountsFilePath;
    }

    public String getTransactionsFilePath() {
        return transactionsFilePath;
    }

    public void setTransactionsFilePath(String transactionsFilePath) {
        this.transactionsFilePath = transactionsFilePath;
    }

    private String holderFilePath = "";
    private String accountsFilePath = "";
    private String transactionsFilePath = "";

    public CSVReader readFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        CSVReader csvReader = new CSVReaderBuilder(br).withSkipLines(1).build();

        return csvReader;
    }
}
