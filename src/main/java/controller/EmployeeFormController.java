package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import modle.dto.Employee;
import service.EmployeeService;
import service.Impl.EmployeeServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
    private ObservableList<Employee> employeeSearchList = FXCollections.observableArrayList();

    @FXML
    private Button btnAddButton;

    @FXML
    private Button btnUpdateButton;

    @FXML
    private Button btnGenId;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableView<Employee> tblEmployeeTable;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnAddButtonOnAction(ActionEvent event) {

        if (!txtId.getText().matches(".*[a-zA-Z].*" ) || !txtName.getText().matches(".*[a-zA-Z0-9].*") || !txtRole.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }

        try {
            employeeService.addEmployee(new Employee(
                    txtId.getText(),
                    txtName.getText(),
                    txtRole.getText(),
                    Integer.parseInt(txtContact.getText())
            ));
            loadDataToTable();
            clearFields();
            txtId.setText(employeeService.getNewEmployeeId());
        } catch (RuntimeException e) {

        }
    }

    @FXML
    void showContextMenu(ContextMenuEvent event) {
        MenuItem menuItemDelete = new MenuItem("Delete");

        menuItemDelete.setOnAction( e -> {
            employeeService.deleteEmployee(tblEmployeeTable.getSelectionModel().getSelectedItem());
            loadDataToTable();
        });

        ContextMenu tblMenu = new ContextMenu(menuItemDelete);
        tblMenu.show(tblEmployeeTable,event.getScreenX(),event.getScreenY());
    }

    @FXML
    void btnUpdateButtonOnAction(ActionEvent event) {
        if (!txtId.getText().matches(".*[a-zA-Z].*" ) || !txtName.getText().matches(".*[a-zA-Z0-9].*") || !txtRole.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }
        try {
            employeeService.updateEmployee(new Employee(
                    txtId.getText(),
                    txtName.getText(),
                    txtRole.getText(),
                    Integer.parseInt(txtContact.getText())
            ));
            loadDataToTable();
            clearFields();
            txtId.setText(employeeService.getNewEmployeeId());
        } catch (RuntimeException e) {

        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtRole.clear();
    }

    private void loadDataToTable() {
        employeeObservableList.clear();
        employeeObservableList = employeeService.getAllEmployee();
        tblEmployeeTable.setItems(employeeObservableList);
    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {
        clearFields();
        txtId.setText(employeeService.getNewEmployeeId());
    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        setEmployeeFields(tblEmployeeTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    void txtEmployeeIdOnAction(ActionEvent event) {
        setEmployeeFields(employeeService.getEmployeeById(txtId.getText()));
    }

    public void setEmployeeFields(Employee employee){
        txtId.setText(employee.getEId());
        txtName.setText(employee.getEName());
        txtRole.setText(employee.getERole());
        txtContact.setText(String.valueOf(employee.getEPhoneNumber()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueSetToTable();
        loadDataToTable();
        searchButtonListner();
        textFomatters();
    }

    private void textFomatters(){
        txtId.setEditable(false);
        txtName.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z ]*")){
                return change;
            }
            return null;
        }));

        txtSearch.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z ]*")){
                return change;
            }
            return null;
        }));

        txtRole.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z-_0-9 ]*")){
                return change;
            }
            return null;
        }));

        txtContact.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[0-9]*") ){
                return change;
            }
            return null;
        }));
    }

    private void searchButtonListner() {
        txtSearch.textProperty().addListener((obs,oldText,newText) -> {

            if(newText == null || newText.trim().length()<2){
                loadDataToTable();
                return;
            }
            employeeSearchList = employeeService.searchEmployeeByName(newText);

            tblEmployeeTable.setItems(employeeSearchList);
        });
    }

    private void valueSetToTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("EId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("EName"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("ERole"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("EPhoneNumber"));
    }
}
