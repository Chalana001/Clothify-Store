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
import modle.dto.Product;
import service.ProductService;
import service.Impl.ProductServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsFormController implements Initializable {
    
    ProductService productService = new ProductServiceImpl();
    
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private ObservableList<Product> searchProductList = FXCollections.observableArrayList();
    private ObservableList<Product> supplierIds = FXCollections.observableArrayList();


    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<String> comboSupplierId;

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
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<Product> tblProductTable;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductCatagory;

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
        if (comboSupplierId.getSelectionModel().getSelectedItem()==null){
            //popup dialog
        }
        productService.addProduct(new Product(
                txtProductId.getText(),
                txtProductName.getText(),
                txtProductCatagory.getText(),
                txtProductSize.getText(),
                Double.valueOf(txtProductPrice.getText()),
                Integer.parseInt(txtProductQty.getText()),
                comboSupplierId.getSelectionModel().getSelectedItem()
        ));
        loadDataToTable();
        clearFields();
        txtProductId.setText(productService.getNewProductId());
        loadSupplierIds();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        productService.updateProduct(new Product(
                txtProductId.getText(),
                txtProductName.getText(),
                txtProductCatagory.getText(),
                txtProductSize.getText(),
                Double.valueOf(txtProductPrice.getText()),
                Integer.parseInt(txtProductQty.getText()),
                comboSupplierId.getSelectionModel().getSelectedItem()
        ));
        loadDataToTable();
    }

    @FXML
    void btnGenIdOnAction(ActionEvent event) {
        clearFields();
        txtProductId.setText(productService.getNewProductId());
        loadSupplierIds();
    }

    @FXML
    void txtProductIdOnAction(ActionEvent event) {
        setProductFields(productService.getProductById(txtProductId.getText()));
    }
    

    @FXML
    void comboSupplierIdOnAction(ActionEvent event) {

    }

    @FXML
    void showContextMenu(ContextMenuEvent event) {
        MenuItem menuItemDelete = new MenuItem("Delete");

        menuItemDelete.setOnAction( e -> {
            productService.deleteProduct(tblProductTable.getSelectionModel().getSelectedItem());
            loadDataToTable();
        });

        ContextMenu tblMenu = new ContextMenu(menuItemDelete);
        tblMenu.show(tblProductTable,event.getScreenX(),event.getScreenY());
    }

    @FXML
    void tblOnMouseClicked(MouseEvent event) {
        supplierIds.clear();
        comboSupplierId.getItems().clear();
        Product product = tblProductTable.getSelectionModel().getSelectedItem();
        txtProductId.setText(product.getPId());
        txtProductName.setText(product.getPName());
        txtProductCatagory.setText(product.getPId());
        txtProductSize.setText(product.getPId());
        txtProductPrice.setText(product.getPId());
        txtProductQty.setText(String.valueOf(product.getAvailibleQty()));
        comboSupplierId.getItems().add(product.getSid());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueSetToTable();
        loadDataToTable();
        searchButtonListner();
        loadSupplierIds();
    }
    private void loadSupplierIds() {
        List<String> productIds = productService.getAllSupplierIds();
        comboSupplierId.getItems().setAll(productIds);

    }


    private void searchButtonListner() {
        txtSearchByName.textProperty().addListener((obs,oldText,newText) -> {
            if(newText == null || newText.trim().length()<2){
                loadDataToTable();
                return;
            }
            searchProductList = productService.searchProductByName(newText);

            tblProductTable.setItems(searchProductList);
        });
    }

    public void setProductFields(Product product){
        supplierIds.clear();
        comboSupplierId.getItems().clear();
        txtProductId.setText(product.getPId());
        txtProductName.setText(product.getPName());
        txtProductCatagory.setText(product.getPId());
        txtProductSize.setText(product.getPId());
        txtProductPrice.setText(product.getPId());
        txtProductQty.setText(String.valueOf(product.getAvailibleQty()));
        comboSupplierId.getItems().add(product.getSid());
    }

    private void valueSetToTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("PId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("PName"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("AvailibleQty"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("Sid"));
    }

    private void loadDataToTable() {
        productObservableList.clear();
        productObservableList = productService.getAllProducts();
        tblProductTable.setItems(productObservableList);
    }

    private void clearFields() {
        txtProductId.clear();
        txtProductName.clear();
        txtProductCatagory.clear();
        txtProductSize.clear();
        txtProductPrice.clear();
        txtProductQty.clear();
        comboSupplierId.getItems().clear();

    }
}

