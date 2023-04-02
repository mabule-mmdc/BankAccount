import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFileMenu = new JMenuItem("Open File");

        fileMenu.add(openFileMenu);
        menuBar.add(fileMenu);

        JFrame frame = new JFrame("AccountList");

        frame.setJMenuBar(menuBar);

        AccountList accountList = new AccountList();

        openFileMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(accountList.mainPanel);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    accountList.setFilePath(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        frame.setPreferredSize(new Dimension(800, 500));
        frame.setContentPane(accountList.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}