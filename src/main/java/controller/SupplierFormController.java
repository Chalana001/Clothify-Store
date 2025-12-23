package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SupplierFormController {

    @FXML
    private Button btnAddButton;

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

}
