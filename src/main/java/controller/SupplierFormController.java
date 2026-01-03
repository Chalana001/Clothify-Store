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
import modle.dto.Supplier;
import service.SupplierService;
import service.Impl.SupplierServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    SupplierService supplierService = new SupplierServiceImpl();

    private ObservableList<Supplier> supDetailsArray = FXCollections.observableArrayList();
    private ObservableList<Supplier> searchedSupplierList = FXCollections.observableArrayList();


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
    private TableView<Supplier> tblSupplierTable;

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

        if (!txtSupplierId.getText().matches(".*[a-zA-Z].*" ) || !txtSupplierName.getText().matches(".*[a-zA-Z0-9].*") || !txtSupplierEmail.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }

        try {
            supplierService.addSupplier(new Supplier(
                    txtSupplierId.getText(),
                    txtSupplierName.getText(),
                    Integer.parseInt(txtSupplierContact.getText()),
                    txtSupplierEmail.getText()
            ));
            loadDataToTable();
            clearFields();
            txtSupplierId.setText(supplierService.getNewSupplierId());
        } catch (RuntimeException e) {

        }
    }

    @FXML
    void btnUpdateButtonOnAction(ActionEvent event) {
        if (!txtSupplierId.getText().matches(".*[a-zA-Z].*" ) || !txtSupplierName.getText().matches(".*[a-zA-Z0-9].*") || !txtSupplierEmail.getText().matches(".*[a-zA-Z0-9@.].*")){
            return;
        }

        try {
            supplierService.updateSupplier(new Supplier(
                    txtSupplierId.getText(),
                    txtSupplierName.getText(),
                    Integer.parseInt(txtSupplierContact.getText()),
                    txtSupplierEmail.getText()
            ));
            loadDataToTable();
        } catch (RuntimeException e) {

        }
    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {
        clearFields();
        txtSupplierId.setText(supplierService.getNewSupplierId());

    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        Supplier supplier = tblSupplierTable.getSelectionModel().getSelectedItem();
        setSupplierFields(supplier);
    }

    @FXML
    void txtSupplierIdOnAction(ActionEvent event) {
        setSupplierFields(supplierService.getSupplierById(txtSupplierId.getText()));
    }

    @FXML
    void showContextMenu(ContextMenuEvent event) {
        MenuItem menuItemDelete = new MenuItem("Delete");

        menuItemDelete.setOnAction( e -> {
            supplierService.deleteSupplier(tblSupplierTable.getSelectionModel().getSelectedItem());
            loadDataToTable();
        });

        ContextMenu tblMenu = new ContextMenu(menuItemDelete);
        tblMenu.show(tblSupplierTable,event.getScreenX(),event.getScreenY());
    }

/// /////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueSetToTable();
        loadDataToTable();
        searchButtonListner();
        textFomatters();
    }

    private void textFomatters(){
        txtSupplierName.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z ]*")){
                return change;
            }
            return null;
        }));
        txtSearchSupplier.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z ]*")){
                return change;
            }
            return null;
        }));
        txtSupplierEmail.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[a-zA-Z0-9-_.@ ]*")){
                return change;
            }
            return null;
        }));
        txtSupplierId.setEditable(false);
        txtSupplierContact.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.matches("[0-9]*")){
                return change;
            }
            return null;
        }));
    }

    private void searchButtonListner() {
        txtSearchSupplier.textProperty().addListener((obs,oldText,newText) -> {

            if(newText == null || newText.trim().length()<2){
                loadDataToTable();
                return;
            }
            searchedSupplierList = supplierService.searchSupplierByName(newText);

            tblSupplierTable.setItems(searchedSupplierList);
        });
    }

    public void setSupplierFields(Supplier supplier){
        txtSupplierId.setText(supplier.getSId());
        txtSupplierName.setText(supplier.getSName());
        txtSupplierContact.setText(String.valueOf(supplier.getSPhoneNumber()));
        txtSupplierEmail.setText(supplier.getSEmail());
    }

    private void valueSetToTable() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("SPhoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("SEmail"));
    }

    private void loadDataToTable() {
        supDetailsArray.clear();
        supDetailsArray = supplierService.getAllSuppliers();
        tblSupplierTable.setItems(supDetailsArray);
    }

    private void clearFields() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierContact.clear();
        txtSupplierEmail.clear();
    }
}
