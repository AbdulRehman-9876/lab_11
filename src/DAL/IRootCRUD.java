package DAL;

import TransferObject.RootTO;

public interface IRootCRUD {
	public void createRootInDB(RootTO root);
	public void updateRootInDB(RootTO root);
	public void deleteRootFromDB(RootTO root);

}

