package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import databaseConnection.SQLConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddBranchGUI {

    private JFrame frame;
    private JTextField branchIDField;
    private JTextField branchNameField;
    private JTextField locationField;
    private JTextField foundingYearField;
    private JComboBox<String> contactPersonComboBox;
    private DefaultTableModel branchTable;

    public AddBranchGUI(DefaultTableModel branchTable) {
        this.branchTable = branchTable;

        frame = new JFrame("Add Branch");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Branch ID:"));
        branchIDField = new JTextField();
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

        JButton addButton = new JButton("Add");
        frame.add(addButton);

        JButton cancelButton = new JButton("Cancel");
        frame.add(cancelButton);

        // Add action listeners to buttons
        addButton.addActionListener(e -> addBranch());
        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
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

    public void addBranch() {
        String branchID = this.branchIDField.getText();
        String branchName = this.branchNameField.getText();
        String location = this.locationField.getText();
        String foundingYear = this.foundingYearField.getText();
        String contactPersonID = (String) this.contactPersonComboBox.getSelectedItem();

        Connection connection = SQLConnection.getConnection();

        if (!branchID.isEmpty() && !branchName.isEmpty() && !location.isEmpty() && !foundingYear.isEmpty() && contactPersonID != null) {
            this.branchTable.addRow(new Object[]{branchID, branchName, location, foundingYear, contactPersonID});
            try {
                // SQL insert statement
                String sql = "INSERT INTO branch (branch_id, branch_code, location, founding_year, contact_person_id) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, branchID);
                preparedStatement.setString(2, branchName);
                preparedStatement.setString(3, location);
                preparedStatement.setString(4, foundingYear);
                preparedStatement.setString(5, contactPersonID);

                // Execute the insert statement
                preparedStatement.executeUpdate();

                // Close the connection
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this.frame, "Error adding branch to database", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
            this.frame.dispose();
        } else {
            JOptionPane.showMessageDialog(this.frame, "Please fill in all fields", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Example usage
        DefaultTableModel branchTableModel = new DefaultTableModel(new String[]{"Branch ID", "Branch Name", "Location", "Founding Year", "Contact Person ID"}, 0);
        new AddBranchGUI(branchTableModel);
    }
}