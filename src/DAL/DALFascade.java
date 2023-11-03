package DAL;


import TransferObject.RootTO;

public class DALFascade implements IDALFascade {

	
	private IRootCRUD iroot;
	

	public DALFascade(IRootCRUD iroot) {
		super();
		this.iroot = iroot;
	}
	
	
	
	@Override
	public void createRootInDB(RootTO root) {
		iroot.createRootInDB(root);
	}
	

	@Override
	public void updateRootInDB(RootTO root) {
		iroot.updateRootInDB(root);
		
	}

	@Override
	public void deleteRootFromDB(RootTO root) {
		iroot.deleteRootFromDB(root);
		
	}
}
