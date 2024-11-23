package databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierTransactions {
    //This class contains transactions b and e for Suppliers

    public int supplier_id;
    public String supplier_name;
    public Double supplier_performance_rating;


    public ArrayList<Integer> supplier_id_list = new ArrayList<>();
    public ArrayList<String>  supplier_name_list = new ArrayList<>();
    public ArrayList<Integer> product_id_list = new ArrayList<>();
    public ArrayList<String>  product_name_list = new ArrayList<>();
    public ArrayList<Integer> product_total_quantity_list = new ArrayList<>();



    public String getItemsList(int supplier_id){
        try {
            Connection conn = SQLConnection.getConnection();
            System.out.println("Connection Successful");

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

            //generate the items list
            PreparedStatement pstmt = conn.prepareStatement("SELECT s.supplier_id, s.supplier_name, p.product_id, p.product_name, " +
                    "(i.quantity_in_stock + sh.quantity_on_shelf) AS total_quantity " +
                    "FROM supplier s JOIN products p ON s.supplier_id = p.supplier_id " +
                    "JOIN inventory i ON p.product_id = i.product_id " +
                    "JOIN inventory_shelves sh ON p.product_id = sh.product_id " +
                    "WHERE s.supplier_id = ?;");
            pstmt.setInt(1, supplier_id);
            ResultSet rst = pstmt.executeQuery();

            supplier_id_list.clear();
            supplier_name_list.clear();
            product_id_list.clear();
            product_name_list.clear();
            product_total_quantity_list.clear();

            while (rst.next()){
                supplier_id_list.add(rst.getInt("s.supplier_id"));
                supplier_name_list.add(rst.getString("s.supplier_name"));
                product_id_list.add(rst.getInt("p.product_id"));
                product_name_list.add(rst.getString("p.product_name"));
                product_total_quantity_list.add(rst.getInt("total_quantity"));
            }

            pstmt.close();
            conn.close();
            return "Item List Created Successfully!";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    public String getPerformanceReviews(int supplier_id){
        try {
            Connection conn = SQLConnection.getConnection();
            System.out.println("Connection Successful");

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

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT s.supplier_id, s.supplier_name, sr.performance_ratings " +
                    "FROM supplier s JOIN supplier_ratings sr ON s.supplier_id = sr.supplier_id " +
                    "WHERE s.supplier_id = ?;");
            pstmt.setInt(1, supplier_id);
            ResultSet rst = pstmt.executeQuery();

            while (rst.next()){
                this.supplier_id = rst.getInt("s.supplier_id");
                this.supplier_name = rst.getString("s.supplier_name");
                this.supplier_performance_rating = rst.getDouble("sr.performance_ratings");
            }

            pstmt.close();
            conn.close();
            return "Performance Review Generated!";
        }
        catch (Exception e){
            return e.getMessage();
        }

    }
}
