package com.georgiancollege.test1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, Integer> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> firstNameColumn;

    @FXML
    private TableColumn<Employee, String> lastNameColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private TableColumn<Employee, String> cityColumn;

    @FXML
    private TableColumn<Employee, String> provinceColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private CheckBox ontarioOnlyCheckBox;

    @FXML
    private ComboBox<String> areaCodeComboBox;

    @FXML
    private Label noOfEmployeesLabel;

    private ArrayList<Employee> allEmployees;
    private ArrayList<Employee> filteredEmployees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allEmployees = DBUtility.getEmployeesFromDB("1");

        setupEmployeeTableColumns();

        initializeAreaCodeFilter();

        initializeEmployeeTable();

        updateEmployeeCountLabel();
    }

    private void setupEmployeeTableColumns() {
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void initializeAreaCodeFilter() {
        HashSet<String> areaCodes = extractAreaCodes();

        populateAreaCodeComboBox(areaCodes);
    }

    private HashSet<String> extractAreaCodes() {
        HashSet<String> areaCodes = new HashSet<>();

        for (Employee employee : allEmployees) {
            String phoneNumber = employee.getPhone();
            if (phoneNumber.length() >= 3) {
                String areaCode = phoneNumber.substring(0, 3);
                areaCodes.add(areaCode);
            }
        }

        return areaCodes;
    }

    private void populateAreaCodeComboBox(HashSet<String> areaCodes) {
        ArrayList<String> sortedAreaCodes = new ArrayList<>(areaCodes);
        sortedAreaCodes.add(0, "All");

        areaCodeComboBox.setItems(FXCollections.observableArrayList(sortedAreaCodes));
        areaCodeComboBox.getSelectionModel().select(0);
    }

    private void initializeEmployeeTable() {
        filteredEmployees = new ArrayList<>(allEmployees);
        tableView.getItems().addAll(filteredEmployees);
    }

    @FXML
    void ontarioOnlyCheckBox_OnClick(ActionEvent event) {
        filterEmployees();
    }

    @FXML
    void areaCodeComboBox_OnClick(ActionEvent event) {
        filterEmployees();
    }

    private void filterEmployees() {
        filteredEmployees.clear();
        String selectedAreaCode = areaCodeComboBox.getSelectionModel().getSelectedItem();
        boolean isOntarioOnly = ontarioOnlyCheckBox.isSelected();

        for (Employee employee : allEmployees) {
            boolean addEmployee = true;
            if (isOntarioOnly && !employee.getProvince().equals("ON")) {
                addEmployee = false;
            }
            if (!selectedAreaCode.equals("All") && !employee.getPhone().startsWith(selectedAreaCode)) {
                addEmployee = false;
            }
            if (addEmployee) {
                filteredEmployees.add(employee);
            }
        }

        tableView.getItems().setAll(filteredEmployees);
        updateEmployeeCountLabel();
    }

    private void updateEmployeeCountLabel() {
        noOfEmployeesLabel.setText("No. of Employees: " + filteredEmployees.size());
    }
}
