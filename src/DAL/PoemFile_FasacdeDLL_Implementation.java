package DAL;

import javax.swing.table.DefaultTableModel;

public class PoemFile_FasacdeDLL_Implementation implements PoemFile_FacadeDLL {

	PoemFileDAL poemFileDAL = new PoemFileDAL();
	@Override
	public void submitToDatabase_(DefaultTableModel tableModel) {
		poemFileDAL.submitToDatabase(tableModel);
	}

}
