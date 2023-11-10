package BLL;
import TransferObject.RootTO;


public interface IBLLFascade extends IRootCRUDBO {
	
	public void createRoot(RootTO root);
	public void updateRoot(RootTO root);
	public void deleteRoot(RootTO root);
}
