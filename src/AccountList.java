import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AccountList {
    public JPanel mainPanel;
    private JLabel accountDetailsHeader;
    private JList accountsList;

    private String filePath;

    public void setFilePath(String fPath) {
        this.filePath = fPath;
        loadFileFromFilePath();
    }

    public void loadFileFromFilePath() {
        String delimiter = ",";
        String line = "";
        String[] columnHeadersFromData = {};
        int lineNumber = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();

            while ((line = br.readLine()) != null) {
                lineNumber += 1;

                if (lineNumber == 1) {
                    continue;
                }

                String[] lineData = line.split(delimiter);

                AccountHolder accountHolder = new AccountHolder(lineData[0], lineData[1]);
                BankAccount bankAccount = new BankAccount(accountHolder, Integer.parseInt(lineData[2]));

                bankAccounts.add(bankAccount);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
