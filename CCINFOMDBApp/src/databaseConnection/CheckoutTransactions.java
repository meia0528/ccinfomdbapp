package databaseConnection;

import java.sql.*;

public class CheckoutTransactions {
	
	// a. Recording products as sold with the price and quantity [Checkout]
	// will add a row in sales table, while the entries are received from the GUI
	// c. Updating inventory deduction after sales [Updated Inventory]
	// a and c are sequential
	
	public static String checkout(int productID, int employeeID, int quantityOrdered) {
	    String result = "";
	    String query = "SELECT quantity_on_shelf FROM inventory_shelves WHERE product_id = ?";
	    String checkIfEmployeeExistsQuery = "SELECT COUNT(*) FROM employees WHERE employee_id = ?";

	    try (Connection connection = SQLConnection.getConnection()) {
	    	
	    	// check if employee exists
	    	
	    	try (PreparedStatement employeePreparedStatement = connection.prepareStatement(checkIfEmployeeExistsQuery)) {
	    		employeePreparedStatement.setInt(1, employeeID);
	            ResultSet rs = employeePreparedStatement.executeQuery();

	            if (rs.next()) {
	                int employeeCount = rs.getInt(1);
	                if (employeeCount == 0) {
	                    return "Employee not found! Please provide a valid employee ID.";
	                }
	            }
	        }
	    	
	    	// check first if enough inventory on shelves
	    	
	        try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {
	        	preparedStatement1.setInt(1, productID);
	            ResultSet rs = preparedStatement1.executeQuery();

	            if (rs.next()) {
	                int availableOnShelf = rs.getInt("quantity_on_shelf");
	                if (availableOnShelf < quantityOrdered)
	                    return "Only " + availableOnShelf + " remaining on shelf. Please ask an employee to restock if possible!";
	            } 
	            
	            else 
	                return "Product not found on shelves!";
	            
	        }
	        
	        // transaction a

	        String updateSalesQuery = "INSERT INTO sales (product_id, noted_by_employee_id, sale_date, quantity_ordered) " +
	                                 "VALUES (?, ?, NOW(), ?)";
	        try (PreparedStatement insertStmt = connection.prepareStatement(updateSalesQuery)) {
	            insertStmt.setInt(1, productID);
	            insertStmt.setInt(2, employeeID);
	            insertStmt.setInt(3, quantityOrdered);
	            insertStmt.executeUpdate();
	        }
	        
	        result = "Checkout completed successfully!";
	        
	    } catch (SQLException e) {
	        result = "Database Error: " + e.getMessage();
	    }

	    return result;
	}
	
	// update shelves inventory - transaction c
	public static String deductShelvesInventory(int productID, int quantityOrdered) {
		
		String result = "";
		String query = "UPDATE inventory_shelves SET quantity_on_shelf = quantity_on_shelf - ?, last_updated = NOW()" +
                "WHERE product_id = ?";
		
		try(Connection connection = SQLConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, quantityOrdered);
			preparedStatement.setInt(2, productID);
			
			int success = preparedStatement.executeUpdate();
			
			if (success > 0)
				result = "Inventory updated successfully!";
			else
				result = "Failed to update inventory";
			
		} catch (SQLException e) {
			result = "Database Error: " + e.getMessage();
		}
		
		return result;
	}

}