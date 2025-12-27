package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modle.dto.TblInventory;
import service.InventoryService;
import service.Impl.InventoryServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryFormController implements Initializable {

    InventoryService inventoryService = new InventoryServiceImpl();
    ObservableList<TblInventory> tblInventories = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<TblInventory> tblInventory;

    @FXML
    private TextField txtSearchInv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuestoTbl();
        loadTable();
    }

    private void loadTable() {
        tblInventories = inventoryService.getInventoryDetails();
        tblInventory.setItems(tblInventories);
    }

    private void setValuestoTbl() {
        colId.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("availibleQty"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
