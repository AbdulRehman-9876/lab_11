package BLL;

import TransferObject.RootTO;

public class BLLFascade implements IBLLFascade {

	
	IRootCRUDBO iroot;
	

	
	public BLLFascade(IRootCRUDBO iroot) {
		this.iroot = iroot;
	}
	

	@Override
	public void createRoot(RootTO root) {
		iroot.createRoot(root);		
	}
	@Override
	public void updateRoot(RootTO root) {
		iroot.updateRoot(root);
		
	}
	@Override
	public void deleteRoot(RootTO root) {
		iroot.deleteRoot(root);
		
	}
}

