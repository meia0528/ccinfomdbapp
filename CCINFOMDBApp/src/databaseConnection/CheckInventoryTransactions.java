package databaseConnection;

import java.sql.*;

public class CheckInventoryTransactions {
	
	// b. Recording and checking if products are still available in inventory
	// type in product_id in GUI
	// if not found, product_id does not exist!
	// found == display the stocks and message "Available!"
	
	// e. Handling low stocks and necessity of reordering products to restock [Low Stocks]
	
	public static String checkInventory(int productID){
		
		String result = "Sorry! Product does not exist!";
		
		try (Connection connection = SQLConnection.getConnection()) {
            // Check if the product exists in the inventory table
            String query = "SELECT COUNT(*) FROM inventory WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        result = "Product exists!";
                    }
                }
            }
        } catch (SQLException e) {
            result = "Database Error: " + e.getMessage();
        }

        return result; // returns message that indicates whether the product exists or not
	}
	
	// getProductName()
	public static String getProductName(int productID) {
		
		String productName = "";
		
		try (Connection connection = SQLConnection.getConnection()){
			
			String query = "SELECT product_name FROM products WHERE product_id = ?";
			
			// prepared statement and result set 
			// then productName = whatever received from database
			 try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, productID);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        productName = resultSet.getString("product_name");
	                    }
	                }
	            }
			
		} catch (SQLException e) {
			productName = "Database Error: " + e.getMessage();
		}
		
		return productName;
	}
	
	
	// getProductCategory()
	public static String getProductCategory(int productID) {
			
			String productCategory = "";
			
			try (Connection connection = SQLConnection.getConnection()){
				
				String query = "SELECT product_category FROM products WHERE product_id = ?";
				
				 try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		                preparedStatement.setInt(1, productID);
	
		                try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                    if (resultSet.next()) {
		                    	productCategory = resultSet.getString("product_category");
		                    }
		                }
		            }
				
			} catch (SQLException e) {
				productCategory = "Database Error: " + e.getMessage();
			}
			
			return productCategory;
	}
	
		
	// getQuantityOnStock()
	public static int getQuantityOnStock(int productID) {
		
		int quantityOnStock = 0;
		
		try (Connection connection = SQLConnection.getConnection()){
			
			String query = "SELECT quantity_in_stock FROM inventory WHERE product_id = ?";
			
			 try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, productID);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                    	quantityOnStock = resultSet.getInt("quantity_in_stock");
	                    }
	                }
	            }
			
		} catch (SQLException e) {
			System.err.println("Database Error: " + e.getMessage());
		}
		
		return quantityOnStock;
	}
	
	
	
	
	// Transaction e
	
	public static String getlowStocks() {
		
		StringBuilder result = new StringBuilder(); 
		boolean lowStock = false;
		
		try (Connection connection = SQLConnection.getConnection()){
		 	String query = "SELECT p.product_id, p.product_name, i.quantity_in_stock, s.supplier_name, s.contact_number FROM inventory i "
		 			+ "JOIN products p ON i.product_id = p.product_id "
		 			+ "JOIN supplier s ON p.supplier_id = s.supplier_id "
		 			+ "WHERE i.quantity_in_stock < 50; "; // TEST: QUANTITY HERE
		 	
		 	try(PreparedStatement preparedStatement = connection.prepareStatement(query);
		        ResultSet rs = preparedStatement.executeQuery()) {
		 		
			 		while (rs.next()) {
		                lowStock = true;
		                
		                // append info of low stocks here
		                // else "Inventory is all good!" -> if this is the case return this to TextArea in GUI
		                result.append(String.format("Product ID: %d\t     Product Name: %s\t    Qty in Stock: %d\t      Supplier: %s\tContact: %s\t\n",
		                        rs.getInt("product_id"),
		                        rs.getString("product_name"),
		                        rs.getInt("quantity_in_stock"),
		                        rs.getString("supplier_name"),
		                        rs.getString("contact_number")));
			 		}
			 		
			 		if (lowStock == false) 
	                    result.append("Inventory is all good!");
		 		
		 		}
	
		 	
		
		} catch (SQLException e) {
			result.append("Database Error: ").append(e.getMessage());
		}
		
		return result.toString();
	}
	
	public static String restock(int productID, int quantityToStock) {
		String message;
		String query = "UPDATE inventory SET quantity_in_stock = quantity_in_stock + ?, date_updated = NOW() WHERE product_id = ?";
		
		try(Connection connection = SQLConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			
			preparedStatement.setInt(1, quantityToStock);
			preparedStatement.setInt(2, productID);
			
			int rowsUpdated = preparedStatement.executeUpdate();
			
			if (rowsUpdated > 0)
				message = "Contacted supplier! Product is now restocked. Please check inventory.";
			else
				message = "Failed to contact supplier. Product is invalid/not restocked.";
			
		} catch (SQLException e) {
			message = "Database Error: " + e.getMessage();
		}
	
		return message;
	}

	
	
} // end of class 

