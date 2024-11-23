package GUI;

import databaseConnection.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementGUI {

    private JFrame inventoryButtonsFrame;
    
    private JFrame checkoutFrame;
    private JFrame checkInventoryFrame;
    private JFrame updatedShelvesFrame;
    private JFrame lowStocksFrame;
    
    private JFrame generateReportFrame;
    
    // buttons
	// Recording products as sold with the price and quantity [Checkout]
	// Recording and checking if products are still available in inventory [Check Inventory]
	// Updating inventory deduction after sales [Updated Inventory]
	// Updating stocks quantity put out in shelves [Updated Shelves Inventory]
	// Handling low stocks and necessity of reordering products to restock [Low Stocks]
	// [Generate Report]

	
    public InventoryManagementGUI() {
    	
        inventoryButtonsFrame = new JFrame("Inventory Management System");
        inventoryButtonsFrame.setSize(800, 700);
        inventoryButtonsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryButtonsFrame.setLocationRelativeTo(null); 
        inventoryButtonsFrame.setResizable(false);
        inventoryButtonsFrame.setVisible(true);
        inventoryButtonsFrame.setLayout(null);
        
                
        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(0, 0, 800, 120);
        ImageIcon titleLabelImage = new ImageIcon("src/resources/inventorylogo.png");
        titleLabel.setIcon(titleLabelImage);
        inventoryButtonsFrame.add(titleLabel);
        
        // button panel 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(0, 120, 800, 580);
        inventoryButtonsFrame.add(buttonPanel);
        
        // declaring new buttons
        JButton checkoutButton = new JButton("Checkout");
        JButton checkInventoryButton = new JButton("Check Inventory");
        JButton updatedShelvesButton = new JButton("Update Shelves Inventory");
        JButton checkLowStocksButton = new JButton("Check Low Stocks");
        JButton generateReportButton = new JButton("Generate Inventory Report");
        
        Dimension buttonSize = new Dimension(350, 65);
        checkoutButton.setBounds(225, 35, buttonSize.width, buttonSize.height);
        checkInventoryButton.setBounds(225, 135, buttonSize.width, buttonSize.height);
        updatedShelvesButton.setBounds(225, 235, buttonSize.width, buttonSize.height);
        checkLowStocksButton.setBounds(225, 335, buttonSize.width, buttonSize.height);
        generateReportButton.setBounds(225, 435, buttonSize.width, buttonSize.height);
        
        
        // formatting - font & font color
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutButton.setForeground(Color.WHITE);
        
        checkInventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkInventoryButton.setForeground(Color.WHITE);
        
        updatedShelvesButton.setFont(new Font("Arial", Font.BOLD, 16));
        updatedShelvesButton.setForeground(Color.WHITE);
        
        checkLowStocksButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkLowStocksButton.setForeground(Color.WHITE);
        
        generateReportButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateReportButton.setForeground(Color.WHITE);
        
        
        // color of buttons - 7/11 colors
        checkoutButton.setBackground(new Color(0xF48115));
        checkInventoryButton.setBackground(new Color(0x008163));
        updatedShelvesButton.setBackground(new Color(0xF48115));
        checkLowStocksButton.setBackground(new Color(0x008163));
        generateReportButton.setBackground(new Color(0xEE2526));
        
        // formatting
        buttonPanel.add(checkoutButton);
        buttonPanel.add(checkInventoryButton);
        buttonPanel.add(updatedShelvesButton);
        buttonPanel.add(checkLowStocksButton);
        buttonPanel.add(generateReportButton);
        
        // Action Listeners
        checkoutButton.addActionListener(e -> checkoutGUI());
        checkInventoryButton.addActionListener(e -> checkInventoryGUI());
        updatedShelvesButton.addActionListener(e -> updatedShelvesInventoryGUI());
        checkLowStocksButton.addActionListener(e -> lowStocksGUI());
        generateReportButton.addActionListener(e -> generateReportGUI());
        
        inventoryButtonsFrame.setVisible(true);
        
    }
    
   
    // a. Recording products as sold with the price and quantity [Checkout]
    // c. Updating inventory deduction after sales [Update Inventory] -> update on shelves inventory
    
    private void checkoutGUI() {
    	
    	checkoutFrame = new JFrame("Checkout Transaction");
    	checkoutFrame.setSize(800, 700);
    	checkoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	checkoutFrame.setLocationRelativeTo(null);
    	checkoutFrame.setResizable(false);
    	checkoutFrame.setLayout(null);
    	
    	// Entering details
    	// Product ID, Quantity Ordered, Employee ID (noted by whom)
    	
    	JLabel productIDLabel = new JLabel("Product ID: ");
    	productIDLabel.setBounds(30, 30, 150, 25);
    	checkoutFrame.add(productIDLabel);
    	
    	JTextField productIDTextField = new JTextField();
    	productIDTextField.setBounds(200, 30, 200, 25);
    	checkoutFrame.add(productIDTextField);
    	
    	JLabel quantityOrderedIDLabel = new JLabel("Quantity Ordered:  ");
    	quantityOrderedIDLabel.setBounds(30, 70, 150, 25);
    	checkoutFrame.add(quantityOrderedIDLabel);
    	
    	JTextField quantityOrderedTextField = new JTextField();
    	quantityOrderedTextField.setBounds(200, 70, 200, 25);
    	checkoutFrame.add(quantityOrderedTextField);
    	
    	JLabel employeeIDLabel = new JLabel("Enter your Employee ID: ");
    	employeeIDLabel.setBounds(30, 110, 150, 25);
    	checkoutFrame.add(employeeIDLabel);
    	
    	JTextField employeeIDTextField = new JTextField();
    	employeeIDTextField.setBounds(200, 110, 200, 25);
    	checkoutFrame.add(employeeIDTextField);
    	
    	JButton checkoutButton = new JButton("Checkout");
    	checkoutButton.setBounds(30, 160, 150, 35);
    	checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
    	checkoutButton.setForeground(Color.WHITE);
    	checkoutButton.setBackground(new Color(0x008163));
    	checkoutFrame.add(checkoutButton);
    	
    	JButton updateInventoryButton = new JButton("Update Inventory");
        updateInventoryButton.setBounds(30, 210, 150, 35);
        updateInventoryButton.setFont(new Font("Arial", Font.BOLD, 12));
        updateInventoryButton.setForeground(Color.WHITE);
        updateInventoryButton.setBackground(new Color(0x008163));
        updateInventoryButton.setEnabled(false); 
        checkoutFrame.add(updateInventoryButton);
    	
    	JTextArea message = new JTextArea();
    	message.setBounds(30, 260, 500, 200);
    	message.setEditable(false);
        checkoutFrame.add(message);
        
        checkoutButton.addActionListener(e -> {
            try {
                int productID = Integer.parseInt(productIDTextField.getText());
                int quantityOrdered = Integer.parseInt(quantityOrderedTextField.getText());
                int employeeID = Integer.parseInt(employeeIDTextField.getText());

                String result = CheckoutTransactions.checkout(productID, employeeID, quantityOrdered);
                message.setText(result);
                
                // enable the updateInventory (transaction c) if successful
                if (result.equals("Checkout completed successfully!"))
                	updateInventoryButton.setEnabled(true);
                else
                	updateInventoryButton.setEnabled(false);

            } catch (NumberFormatException ex) {
            	message.setText("Invalid input! Please ensure all fields are filled with correct values.");
            }
            
            
        });
    	
        // action listener for updateInventoryButton
        
        updateInventoryButton.addActionListener(e -> {
        	
        	try {
                int productID = Integer.parseInt(productIDTextField.getText());
                int quantityOrdered = Integer.parseInt(quantityOrderedTextField.getText());

                String updateResult = CheckoutTransactions.deductShelvesInventory(productID, quantityOrdered);
                message.append("\n" + updateResult); 
            
        	} catch (NumberFormatException ex) {
                message.append("\nInvalid input for inventory update.");
            }
        	
        	
        });
        
    	
    	checkoutFrame.setVisible(true);
    	

    }
    
    
    
    
    
    
    
    
    
    // b. Recording and checking if products are still available in inventory [Check Inventory]
    private void checkInventoryGUI() {
    	checkInventoryFrame = new JFrame("Check Inventory");
    	checkInventoryFrame.setSize(800, 700);
    	checkInventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	checkInventoryFrame.setLocationRelativeTo(null);
    	checkInventoryFrame.setResizable(false);
    	checkInventoryFrame.setLayout(null);
    	checkInventoryFrame.setVisible(true);
    	
    	JLabel productIDLabel2 = new JLabel("Enter Product ID: ");
    	productIDLabel2.setBounds(30, 30, 150, 25);
    	checkInventoryFrame.add(productIDLabel2);
    	
    	JTextField productIDTextField2 = new JTextField();
    	productIDTextField2.setBounds(200, 30, 200, 25);
    	checkInventoryFrame.add(productIDTextField2);
    	
    	// show product id, product name, product category, quantity available, quantity on shelves
    	// message - "Product is still available!" / "Product is sold out!"
    	
    	JLabel productNameLabel = new JLabel("Product Name: ");
        productNameLabel.setBounds(30, 80, 350, 25);
        checkInventoryFrame.add(productNameLabel);

        JLabel productCategoryLabel = new JLabel("Category: ");
        productCategoryLabel.setBounds(30, 130, 350, 25);
        checkInventoryFrame.add(productCategoryLabel);

        JLabel quantityAvailableLabel = new JLabel("Quantity Available: ");
        quantityAvailableLabel.setBounds(30, 180, 350, 25);
        checkInventoryFrame.add(quantityAvailableLabel);
    	
        JTextArea availabilityMessageArea = new JTextArea();
        availabilityMessageArea.setBounds(30, 225, 400, 40);
        availabilityMessageArea.setEditable(false);  
        availabilityMessageArea.setFont(new Font("Arial", Font.PLAIN, 14));       
        checkInventoryFrame.add(availabilityMessageArea);
        
        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setBounds(30, 285, 200, 50);
        checkAvailabilityButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkAvailabilityButton.setForeground(Color.WHITE);
        checkAvailabilityButton.setBackground(new Color(0x008163));
        checkInventoryFrame.add(checkAvailabilityButton);
        
        // add action listener for submit button
        
        
        checkAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                try {
                    int productID = Integer.parseInt(productIDTextField2.getText());
                    String result = CheckInventoryTransactions.checkInventory(productID);

                    if (!result.equals("Sorry! Product does not exist!")) {
                        String productName = CheckInventoryTransactions.getProductName(productID);
                        String productCategory = CheckInventoryTransactions.getProductCategory(productID);
                        int quantityOnStock = CheckInventoryTransactions.getQuantityOnStock(productID);

                        productNameLabel.setText("Product Name:                           " + productName);
                        productCategoryLabel.setText("Category:                                    " + productCategory);
                        quantityAvailableLabel.setText("Quantity Available:                     " + quantityOnStock);

                        if (quantityOnStock > 0) 
                        	availabilityMessageArea.setText("Product is still available!");
                        else 
                        	availabilityMessageArea.setText("Product is sold out!");
                        
                    } else {
                        productNameLabel.setText("Product Name:                           N/A");
                        productCategoryLabel.setText("Category:                                    N/A");
                        quantityAvailableLabel.setText("Quantity Available:                     N/A");
                        availabilityMessageArea.setText(result);  // "Sorry! Product does not exist!"
                    }
                } catch (NumberFormatException ex) {
                    availabilityMessageArea.setText("Please enter a valid product ID.");
                }
        	}
        });
        
    	
    	
    }
    
    
    
    
    
    // For Transaction D
    
    private void restockShelvesOptionPane() {
    	
    	JTextField productIDTextField = new JTextField();
        JTextField quantityToStockTextField = new JTextField();
    	
        Object[] message = {
                "Product ID:", productIDTextField,
                "Quantity to Restock on Shelves:", quantityToStockTextField
        };
        
        int option = JOptionPane.showConfirmDialog(updatedShelvesFrame, 
                message, 
                "Enter Restock (Shelves) Details", 
                JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                try {
                	 int productID = Integer.parseInt(productIDTextField.getText());
                     int quantityToStock = Integer.parseInt(quantityToStockTextField.getText());

                    String updateResult = UpdatedInventoryAndShelves.restockShelves(productID, quantityToStock); // CHANGE THIS BACKEND
                    JOptionPane.showMessageDialog(updatedShelvesFrame, updateResult, 
                        "Restock Status", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(updatedShelvesFrame, 
                        "Please enter valid numbers for Product ID and Quantity to Restock on Shelves.", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        
        
    }
    
    
    
    
    // d. Updating stocks quantity put out in shelves [Updated Shelves Inventory]
    private void updatedShelvesInventoryGUI() {
    	updatedShelvesFrame = new JFrame("Update Shelves Stock");
    	updatedShelvesFrame.setSize(800, 700);
    	updatedShelvesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	updatedShelvesFrame.setLocationRelativeTo(null);
    	updatedShelvesFrame.setResizable(false); 
    	updatedShelvesFrame.setLayout(null);
    	updatedShelvesFrame.setVisible(true);
    	
    	
    	JButton checkLowShelvesStockButton = new JButton("Check Shelves with Low Stock");
    	checkLowShelvesStockButton.setBounds(30, 30, 300, 50);
    	checkLowShelvesStockButton.setFont(new Font("Arial", Font.BOLD, 14));
    	checkLowShelvesStockButton.setForeground(Color.WHITE);    	
        updatedShelvesFrame.add(checkLowShelvesStockButton);
        checkLowShelvesStockButton.setBackground(new Color(0x008163));
    	
    	
    	JTextArea lowStocksTextArea = new JTextArea();
    	lowStocksTextArea.setBounds(30, 100, 720, 350);
    	lowStocksTextArea.setEditable(false);
    	updatedShelvesFrame.add(lowStocksTextArea);
    	
    	// show products with low stocks --> action listener
    	
    	checkLowShelvesStockButton.addActionListener(new ActionListener() {
		           
	        	public void actionPerformed(ActionEvent e) {
	                String report = UpdatedInventoryAndShelves.getLowShelvesStock();  
	                lowStocksTextArea.setText(report);
	                
	                if(!report.equals("All shelves have enough stock!")) {
	            		
	                	int yes_or_no = JOptionPane.showConfirmDialog(updatedShelvesFrame, 
	                            "Do you want to restock shelves?",
	                            "Restock Confirmation",  // check this
	                            JOptionPane.YES_NO_OPTION);
	
	                        if (yes_or_no == JOptionPane.YES_OPTION) {
	                            restockShelvesOptionPane(); 
	                        }
	            	}
	            }
		  });
       	
    	
    }
    
    
    
    
    
    
    
    // for Transaction E
    
    private void restockOptionPane(JTextArea restockedMessage) {
    	
    	restockedMessage.setText("");
    	
    	JTextField productIDTextField = new JTextField();
        JTextField quantityToStockTextField = new JTextField();
        
        Object[] message = {
                "Product ID:", productIDTextField,
                "Quantity to Restock:", quantityToStockTextField
        };

        int option = JOptionPane.showConfirmDialog(lowStocksFrame, 
            message, 
            "Enter Restock Details", 
            JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
            	 int productID = Integer.parseInt(productIDTextField.getText());
                 int quantityToStock = Integer.parseInt(quantityToStockTextField.getText());

                 String updateResult = CheckInventoryTransactions.restock(productID, quantityToStock);
                 
                 String result = String.format("Product ID: %d\nQuantity Restocked: %d\nStatus: %s\n", 
                         productID, quantityToStock, updateResult);
                 
                 restockedMessage.append(result);
               
                 
            } catch (NumberFormatException ex) {
            	restockedMessage.append("Please enter valid numbers.");
            }
        }
    	
    }

    
    
    // e. Handling low stocks and necessity of reordering products to restock [Low Stocks]
    private void lowStocksGUI() {
    	lowStocksFrame = new JFrame("Check Low Stocks");
    	lowStocksFrame.setSize(800, 700);
    	lowStocksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	lowStocksFrame.setLocationRelativeTo(null);
    	lowStocksFrame.setResizable(false);
    	lowStocksFrame.setLayout(null);
    	lowStocksFrame.setVisible(true);
    	
    	
    	JButton checkLowStocksButton = new JButton("Check Low Stocks");
    	checkLowStocksButton.setBounds(30, 20, 220, 40);
    	checkLowStocksButton.setFont(new Font("Arial", Font.BOLD, 14));
    	checkLowStocksButton.setForeground(Color.WHITE);
    	checkLowStocksButton.setBackground(new Color(0x008163));
    	lowStocksFrame.add(checkLowStocksButton);
    	
    	JTextArea lowstocksMessage = new JTextArea();
    	lowstocksMessage.setEditable(false);
    	lowstocksMessage.setBounds(30, 80, 725, 220);
    	lowStocksFrame.add(lowstocksMessage);
    	
    	JTextArea restockedMessage = new JTextArea();
    	restockedMessage.setEditable(false);
    	restockedMessage.setBounds(30, 350, 725, 220);
    	lowStocksFrame.add(restockedMessage);
    	
    	// JOptionPane source: https://www.geeksforgeeks.org/java-joptionpane/
    	
    	checkLowStocksButton.addActionListener(new ActionListener() {
            
        	public void actionPerformed(ActionEvent e) {
                
        		// show on text area the products that have low stocks
        		String report = CheckInventoryTransactions.getlowStocks(); // low stockssss
                lowstocksMessage.setText(report);
                
                if(!report.equals("Inventory is all good!")) {
            		
                	int yes_or_no = JOptionPane.showConfirmDialog(lowStocksFrame, 
                            "Do you want to restock inventory?",
                            "Restock Confirmation",  // check this
                            JOptionPane.YES_NO_OPTION);

                        if (yes_or_no == JOptionPane.YES_OPTION) {
                            restockOptionPane(restockedMessage);
                        }
                	
            		
            	}
            }

        	
        });
    	
    	
    }
    
    
    // Inventory Report (total amount of products sold and remaining) per product, per day 
    private void generateReportGUI() {
    	
    	generateReportFrame = new JFrame("Inventory Report");
    	generateReportFrame.setSize(800, 700);
    	generateReportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	generateReportFrame.setLocationRelativeTo(null);
    	generateReportFrame.setResizable(false);
    	generateReportFrame.setLayout(null);
    	generateReportFrame.getContentPane().setBackground(Color.WHITE);
    	
    	
    	JButton generateReportButton = new JButton("Generate Report");
    	generateReportButton.setBounds(212, 200, 350, 65);
    	generateReportButton.setFont(new Font("Arial", Font.BOLD, 16));
    	generateReportButton.setForeground(Color.WHITE);
    	generateReportButton.setBackground(new Color(0x008163));
    	
    	generateReportButton.addActionListener(e -> {
    		String report = InventoryGenerateReport.showReport();
    		
    		JLabel titleText = new JLabel("Inventory Report");
    		titleText.setBounds(10, 0, 200, 70);
    		titleText.setFont(new Font("Arial", Font.BOLD, 24));
    		generateReportFrame.add(titleText);
    		
        	
        	JTextArea reportTextArea = new JTextArea(report);
        	reportTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        	reportTextArea.setEditable(false);
        	
        	JScrollPane scrollPane = new JScrollPane(reportTextArea);
        	scrollPane.setBounds(10, 60, 760, 580);
        	
        	generateReportFrame.add(scrollPane);
        	
        	generateReportFrame.remove(generateReportButton);
        	
        	generateReportFrame.revalidate();
        	generateReportFrame.repaint();
        	
    		
    	});
    	
 
    	generateReportFrame.add(generateReportButton);       
    	generateReportFrame.setVisible(true);

 
    }
    
    
}
