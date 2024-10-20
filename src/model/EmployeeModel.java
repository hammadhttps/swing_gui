package model;
import java.io.*;
import java.util.*;


public class EmployeeModel {
    private String filename;
    private Map<String, String> employees = new HashMap<>();

    // Constructor
    public EmployeeModel(String filename) {
        this.filename = filename;
    }

    // Getter for filename
    public String getFilename() {
        return filename;
    }

    // Setter for filename
    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Getter for employees
    public Map<String, String> getEmployees() {
        return employees;
    }

    // Load employee data from file
    public void loadEmployeeData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0].trim(); // remove extra spaces
                    String pass = parts[1].trim(); // remove extra spaces
                    employees.put(username, pass);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Save employee data to file
    public void saveEmployeeData() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Map.Entry<String, String> entry : employees.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Change employee password
    public boolean changePassword(String username, String currentPassword, String newPassword) {
        if (employees.containsKey(username) && employees.get(username).equals(currentPassword)) {
            employees.put(username, newPassword);
            saveEmployeeData();
            return true;
        }
        return false;
    }

    // Validate employee login
    public boolean validateEmployee(String username, String password) {
        return employees.containsKey(username) && employees.get(username).equals(password);
    }
}
