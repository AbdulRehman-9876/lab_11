package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import TransferObject.RootTO;

public class RootCRUDDAO extends DALFascade {
	
		IRootCRUD iRoot;
		public RootCRUDDAO(IRootCRUD iRoot) {
		super(iRoot);
		this.iRoot = iRoot;
	}

		private static final String DB_URL = "jdbc:mysql://localhost:3306/rootscrud"; 
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "";
		

	        public void createRootInDB(RootTO rootTo) {
	        	try {
	    			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    			java.sql.Statement statement = connection.createStatement();
	    			
	    			if (connection != null) {
	    				System.out.println("Connected to Database");
	    				
	    				String createBookSQLSquery = "INSERT INTO roots(root_content) VALUES('" + rootTo.getRoot() + "')";
	    				int rowsAffected = statement.executeUpdate(createBookSQLSquery);
	    				
	    				System.out.println("Rows affected: " + rowsAffected);
	    			}
	    			
	    			statement.close();
	    			connection.close();
	    		} 
	    		catch(SQLException e) {
	    			System.err.println("Connection error: " + e.getMessage());
	    		}
	    	}
	    	

	     // Function to Update the Book Record
	    	public void updateRootInDB(RootTO root) {
	    		
	    		try {
	    			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    			java.sql.Statement statement = connection.createStatement();
	    			
	    			if (connection != null) {
	    				System.out.println("Connected to Database");
	    				
	    				String updateBookSQLSquery = "UPDATE books SET root = '" + root.getRoot() + "' WHERE id = " + root.getId();     
	    				int rowsAffected = statement.executeUpdate(updateBookSQLSquery);
	    				
	    				System.out.println("Rows affected: " + rowsAffected);
	    			}
	    			
	    			statement.close();
	    			connection.close();
	    		} 
	    		catch(SQLException e) {
	    			System.err.println("Connection error: " + e.getMessage());
	    		}
	    	}
	    	

	    	// Function for deleting Specific Book from DB
	    	public void deleteRootFromDB(RootTO book) {
	    		
	    		try {
	    			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	    			java.sql.Statement statement = connection.createStatement();
	    			
	    			if (connection != null) {
	    				System.out.println("Connected to Database");
	    				
	    				String deleteBookSQLSquery = "DELETE FROM roots WHERE id = " + book.getId() ;  
	    				int rowsAffected = statement.executeUpdate(deleteBookSQLSquery);
	    				
	    				System.out.println("Rows affected: " + rowsAffected);
	    			}
	    			
	    			statement.close();
	    			connection.close();
	    		} 
	    		catch(SQLException e) {
	    			System.err.println("Connection error: " + e.getMessage());
	    		}
	    	}
	    }


