package GUI;

import javax.swing.*;
import java.awt.*;

// NOTE: Only edit the function that is assigned to you -> create a new class under the GUI package for your system's GUI
// the main will be contained here

public class DatabaseApplicationHomepage {

    private JFrame frame;
    
    public DatabaseApplicationHomepage() {
        
    	// frame specs
        frame = new JFrame();
        frame.setTitle("Database Application - Homepage");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        
        // "Convenience Store" title
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(frame.getWidth(), 120));
   
        JLabel titleLabel = new JLabel("Convenience Store", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        // button panel 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // declaring new buttons
        JButton branchManagementButton = new JButton("Branch Management System");
        JButton inventoryManagementButton = new JButton("Inventory Management System");
        JButton employeeManagementButton = new JButton("Employee Management System");    
        JButton supplierManagementButton = new JButton("Supplier Management System");
        
        
        // sizing & formatting
        Dimension buttonSize = new Dimension(350, 65);
        branchManagementButton.setMaximumSize(buttonSize);
        inventoryManagementButton.setMaximumSize(buttonSize);
        employeeManagementButton.setMaximumSize(buttonSize);
        supplierManagementButton.setMaximumSize(buttonSize);
        
        branchManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventoryManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeeManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        supplierManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        // formatting - font & font color
        branchManagementButton.setFont(new Font("Arial", Font.BOLD, 16));
        branchManagementButton.setForeground(Color.WHITE);
        //branchManagementButton.setBorder(BorderFactory.createEmptyBorder()); 
        
        inventoryManagementButton.setFont(new Font("Arial", Font.BOLD, 16));
        inventoryManagementButton.setForeground(Color.WHITE);
        employeeManagementButton.setFont(new Font("Arial", Font.BOLD, 16));
        employeeManagementButton.setForeground(Color.WHITE);
        supplierManagementButton.setFont(new Font("Arial", Font.BOLD, 16));
        supplierManagementButton.setForeground(Color.WHITE);
        
        // color of buttons - 7/11 colors
        branchManagementButton.setBackground(new Color(0xF48115));
        inventoryManagementButton.setBackground(new Color(0x008163));
        employeeManagementButton.setBackground(new Color(0xEE2526));
        supplierManagementButton.setBackground(new Color(0xF48115));
        
        // formatting
        buttonPanel.add(branchManagementButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        buttonPanel.add(inventoryManagementButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        buttonPanel.add(employeeManagementButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        buttonPanel.add(supplierManagementButton);
        
        // Action Listeners
        branchManagementButton.addActionListener(e -> openBranchManagement());
        inventoryManagementButton.addActionListener(e -> openInventoryManagement());
        employeeManagementButton.addActionListener(e -> openEmployeeManagement());
        supplierManagementButton.addActionListener(e -> openSupplierManagement());
        
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Placeholder methods for each module
    // you are free to modify and create new methods/classes if necessary
    private void openBranchManagement() {
    	JFrame branchFrame = new JFrame("Branch Management System");
        branchFrame.setSize(800, 700); // let's follow this as standard for the opening so all records are visible
        branchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        branchFrame.setLocationRelativeTo(frame);
        branchFrame.setResizable(false);
        branchFrame.setVisible(true);
    }

    private void openInventoryManagement() {
    	new InventoryManagementGUI();
    }

    private void openEmployeeManagement() {
    	JFrame employeeFrame = new JFrame("Employee Management System");
    	employeeFrame.setSize(800, 700); // let's follow this as standard for the opening so all records are visible
    	employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	employeeFrame.setLocationRelativeTo(frame);
    	employeeFrame.setResizable(false);
    	employeeFrame.setVisible(true);
    }

    private void openSupplierManagement() {
    	JFrame supplierFrame = new JFrame("Supplier Management System");
    	supplierFrame.setSize(800, 700); // let's follow this as standard for the opening so all records are visible
    	supplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	supplierFrame.setLocationRelativeTo(frame);
    	supplierFrame.setResizable(false);
    	supplierFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DatabaseApplicationHomepage::new);
    }
}
