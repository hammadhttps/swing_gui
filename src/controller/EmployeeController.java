package controller;
import model.EmployeeModel;
import view.EmployeeView;

import java.util.Map;


public class EmployeeController {

    private EmployeeModel model;
    private EmployeeView view;

    // Constructor
    public EmployeeController(EmployeeModel model, EmployeeView view) {
        this.model = model;
        this.view = view;
    }

    // Load employee data
    public void loadEmployeeData() {
        model.loadEmployeeData();
    }

    // Handle employee login
    public boolean handleLogin() {
        String username = view.getUsernameInput();
        String password = view.getPasswordInput();

        if (model.validateEmployee(username, password)) {
            view.showLoginSuccess();
            return true;
        } else {
            view.showLoginFailure();
            return false;
        }
    }

    // Handle password change
    public void handleChangePassword() {
        String username = view.getUsernameInput();
        String currentPassword = view.getPasswordInput();
        String newPassword = view.getNewPasswordInput();

        if (model.changePassword(username, currentPassword, newPassword)) {
            view.showPasswordChangeSuccess(username);
        } else {
            view.showPasswordChangeFailure();
        }
    }
    public boolean handleChangePassword(String username, String currentPassword, String newPassword) {
        if (model.changePassword(username, currentPassword, newPassword)) {
            view.showPasswordChangeSuccess(username);
            return true;
        } else {
           view.showPasswordChangeFailure();
            return false;
        }
    }

    // Display all employees
//    public void displayAllEmployees() {
//        for (Map.Entry<String, String> entry : model.getEmployees().entrySet()) {
//            view.displayEmployee(entry.getKey(), entry.getValue());
//        }
    }

