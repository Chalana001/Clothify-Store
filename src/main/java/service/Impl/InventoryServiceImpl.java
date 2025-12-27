package service.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.dto.Product;
import modle.dto.TblInventory;
import service.InventoryService;
import service.ProductService;

public class InventoryServiceImpl implements InventoryService {

    ProductService productService = new ProductServiceImpl();
    ObservableList<Product> allProducts = FXCollections.observableArrayList();
    ObservableList<TblInventory> tblInventories = FXCollections.observableArrayList();


    @Override
    public ObservableList<TblInventory> getInventoryDetails() {
        allProducts = productService.getAllProducts();
        tblInventories.clear();
        for (Product product: allProducts){
            tblInventories.add(new TblInventory(
                    product.getPName(),
                    product.getAvailibleQty()
            ));
        }
        return tblInventories;
    }
}
