package BLL;

import javax.swing.table.DefaultTableModel;

public class PoemFile_FacadeBLL_Implementation implements PoemFile_FacadeBLL {

    PoemFileBLL poemFileBLL = new PoemFileBLL(); // Instantiate the PoemFileBLL
	
	@Override
	public DefaultTableModel parseCSV_(String filePath) {
		return poemFileBLL.parseCSV(filePath);
	}

	@Override
	public void submitPoemDataToDatabase_() {
		poemFileBLL.submitPoemDataToDatabase();
		
	}

	@Override
	public void setTableModel_(DefaultTableModel tableModel) {
		poemFileBLL.setTableModel(tableModel);
		
	}
	
}
