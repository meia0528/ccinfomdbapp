package GUI;

import databaseConnection.SupplierEngagementReport;
import databaseConnection.SupplierOrders;
import databaseConnection.SupplierTransactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierManagementGUI {

    private JFrame supplierFrame;
    private JFrame orderExistingProductFrame;
    private JFrame listOfItemsFromSupplierFrame;
    private JFrame supplierPerformanceReviewsFrame;

    private SupplierTransactions supplierTransactions = new SupplierTransactions();
    private SupplierOrders supplierOrders = new SupplierOrders();

    public SupplierManagementGUI(){
        supplierFrame = new JFrame("Supplier Management System");
        supplierFrame.setSize(800, 700); // let's follow this as standard for the opening so all records are visible
        supplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supplierFrame.setLocationRelativeTo(null);
        supplierFrame.setResizable(false);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(0, 120, 800, 580);
        supplierFrame.add(buttonPanel);

        JButton orderNewProductButton = new JButton("Order New Product From Supplier");
        JButton orderExistingProductButton = new JButton("Order Existing Product From Supplier");
        JButton retrieveListOfItemsFromSupplierButton = new JButton("Retrieve List Of Items From Supplier");
        JButton checkPerformanceReviewsButton = new JButton("Check Performance Reviews");
        JButton generateReportButton = new JButton("Generate Supplier Report");

        Dimension buttonSize = new Dimension(350, 65);
        orderNewProductButton.setBounds(225, 35, buttonSize.width, buttonSize.height);
        orderExistingProductButton.setBounds(225, 135, buttonSize.width, buttonSize.height);
        retrieveListOfItemsFromSupplierButton.setBounds(225, 235, buttonSize.width, buttonSize.height);
        checkPerformanceReviewsButton.setBounds(225, 335, buttonSize.width, buttonSize.height);
        generateReportButton.setBounds(225, 435, buttonSize.width, buttonSize.height);

        // formatting - font & font color
        orderNewProductButton.setFont(new Font("Arial", Font.BOLD, 16));
        orderNewProductButton.setForeground(Color.WHITE);

        orderExistingProductButton.setFont(new Font("Arial", Font.BOLD, 16));
        orderExistingProductButton.setForeground(Color.WHITE);

        retrieveListOfItemsFromSupplierButton.setFont(new Font("Arial", Font.BOLD, 16));
        retrieveListOfItemsFromSupplierButton.setForeground(Color.WHITE);

        checkPerformanceReviewsButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkPerformanceReviewsButton.setForeground(Color.WHITE);

        generateReportButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateReportButton.setForeground(Color.WHITE);


        // color of buttons - 7/11 colors
        orderNewProductButton.setBackground(new Color(0xF48115));
        orderExistingProductButton.setBackground(new Color(0x008163));
        retrieveListOfItemsFromSupplierButton.setBackground(new Color(0xF48115));
        checkPerformanceReviewsButton.setBackground(new Color(0x008163));
        generateReportButton.setBackground(new Color(0xEE2526));

        // formatting
        buttonPanel.add(orderNewProductButton);
        buttonPanel.add(orderExistingProductButton);
        buttonPanel.add(retrieveListOfItemsFromSupplierButton);
        buttonPanel.add(checkPerformanceReviewsButton);
        buttonPanel.add(generateReportButton);

        // Action Listeners
        orderExistingProductButton.addActionListener(e -> orderExistingProductGUI());
        retrieveListOfItemsFromSupplierButton.addActionListener(e -> listOfItemsFromSupplierGUI());
        checkPerformanceReviewsButton.addActionListener(e -> supplierPerformanceReviewsGUI());

        supplierFrame.setVisible(true);
    }



    private void orderExistingProductGUI(){
        orderExistingProductFrame = new JFrame("Order Existing Product From Supplier");
        orderExistingProductFrame.setSize(800, 700);
        orderExistingProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderExistingProductFrame.setLocationRelativeTo(null);
        orderExistingProductFrame.setResizable(false);
        orderExistingProductFrame.setLayout(null);


        JLabel productIDLabel = new JLabel("Enter Product ID: ");
        productIDLabel.setBounds(30, 30, 150, 25);
        orderExistingProductFrame.add(productIDLabel);

        JTextField productIDTextField = new JTextField();
        productIDTextField.setBounds(200, 30, 200, 25);
        orderExistingProductFrame.add(productIDTextField);

        JLabel quantityLabel = new JLabel("Enter The Amount You Want To Order: ");
        quantityLabel.setBounds(30, 30, 150, 25);
        orderExistingProductFrame.add(quantityLabel);

        JTextField quantityTextField = new JTextField();
        quantityTextField.setBounds(200, 30, 200, 25);
        orderExistingProductFrame.add(quantityTextField);

        JTextArea successMessageArea = new JTextArea();
        successMessageArea.setBounds(30, 225, 400, 40);
        successMessageArea.setEditable(false);
        successMessageArea.setFont(new Font("Arial", Font.PLAIN, 14));
        orderExistingProductFrame.add(successMessageArea);

        JButton addProductButton = new JButton("Order");
        addProductButton.setBounds(30, 285, 200, 50);
        addProductButton.setFont(new Font("Arial", Font.BOLD, 14));
        addProductButton.setForeground(Color.WHITE);
        addProductButton.setBackground(new Color(0x008163));
        orderExistingProductFrame.add(addProductButton);

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int product_id = Integer.parseInt(productIDTextField.getText());
                    int amountOrdered = Integer.parseInt(quantityTextField.getText());
                    String result = supplierOrders.orderExistingProduct(product_id, amountOrdered);

                    successMessageArea.setText(result);

                } catch (NumberFormatException ex) {
                    successMessageArea.setText("Please enter valid inputs.");
                }
            }
        });
        orderExistingProductFrame.setVisible(true);
    }

    private void listOfItemsFromSupplierGUI(){
        listOfItemsFromSupplierFrame = new JFrame("List of Items From Supplier");
        listOfItemsFromSupplierFrame.setSize(800, 700);
        listOfItemsFromSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listOfItemsFromSupplierFrame.setLocationRelativeTo(null);
        listOfItemsFromSupplierFrame.setResizable(false);
        listOfItemsFromSupplierFrame.setLayout(null);


        JLabel supplierIDLabel = new JLabel("Enter Supplier ID: ");
        supplierIDLabel.setBounds(30, 30, 150, 25);
        listOfItemsFromSupplierFrame.add(supplierIDLabel);

        JTextField supplierIDTextField = new JTextField();
        supplierIDTextField.setBounds(200, 30, 200, 25);
        listOfItemsFromSupplierFrame.add(supplierIDTextField);

        JTextArea successMessageArea = new JTextArea();
        successMessageArea.setBounds(30, 225, 400, 40);
        successMessageArea.setEditable(false);
        successMessageArea.setFont(new Font("Arial", Font.PLAIN, 14));
        listOfItemsFromSupplierFrame.add(successMessageArea);

        JButton getItemsButton = new JButton("Get Items");
        getItemsButton.setBounds(30, 285, 200, 50);
        getItemsButton.setFont(new Font("Arial", Font.BOLD, 14));
        getItemsButton.setForeground(Color.WHITE);
        getItemsButton.setBackground(new Color(0x008163));
        listOfItemsFromSupplierFrame.add(getItemsButton);

        getItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int supplier_id = Integer.parseInt(supplierIDTextField.getText());
                String result = supplierTransactions.getItemsList(supplier_id);
                if (result.equals("Item List Created Successfully!")){
                    StringBuilder sb = new StringBuilder();
                    sb.append("Supplier_Name\t" + "Product_ID\t" + "Product_NameD\t" + "Total_Quantity\t" + "\n");
                    for (int i = 0; i < supplierTransactions.supplier_id_list.size(); i++){
                        String supplier_name = supplierTransactions.supplier_name_list.get(i);
                        int product_id = supplierTransactions.product_id_list.get(i);
                        String product_name = supplierTransactions.product_name_list.get(i);
                        int quantity = supplierTransactions.product_total_quantity_list.get(i);

                        String record = supplier_name + "\t" + product_id +"\t" + product_name + "\t" + quantity + "\t" + "\n";
                        sb.append(record);
                    }
                    successMessageArea.setText(sb.toString());
                }
            }
        });
        listOfItemsFromSupplierFrame.setVisible(true);
    }

    private void supplierPerformanceReviewsGUI(){
        supplierPerformanceReviewsFrame = new JFrame("Supplier Performance Reviews");
        supplierPerformanceReviewsFrame.setSize(800, 700);
        supplierPerformanceReviewsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        supplierPerformanceReviewsFrame.setLocationRelativeTo(null);
        supplierPerformanceReviewsFrame.setResizable(false);
        supplierPerformanceReviewsFrame.setLayout(null);

        JLabel supplierIDLabel = new JLabel("Enter Supplier ID: ");
        supplierIDLabel.setBounds(30, 30, 150, 25);
        supplierPerformanceReviewsFrame.add(supplierIDLabel);

        JTextField supplierIDTextField = new JTextField();
        supplierIDTextField.setBounds(200, 30, 200, 25);
        supplierPerformanceReviewsFrame.add(supplierIDTextField);

        JLabel supplierNameLabel = new JLabel("Supplier Name: ");
        supplierNameLabel.setBounds(30, 80, 350, 25);
        supplierPerformanceReviewsFrame.add(supplierNameLabel);

        JLabel performanceRatingLabel = new JLabel("Performance Rating: ");
        performanceRatingLabel.setBounds(30, 130, 350, 25);
        supplierPerformanceReviewsFrame.add(performanceRatingLabel);

        JTextArea successMessageArea = new JTextArea();
        successMessageArea.setBounds(30, 225, 400, 40);
        successMessageArea.setEditable(false);
        successMessageArea.setFont(new Font("Arial", Font.PLAIN, 14));
        supplierPerformanceReviewsFrame.add(successMessageArea);

        JButton checkReviewButton = new JButton("Check Performance Reviews");
        checkReviewButton.setBounds(30, 285, 200, 50);
        checkReviewButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkReviewButton.setForeground(Color.WHITE);
        checkReviewButton.setBackground(new Color(0x008163));
        supplierPerformanceReviewsFrame.add(checkReviewButton);


        checkReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int supplier_id = Integer.parseInt(supplierIDTextField.getText());
                    String result = supplierTransactions.getPerformanceReviews(supplier_id);

                    if (result.equals("Performance Review Generated!")) {
                        String supplierName = supplierTransactions.supplier_name;
                        Double performanceRating = supplierTransactions.supplier_performance_rating;

                        supplierNameLabel.setText("Supplier Name:                           " + supplierName);
                        performanceRatingLabel.setText("Performance Rating:                                    " + performanceRating);

                        successMessageArea.setText(result);

                    } else {
                        supplierNameLabel.setText("Supplier Name:                           " + "N/A");
                        performanceRatingLabel.setText("Performance Rating:                                    " + "N/A");

                        successMessageArea.setText(result);
                    }

                } catch (NumberFormatException ex) {
                    successMessageArea.setText("Please enter a valid supplier ID.");
                }
            }
        });
        supplierPerformanceReviewsFrame.setVisible(true);
    }

}
