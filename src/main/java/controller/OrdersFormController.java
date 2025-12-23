package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class OrdersFormController {

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> coltProductId;

    @FXML
    private Label summaryCusName;

    @FXML
    private Label summaryNetTotal;

    @FXML
    private Label summaryQty;

    @FXML
    private Label summarySubTotal;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtProductIdOnAction(ActionEvent event) {

    }

}

