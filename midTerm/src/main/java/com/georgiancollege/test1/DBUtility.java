package com.georgiancollege.test1;

import javafx.scene.chart.XYChart;
import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    // Database connection credentials of AWS
    private static String HOST = "172.31.22.43";
    private static int PORT = 3306;
    private static String DB_NAME = "Anmol200534220";
    private static String USERNAME = "Anmol200534220";
    private static String PASSWORD = "m2OuVb_l6I";

    // Method to retrieve employees from the database based on a filter clause
    public static ArrayList<Employee> getEmployeesFromDB(String clause) {
        ArrayList<Employee> employees = new ArrayList<>();

        // SQL query to select specific employee information
        String sql = "SELECT employee_id, first_name, last_name, address, city, province, phone " +
                "FROM midTermEmployee WHERE " + clause + ";";

        try (
                // Establish a database connection using the provided credentials
                Connection connection = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            // Iterate through the result set and populate Employee objects
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String province = resultSet.getString("province");
                String phone = resultSet.getString("phone");

                Employee employee = new Employee(employeeId, firstName, lastName, address, city, province, phone);
                employees.add(employee);
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the database operations
            e.printStackTrace();
        }

        return employees; // Return the list of employees
    }
}
