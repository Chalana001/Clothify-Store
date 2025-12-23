package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ProductsFormController {

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtProductSize;

    @FXML
    private TextField txtSearchByName;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void txtProductIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchByNameOnAction(ActionEvent event) {

    }

}

