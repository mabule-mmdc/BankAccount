import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class FileChooser {
    private JButton holdersChooser;
    private JTextField holdersPath;
    public JPanel mainPanel;
    private JButton accountsChooser;
    private JButton transactionsChooser;
    private JTextField accountsPath;
    private JTextField transactionsPath;

    public void setRepainter(Consumer<Boolean> repainter) {
        this.repainter = repainter;
    }

    private Consumer<Boolean> repainter;

    LoadedFiles loadedFiles;
    public FileChooser(LoadedFiles loadedFiles) {
        this.loadedFiles = loadedFiles;

        holdersChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(mainPanel);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    loadedFiles.setHolderFilePath(path);
                    holdersPath.setText(path);

                    repainter.accept(!loadedFiles.getHolderFilePath().isBlank() && !loadedFiles.getAccountsFilePath().isBlank() && !loadedFiles.getTransactionsFilePath().isBlank());
                }
            }
        });
        accountsChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(mainPanel);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    loadedFiles.setAccountsFilePath(path);
                    accountsPath.setText(path);

                    repainter.accept(!loadedFiles.getHolderFilePath().isBlank() && !loadedFiles.getAccountsFilePath().isBlank() && !loadedFiles.getTransactionsFilePath().isBlank());
                }
            }
        });
        transactionsChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int choice = fileChooser.showOpenDialog(mainPanel);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    loadedFiles.setTransactionsFilePath(path);
                    transactionsPath.setText(path);

                    repainter.accept(!loadedFiles.getHolderFilePath().isBlank() && !loadedFiles.getAccountsFilePath().isBlank() && !loadedFiles.getTransactionsFilePath().isBlank());
                }
            }
        });
    }
}
