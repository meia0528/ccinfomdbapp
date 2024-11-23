package GUI;

import databaseConnection.CheckoutTransactions;
import databaseConnection.EmployeeManagementFunctions;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;

public class EmployeeManagementGUI extends JFrame {

    private JFrame employeeManagementFrame;
    private JFrame addEmployeeFrame;
    private JFrame deleteEmployeeFrame;
    private JFrame updateEmployeeFrame;
    private JFrame updateShiftFrame;
    private JFrame viewShiftFrame;
    private JFrame viewSalesFrame;
    private JFrame attendanceFrame;
    private JFrame updatePerformanceFrame;
    private JFrame viewPerformanceFrame;
    private JFrame performanceReportFrame;

    public EmployeeManagementGUI() {

        employeeManagementFrame = new JFrame("Employee Management System");
        employeeManagementFrame.setSize(800, 700);
        employeeManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        employeeManagementFrame.setLocationRelativeTo(null);
        employeeManagementFrame.setResizable(false);
        employeeManagementFrame.setVisible(true);
        employeeManagementFrame.setLayout(null);


        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(0, 0, 800, 120);
        ImageIcon titleLabelImage = new ImageIcon("src/resources/employeelogo.png");
        titleLabel.setIcon(titleLabelImage);
        employeeManagementFrame.add(titleLabel);

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(0, 120, 800, 580);
        employeeManagementFrame.add(buttonPanel);

        JButton btnAddEmployee = new JButton("Add Employee");
        JButton btnUpdateEmployee = new JButton("Update Employee");
        JButton btnDeleteEmployee = new JButton("Delete Employee");
        JButton btnUpdateShift = new JButton("Update Shift");
        JButton btnViewShift = new JButton("View Shift");
        JButton btnViewSales = new JButton("View Sales by Shift");
        JButton btnMarkAttendance = new JButton("Mark Attendance");
        JButton btnUpdatePerformance = new JButton("Update Performance");
        JButton btnViewPerformance = new JButton("View Performance");
        JButton btnPerformanceReport = new JButton("View Performance Report");

        Dimension buttonSize = new Dimension(350, 65);
        btnAddEmployee.setBounds(50, 35, buttonSize.width, buttonSize.height);
        btnUpdateEmployee.setBounds(50, 135, buttonSize.width, buttonSize.height);
        btnDeleteEmployee.setBounds(50, 235, buttonSize.width, buttonSize.height);
        btnUpdateShift.setBounds(50, 335, buttonSize.width, buttonSize.height);
        btnViewShift.setBounds(50, 435, buttonSize.width, buttonSize.height);

        btnViewSales.setBounds(400, 35, buttonSize.width, buttonSize.height);
        btnMarkAttendance.setBounds(400, 135, buttonSize.width, buttonSize.height);
        btnUpdatePerformance.setBounds(400, 235, buttonSize.width, buttonSize.height);
        btnViewPerformance.setBounds(400, 335, buttonSize.width, buttonSize.height);
        btnPerformanceReport.setBounds(400, 435, buttonSize.width, buttonSize.height);

        btnAddEmployee.setFont(new Font("Arial", Font.BOLD, 16));
        btnAddEmployee.setForeground(Color.WHITE);

        btnUpdateEmployee.setFont(new Font("Arial", Font.BOLD, 16));
        btnUpdateEmployee.setForeground(Color.WHITE);

        btnDeleteEmployee.setFont(new Font("Arial", Font.BOLD, 16));
        btnDeleteEmployee.setForeground(Color.WHITE);

        btnUpdateShift.setFont(new Font("Arial", Font.BOLD, 16));
        btnUpdateShift.setForeground(Color.WHITE);

        btnViewShift.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewShift.setForeground(Color.WHITE);

        btnViewSales.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewSales.setForeground(Color.WHITE);

        btnMarkAttendance.setFont(new Font("Arial", Font.BOLD, 16));
        btnMarkAttendance.setForeground(Color.WHITE);

        btnUpdatePerformance.setFont(new Font("Arial", Font.BOLD, 16));
        btnUpdatePerformance.setForeground(Color.WHITE);

        btnViewPerformance.setFont(new Font("Arial", Font.BOLD, 16));
        btnViewPerformance.setForeground(Color.WHITE);

        btnPerformanceReport.setFont(new Font("Arial", Font.BOLD, 16));
        btnPerformanceReport.setForeground(Color.WHITE);

        btnAddEmployee.setBackground(new Color(0xF48115));
        btnUpdateEmployee.setBackground(new Color(0x008163));
        btnDeleteEmployee.setBackground(new Color(0xF48115));
        btnUpdateShift.setBackground(new Color(0x008163));
        btnViewShift.setBackground(new Color(0xEE2526));
        btnViewSales.setBackground(new Color(0xF48115));
        btnMarkAttendance.setBackground(new Color(0x008163));
        btnUpdatePerformance.setBackground(new Color(0xF48115));
        btnViewPerformance.setBackground(new Color(0x008163));
        btnPerformanceReport.setBackground(new Color(0xEE2526));

        buttonPanel.add(btnAddEmployee);
        buttonPanel.add(btnUpdateEmployee);
        buttonPanel.add(btnDeleteEmployee);
        buttonPanel.add(btnUpdateShift);
        buttonPanel.add(btnViewShift);
        buttonPanel.add(btnViewSales);
        buttonPanel.add(btnMarkAttendance);
        buttonPanel.add(btnUpdatePerformance);
        buttonPanel.add(btnViewPerformance);
        buttonPanel.add(btnPerformanceReport);

        btnAddEmployee.addActionListener(e -> addEmployeeGUI());
        btnUpdateEmployee.addActionListener(e -> updateEmployeeGUI());
        btnDeleteEmployee.addActionListener(e -> deleteEmployeeGUI());
        btnUpdateShift.addActionListener(e -> updateShiftGUI());
        btnViewShift.addActionListener(e -> viewShiftGUI());
        btnViewSales.addActionListener(e -> viewSalesGUI());
        btnMarkAttendance.addActionListener(e -> markAttendanceGUI());
        btnUpdatePerformance.addActionListener(e -> updatePerformanceGUI());
        btnViewPerformance.addActionListener(e -> viewPerformanceGUI());
        btnPerformanceReport.addActionListener(e -> performanceReportGUI());

        employeeManagementFrame.setVisible(true);
    }

