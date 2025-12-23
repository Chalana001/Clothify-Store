package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomerFormController {

    @FXML
    private Button btnAddButton;

    @FXML
    private Button btnGenId;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<?> tblCustomerTable;

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
    void btnAddButtonOnAction(ActionEvent event) {

    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {

    }

}
