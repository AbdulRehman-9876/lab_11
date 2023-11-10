package BLL;
import DAL.PoemFile_FasacdeDLL_Implementation;
import DAL.PoemFile_FacadeDLL;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class PoemFileBLL {
    private DefaultTableModel tableModel;

    public DefaultTableModel parseCSV(String filePath) {
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Verse", "Title", "Misra #1", "Misra #2"}, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String val;
            boolean check = false;
            String misra1 = "";
            String misra2 = "";
            int verseCount = 1;
            String currentTitle = "";

            while ((val = reader.readLine()) != null) {
                if (val.contains("_________")) {
                    check = true;
                } else if (check) {
                    if (val.contains("==========")) {
                        check = false;
                    }
                } else if (val.contains("[")) {
                    currentTitle = val.replaceAll("\\[|\\]", "");
                    verseCount = 1;
                } else {
                    if (val.contains("(")) {
                        val = val.replace("(", "");
                    }
                    if (val.contains(")")) {
                        val = val.replace(")", "");
                        if (verseCount == 1) {
                            Object[] rowData = {verseCount, currentTitle, misra1, misra2};
                            tableModel.addRow(rowData);
                        } else {
                            Object[] rowData = {verseCount, " ", misra1, misra2};
                            tableModel.addRow(rowData);
                        }
                        verseCount++;
                        misra1 = "";
                        misra2 = "";
                    }
                    if (val.contains("...")) {
                        String[] misraLines = val.split("\\.\\.\\.");
                        int misraCount = 1;
                        for (String misraLine : misraLines) {
                            if (misraCount == 1) {
                                misra1 = misraLine.replaceAll("\\[|\\]", "");
                            } else if (misraCount == 2) {
                                misra2 = misraLine.replaceAll("\\[|\\]", "");
                            }
                            misraCount++;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading the file: " + ex.getMessage());
        }
        return tableModel;
    }

    public void submitPoemDataToDatabase() {
        PoemFile_FacadeDLL poemFile_FacadeDll = new PoemFile_FasacdeDLL_Implementation();
        poemFile_FacadeDll.submitToDatabase_(tableModel);
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
}