    private void addEmployeeGUI() {

        addEmployeeFrame = new JFrame("Checkout Transaction");
        addEmployeeFrame.setSize(800, 700);
        addEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEmployeeFrame.setLocationRelativeTo(null);
        addEmployeeFrame.setResizable(false);
        addEmployeeFrame.setLayout(null);

        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(employeeIDLabel);

        JTextField employeeIDTextField = new JTextField();
        employeeIDTextField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(employeeIDTextField);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(firstNameField);

        JLabel emailLabel = new JLabel("Employee Email: ");
        emailLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(emailField);

        JLabel contactNumberLabel = new JLabel("Contact Number: ");
        contactNumberLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(contactNumberLabel);

        JTextField contactNumberField = new JTextField();
        contactNumberField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(contactNumberField);

        JLabel jobTitleLabel = new JLabel("Job Title: ");
        jobTitleLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(jobTitleLabel);

        JTextField jobTitleField = new JTextField();
        jobTitleField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(jobTitleField);

        JLabel hireDateLabel = new JLabel("Hire Date: ");
        hireDateLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(hireDateLabel);

        JTextField hireDateField = new JTextField();
        hireDateField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(hireDateField);

        JLabel employeeScheduleLabel = new JLabel("Employee Schedule: ");
        employeeScheduleLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(employeeScheduleLabel);

        JTextField employeeScheduleField = new JTextField();
        employeeScheduleField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(employeeScheduleField);

        JLabel hourlyRateLabel = new JLabel("Hourly Rate: ");
        hourlyRateLabel.setBounds(30,30,150,25);
        addEmployeeFrame.add(hourlyRateLabel);

        JTextField hourlyRateField = new JTextField();
        hourlyRateField.setBounds(200, 110, 200, 25);
        addEmployeeFrame.add(hourlyRateField);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.setBounds(30, 160, 150, 35);
        addEmployeeButton.setFont(new Font("Arial", Font.BOLD, 16));
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.setBackground(new Color(0x008163));
        addEmployeeFrame.add(addEmployeeButton);

        JTextArea message = new JTextArea();
        message.setBounds(30, 260, 500, 200);
        message.setEditable(false);
        addEmployeeFrame.add(message);

        addEmployeeButton.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                double contactNumber = Double.parseDouble(contactNumberField.getText());
                String jobTitle = jobTitleField.getText();
                String hireDate = hireDateField.getText();
                String schedule = employeeScheduleField.getText();
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());

