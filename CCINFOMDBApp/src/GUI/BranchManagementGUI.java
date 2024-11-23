package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import databaseConnection.SQLConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchManagementGUI {

    private JFrame frame;

    public BranchManagementGUI() {

        // frame specs
        frame = new JFrame();
        frame.setTitle("Convenience Store Database System - Branch Management System");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, 800, 700);
        frame.add(layer);

        ImageIcon backgroundHomepage = new ImageIcon(getClass().getResource("/resources/background.png"));
        JLabel backgroundLabel = new JLabel(backgroundHomepage);
        backgroundLabel.setBounds(0, 120, 800, 580);
        layer.add(backgroundLabel, Integer.valueOf(0));

        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(0, 0, 800, 120);
        ImageIcon titleLabelImage = new ImageIcon(getClass().getResource("/resources/logo.png"));
        titleLabel.setIcon(titleLabelImage);
        layer.add(titleLabel, Integer.valueOf(1));

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(0, 120, 800, 100); // Adjusted height to make space for the search bar
        buttonPanel.setOpaque(false);
        layer.add(buttonPanel, Integer.valueOf(2));

        // declaring new buttons
        JButton addBranchButton = new JButton("Add Branch");
        JButton updateBranchButton = new JButton("Update Branch");
        JButton deleteBranchButton = new JButton("Delete Branch");
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton searchButton = new JButton("Search");

        // sizing & formatting
        Dimension buttonSize = new Dimension(150, 45);
        int spacing = 20;
        int startX = 50;
        int startY = 20;

        addBranchButton.setFont(new Font("Arial", Font.BOLD, 14));
        addBranchButton.setForeground(Color.WHITE);
        addBranchButton.setBackground(new Color(0xF48115));
        
        updateBranchButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateBranchButton.setForeground(Color.WHITE);
        updateBranchButton.setBackground(new Color(0x008163));

        deleteBranchButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteBranchButton.setForeground(Color.WHITE);
        deleteBranchButton.setBackground(new Color(0xEE2526));

        viewTransactionsButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewTransactionsButton.setForeground(Color.WHITE);
        viewTransactionsButton.setBackground(new Color(0xF48115));

        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(0xF48115));

        // main button positions
        int totalButtonWidth = 4 * buttonSize.width + 3 * spacing;
        int centeredStartX = (800 - totalButtonWidth) / 2;

        addBranchButton.setBounds(centeredStartX, startY, buttonSize.width, buttonSize.height);
        updateBranchButton.setBounds(centeredStartX + buttonSize.width + spacing, startY, buttonSize.width, buttonSize.height);
        deleteBranchButton.setBounds(centeredStartX + 2 * (buttonSize.width + spacing), startY, buttonSize.width, buttonSize.height);
        viewTransactionsButton.setBounds(centeredStartX + 3 * (buttonSize.width + spacing), startY, buttonSize.width, buttonSize.height);

        // adding buttons to panel
        buttonPanel.add(addBranchButton);
        buttonPanel.add(updateBranchButton);
        buttonPanel.add(deleteBranchButton);
        buttonPanel.add(viewTransactionsButton);

        // Create search bar and button
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(startX, startY + 50, 60, buttonSize.height - 15); // Adjusted width to fit the word "Search"
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchLabel.setForeground(Color.WHITE);

        final JTextField searchBar = new JTextField();
        searchBar.setBounds(startX + 70, startY + 50, 500, buttonSize.height - 15); // Adjusted x position to fit the word "Search"
        searchButton.setBounds(startX + 580, startY + 50, 100, buttonSize.height - 15);

        // adding search components to panel
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchBar);
        buttonPanel.add(searchButton);

        // Create table model and table
        String[] columnNames = {"Branch ID", "Branch Name", "Location", "Founding Year", "Contact Person", "Rating", "Last Updated"};
        DefaultTableModel branchTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells uneditable
            }
        };

        JTable branchTable = new JTable(branchTableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);
            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(false);
            }
            c.setForeground(Color.WHITE);
            return c;
            }
        };
        branchTable.setOpaque(false);
        branchTable.setForeground(Color.WHITE);
        branchTable.setShowGrid(true);
        branchTable.setGridColor(Color.WHITE);

        JTableHeader header = branchTable.getTableHeader();
        header.setBackground(new Color(0x008163));
        header.setForeground(Color.WHITE);
        header.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(branchTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        branchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Make rows selectable
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(branchTableModel);
        branchTable.setRowSorter(sorter);
        scrollPane.setBounds(50, 250, 700, 360);
        layer.add(scrollPane, Integer.valueOf(3));

        // Load data
        String[] branchColumns = {"Branch ID", "Branch Name", "Location", "Founding Year", "Contact Person", "Rating", "Last Updated"};
        String query = "SELECT branch.branch_id AS `Branch ID`, " +
                    "       branch.branch_code AS `Branch Name`, " +
                    "       branch.location AS `Location`, " +
                    "       branch.founding_year AS `Founding Year`, " +
                    "       branch.contact_person_id AS `Contact Person`, " +
                    "       branch_ratings.performance_ratings AS `Rating`, " +
                    "       branch_ratings.date_updated AS `Last Updated` " +
                    "FROM branch " +
                    "INNER JOIN branch_ratings ON branch.branch_id = branch_ratings.branch_id;";
        loadData(branchTableModel, query, branchColumns);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(650, 620, 100, 30);
        layer.add(backButton, Integer.valueOf(4));

        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0xEE2526));

        // button actions
        addBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBranchGUI(branchTableModel);
            }
        });

        updateBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = branchTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedRow = branchTable.convertRowIndexToModel(selectedRow);
                    String branchID = branchTableModel.getValueAt(selectedRow, 0).toString();
                    new UpdateBranchGUI(branchTableModel, branchID);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a branch to update.", "No Branch Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteBranchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = branchTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedRow = branchTable.convertRowIndexToModel(selectedRow);
                    String branchID = branchTableModel.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this branch?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteRow("branch", "branch_id", branchID, "branch_ratings", "brand_id");
                        branchTableModel.removeRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a branch to delete.", "No Branch Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        viewTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = branchTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedRow = branchTable.convertRowIndexToModel(selectedRow);
                    String contactPersonID = branchTableModel.getValueAt(selectedRow, 4).toString();
                    viewTransactions(contactPersonID);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a branch to view transactions.", "No Branch Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchBar.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Refresh Button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(50, 620, 100, 30);
        layer.add(refreshButton, Integer.valueOf(4));

        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(0xF48115));

        refreshButton.addActionListener(e -> {
            branchTableModel.setRowCount(0); // Clear existing data
            loadData(branchTableModel, query, branchColumns); // Reload data
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new DatabaseApplicationHomepage();
        });

    }

    private void viewTransactions(String contactPersonID) {
        JFrame transactionsFrame = new JFrame("Transactions");
        transactionsFrame.setSize(800, 600);
        transactionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        transactionsFrame.setLocationRelativeTo(null);
        transactionsFrame.setResizable(false);
        transactionsFrame.setLayout(null);
    
        JLayeredPane layer = new JLayeredPane();
        layer.setBounds(0, 0, 800, 600);
        transactionsFrame.add(layer);
    
        ImageIcon backgroundHomepage = new ImageIcon(getClass().getResource("/resources/background.png"));
        JLabel backgroundLabel = new JLabel(backgroundHomepage);
        backgroundLabel.setBounds(0, 0, 800, 600);
        layer.add(backgroundLabel, Integer.valueOf(0));
    
        DefaultTableModel transactionsTableModel = new DefaultTableModel(new String[]{"Product ID", "Noted By Employee ID", "Sale Date", "Quantity Ordered"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells uneditable
            }
        };
    
        JTable transactionsTable = new JTable(transactionsTableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    ((JComponent) c).setOpaque(false);
                }
                c.setForeground(Color.WHITE);
                return c;
            }
        };
        transactionsTable.setOpaque(false);
        transactionsTable.setForeground(Color.WHITE);
        transactionsTable.setShowGrid(true);
        transactionsTable.setGridColor(Color.WHITE);
    
        JTableHeader header = transactionsTable.getTableHeader();
        header.setBackground(new Color(0x008163));
        header.setForeground(Color.WHITE);
        header.setOpaque(false);
    
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        transactionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setBounds(50, 100, 700, 360);
        layer.add(scrollPane, Integer.valueOf(1));
    
        Connection connection = SQLConnection.getConnection();
        try {
            String sql = "SELECT product_id, noted_by_employee_id, sale_date, quantity_ordered FROM sales WHERE noted_by_employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, contactPersonID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                transactionsTableModel.addRow(new Object[]{
                    rs.getInt("product_id"),
                    rs.getInt("noted_by_employee_id"),
                    rs.getDate("sale_date"),
                    rs.getInt("quantity_ordered")
                });
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(transactionsFrame, "Error loading transactions", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    
        // Add buttons to layer
        JButton addTransactionButton = new JButton("Add Transaction");
        JButton updateTransactionButton = new JButton("Update Transaction");
        JButton deleteTransactionButton = new JButton("Delete Transaction");
    
        addTransactionButton.setBounds(175, 500, 150, 30);
        updateTransactionButton.setBounds(335, 500, 150, 30);
        deleteTransactionButton.setBounds(495, 500, 150, 30);

        addTransactionButton.setFont(new Font("Arial", Font.BOLD, 14));
        addTransactionButton.setForeground(Color.WHITE);
        addTransactionButton.setBackground(new Color(0xF48115));

        updateTransactionButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateTransactionButton.setForeground(Color.WHITE);
        updateTransactionButton.setBackground(new Color(0x008163));

        deleteTransactionButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteTransactionButton.setForeground(Color.WHITE);
        deleteTransactionButton.setBackground(new Color(0xEE2526));
    
        layer.add(addTransactionButton, Integer.valueOf(2));
        layer.add(updateTransactionButton, Integer.valueOf(2));
        layer.add(deleteTransactionButton, Integer.valueOf(2));
    
        // Button actions
        addTransactionButton.addActionListener(e -> addTransaction(transactionsTableModel));
        updateTransactionButton.addActionListener(e -> {
            int selectedRow = transactionsTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedRow = transactionsTable.convertRowIndexToModel(selectedRow);
                updateTransaction(transactionsTableModel, selectedRow);
            } else {
                JOptionPane.showMessageDialog(transactionsFrame, "Please select a transaction to update.", "No Transaction Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        deleteTransactionButton.addActionListener(e -> {
            int selectedRow = transactionsTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedRow = transactionsTable.convertRowIndexToModel(selectedRow);
                deleteTransaction(transactionsTableModel, selectedRow);
            } else {
                JOptionPane.showMessageDialog(transactionsFrame, "Please select a transaction to delete.", "No Transaction Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
    
        transactionsFrame.setVisible(true);
    }

    private void addTransaction(DefaultTableModel transactionsTableModel) {
        JDialog addTransactionDialog = new JDialog(frame, "Add Transaction", true);
        addTransactionDialog.setSize(400, 300);
        addTransactionDialog.setLayout(new GridLayout(5, 2));
        addTransactionDialog.setLocationRelativeTo(frame);
    
        JLabel productIDLabel = new JLabel("Product ID:");
        JTextField productIDField = new JTextField();
        JLabel employeeIDLabel = new JLabel("Noted By (ID):");
        JTextField employeeIDField = new JTextField();
        JLabel saleDateLabel = new JLabel("Sale Date (YYYY-MM-DD):");
        JTextField saleDateField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
    
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
    
        addTransactionDialog.add(productIDLabel);
        addTransactionDialog.add(productIDField);
        addTransactionDialog.add(employeeIDLabel);
        addTransactionDialog.add(employeeIDField);
        addTransactionDialog.add(saleDateLabel);
        addTransactionDialog.add(saleDateField);
        addTransactionDialog.add(quantityLabel);
        addTransactionDialog.add(quantityField);
        addTransactionDialog.add(addButton);
        addTransactionDialog.add(cancelButton);
    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productID = productIDField.getText();
                String employeeID = employeeIDField.getText();
                String saleDate = saleDateField.getText();
                String quantity = quantityField.getText();
    
                if (productID.isEmpty() || employeeID.isEmpty() || saleDate.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(addTransactionDialog, "Please fill in all fields.", "Incomplete Data", JOptionPane.WARNING_MESSAGE);
                    return;
                }
    
                Connection connection = SQLConnection.getConnection();
                try {
                    String sql = "INSERT INTO sales (product_id, noted_by_employee_id, sale_date, quantity_ordered) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, Integer.parseInt(productID));
                    preparedStatement.setInt(2, Integer.parseInt(employeeID));
                    preparedStatement.setDate(3, java.sql.Date.valueOf(saleDate));
                    preparedStatement.setInt(4, Integer.parseInt(quantity));
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
    
                    transactionsTableModel.addRow(new Object[]{productID, employeeID, saleDate, quantity});
                    addTransactionDialog.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addTransactionDialog, "Error adding transaction", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTransactionDialog.dispose();
            }
        });
    
        addTransactionDialog.setVisible(true);
    }

    private void updateTransaction(DefaultTableModel transactionsTableModel, int selectedRow) {
        JDialog updateTransactionDialog = new JDialog(frame, "Update Transaction", true);
        updateTransactionDialog.setSize(400, 300);
        updateTransactionDialog.setLayout(new GridLayout(5, 2));
        updateTransactionDialog.setLocationRelativeTo(frame);
    
        JLabel productIDLabel = new JLabel("Product ID:");
        JTextField productIDField = new JTextField(transactionsTableModel.getValueAt(selectedRow, 0).toString());
        JLabel employeeIDLabel = new JLabel("Noted By (ID):");
        JTextField employeeIDField = new JTextField(transactionsTableModel.getValueAt(selectedRow, 1).toString());
        JLabel saleDateLabel = new JLabel("Sale Date (YYYY-MM-DD):");
        JTextField saleDateField = new JTextField(transactionsTableModel.getValueAt(selectedRow, 2).toString());
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(transactionsTableModel.getValueAt(selectedRow, 3).toString());
    
        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");
    
        updateTransactionDialog.add(productIDLabel);
        updateTransactionDialog.add(productIDField);
        updateTransactionDialog.add(employeeIDLabel);
        updateTransactionDialog.add(employeeIDField);
        updateTransactionDialog.add(saleDateLabel);
        updateTransactionDialog.add(saleDateField);
        updateTransactionDialog.add(quantityLabel);
        updateTransactionDialog.add(quantityField);
        updateTransactionDialog.add(updateButton);
        updateTransactionDialog.add(cancelButton);
    
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productID = productIDField.getText();
                String employeeID = employeeIDField.getText();
                String saleDate = saleDateField.getText();
                String quantity = quantityField.getText();
    
                if (productID.isEmpty() || employeeID.isEmpty() || saleDate.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(updateTransactionDialog, "Please fill in all fields.", "Incomplete Data", JOptionPane.WARNING_MESSAGE);
                    return;
                }
    
                Connection connection = SQLConnection.getConnection();
                try {
                    String sql = "UPDATE sales SET product_id = ?, noted_by_employee_id = ?, sale_date = ?, quantity_ordered = ? WHERE product_id = ? AND noted_by_employee_id = ? AND sale_date = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, Integer.parseInt(productID));
                    preparedStatement.setInt(2, Integer.parseInt(employeeID));
                    preparedStatement.setDate(3, java.sql.Date.valueOf(saleDate));
                    preparedStatement.setInt(4, Integer.parseInt(quantity));
                    preparedStatement.setInt(5, Integer.parseInt(transactionsTableModel.getValueAt(selectedRow, 0).toString()));
                    preparedStatement.setInt(6, Integer.parseInt(transactionsTableModel.getValueAt(selectedRow, 1).toString()));
                    preparedStatement.setDate(7, java.sql.Date.valueOf(transactionsTableModel.getValueAt(selectedRow, 2).toString()));
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
    
                    transactionsTableModel.setValueAt(productID, selectedRow, 0);
                    transactionsTableModel.setValueAt(employeeID, selectedRow, 1);
                    transactionsTableModel.setValueAt(saleDate, selectedRow, 2);
                    transactionsTableModel.setValueAt(quantity, selectedRow, 3);
                    updateTransactionDialog.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updateTransactionDialog, "Error updating transaction", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTransactionDialog.dispose();
            }
        });
    
        updateTransactionDialog.setVisible(true);
    }

    private void deleteTransaction(DefaultTableModel transactionsTableModel, int selectedRow) {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Connection connection = SQLConnection.getConnection();
            try {
                String sql = "DELETE FROM sales WHERE product_id = ? AND noted_by_employee_id = ? AND sale_date = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(transactionsTableModel.getValueAt(selectedRow, 0).toString()));
                preparedStatement.setInt(2, Integer.parseInt(transactionsTableModel.getValueAt(selectedRow, 1).toString()));
                preparedStatement.setDate(3, java.sql.Date.valueOf(transactionsTableModel.getValueAt(selectedRow, 2).toString()));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
    
                transactionsTableModel.removeRow(selectedRow);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error deleting transaction", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void loadData(DefaultTableModel tableModel, String query, String[] columns) {
        Connection conn = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Object[] rowData = new Object[columns.length];
                for (int i = 0; i < columns.length; i++) {
                    rowData[i] = rs.getObject(columns[i]);
                }
                tableModel.addRow(rowData);
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void deleteRow(String parentTable, String parentColumn, String value, String childTable, String childColumn) {
        Connection conn = SQLConnection.getConnection();
        try {
            // Delete related rows in the child table first, if specified
            if (childTable != null && childColumn != null) {
                String childSql = "DELETE FROM " + childTable + " WHERE " + childColumn + " = ?";
                PreparedStatement childPreparedStatement = conn.prepareStatement(childSql);
                childPreparedStatement.setString(1, value);
                childPreparedStatement.executeUpdate();
                childPreparedStatement.close();
            }
    
            // Delete the row in the parent table
            String sql = "DELETE FROM " + parentTable + " WHERE " + parentColumn + " = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BranchManagementGUI();
    }
}