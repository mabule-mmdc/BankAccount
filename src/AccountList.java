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
import java.util.ArrayList;
import java.util.List;

public class AccountList {
    public JPanel mainPanel;
    private JLabel accountDetailsHeader;
    private JTable accountsList;
    private JScrollPane tableScroll;

    private String filePath;

    public AccountList() {

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
        accountsList.addMouseListener(new MouseAdapter() {
        });
    }

    public void setFilePath(String fPath) {
        this.filePath = fPath;
        loadFileFromFilePath();
    }

    public void loadFileFromFilePath() {
        String delimiter = ",";
        String[] line = new String[19];
        String[] columnHeadersFromData = {};
        int lineNumber = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
            CSVReader csvReader = new CSVReaderBuilder(br).withSkipLines(1).build();

            String[] columns = {
                    "Account Number",
                    "Account Name",
                    "Account Type",
                    "Balance"
            };

            List<String[]> data = csvReader.readAll();

            DefaultTableModel model = new DefaultTableModel(columns, 0);

            for (int i = 0; i < data.size(); i++) {
                model.addRow(new String[]{data.get(i)[2], data.get(i)[0] + " " + data.get(i)[1], data.get(i)[3], data.get(i)[4]});
            }

            accountsList.setModel(model);

            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
