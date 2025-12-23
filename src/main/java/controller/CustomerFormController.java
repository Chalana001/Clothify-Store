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
import service.CustomerService;
import service.CustomerServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    ObservableList<Customer> cusDetailsArray = FXCollections.observableArrayList();
    private CustomerService customerService = new CustomerServiceImpl();

    ContextMenu suggestionCustomerNameMenu = new ContextMenu();

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

        Customer selectedCustomer = tblCustomerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }

        MenuItem edit = new MenuItem("Editable (False)");
        MenuItem delete = new MenuItem("Delete");

        edit.setOnAction(e -> {

            tblCustomerTable.setEditable(true);
            colName.setEditable(true);
            colEmail.setEditable(true);
            colContact.setEditable(true);

        });

        delete.setOnAction(e -> {
            customerService.deleteCustomer(selectedCustomer);
            loadDataToTable();
        });

        ContextMenu tblMenu = new ContextMenu(edit, delete);
        tblMenu.show(tblCustomerTable, event.getScreenX(), event.getScreenY());

    }

    private void cellEditComits() {

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colContact.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        colName.setOnEditCommit(ev -> {

            Customer customer = ev.getRowValue();
            String newName = ev.getNewValue();
            if (newName.equals(ev.getOldValue())){
                return;
            }
            customer.setName(newName);
            customerService.updateCustomer(customer);
        });

        colEmail.setOnEditCommit(ev -> {

            Customer customer = ev.getRowValue();
            String newEmail = ev.getNewValue();
            if (newEmail.equals(ev.getOldValue())){
                return;
            }
            customer.setEmail(newEmail);
            customerService.updateCustomer(customer);
        });

        colContact.setOnEditCommit(ev -> {

            Customer customer = ev.getRowValue();
            Integer newPhone = ev.getNewValue();
            if (newPhone.equals(ev.getOldValue())){
                return;
            }
            customer.setPhoneNumber(newPhone);
            customerService.updateCustomer(customer);
        });

        tblCustomerTable.setEditable(false);
        colName.setEditable(false);
        colEmail.setEditable(false);
        colContact.setEditable(false);

        loadDataToTable();
    }

    @FXML
    void btnAddButtonOnAction(ActionEvent event) {

        customerService.addCustomer(new Customer(
                txtId.getText(),
                txtName.getText(),
                Integer.parseInt(txtContact.getText()),
                txtEmail.getText()
        ));
        loadDataToTable();
        clearFields();
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
        System.out.println("clicked");

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
        tblCustomerTable.setEditable(false);

        cellEditComits();
    }

    private void searchButtonListner() {
        try {
            txtSearch.textProperty().addListener((obs,oldText,newText) -> {
                suggestionCustomerNameMenu.hide();
                suggestionCustomerNameMenu.getItems().clear();

                if(newText == null || newText.trim().length()<2){
                    return;
                }

                ObservableList<Customer> customerList = customerService.searchCustomerByNameSearch(newText);

                for (Customer customer: customerList){

                    Label label = new Label(customer.getId() +" - " +customer.getName());
                    label.setStyle("-fx-font-weight: bold;");

                    MenuItem menuItem = new MenuItem();
                    menuItem.setGraphic(label);
                    menuItem.setOnAction((e -> {
                        setCustomerFields(customer);

                    }));
                    suggestionCustomerNameMenu.getItems().add(menuItem);
                }

                if (!suggestionCustomerNameMenu.getItems().isEmpty()) {
                    suggestionCustomerNameMenu.show(txtSearch, Side.BOTTOM, 0, 0);
                }

            });
        } catch (RuntimeException e) {

        }
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
