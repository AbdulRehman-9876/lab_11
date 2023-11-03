package BLL;

import DAL.RootCRUDDAO;
	import DAL.DALFascade;
	import TransferObject.RootTO;
import DAL.IRootCRUD;



    public class RootCRUDBO  extends BLLFascade {
		
    	IRootCRUD root1;
		IRootCRUD rootDAO = new RootCRUDDAO(root1);
		DALFascade DAOFas = new DALFascade(rootDAO);

	    public RootCRUDBO(IRootCRUDBO iroot) {
	    	super(iroot);
	    }

	    public void createRoot(RootTO root) {
	        if (root.getRoot().isEmpty()) {
	            // Handle the case when the root is empty by throwing an exception and logging an error.
	            throw new IllegalArgumentException("Root is empty. Cannot create an empty root.");
	        } else {
	        	DAOFas.createRootInDB(root);
	        }
	    }

	    public void updateRoot(RootTO root) {
	        if (root.getRoot().isEmpty()) {
	            // Handle the case when the root is empty by throwing an exception and logging an error.
	            throw new IllegalArgumentException("Root is empty. Cannot update an empty root.");
	        } else {
	            rootDAO.updateRootInDB(root);
	        }
	    }

	    public void deleteRoot(RootTO root) {
	        if (root.getRoot().isEmpty()) {
	            // Handle the case when the root is empty by throwing an exception and logging an error.
	            throw new IllegalArgumentException("Root is empty. Cannot delete an empty root.");
	        } else {
	            rootDAO.deleteRootFromDB(root);
	        }
	    }
	}
	