                EmployeeManagementFunctions.addEmployee(firstName, lastName, email, contactNumber, jobTitle, hireDate, schedule, hourlyRate);

                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }

        });
        addEmployeeFrame.setVisible(true);
    }

    private void updateEmployeeGUI() {
        updateEmployeeFrame = new JFrame("Update Employee");
        updateEmployeeFrame.setSize(800, 700);
        updateEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateEmployeeFrame.setLocationRelativeTo(null);
        updateEmployeeFrame.setResizable(false);
        updateEmployeeFrame.setLayout(null);

        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(30, 30, 150, 25);
        updateEmployeeFrame.add(employeeIDLabel);

        JTextField employeeIDField = new JTextField();
        employeeIDField.setBounds(200, 30, 200, 25);
        updateEmployeeFrame.add(employeeIDField);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(30, 80, 150, 25);
        updateEmployeeFrame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 80, 200, 25);
        updateEmployeeFrame.add(emailField);

        JLabel contactLabel = new JLabel("Contact Number: ");
        contactLabel.setBounds(30, 130, 150, 25);
        updateEmployeeFrame.add(contactLabel);

        JTextField contactField = new JTextField();
        contactField.setBounds(200, 130, 200, 25);
        updateEmployeeFrame.add(contactField);

        JLabel jobTitleLabel = new JLabel("Job Title: ");
        jobTitleLabel.setBounds(30, 180, 150, 25);
        updateEmployeeFrame.add(jobTitleLabel);

        JTextField jobTitleField = new JTextField();
        jobTitleField.setBounds(200, 180, 200, 25);
        updateEmployeeFrame.add(jobTitleField);

        JLabel hourlyRateLabel = new JLabel("Hourly Rate: ");
        hourlyRateLabel.setBounds(30, 230, 150, 25);
        updateEmployeeFrame.add(hourlyRateLabel);

        JTextField hourlyRateField = new JTextField();
        hourlyRateField.setBounds(200, 230, 200, 25);
        updateEmployeeFrame.add(hourlyRateField);

        JButton updateButton = new JButton("Update Employee");
        updateButton.setBounds(200, 300, 200, 35);
        updateButton.setBackground(new Color(0x008163));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateEmployeeFrame.add(updateButton);

        updateButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIDField.getText());
                String email = emailField.getText().isEmpty() ? null : emailField.getText();
                Double contact = contactField.getText().isEmpty() ? null : Double.parseDouble(contactField.getText());
                String jobTitle = jobTitleField.getText().isEmpty() ? null : jobTitleField.getText();
                Double hourlyRate = hourlyRateField.getText().isEmpty() ? null : Double.parseDouble(hourlyRateField.getText());

                EmployeeManagementFunctions.updateEmployee(employeeId, email, contact, jobTitle, hourlyRate);
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        updateEmployeeFrame.setVisible(true);
    }
    private void deleteEmployeeGUI() {
        deleteEmployeeFrame = new JFrame("Delete Employee");
        deleteEmployeeFrame.setSize(800, 700);
        deleteEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteEmployeeFrame.setLocationRelativeTo(null);
        deleteEmployeeFrame.setResizable(false);
        deleteEmployeeFrame.setLayout(null);

        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(30, 50, 150, 25);
        deleteEmployeeFrame.add(employeeIDLabel);

        JTextField employeeIDField = new JTextField();
        employeeIDField.setBounds(150, 50, 200, 25);
        deleteEmployeeFrame.add(employeeIDField);

        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.setBounds(100, 120, 200, 35);
        deleteButton.setBackground(new Color(0xF48115));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteEmployeeFrame.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIDField.getText());
                EmployeeManagementFunctions.deleteEmployee(employeeId);
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        deleteEmployeeFrame.setVisible(true);
    }

    private void updateShiftGUI() {
        updateShiftFrame = new JFrame("Update Shift");
        updateShiftFrame.setSize(800, 700);
        updateShiftFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateShiftFrame.setLocationRelativeTo(null);
        updateShiftFrame.setResizable(false);
        updateShiftFrame.setLayout(null);

        JLabel employeeIDLabel = new JLabel("Employee ID: ");
        employeeIDLabel.setBounds(30, 50, 150, 25);
        updateShiftFrame.add(employeeIDLabel);

        JTextField employeeIDField = new JTextField();
        employeeIDField.setBounds(150, 50, 200, 25);
        updateShiftFrame.add(employeeIDField);

        JLabel shiftLabel = new JLabel("Shift: ");
        shiftLabel.setBounds(30, 100, 150, 25);
        updateShiftFrame.add(shiftLabel);

        JTextField shiftField = new JTextField();
        shiftField.setBounds(150, 100, 200, 25);
        updateShiftFrame.add(shiftField);

        JButton updateShiftButton = new JButton("Update Shift");
        updateShiftButton.setBounds(100, 180, 200, 35);
        updateShiftButton.setBackground(new Color(0x008163));
        updateShiftButton.setForeground(Color.WHITE);
        updateShiftButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateShiftFrame.add(updateShiftButton);

        updateShiftButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIDField.getText());
                String shift = shiftField.getText();
                EmployeeManagementFunctions.updateShift(employeeId, shift);
                JOptionPane.showMessageDialog(this, "Shift updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        updateShiftFrame.setVisible(true);
    }

    private void viewShiftGUI() {
        viewShiftFrame = new JFrame("View Shift");
        viewShiftFrame.setSize(800, 700);
        viewShiftFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewShiftFrame.setLocationRelativeTo(null);
        viewShiftFrame.setResizable(false);
        viewShiftFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        viewShiftFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 200, 25);
        viewShiftFrame.add(employeeIdField);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 150, 700, 200);
        resultArea.setEditable(false);
        viewShiftFrame.add(resultArea);

        JButton viewShiftButton = new JButton("View Shift");
        viewShiftButton.setBounds(50, 100, 150, 30);
        viewShiftButton.setBackground(new Color(0x008163));
        viewShiftButton.setForeground(Color.WHITE);
        viewShiftButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewShiftButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                ResultSet rs = EmployeeManagementFunctions.viewShift(employeeId);

                if (rs.next()) {
                    String schedule = rs.getString("employee_schedule");
                    resultArea.setText("Employee Schedule: " + schedule);
                } else {
                    resultArea.setText("No shift found for Employee ID: " + employeeId);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewShiftFrame, "Error: " + ex.getMessage());
            }
        });
        viewShiftFrame.add(viewShiftButton);

        viewShiftFrame.setVisible(true);
    }

    private void viewSalesGUI() {
        viewSalesFrame = new JFrame("View Sales by Shift");
        viewSalesFrame.setSize(800, 700);
        viewSalesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewSalesFrame.setLocationRelativeTo(null);
        viewSalesFrame.setResizable(false);
        viewSalesFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        viewSalesFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 200, 25);
        viewSalesFrame.add(employeeIdField);

        JLabel shiftDateLabel = new JLabel("Shift Date (YYYY-MM-DD):");
        shiftDateLabel.setBounds(50, 100, 200, 25);
        viewSalesFrame.add(shiftDateLabel);

        JTextField shiftDateField = new JTextField();
        shiftDateField.setBounds(200, 100, 200, 25);
        viewSalesFrame.add(shiftDateField);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 200, 700, 250);
        resultArea.setEditable(false);
        viewSalesFrame.add(resultArea);

        JButton viewSalesButton = new JButton("View Sales");
        viewSalesButton.setBounds(50, 150, 150, 30);
        viewSalesButton.setBackground(new Color(0xF48115));
        viewSalesButton.setForeground(Color.WHITE);
        viewSalesButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewSalesButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                Date shiftDate = Date.valueOf(shiftDateField.getText());
                ResultSet rs = EmployeeManagementFunctions.viewSalesByShift(employeeId, shiftDate);

                StringBuilder result = new StringBuilder("Sales Data:\n");
                while (rs.next()) {
                    result.append("Product Name: ").append(rs.getString("product_name"))
                            .append(", Quantity: ").append(rs.getInt("quantity_ordered"))
                            .append(", Amount: ").append(rs.getDouble("sales_amount"))
                            .append(", Date: ").append(rs.getDate("sale_date")).append("\n");
                }
                resultArea.setText(result.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewSalesFrame, "Error: " + ex.getMessage());
            }
        });
        viewSalesFrame.add(viewSalesButton);

        viewSalesFrame.setVisible(true);
    }

    private void markAttendanceGUI() {
        attendanceFrame = new JFrame("Mark Attendance");
        attendanceFrame.setSize(800, 700);
        attendanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        attendanceFrame.setLocationRelativeTo(null);
        attendanceFrame.setResizable(false);
        attendanceFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        attendanceFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 150, 25);
        attendanceFrame.add(employeeIdField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(50, 100, 150, 25);
        attendanceFrame.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(200, 100, 150, 25);
        attendanceFrame.add(dateField);

        JButton markAttendanceButton = new JButton("Mark Attendance");
        markAttendanceButton.setBounds(100, 150, 200, 40);
        markAttendanceButton.setBackground(new Color(0xF48115));
        markAttendanceButton.setForeground(Color.WHITE);
        markAttendanceButton.setFont(new Font("Arial", Font.BOLD, 16));
        markAttendanceButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                Date attendanceDate = Date.valueOf(dateField.getText());
                EmployeeManagementFunctions.markAttendance(employeeId, attendanceDate);

                JOptionPane.showMessageDialog(attendanceFrame, "Attendance marked successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(attendanceFrame, "Error: " + ex.getMessage());
            }
        });
        attendanceFrame.add(markAttendanceButton);

        attendanceFrame.setVisible(true);
    }

    private void updatePerformanceGUI() {
        updatePerformanceFrame = new JFrame("Update Performance");
        updatePerformanceFrame.setSize(800, 700);
        updatePerformanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updatePerformanceFrame.setLocationRelativeTo(null);
        updatePerformanceFrame.setResizable(false);
        updatePerformanceFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        updatePerformanceFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 150, 25);
        updatePerformanceFrame.add(employeeIdField);

        JLabel performanceLabel = new JLabel("Performance Rating:");
        performanceLabel.setBounds(50, 100, 150, 25);
        updatePerformanceFrame.add(performanceLabel);

        JTextField performanceField = new JTextField();
        performanceField.setBounds(200, 100, 150, 25);
        updatePerformanceFrame.add(performanceField);

        JButton updatePerformanceButton = new JButton("Update Performance");
        updatePerformanceButton.setBounds(100, 150, 200, 40);
        updatePerformanceButton.setBackground(new Color(0x008163));
        updatePerformanceButton.setForeground(Color.WHITE);
        updatePerformanceButton.setFont(new Font("Arial", Font.BOLD, 16));
        updatePerformanceButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                int performanceRating = Integer.parseInt(performanceField.getText());
                EmployeeManagementFunctions.updatePerformance(employeeId, performanceRating);

                JOptionPane.showMessageDialog(updatePerformanceFrame, "Performance updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(updatePerformanceFrame, "Error: " + ex.getMessage());
            }
        });
        updatePerformanceFrame.add(updatePerformanceButton);

        updatePerformanceFrame.setVisible(true);
    }

    private void viewPerformanceGUI() {
        viewPerformanceFrame = new JFrame("View Performance");
        viewPerformanceFrame.setSize(800, 700);
        viewPerformanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewPerformanceFrame.setLocationRelativeTo(null);
        viewPerformanceFrame.setResizable(false);
        viewPerformanceFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 150, 25);
        viewPerformanceFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 200, 25);
        viewPerformanceFrame.add(employeeIdField);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 150, 500, 200);
        resultArea.setEditable(false);
        viewPerformanceFrame.add(resultArea);

        JButton viewPerformanceButton = new JButton("View Performance");
        viewPerformanceButton.setBounds(50, 100, 200, 30);
        viewPerformanceButton.setBackground(new Color(0xF48115));
        viewPerformanceButton.setForeground(Color.WHITE);
        viewPerformanceButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewPerformanceButton.addActionListener(e -> {
            try {
                int employeeId = Integer.parseInt(employeeIdField.getText());
                ResultSet rs = EmployeeManagementFunctions.viewPerformance(employeeId);

                if (rs.next()) {
                    StringBuilder performance = new StringBuilder("Performance Data:\n");
                    performance.append("Rating: ").append(rs.getInt("performance_rating")).append("\n")
                            .append("Remarks: ").append(rs.getString("remarks")).append("\n");
                    resultArea.setText(performance.toString());
                } else {
                    resultArea.setText("No performance data found for Employee ID: " + employeeId);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(viewPerformanceFrame, "Error: " + ex.getMessage());
            }
        });
        viewPerformanceFrame.add(viewPerformanceButton);

        viewPerformanceFrame.setVisible(true);
    }

    public void performanceReportGUI() {
        performanceReportFrame = new JFrame("Performance Report");
        performanceReportFrame.setSize(800, 700));
        performanceReportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        performanceReportFrame.setLocationRelativeTo(null);
        performanceReportFrame.setResizable(false);
        performanceReportFrame.setLayout(null);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setBounds(50, 50, 200, 25);
        performanceReportFrame.add(employeeIdLabel);

        JTextField employeeIdField = new JTextField();
        employeeIdField.setBounds(200, 50, 200, 25);
        performanceReportFrame.add(employeeIdField);

        JLabel yearLabel = new JLabel("Year (YYYY):");
        yearLabel.setBounds(50, 90, 200, 25);
        performanceReportFrame.add(yearLabel);

        JTextField yearField = new JTextField();
        yearField.setBounds(200, 90, 200, 25);
        performanceReportFrame.add(yearField);

        JLabel monthLabel = new JLabel("Month (MM):");
        monthLabel.setBounds(50, 130, 200, 25);
        performanceReportFrame.add(monthLabel);

        JTextField monthField = new JTextField();
        monthField.setBounds(200, 130, 200, 25);
        performanceReportFrame.add(monthField);

        // Report Area
        JTextArea reportArea = new JTextArea();
        reportArea.setBounds(50, 200, 700, 250);
        reportArea.setEditable(false);
        performanceReportFrame.add(reportArea);

        // Generate Report Button
        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setBounds(50, 170, 200, 30);
        generateReportButton.setBackground(new Color(0xF48115));
        generateReportButton.setForeground(Color.WHITE);
        generateReportButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateReportButton.addActionListener(e -> {
            try {
                // Validate inputs
                int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                int year = Integer.parseInt(yearField.getText().trim());
                int month = Integer.parseInt(monthField.getText().trim());

                if (year < 1 || month < 1 || month > 12) {
                    throw new IllegalArgumentException("Invalid year or month.");
                }

                // Get performance report based on employeeId, year, and month
                ResultSet rs = EmployeeManagementFunctions.getPerformanceReport(employeeId, year, month);

                // Build the report
                StringBuilder report = new StringBuilder("Performance Report:\n");
                while (rs.next()) {
                    report.append("Employee ID: ").append(rs.getInt("employee_id"))
                            .append(", Name: ").append(rs.getString("first_name")).append(" ")
                            .append(rs.getString("last_name"))
                            .append(", Rating: ").append(rs.getDouble("average_rating"))
                            .append(", Total Sales: ").append(rs.getDouble("total_sales"))
                            .append(", Days Present: ").append(rs.getInt("total_days_present")).append("\n");
                }
                reportArea.setText(report.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(performanceReportFrame, "Error: " + ex.getMessage());
            }
        });
        performanceReportFrame.add(generateReportButton);

        performanceReportFrame.setVisible(true);
    }

}

