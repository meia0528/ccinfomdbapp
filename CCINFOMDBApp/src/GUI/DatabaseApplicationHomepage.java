// https://www.canva.com/design/DAGXMMFWhZ0/yhuyAce4nnlhc_GRQGiQqg/edit?utm_content=DAGXMMFWhZ0&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
// canva link for images used

package GUI;

import javax.swing.*;
import java.awt.*;

public class DatabaseApplicationHomepage {
    
 
    private JFrame frame;
    
    public DatabaseApplicationHomepage() {
        
    	// frame specs
        frame = new JFrame();
        frame.setTitle("Convenience Store Database System - Homepage");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        
        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, 800, 700);
        frame.add(layer);

        ImageIcon backgroundHomepage = new ImageIcon("src/resources/background.png");
        JLabel backgroundLabel = new JLabel(backgroundHomepage);
        backgroundLabel.setBounds(0, 120, 800, 580);
        layer.add(backgroundLabel, Integer.valueOf(0));
        
        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(0, 0, 800, 120);
        ImageIcon titleLabelImage = new ImageIcon("src/resources/logo.png");
        titleLabel.setIcon(titleLabelImage);
        layer.add(titleLabel, Integer.valueOf(1));  
        
        // button panel 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(0, 120, 800, 580);
        buttonPanel.setOpaque(false);
        layer.add(buttonPanel, Integer.valueOf(2));       
       
        
        // declaring new buttons
        JButton branchManagementButton = new JButton("Branch Management System");
        JButton inventoryManagementButton = new JButton("Inventory Management System");
        JButton employeeManagementButton = new JButton("Employee Management System");    
        JButton supplierManagementButton = new JButton("Supplier Management System");
        
        
        // sizing & formatting
        Dimension buttonSize = new Dimension(350, 65);
        branchManagementButton.setBounds(225, 60, buttonSize.width, buttonSize.height);
        inventoryManagementButton.setBounds(225, 160, buttonSize.width, buttonSize.height);
        employeeManagementButton.setBounds(225, 260, buttonSize.width, buttonSize.height);
        supplierManagementButton.setBounds(225, 360, buttonSize.width, buttonSize.height);
 
        
        // formatting - font & font color
        branchManagementButton.setFont(new Font("Arial", Font.BOLD, 16));
        branchManagementButton.setForeground(Color.WHITE);
        
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
        buttonPanel.add(inventoryManagementButton);
        buttonPanel.add(employeeManagementButton);
        buttonPanel.add(supplierManagementButton);
        
        // Action Listeners
        branchManagementButton.addActionListener(e -> openBranchManagement());
        inventoryManagementButton.addActionListener(e -> openInventoryManagement());
        employeeManagementButton.addActionListener(e -> openEmployeeManagement());
        supplierManagementButton.addActionListener(e -> openSupplierManagement());
        
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
