import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AccountList {
    public JPanel mainPanel;
    private JLabel accountDetailsHeader;
    private JTable accountsList;
    private JScrollPane tableScroll;

    private String filePath;

    private LoadedFiles loadedFiles;

    public AccountList(LoadedFiles loadedFiles) {
        this.loadedFiles = loadedFiles;

        accountsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() % 2 == 0 && !e.isConsumed()) {
                    e.consume();

                    JTable source = (JTable)e.getSource();
                    int row = source.rowAtPoint(e.getPoint());

                    System.out.println("Row pressed is " + row);
                }
            }
        });
    }

    public void loadAccountsTable() {
        try {
            CSVReader holderReader = loadedFiles.readFile(loadedFiles.getHolderFilePath());
            CSVReader accountsReader = loadedFiles.readFile(loadedFiles.getAccountsFilePath());
            CSVReader transactionsReader = loadedFiles.readFile(loadedFiles.getTransactionsFilePath());

            List<String[]> holdersList = holderReader.readAll();
            List<String[]> accounts = accountsReader.readAll();
            List<String[]> transactions = transactionsReader.readAll();
            ArrayList<AccountHolder> accountHolders = new ArrayList<>();
            ArrayList<BankAccount> allBankAccounts = new ArrayList<>();

            for(int i = 0; i < holdersList.size(); i++) {
                accountHolders.add(new AccountHolder(holdersList.get(i)[1], holdersList.get(i)[2], holdersList.get(i)[0], accounts, i));
            }

            for (int i = 0; i < accountHolders.size(); i++) {
                allBankAccounts.addAll(accountHolders.get(i).getBankAccounts());
            }

            for (int i = 0; i < allBankAccounts.size(); i++) {
                BankAccount currentBankAccount = allBankAccounts.get(i);

                for(int j = 0; j < transactions.size(); j++) {
                    String[] currentTransaction = transactions.get(j);

                    if (currentBankAccount.getAccountNumber().matches(currentTransaction[1])) {
                        currentBankAccount.addTransaction(new Transaction(currentBankAccount, currentTransaction[0], currentTransaction[2], currentTransaction[3], currentTransaction[4], currentTransaction[5]));
                    }
                }

                allBankAccounts.set(i, currentBankAccount);
            }

            String[] columns = {
                    "Account Number",
                    "Account Name",
                    "Account Type",
                    "Balance"
            };

            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (int i = 0; i < allBankAccounts.size(); i++) {
                model.addRow(new String[]{allBankAccounts.get(i).getAccountNumber(), allBankAccounts.get(i).getAccountName(), allBankAccounts.get(i).getAccountType(), String.valueOf(allBankAccounts.get(i).getCurrentBalance())});
            }

            accountsList.setModel(model);

            holderReader.close();
            accountsReader.close();
            transactionsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
