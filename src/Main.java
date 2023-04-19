import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFileMenu = new JMenuItem("Open File");
        JMenuItem openFilesMenuItem = new JMenuItem("Open Files");

        fileMenu.add(openFileMenu);
        fileMenu.add(openFilesMenuItem);
        menuBar.add(fileMenu);

        JFrame frame = new JFrame("AccountList");

        frame.setJMenuBar(menuBar);

        LoadedFiles loadedFiles = new LoadedFiles();
        AccountList accountList = new AccountList(loadedFiles);
        FileChooser fileChooserPanel = new FileChooser(loadedFiles);

        Consumer consumer = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean showMainPanel) {
                if (showMainPanel) {
                    frame.setContentPane(accountList.mainPanel);
                    accountList.loadAccountsTable();
                } else {
                    frame.setContentPane(fileChooserPanel.mainPanel);
                }

                frame.pack();

                if (frame.isVisible()) {
                    frame.repaint();
                }
            }
        };

        fileChooserPanel.setRepainter(consumer);

        frame.setPreferredSize(new Dimension(800, 500));
        consumer.accept(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    loadedFiles.setHolderFilePath("/Users/micahbule/Downloads/BankAccount Data - Holders.csv");
                    loadedFiles.setAccountsFilePath("/Users/micahbule/Downloads/BankAccount Data - Accounts.csv");
                    loadedFiles.setTransactionsFilePath("/Users/micahbule/Downloads/BankAccount Data - Transactions.csv");

                    consumer.accept(true);
                }
            }
        });

        frame.setVisible(true);
    }
}