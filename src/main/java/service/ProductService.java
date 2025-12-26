package service;

import javafx.collections.ObservableList;
import modle.dto.Product;

import java.util.List;

public interface ProductService {
    ObservableList<Product> getAllProducts();

    void addProduct(Product product);

    String getNewProductId();

    void updateProduct(Product product);

    void deleteProduct(Product selectedProduct);

    Product getProductById(String text);

    List<String> getAllSupplierIds();

    ObservableList<Product> searchProductByName(String newText);

}
