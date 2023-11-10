package PLL;
import javax.swing.*;
import BLL.PoemFile_FacadeBLL_Implementation;
import BLL.PoemFileBLL;
import BLL.PoemFile_FacadeBLL;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoemFileUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable poemTable;
    private JButton openButton;
    private JButton submitButton;

    private PoemFile_FacadeBLL poemFile_FacadeBLL; // Facade Business Layer instance

    public PoemFileUI() {
        tableModel = new DefaultTableModel(new String[]{"Verse", "Title", "Misra #1", "Misra #2"}, 0);
        poemTable = new JTable(tableModel);
        openButton = new JButton("Open File");
        submitButton = new JButton("Submit");

        poemFile_FacadeBLL = new PoemFile_FacadeBLL_Implementation(); // Create Business Layer instance

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    tableModel = poemFile_FacadeBLL.parseCSV_(filePath); // Use Business Layer
                    poemTable.setModel(tableModel);
                    submitButton.setEnabled(true);
                    poemFile_FacadeBLL.setTableModel_(tableModel);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableModel != null) {
                	poemFile_FacadeBLL.submitPoemDataToDatabase_(); // Use Business Layer
                    tableModel.setRowCount(0);
                    submitButton.setEnabled(false);
                }
            }
        });

        submitButton.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(poemTable);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(openButton);
        buttonPanel.add(submitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PoemFileUI();
            }
        });
    }
}
