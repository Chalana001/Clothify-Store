package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modle.dto.Supplier;
import service.SupplierService;
import service.SupplierServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    SupplierService supplierService = new SupplierServiceImpl();


    @FXML
    private Button btnAddButton;

    @FXML
    private Button btnUpdateButton;

    @FXML
    private Button btnGenId;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<?> tblSupplierTable;

    @FXML
    private TextField txtSearchSupplier;

    @FXML
    private TextField txtSupplierContact;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    @FXML
    void btnAddButtonOnAction(ActionEvent event) {
        supplierService.addSupplier(new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                Integer.parseInt(txtSupplierContact.getText()),
                txtSupplierEmail.getText()
        ));
        loadDataToTable();
        clearFields();
        txtSupplierId.setText(customerService.getNewCustomerId());
    }

    private void clearFields() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierContact.clear();
        txtSupplierEmail.clear();
    }

    @FXML
    void btnUpdateButtonOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtSupplierIdOnAction(ActionEvent event) {

    }
/// /////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueSetToTable();
    }

    private void valueSetToTable() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("SPhoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("SEmail"));
    }
}
