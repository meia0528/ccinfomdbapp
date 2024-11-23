package databaseConnection;

import java.sql.*;

public class EmployeeManagementFunctions {
    // Add Employee
    public static void addEmployee(String firstName, String lastName, String email, double contactNumber,
                                   String jobTitle, String hireDate, String schedule, double hourlyRate) throws SQLException {
        String sql = "INSERT INTO employees (first_name, last_name, employee_email, contact_number, job_title, hire_date, employee_schedule, hourly_rate)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setDouble(4, contactNumber);
            pst.setString(5, jobTitle);
            pst.setString(6, hireDate);
            pst.setString(7, schedule);
            pst.setDouble(8, hourlyRate);
            pst.executeUpdate();
        }
    }

    // Update Employee
    public static void updateEmployee(int employeeId, String email, Double contactNumber, String jobTitle, Double hourlyRate) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE employees SET ");
        boolean isFirst = true;

        if (email != null) {
            sql.append("employee_email = ? ");
            isFirst = false;
        }

        if (contactNumber != null) {
            if (!isFirst) sql.append(", ");
            sql.append("contact_number = ? ");
            isFirst = false;
        }

        if (jobTitle != null) {
            if (!isFirst) sql.append(", ");
            sql.append("job_title = ? ");
        }

        if (hourlyRate != null) {
            if (!isFirst) sql.append(", ");
            sql.append("hourly_rate = ? ");
        }

        sql.append("WHERE employee_id = ?");

        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql.toString())) {
            int index = 1;
            if (email != null) pst.setString(index++, email);
            if (null != contactNumber) pst.setString(index++, String.valueOf(contactNumber));
            if (jobTitle != null) pst.setString(index++, jobTitle);
            if (hourlyRate != null) pst.setDouble(index++, hourlyRate);
            pst.setInt(index, employeeId);
            pst.executeUpdate();
        }
    }

    // Delete Employee
    public static void deleteEmployee(int employeeId) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, employeeId);
            pst.executeUpdate();
        }
    }

    // Update Employee Shift
    public static void updateShift(int employeeId, String shift) throws SQLException {
        String sql = "UPDATE employees SET employee_schedule = ? WHERE employee_id = ?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, shift);
            pst.setInt(2, employeeId);
            pst.executeUpdate();
        }
    }

    // View Employee Shift
    public static ResultSet viewShift(int employeeId) throws SQLException {
        String sql = "SELECT employee_schedule FROM employees WHERE employee_id = ?";
        Connection connection = SQLConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, employeeId);
        return pst.executeQuery();
    }

    // View Sales by Shift
    public static ResultSet viewSalesByShift(int employeeId, Date shiftDate) throws SQLException {
        String sql = "SELECT p.product_name, s.quantity_ordered, s.selling_price, s.sales_amount, s.sale_date " +
                "FROM sales s " +
                "JOIN products p ON s.product_id = p.product_id " +
                "WHERE s.employee_id = ? AND s.sale_date = ?";
        Connection connection = SQLConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, employeeId);
        pst.setDate(2, shiftDate);
        return pst.executeQuery();
    }

    // Mark Attendance
    public static void markAttendance(int employeeId, Date attendanceDate) throws SQLException {
        String sql = "INSERT INTO attendance (employee_id, attendance_date) VALUES (?, ?)";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, employeeId);
            pst.setDate(2, attendanceDate);
            pst.executeUpdate();
        }
    }

    // Update Performance Review
    public static void updatePerformance(int employeeId, int performanceRating) throws SQLException {
        String sql = "UPDATE performance_reviews SET performance_ratings = ? WHERE employee_id = ?";
        try (Connection connection = SQLConnection.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, performanceRating);
            pst.setInt(2, employeeId);
            pst.executeUpdate();
        }
    }

    // View Performance Review
    public static ResultSet viewPerformance(int employeeId) throws SQLException {
        String sql = "SELECT date_updated, performance_ratings FROM performance_reviews WHERE employee_id = ?";
        Connection connection = SQLConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, employeeId);
        return pst.executeQuery();
    }

    // Get Performance Report for the given year and month
    public static ResultSet getPerformanceReport(int employeeId, int year, int month) throws SQLException {
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.hourly_rate, " +
                "SUM(CASE WHEN a.attendance_date IS NOT NULL THEN 1 ELSE 0 END) AS total_days_present, " +
                "AVG(pr.performance_ratings) AS average_rating, " +
                "SUM(s.sales_amount) AS total_sales " +
                "FROM employees e " +
                "LEFT JOIN attendance a ON e.employee_id = a.employee_id AND YEAR(a.attendance_date) = ? AND MONTH(a.attendance_date) = ? " +
                "LEFT JOIN performance_reviews pr ON e.employee_id = pr.employee_id " +
                "LEFT JOIN sales s ON e.employee_id = s.employee_id " +
                "WHERE e.employee_id = ? " +
                "GROUP BY e.employee_id";
        Connection connection = SQLConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, year);
        pst.setInt(2, month);
        pst.setInt(3, employeeId);
        return pst.executeQuery();
    }
}