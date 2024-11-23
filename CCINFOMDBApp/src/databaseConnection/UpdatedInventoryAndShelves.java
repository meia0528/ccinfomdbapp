package databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatedInventoryAndShelves {

	
	// d. Updating stocks quantity put out in shelves [Updated Shelves Inventory]
	// getLowShelvesStock()
	
	public static String getLowShelvesStock() {
		
		StringBuilder result = new StringBuilder(); 
		boolean lowStockOnShelves = false;

		
		try (Connection connection = SQLConnection.getConnection()){
		 	String query = "SELECT s.shelf_id, s.product_id, p.product_name, s.quantity_on_shelf, i.quantity_in_stock "
		 			+ "FROM inventory_shelves s "
		 			+ "JOIN products p ON s.product_id = p.product_id "
		 			+ "JOIN inventory i ON s.product_id = i.product_id "
		 			+ "WHERE s.quantity_on_shelf < 10;";
		 	
		 	try(PreparedStatement preparedStatement = connection.prepareStatement(query);
		        ResultSet rs = preparedStatement.executeQuery()) {
		 		
		 			result.append(String.format("Loading all shelves with low stocks...\n\n"));
		 			result.append(String.format("%-25s %-25s %-25s %-25s %-25s%n", 
                            "Shelf ID", "Quantity Left", "Inventory Stock", "Product ID", "Product Name"));

					while (rs.next()) {
					    lowStockOnShelves = true;
					
					    result.append(String.format("%-30d %-30d %-32d %-29d %-25s%n",
                                rs.getInt("shelf_id"),
                                rs.getInt("quantity_on_shelf"),
                                rs.getInt("quantity_in_stock"),
                                rs.getInt("product_id"),
                                rs.getString("product_name")));
					}
			 		
			 		if (lowStockOnShelves == false) 
	                    result.append("All shelves have enough stock!");
		 		
		 		}
	
		 	
		
		} catch (SQLException e) {
			result.append("Database Error: ").append(e.getMessage());
		}
		
		return result.toString();
	}
	
	
	
	
	// restockShelves() -> check if enough in inventory 
	
	public static String restockShelves(int productID, int quantityToStock) {
		String message = "";
		
		String checkAvailableInventoryQuery = "SELECT quantity_in_stock FROM inventory WHERE product_id = ?";
		String updateShelvesQuery = "UPDATE inventory_shelves SET quantity_on_shelf = quantity_on_shelf + ?, last_updated = NOW() WHERE product_id = ?";              
		String deductInventoryQuery = "UPDATE inventory SET quantity_in_stock = quantity_in_stock - ?, date_updated = NOW() WHERE product_id = ?";
		
		try (Connection connection = SQLConnection.getConnection();
		         PreparedStatement preparedStatement1 = connection.prepareStatement(checkAvailableInventoryQuery);
		         PreparedStatement preparedStatement2 = connection.prepareStatement(updateShelvesQuery);
		         PreparedStatement preparedStatement3 = connection.prepareStatement(deductInventoryQuery)) {

				preparedStatement1.setInt(1, productID);
		        try (ResultSet rs = preparedStatement1.executeQuery()) {
		            if (rs.next()) {
		            	
		                int availableInventoryStock = rs.getInt("quantity_in_stock");
		                
		                if (availableInventoryStock < quantityToStock) 
		                    message =  "Not enough stock in inventory.";
		                
		            } 
		            else 
		                message = "Product not found in inventory.";
		        }
		        
		        
		        preparedStatement2.setInt(1, quantityToStock);
		        preparedStatement2.setInt(2, productID);
		        int updatedShelves = preparedStatement2.executeUpdate();

		        if (updatedShelves > 0) {
		        	preparedStatement3.setInt(1, quantityToStock);
		        	preparedStatement3.setInt(2, productID);
		            int updatedInventory = preparedStatement3.executeUpdate();

		            if (updatedInventory > 0) 
		                message = "Product restocked to shelves!";
		           else 
		        	   message = "Error in updating inventory. Please check again.";
		            
		        } 
		        else 
		        	message = "Error updating shelves. Please check again.";
		        
		} catch (SQLException e) {
			message = "Database Error: " + e.getMessage();
		}
	
		return message;
	}
	

	
	

}
