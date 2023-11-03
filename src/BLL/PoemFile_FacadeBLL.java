package BLL;

import javax.swing.table.DefaultTableModel;

public interface PoemFile_FacadeBLL {
	 public DefaultTableModel parseCSV_(String filePath);
	 public void submitPoemDataToDatabase_();
	 public void setTableModel_(DefaultTableModel tableModel);

}
