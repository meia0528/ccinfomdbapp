package databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierOrders {
    //This class has transactions a, c, and d for Suppliers

    public String orderNewProduct(int product_id, String product_name, double purchase_price, double selling_price, String product_category, int supplier_id, int quantity_added){
        try {
            Connection conn = SQLConnection.getConnection();
            System.out.println("Connection Successful");

            //check if product exists
            String checkIfProductExists = "SELECT COUNT(*) FROM products WHERE product_id = ?;";
            try (PreparedStatement supplierExists = conn.prepareStatement(checkIfProductExists)){
                supplierExists.setInt(1, product_id);
                ResultSet rst = supplierExists.executeQuery();

                if (rst.next()){
                    int productCount = rst.getInt(1);
                    if (productCount == 0)
                        return "Product Already Exists";
                }
            }

            //check if supplier exists
            String checkIfSupplierExists = "SELECT COUNT(*) FROM supplier WHERE supplier_id = ?;";
            try (PreparedStatement supplierExists = conn.prepareStatement(checkIfSupplierExists)){
                supplierExists.setInt(1, supplier_id);
                ResultSet rst = supplierExists.executeQuery();

                if (rst.next()){
                    int supplierCount = rst.getInt(1);
                    if (supplierCount == 0)
                        return "Supplier Not Found, Please provide a valid supplier ID";
                }
            }

            PreparedStatement psmt = conn.prepareStatement("INSERT INTO products " +
                    "VALUES(?, ?, ?, ?, ?, ?)");
            psmt.setInt(1, product_id);
            psmt.setString(2, product_name);
            psmt.setDouble(3, purchase_price);
            psmt.setDouble(4, selling_price);
            psmt.setString(5, product_category);
            psmt.setInt(6, supplier_id);
            psmt.executeUpdate();
            psmt.close();

            PreparedStatement psmt2 = conn.prepareStatement("INSERT INTO inventory " +
                    "VALUES(?, ?, NOW())");
            psmt2.setInt(1, product_id);
            psmt2.setInt(2, quantity_added);

            psmt2.close();
            conn.close();
            return "Product Successfully Added!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    public String orderExistingProduct(int product_id, int amountOrdered){
        try{
            Connection conn = SQLConnection.getConnection();
            System.out.println("Connection Successful");

            //check if product exists
            String checkIfProductExists = "SELECT COUNT(*) FROM products WHERE product_id = ?;";
            try (PreparedStatement supplierExists = conn.prepareStatement(checkIfProductExists)){
                supplierExists.setInt(1, product_id);
                ResultSet rst = supplierExists.executeQuery();

                if (rst.next()){
                    int productCount = rst.getInt(1);
                    if (productCount == 0)
                        return "Product Not Found, Please provide an existing product ID";
                }
            }

            PreparedStatement psmt = conn.prepareStatement("UPDATE inventory " +
                    "SET quantity_in_stock = quantity_in_stock + ?, date_updated = NOW() " +
                    "WHERE product_id = ?;");
            psmt.setInt(1, amountOrdered);
            psmt.setInt(2, product_id);
            int result = psmt.executeUpdate();


            psmt.close();
            conn.close();
            if (result > 0)
                return "Order Successful!";
            else
                return "Order Unsuccessful!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
