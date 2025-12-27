package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.dto.CartProducts;
import modle.dto.Customer;
import modle.dto.Orders;
import modle.dto.Product;
import service.PlaceOrderService;
import service.PlaceOrderServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrdersFormController implements Initializable {

    PlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    ObservableList<CartProducts> cartProducts = FXCollections.observableArrayList();
    ObservableList<Product> searchProducts = FXCollections.observableArrayList();
    ObservableList<Customer> searchCustomers = FXCollections.observableArrayList();
    private String customerID = "C001";


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
    private TextField txtCustName;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TableView<CartProducts> tblCart;

    @FXML
    private ContextMenu contextPNames;


    @FXML
    private ContextMenu contextCustName;

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        placeOrderService.placeOrder(new Orders(
                genOrderId(),
                customerID,
                LocalDate.now()
        ), cartProducts);
    }

    private String genOrderId() {
        return placeOrderService.genOrderId();
    }


    @FXML
    void txtProductIdOnAction(ActionEvent event) {
        Product product = placeOrderService.getProductById(txtProductId.getText());
        addToCart(product);
        txtProductName.clear();
        txtProductId.clear();
    }

    private void addToCart(Product product){
        CartProducts cartProduct = new CartProducts(
                product.getPId(),
                product.getPName(),
                1,
                product.getPrice(),
                product.getPrice()
        );
        cartProducts.add(cartProduct);
        tblCart.setItems(cartProducts);
        calcNetTotal ();
        summaryQty.setText(String.valueOf(cartProducts.size()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableValueSet();
        searchProductByName();
        searchCustomerByName();
    }

    private void searchCustomerByName() {
        txtCustName.textProperty().addListener((obs,oldText,newText) -> {
            contextCustName.hide();
            contextCustName.getItems().clear();

            if (newText == null || newText.trim().length() < 3) {
                return;
            }

            searchCustomers = placeOrderService.searchCustomerByName(newText);

            for (Customer customer : searchCustomers) {

                Label label = new Label(customer.getId()+ " - " + customer.getName());
                label.setStyle("-fx-font-weight: bold;");

                MenuItem menuItem = new MenuItem(); //item.getItemName()
                menuItem.setGraphic(label);
                menuItem.setOnAction(e -> {
                    customerID = customer.getId();
                    summaryCusName.setText(label.getText());
                    txtCustName.clear();
                });
                contextCustName.getItems().add(menuItem);
            }

            if (!contextCustName.getItems().isEmpty()) {
                contextCustName.show(txtCustName, Side.BOTTOM, 0, 0);
            }
        });
    }

    private void tableValueSet() {
        coltProductId.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("pQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void searchProductByName(){
        txtProductName.textProperty().addListener((obs,oldText,newText) -> {
            contextPNames.hide();
            contextPNames.getItems().clear();

            if (newText == null || newText.trim().length() < 3) {
                return;
            }

            searchProducts = placeOrderService.searchProductByName(newText);

            for (Product product : searchProducts) {

                Label label = new Label(product.getPId()+ " - " + product.getPName() + " - " + " - Rs."+product.getPrice());
                label.setStyle("-fx-font-weight: bold;");

                MenuItem menuItem = new MenuItem(); //item.getItemName()
                menuItem.setGraphic(label);
                menuItem.setOnAction(e -> {
                    addToCart(product);
                    txtProductName.clear();
                    txtProductId.clear();
                });
                contextPNames.getItems().add(menuItem);
            }

            if (!contextPNames.getItems().isEmpty()) {
                contextPNames.show(txtProductName, Side.BOTTOM, 0, 0);
            }
        });
    }

    private void calcNetTotal () {
        Double netTotal = 0.0;
        for (CartProducts cartProducts : cartProducts) {
            netTotal += cartProducts.getTotal();
        }
        summaryNetTotal.setText(String.valueOf(netTotal));
        summarySubTotal.setText(String.valueOf(netTotal));
    }
}

