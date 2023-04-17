import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class AddBankAccount {
    private JPanel addBankAccountPanel;
    private JLabel formLablel;
    private JTextField firstNameField;
    private JTextField accountIDField;
    private JButton addButton;
    private JTextField lastNameField;

    private String fileName = "/Users/micahbule/Downloads/BankAccount Data - Account Holders.csv";

    public AddBankAccount() {
        addButton.addActionListener(new ActionListener() {
            /** On button click, add new line to CSV */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    /** Open file using CSV reader for reading purposes */
                    BufferedReader br = new BufferedReader(new FileReader("/Users/micahbule/Downloads/BankAccount Data - Account Holders.csv"));
                    CSVReader csvReader = new CSVReaderBuilder(br).build();

                    /** Read entire CSV file and assign to a list */
                    List<String[]> csvContent = csvReader.readAll();

                    /** Add new string array based on form values in the list */
                    csvContent.add(new String[]{firstNameField.getText(), lastNameField.getText(), accountIDField.getText()});

                    csvReader.close();

                    /** Open file using CSV writer for writing purposes */
                    BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/micahbule/Downloads/BankAccount Data - Account Holders.csv"));
                    CSVWriter csvWriter = new CSVWriter(bw);

                    /** Write new line at the end of CSV file */
                    csvWriter.writeAll(csvContent);
                    csvWriter.flush();

                    /** Close the CSV writer and reader */
                    csvWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (CsvException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public String getFileName() {
        return this.fileName;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddBankAccount");
        frame.setContentPane(new AddBankAccount().addBankAccountPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
    }
}
