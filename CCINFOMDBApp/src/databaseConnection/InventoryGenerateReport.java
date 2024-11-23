package databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryGenerateReport {

	// updateInventoryForTheDay() -> update shelves and inventory table's dates to today
	
	public static String showReport() {
		
		StringBuilder report = new StringBuilder();
		
		String updateInventoryDateQuery = "UPDATE inventory SET date_updated = CURRENT_DATE()";
		String updateShelvesDateQuery = "UPDATE inventory_shelves SET last_updated = CURRENT_DATE()";
		
		String generateReportQuery = "SELECT p.product_id, p.product_name, p.product_category, s.quantity_on_shelf, i.quantity_in_stock, "
				+ "(s.quantity_on_shelf + i.quantity_in_stock) AS total_inventory, i.date_updated "
				+ "FROM products p JOIN inventory i ON p.product_id = i.product_id "
				+ "JOIN inventory_shelves s ON s.product_id = p.product_id "
				+ "WHERE DATE(i.date_updated) = CURRENT_DATE();";
		
		// update inventory to today first
		try (Connection connection = SQLConnection.getConnection()) {
	    	
	    	try(PreparedStatement preparedStatement1 = connection.prepareStatement(updateInventoryDateQuery);
	    		PreparedStatement preparedStatement2 = connection.prepareStatement(updateShelvesDateQuery)) {
	    		
	    		preparedStatement1.executeUpdate();
	    		preparedStatement2.executeUpdate();
	    	}
	    	
	    	try(PreparedStatement preparedStatement3 = connection.prepareStatement(generateReportQuery);
	    		ResultSet resultSet = preparedStatement3.executeQuery()) {
	    		
	    		// can move this title to GUI later
	    		report.append("Inventory Report for ").append(java.time.LocalDate.now()).append("\n\n");
	    		
	    		report.append(String.format("%-15s %-35s %-25s %-25s %-25s %-20s %-20s%n", 
                        "Product ID", "Name", "Category", 
                        "Shelf Quantity", "Inventory Quantity", "Total Quantity", "Date Updated"));

				while (resultSet.next()) {
				report.append(String.format("%-15s %-35s %-25s %-25d %-25d %-20d %-20s%n",
                            resultSet.getString("product_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_category"),
                            resultSet.getInt("quantity_on_shelf"),
                            resultSet.getInt("quantity_in_stock"),
                            resultSet.getInt("total_inventory"),
                            resultSet.getDate("date_updated").toString()));
				}
	    	}
	    	
	        
	            
	    } catch (SQLException e) {
	    	report.append("Database Error: ").append(e.getMessage());
	    }
		
		return report.toString();
	}
		
}
