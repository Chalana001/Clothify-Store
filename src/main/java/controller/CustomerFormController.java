package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import modle.dto.Customer;
import modle.dto.Supplier;
import service.CustomerService;
import service.Impl.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    ObservableList<Customer> cusDetailsArray = FXCollections.observableArrayList();
    private CustomerService customerService = new CustomerServiceImpl();
    ObservableList<Customer> searchedCustomerList;


    private Customer updateCustomer;

    @FXML
    private Button btnAddButton;

    @FXML
    private Button btnGenId;

    @FXML
    private TableColumn<Customer, Integer> colContact;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, String> colId;

    @FXML
    private TableColumn<Customer, String> colName;

    @FXML
    private TableView<Customer> tblCustomerTable;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    private void showContextMenu(ContextMenuEvent event) {

        MenuItem menuItemDelete = new MenuItem("Delete");

        menuItemDelete.setOnAction( e -> {
            customerService.deleteCustomer(tblCustomerTable.getSelectionModel().getSelectedItem());
            loadDataToTable();
            clearFields();
        });

        ContextMenu tblMenu = new ContextMenu(menuItemDelete);
        tblMenu.show(tblCustomerTable,event.getScreenX(),event.getScreenY());

    }


    @FXML
    void btnAddButtonOnAction(ActionEvent event) {

        if (!txtId.getText().matches(".*[a-zA-Z].*" ) || !txtName.getText().matches(".*[a-zA-Z0-9].*") || !txtEmail.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }
        try {
            customerService.addCustomer(new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtContact.getText()),
                    txtEmail.getText()
            ));
            loadDataToTable();
            clearFields();
        } catch (RuntimeException e) {

        }
    }

    @FXML
    void btnUpdateButtonOnAction(ActionEvent event) {

        if (!txtId.getText().matches(".*[a-zA-Z].*" ) || !txtName.getText().matches(".*[a-zA-Z0-9].*") || !txtEmail.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }
        try {
            customerService.updateCustomer(new Customer(
                    txtId.getText(),
                    txtName.getText(),
                    Integer.parseInt(txtContact.getText()),
                    txtEmail.getText()
            ));
            loadDataToTable();
        } catch (RuntimeException e) {

        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtEmail.clear();
        txtId.setText(customerService.getNewCustomerId());
    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {
        txtId.setText(customerService.getNewCustomerId());
    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        Customer customer = tblCustomerTable.getSelectionModel().getSelectedItem();
        setCustomerFields(customer);
    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        try {
            setCustomerFields(customerService.getCustomerById(txtId.getText()));
        } catch (RuntimeException e) {

        }
    }

    private void setCustomerFields(Customer customer){
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtContact.setText(String.valueOf(customer.getPhoneNumber()));
        txtEmail.setText(customer.getEmail());
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
        txtEmail.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z0-9-_.@ ]*")){
                return change;
            }
            return null;
        }));

        txtContact.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[0-9]*")){
                return change;
            }
            return null;
        }));

    }

    private void searchButtonListner() {
//        try {
//            txtSearch.textProperty().addListener((obs,oldText,newText) -> {
//                suggestionCustomerNameMenu.hide();
//                suggestionCustomerNameMenu.getItems().clear();
//
//                if(newText == null || newText.trim().length()<2){
//                    return;
//                }
//
//                ObservableList<Customer> customerList = customerService.searchCustomerByNameSearch(newText);
//
//                for (Customer customer: customerList){
//
//                    Label label = new Label(customer.getId() +" - " +customer.getName());
//                    label.setStyle("-fx-font-weight: bold;");
//
//                    MenuItem menuItem = new MenuItem();
//                    menuItem.setGraphic(label);
//                    menuItem.setOnAction((e -> {
//                        setCustomerFields(customer);
//
//                    }));
//                    suggestionCustomerNameMenu.getItems().add(menuItem);
//                }
//
//                if (!suggestionCustomerNameMenu.getItems().isEmpty()) {
//                    suggestionCustomerNameMenu.show(txtSearch, Side.BOTTOM, 0, 0);
//                }
//
//            });
//        } catch (RuntimeException e) {
//
//        }

        txtSearch.textProperty().addListener((obs,oldText,newText) -> {

            if(newText == null || newText.trim().length()<2){
                loadDataToTable();
                return;
            }
            searchedCustomerList = customerService.searchCustomerByNameSearch(newText);

            tblCustomerTable.setItems(searchedCustomerList);
        });
    }

    private void valueSetToTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadDataToTable() {
        cusDetailsArray.clear();
        cusDetailsArray = customerService.getAllCustomers();
        tblCustomerTable.setItems(cusDetailsArray);
    }
}
