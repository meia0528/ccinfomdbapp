package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseConnection.SQLConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateBranchGUI {

    private JFrame frame;
    private JTextField branchIDField;
    private JTextField branchNameField;
    private JTextField locationField;
    private JTextField foundingYearField;
    private JComboBox<String> contactPersonComboBox;

    private DefaultTableModel branchTable;
    private String originalBranchID;

    public UpdateBranchGUI(DefaultTableModel branchTable, String branchID) {
        this.branchTable = branchTable;
        this.originalBranchID = branchID;

        frame = new JFrame("Update Branch");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Branch ID:"));
        branchIDField = new JTextField(branchID);
        frame.add(branchIDField);

        frame.add(new JLabel("Branch Name:"));
        branchNameField = new JTextField();
        frame.add(branchNameField);

        frame.add(new JLabel("Location:"));
        locationField = new JTextField();
        frame.add(locationField);

        frame.add(new JLabel("Founding Year:"));
        foundingYearField = new JTextField();
        frame.add(foundingYearField);

        frame.add(new JLabel("Contact Person ID:"));
        contactPersonComboBox = new JComboBox<>();
        populateContactPersonComboBox();
        frame.add(contactPersonComboBox);

        JButton updateButton = new JButton("Update");
        frame.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        frame.add(cancelButton);

        // Add action listeners to buttons
        updateButton.addActionListener(e -> updateBranch());
        cancelButton.addActionListener(e -> frame.dispose());
        loadBranchData(branchID);
        frame.setVisible(true);
    }

    private void loadBranchData(String branchID) {
        Connection connection = SQLConnection.getConnection();
        try {
            String sql = "SELECT branch_code, location, founding_year, contact_person_id FROM branch WHERE branch_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, branchID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                branchNameField.setText(rs.getString("branch_code"));
                locationField.setText(rs.getString("location"));
                foundingYearField.setText(rs.getString("founding_year"));
                contactPersonComboBox.setSelectedItem(rs.getString("contact_person_id"));
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading branch data", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBranch() {
        Connection connection = SQLConnection.getConnection();
        try {
            String sql = "UPDATE branch SET branch_id = ?, branch_code = ?, location = ?, founding_year = ?, contact_person_id = ? WHERE branch_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, branchIDField.getText());
            preparedStatement.setString(2, branchNameField.getText());
            preparedStatement.setString(3, locationField.getText());
            preparedStatement.setInt(4, Integer.parseInt(foundingYearField.getText()));
            preparedStatement.setString(5, contactPersonComboBox.getSelectedItem().toString());
            preparedStatement.setString(6, originalBranchID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(frame, "Branch updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Reload data into table model
            reloadTableData();
            
            frame.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error updating branch", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void reloadTableData() {
        // Clear the existing data
        branchTable.setRowCount(0);
        
        // Reload data from the database
        String query = "SELECT branch_id AS 'Branch ID', branch_code AS 'Branch Name', location AS 'Location', founding_year AS 'Founding Year', contact_person_id AS 'Contact Person' FROM branch";
        String[] columnNames = {"Branch ID", "Branch Name", "Location", "Founding Year", "Contact Person"};
        DatabaseApplicationHomepage.loadData(branchTable, query, columnNames);
    }

    private void populateContactPersonComboBox() {
        Connection connection = SQLConnection.getConnection();
        try {
            String sql = "SELECT employee_id FROM employees";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                contactPersonComboBox.addItem(rs.getString("employee_id"));
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching employee IDs", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
